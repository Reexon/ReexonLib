/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.parser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.joda.time.DateTime;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.reexon.lib.gpx.runtastic.Gpx;
import it.reexon.lib.gpx.runtastic.types.CopyrightType;
import it.reexon.lib.gpx.runtastic.types.LinkType;
import it.reexon.lib.gpx.runtastic.types.MetadataType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class GpxParser
{
    private final File gpxFile;
    private Gpx gpx;

    public GpxParser(File gpxFile) throws ParserConfigurationException, SAXException, IOException, DOMException, URISyntaxException
    {
        this.gpxFile = gpxFile;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(gpxFile);
        doc.getDocumentElement().normalize();

        String version = doc.getDocumentElement().getAttributeNode("version").getValue();
        String creator = doc.getDocumentElement().getAttributeNode("creator").getValue();
        this.gpx = new Gpx(version, creator);

        NodeList nList = doc.getElementsByTagName("gpx");
        Node nNode = nList.item(0);

        NodeList lista = nNode.getChildNodes();

        for (int j = 0; j < lista.getLength(); j++)
        {
            Node node = lista.item(j);
            analyzesNode(node);

            System.out.println("index: " + j);
            if (lista.item(j).getNodeType() == Node.ELEMENT_NODE)
            {
                Element e = (Element) lista.item(j);
                NamedNodeMap attributes = e.getAttributes();
                System.out.println("Attributes: " + attributes.toString());
            }
            else if (lista.item(j).getNodeType() == Node.TEXT_NODE)
            {
                System.out.println("Text: " + lista.item(j).getTextContent());
            }

            System.out.println("NodeName: " + lista.item(j).getNodeName());
            System.out.println("NodeType: " + lista.item(j).getNodeType());
            System.out.println("NodeValue: " + lista.item(j).getNodeValue());
            System.out.println("TextContent: " + lista.item(j).getTextContent());
        }

        System.out.println("\nCurrent Element :" + nNode.getNodeName());

        System.out.println("----------------------------");
        MetadataType metadataType = new MetadataType();
        if (nNode.getNodeType() == Node.ELEMENT_NODE)
        {
            Element metadataElement = (Element) nNode;

            // ---------------------------- Copyright ----------------------------
            NodeList copyright = metadataElement.getElementsByTagName("copyright");
            CopyrightType copyrightType = new CopyrightType();
            if (copyright.getLength() == 1)
            {
                Node e = copyright.item(0);
                copyrightType.setAuthor(e.getAttributes().getNamedItem("author").getNodeValue());
            }
            Year year = Year.of(new Integer(metadataElement.getElementsByTagName("year").item(0).getTextContent()).intValue());
            URI licence = new URI(metadataElement.getElementsByTagName("license").item(0).getTextContent());

            System.out.println("Year: " + year.toString());
            System.out.println("License: " + licence.toString());

            // ---------------------------- Link ----------------------------
            NodeList link = metadataElement.getElementsByTagName("link");
            LinkType linkType = new LinkType();
            if (link.getLength() == 1)
            {
                Node node = link.item(0);
                linkType.setAnyURI(new URI(node.getAttributes().getNamedItem("href").getNodeValue()));
            }
            String text = metadataElement.getElementsByTagName("text").item(0).getTextContent();
            linkType.setText(text);
            System.out.println("Text: " + text);
            Date time = new DateTime(metadataElement.getElementsByTagName("time").item(0).getTextContent()).toDate();
            System.out.println("Time: " + time.toString());

            copyrightType.setYear(year);
            copyrightType.setLicence(licence);
            metadataType.setCopyright(copyrightType);
            metadataType.setTime(time);
        }
    }

    private void analyzesNode(Node node)
    {
        // TODO Auto-generated method stub
        
    }

    private MetadataType printNodes(NodeList nList)
    {

        System.out.println("----------- METADATA ----------");
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {

                Element eElement = (Element) nNode;

                System.out.println("First Name : " + eElement.getElementsByTagName("year").item(0));
            }
        }
        return null;
    }

    private static void printNote(NodeList nodeList)
    {

        for (int count = 0; count < nodeList.getLength(); count++)
        {
            Node tempNode = nodeList.item(count);

            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE)
            {
                // get node name and value
                System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                System.out.println("Node Value =" + tempNode.getTextContent());

                if (tempNode.hasAttributes())
                {
                    // get attributes names and values
                    NamedNodeMap nodeMap = tempNode.getAttributes();

                    for (int i = 0; i < nodeMap.getLength(); i++)
                    {
                        Node node = nodeMap.item(i);
                        System.out.println("attr name : " + node.getNodeName());
                        System.out.println("attr value : " + node.getNodeValue());
                    }
                }

                if (tempNode.hasChildNodes())
                {
                    // loop again if has child nodes
                    printNote(tempNode.getChildNodes());

                }
                System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
            }
        }
    }

    public File getGpxFile()
    {
        return gpxFile;
    }

    public static void main(String args[]) throws Exception
    {
        //        GpxParser g = new GpxParser(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//test.xml"));
        GpxParser r = new GpxParser(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//runtastic.xml"));
    }

    public Gpx getGpx()
    {
        return gpx;
    }
}

/**
 * 
 <?xml version="1.0"?>  
    <company>
        <staff id="1001">
                <firstname>yong</firstname>
                <lastname>mook kim</lastname>
                <nickname>mkyong</nickname>
                <salary>100000</salary>
        </staff>
        <staff id="2001">
                <firstname>low</firstname>
                <lastname>yin fong</lastname>
                <nickname>fong fong</nickname>
                <salary>200000</salary>
        </staff>
</company>
*/
/*
   public GpxParser() throws ParserConfigurationException, SAXException, IOException
    {
        File fXmlFile = new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//test.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("staff");

        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++)
        {

            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {

                Element eElement = (Element) nNode;

                System.out.println("Staff id : " + eElement.getAttribute("id"));
                System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

            }
        }
    }
 * */
