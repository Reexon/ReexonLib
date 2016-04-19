package it.reexon.lib.gpx;

import java.util.List;

import it.reexon.lib.gpx.types.LinkType;
import it.reexon.lib.types.NonNegativeInteger;


/**
 * 
 * @author Marco Velluto
 * @since Java 1.8
 *
 */
public class Track
{
    /** Optional Information */
    private String name; //GPS track name
    private String cmt; //GPS track comment
    private String desc; //Description of the track
    private String src; //Source of the track data
    private LinkType url; //URL associated with the track
    private String urlName; //Text to display on the <url> hyperlink
    private NonNegativeInteger number; //GPS track number
    private String type;

    private List<TrackSegment> trackSegment; // List of Track Segments

    public Track()
    {}

    /**
     * @param name      GPS track name
     * @param cmt       GPS track comment
     * @param desc      Description of the track
     * @param src       Source of the track data
     * @param url       Source of the track data
     * @param urlName   Text to display on the <url> hyperlink
     * @param number    GPS track number
     * @param trackSegment      List of Track Segments
     */
    public Track(String name, String cmt, String desc, String src, LinkType url, String urlName, NonNegativeInteger number, String type,
                 List<TrackSegment> trackSegment)
    {
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.url = url;
        this.urlName = urlName;
        this.number = number;
        this.type = type;
        this.trackSegment = trackSegment;
    }

    /**
     * @return GPS track name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param GPS track name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return GPS track comment
     */
    public String getCmt()
    {
        return cmt;
    }

    /**
     * @param GPS track comment
     */
    public void setCmt(String cmt)
    {
        this.cmt = cmt;
    }

    /**
     * @return Description of the track
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param Description of the track
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return Source of the track data
     */
    public String getSrc()
    {
        return src;
    }

    /**
     * @param Source of the track data
     */
    public void setSrc(String src)
    {
        this.src = src;
    }

    /**
     * @return Source of the track data
     */
    public LinkType getUrl()
    {
        return url;
    }

    /**
     * @param Source of the track data
     */
    public void setUrl(LinkType url)
    {
        this.url = url;
    }

    /**
     * @return Text to display on the <url> hyperlink
     */
    public String getUrlName()
    {
        return urlName;
    }

    /**
     * @param Text to display on the <url> hyperlink
     */
    public void setUrlName(String urlName)
    {
        this.urlName = urlName;
    }

    /**
     * @return GPS track number
     */
    public NonNegativeInteger getNumber()
    {
        return number;
    }

    /**
     * @param GPS track number
     */
    public void setNumber(NonNegativeInteger number)
    {
        this.number = number;
    }

    /**
     * @return List of Track Segments
     */
    public List<TrackSegment> getTrackSegment()
    {
        return trackSegment;
    }

    /**
     * @param List of Track Segments
     */
    public void setTrackSegment(List<TrackSegment> trackSegment)
    {
        this.trackSegment = trackSegment;
    }

}
