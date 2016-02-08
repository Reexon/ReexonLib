package it.reexon.reexonlib.gpx;

/**
 * 
 * @author marco.velluto
 *
 */
public class Track
{
    /** Optional Information */
    private String name; //GPS track name
    private String cmt; //GPS track comment
    private String desc; //Description of the track
    private String src; //Source of the track data
    private String url; //URL associated with the track
    private String urlName; //Text to display on the <url> hyperlink
    private Long number; //GPS track number

    private TrackSegment trackSegment; // List of Track Segments

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
    public Track(String name, String cmt, String desc, String src, String url, String urlName, Long number, TrackSegment trackSegment)
    {
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.url = url;
        this.urlName = urlName;
        this.number = number;
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
    public String getUrl()
    {
        return url;
    }

    /**
     * @param Source of the track data
     */
    public void setUrl(String url)
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
    public Long getNumber()
    {
        return number;
    }

    /**
     * @param GPS track number
     */
    public void setNumber(Long number)
    {
        this.number = number;
    }

    /**
     * @return List of Track Segments
     */
    public TrackSegment getTrackSegment()
    {
        return trackSegment;
    }

    /**
     * @param List of Track Segments
     */
    public void setTrackSegment(TrackSegment trackSegment)
    {
        this.trackSegment = trackSegment;
    }

}
