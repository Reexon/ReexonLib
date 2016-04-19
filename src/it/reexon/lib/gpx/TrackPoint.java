package it.reexon.lib.gpx;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

import it.reexon.lib.gpx.abstracts.Point;
import it.reexon.lib.gpx.types.LatitudeType;
import it.reexon.lib.gpx.types.LongitudeType;


/**
 * 
 * @author marco.velluto
 *
 */
public class TrackPoint extends Point
{

    /**Optional Description Information   */
    private String name; //GPS waypoint name of the trackpoint
    private String cmt; //GPS comment of the trackpoint
    private String desc; // Descriptive description of the trackpoint
    private String src; //Source of the trackpoint data
    private URL url; //URL associated with the trackpoint
    private String urlname; //Text to display on the <url> hyperlink
    private String sym; //trackpoint symbol
    private String type; //Type (category) of trackpoint

    /**
     * @param lat   Latitude of the waypoint.
     * @param lon   Longitude of the waypoint.
     */
    public TrackPoint(LatitudeType lat, LongitudeType lon)
    {
        super(lat, lon);
    }

    /**
     * 
     * @param lat   Latitude of the waypoint.
     * @param lon   Longitude of the waypoint.
     * @param ele   Elevation of the waypoint.
     * @param time   Creation date/time of the waypoint
     * @param magvar   Magnetic variation of the waypoint
     * @param geoidheight   Geoid height of the waypoint
     * @param fix   Type of GPS fix
     * @param sat   Number of satellites
     * @param hdop   Horizontal Diluition Of Precision
     * @param vdop   Vertical Diluition Of Precision
     * @param pdop   Position Diluition Of Precision
     * @param ageofdgpsdata   Time since last DGPS fix
     * @param dgpsid   DGPS station ID
     */
    public TrackPoint(LatitudeType lat, LongitudeType lon, BigDecimal ele, Date time, String magvar, Long geoidheight, String fix, int sat, Long hdop,
                      Long vdop, Long pdop, Long ageofdgpsdata, Long dgpsid)
    {
        super(lat, lon, ele, time, magvar, geoidheight, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid);
    }

    /**
     * @param lat   Latitude of the waypoint.
     * @param lon   Longitude of the waypoint.
     * @param name   GPS waypoint name of the trackpoint
     * @param cmt   GPS comment of the trackpoint
     * @param desc   Descriptive description of the trackpoint
     * @param src   Source of the trackpoint data
     * @param url   URL associated with the trackpoint
     * @param urlname   Text to display on the <url> hyperlink
     * @param sym   trackpoint symbol
     * @param type   Type (category) of trackpoint
     */
    public TrackPoint(LatitudeType lat, LongitudeType lon, String name, String cmt, String desc, String src, URL url, String urlname, String sym,
                      String type)
    {
        super(lat, lon);
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.url = url;
        this.urlname = urlname;
        this.sym = sym;
        this.type = type;
    }

    /**
     * @return the name   GPS waypoint name of the trackpoint
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name   GPS waypoint name of the trackpoint
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the cmt   Source of the trackpoint data
     */
    public String getCmt()
    {
        return cmt;
    }

    /**
     * @param cmt   Source of the trackpoint data
     */
    public void setCmt(String cmt)
    {
        this.cmt = cmt;
    }

    /**
     * @return the desc   Descriptive description of the trackpoint
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc   Descriptive description of the trackpoint
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the src   Source of the trackpoint data
     */
    public String getSrc()
    {
        return src;
    }

    /**
     * @param src   Source of the trackpoint data
     */
    public void setSrc(String src)
    {
        this.src = src;
    }

    /**
     * @return the url   URL associated with the trackpoint
     */
    public URL getUrl()
    {
        return url;
    }

    /**
     * @param url   URL associated with the trackpoint
     */
    public void setUrl(URL url)
    {
        this.url = url;
    }

    /**
     * @return the urlname   Text to display on the <url> hyperlink
     */
    public String getUrlname()
    {
        return urlname;
    }

    /**
     * @param urlname   Text to display on the <url> hyperlink
     */
    public void setUrlname(String urlname)
    {
        this.urlname = urlname;
    }

    /**
     * @return the sym   Trackpoint symbol
     */
    public String getSym()
    {
        return sym;
    }

    /**
     * @param sym   Trackpoint symbol
     */
    public void setSym(String sym)
    {
        this.sym = sym;
    }

    /**
     * @return the type   Type (category) of trackpoint
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type   Type (category) of trackpoint
     */
    public void setType(String type)
    {
        this.type = type;
    }

}
