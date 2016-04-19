/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.parser;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.reexon.lib.gpx.Gpx;
import it.reexon.lib.gpx.Track;
import it.reexon.lib.gpx.TrackPoint;
import it.reexon.lib.gpx.TrackSegment;
import it.reexon.lib.gpx.types.CopyrightType;
import it.reexon.lib.gpx.types.LatitudeType;
import it.reexon.lib.gpx.types.LinkType;
import it.reexon.lib.gpx.types.LongitudeType;
import it.reexon.lib.gpx.types.MetadataType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 * @version GPX Version 1.1
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
            analyzesNode(lista.item(j));
        }
    }

    private void analyzesNode(Node node) throws DOMException, URISyntaxException
    {
        if (node == null)
            return;
        String nodeName = node.getNodeName();
        if (StringUtils.defaultString(nodeName).equalsIgnoreCase("metadata"))
        {
            analyzesMetadataNode(node);
        }
        else if (StringUtils.defaultString(nodeName).equalsIgnoreCase("trk"))
        {
            analyzesTrkNode(node);
        }
    }

    private void analyzesTrkNode(Node node) throws URISyntaxException
    {
        Track trkType = new Track();
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element trkElement = (Element) node;

            // ---------------------------- Link ----------------------------
            NodeList link = trkElement.getElementsByTagName("link");
            LinkType linkType = new LinkType();

            if (link.getLength() == 1)
            {
                Node linkNode = link.item(0);
                if (linkNode instanceof Element)
                {
                    Element linkElement = (Element) linkNode;
                    URI uri = new URI(linkElement.getAttribute("href"));
                    String text = linkElement.getElementsByTagName("text").item(0).getTextContent();

                    linkType.setAnyURI(uri);
                    linkType.setText(text);
                }
            }

            // ---------------------------- Trkseg ----------------------------
            TrackSegment trkSegType = new TrackSegment();
            List<TrackSegment> trkSegTypeList = new LinkedList<>();
            NodeList trkseg = trkElement.getElementsByTagName("trkseg");
            List<TrackPoint> trkptList = new LinkedList<>();
            Node trkNode = trkseg.item(0);
            if (trkNode instanceof Element)
            {
                Element trksegElement = (Element) trkNode;
                NodeList trkptNodeList = trksegElement.getElementsByTagName("trkpt");

                for (int i = 0; i < trkptNodeList.getLength(); i++)
                {
                    Node trkPtNode = trkptNodeList.item(i);
                    if (trkPtNode instanceof Element)
                    {
                        Element trkPtElement = (Element) trkPtNode;

                        TrackPoint wptType;
                        LatitudeType latitude = new LatitudeType(new BigDecimal(trkPtElement.getAttribute("lat")));
                        LongitudeType longitude = new LongitudeType(new BigDecimal(trkPtElement.getAttribute("lon")));
                        wptType = new TrackPoint(latitude, longitude);

                        wptType.setEle(new BigDecimal(trkPtElement.getElementsByTagName("ele").item(0).getTextContent()));
                        wptType.setTime(new DateTime(trkPtElement.getElementsByTagName("time").item(0).getTextContent()).toDate());

                        trkptList.add(wptType);
                    }
                }
            }
            trkSegType.setTrackPoints(trkptList);
            trkType.setTrackSegment(trkSegTypeList);
        }
    }

    private void analyzesMetadataNode(Node node) throws DOMException, URISyntaxException
    {
        MetadataType metadataType = new MetadataType();
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element metadataElement = (Element) node;

            // ---------------------------- Copyright ----------------------------
            NodeList copyright = metadataElement.getElementsByTagName("copyright");
            CopyrightType copyrightType = new CopyrightType();
            if (copyright.getLength() == 1)
            {
                Node copyrightNode = copyright.item(0);
                copyrightType.setAuthor(copyrightNode.getAttributes().getNamedItem("author").getNodeValue());
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
                Node linkNode = link.item(0);
                linkType.setAnyURI(new URI(linkNode.getAttributes().getNamedItem("href").getNodeValue()));
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

            gpx.setMetadata(metadataType);
        }
    }

    public File getGpxFile()
    {
        return gpxFile;
    }

    public Gpx getGpx()
    {
        return gpx;
    }

    public static void main(String args[]) throws Exception
    {
        new GpxParser(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//runtastic.xml"));
    }
}
