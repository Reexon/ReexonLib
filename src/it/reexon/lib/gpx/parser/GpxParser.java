/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.reexon.lib.gpx.Gpx;
import it.reexon.lib.gpx.runtastic.types.CopyrightType;
import it.reexon.lib.gpx.runtastic.types.MetadataType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class GpxParser
{
    private final File gpxFile;
    private Gpx gpx;

    public GpxParser(File gpxFile) throws ParserConfigurationException, SAXException, IOException
    {
        this.gpxFile = gpxFile;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(gpxFile);
        doc.getDocumentElement().normalize();

        System.out.println("Xml Version :" + doc.getXmlVersion());
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        System.out.println("----------------------------");

        String version = doc.getDocumentElement().getAttributeNode("version").getValue();
        String creator = doc.getDocumentElement().getAttributeNode("creator").getValue();
        System.out.println("Version : " + version);
        System.out.println("Creator : " + creator);

        this.gpx = new Gpx(version, creator);

        System.out.println("----------------------------");

        NodeList nList = doc.getElementsByTagName("gpx");
        System.out.println("----------------------------");
        for (int temp = 0; temp < nList.getLength(); temp++)
        {

            Node nNode = nList.item(temp);

            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element metadataNode = (Element) nNode;

                NodeList copyright = metadataNode.getElementsByTagName("copyright");
                if (copyright.getLength() == 1)
                {
                    CopyrightType copyrightType = new CopyrightType();
                    Node e = copyright.item(0);
                    copyrightType.setAuthor(e.getAttributes().getNamedItem("author").getNodeValue());
                    NodeList children = e.getChildNodes();
                    Node c = children.item(0);
                }
                //
                //                System.out.println("First Name : " + eElement.getElementsByTagName("metadata").item(0).getTextContent());
                //                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                //                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                //                System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
            }
        }
    }

    private MetadataType metadata(NodeList nList)
    {

        System.out.println("----------- METADATA ----------");
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE)
            {

                Element eElement = (Element) nNode;

                System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

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

    public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException
    {
        //        GpxParser g = new GpxParser(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//test.xml"));
        GpxParser r = new GpxParser(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//runtastic.xml"));
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
