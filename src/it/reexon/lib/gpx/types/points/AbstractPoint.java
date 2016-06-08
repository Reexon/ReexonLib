/**
 * 
 */
package it.reexon.lib.gpx.types.points;

import java.math.BigDecimal;
import java.util.Date;

import it.reexon.lib.gpx.types.LatitudeType;
import it.reexon.lib.gpx.types.LongitudeType;
import it.reexon.lib.types.Coordinates;


/**
 * @author marco.velluto
 *
 */
public abstract class AbstractPoint
{
    /**Required Information: */
    private LatitudeType lat; //Latitude of the waypoint.
    private LongitudeType lon; //Longitude of the waypoint.

    /**Optional Position Information: */
    private BigDecimal ele; //Elevation of the waypoint.
    private Date time; //Creation date/time of the waypoint
    private String magvar; //Magnetic variation of the waypoint
    private Long geoidheight; //Geoid height of the waypoint

    /**Optional Accuracy Information: */
    private String fix; //Type of GPS fix
    private int sat; //Number of satellites
    private Long hdop; //HDOP: è l'acronimo Horizontal Diluition Of Precision e indica quanto sia buona la geometria dei satelliti utilizzati dal ricevitore per fare il fix
    private Long vdop; //VDOP: è l'acronimo Vertical Diluition Of Precision e indica quanto sia buona la geometria dei satelliti utilizzati dal ricevitore per fare il fix
    private Long pdop; //PDOP: è l'acronimo Position Diluition Of Precision e indica quanto sia buona la geometria dei satelliti utilizzati dal ricevitore per fare il fix
    private Long ageofdgpsdata; //Time since last DGPS fix
    private Long dgpsid; //DGPS station ID

    /**
     * @param lat latitude is required
     * @param lon Longitude is required
     */
    public AbstractPoint(LatitudeType lat, LongitudeType lon)
    {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * 
     * @param lat       Latitude of the waypoint.
     * @param lon       Longitude of the waypoint.
     * @param ele       Elevation of the waypoint.
     * @param time      Creation date/time of the waypoint
     * @param magvar    Magnetic variation of the waypoint
     * @param geoidheight       Geoid height of the waypoint
     * @param fix       Type of GPS fix
     * @param sat       Number of satellites
     * @param hdop      Horizontal Diluition Of Precision
     * @param vdop      Vertical Diluition Of Precision
     * @param pdop      Position Diluition Of Precision
     * @param ageofdgpsdata     Time since last DGPS fix
     * @param dgpsid    DGPS station ID
     */
    public AbstractPoint(LatitudeType lat, LongitudeType lon, BigDecimal ele, Date time, String magvar, Long geoidheight, String fix, int sat,
                         Long hdop, Long vdop, Long pdop, Long ageofdgpsdata, Long dgpsid)
    {
        super();
        this.lat = lat;
        this.lon = lon;
        this.ele = ele;
        this.time = time;
        this.magvar = magvar;
        this.geoidheight = geoidheight;
        this.fix = fix;
        this.sat = sat;
        this.hdop = hdop;
        this.vdop = vdop;
        this.pdop = pdop;
        this.ageofdgpsdata = ageofdgpsdata;
        this.dgpsid = dgpsid;
    }

    /**
     * @return the lat Longitude of the waypoint.
     */
    public LatitudeType getLat()
    {
        return lat;
    }

    /**
     * @param lat Longitude of the waypoint.
     */
    public void setLat(LatitudeType lat)
    {
        this.lat = lat;
    }

    /**
     * @return lon Longitude of the waypoint.
     */
    public LongitudeType getLon()
    {
        return lon;
    }

    /**
     * @param lon Longitude of the waypoint.
     */
    public void setLon(LongitudeType lon)
    {
        this.lon = lon;
    }

    /**
     * @return the ele Elevation of the waypoint.
     */
    public BigDecimal getEle()
    {
        return ele;
    }

    /**
     * @param ele Elevation of the waypoint.
     */
    public void setEle(BigDecimal ele)
    {
        this.ele = ele;
    }

    /**
     * @return the time Creation date/time of the waypoint
     */
    public Date getTime()
    {
        return time;
    }

    /**
     * @param time Creation date/time of the waypoint
     */
    public void setTime(Date time)
    {
        this.time = time;
    }

    /**
     * @return the magvar Magnetic variation of the waypoint
     */
    public String getMagvar()
    {
        return magvar;
    }

    /**
     * @param magvar Magnetic variation of the waypoint
     */
    public void setMagvar(String magvar)
    {
        this.magvar = magvar;
    }

    /**
     * @return the geoidheight Geoid height of the waypoint
     */
    public Long getGeoidheight()
    {
        return geoidheight;
    }

    /**
     * @param geoidheight Geoid height of the waypoint
     */
    public void setGeoidheight(Long geoidheight)
    {
        this.geoidheight = geoidheight;
    }

    /**
     * @return the fix Type of GPS fix
     */
    public String getFix()
    {
        return fix;
    }

    /**
     * @param fix Type of GPS fix
     */
    public void setFix(String fix)
    {
        this.fix = fix;
    }

    /**
     * @return the sat Number of satellites
     */
    public int getSat()
    {
        return sat;
    }

    /**
     * @param sat Number of satellites
     */
    public void setSat(int sat)
    {
        this.sat = sat;
    }

    /**
     * @return the hdop Horizontal Diluition Of Precision
     */
    public Long getHdop()
    {
        return hdop;
    }

    /**
     * @param hdop Horizontal Diluition Of Precision
     */
    public void setHdop(Long hdop)
    {
        this.hdop = hdop;
    }

    /**
     * @return the vdop Vertical Diluition Of Precision
     */
    public Long getVdop()
    {
        return vdop;
    }

    /**
     * @param vdop the vdop Vertical Diluition Of Precision
     */
    public void setVdop(Long vdop)
    {
        this.vdop = vdop;
    }

    /**
     * @return the pdop Position Diluition Of Precision
     */
    public Long getPdop()
    {
        return pdop;
    }

    /**
     * @param pdop the pdop Position Diluition Of Precision
     */
    public void setPdop(Long pdop)
    {
        this.pdop = pdop;
    }

    /**
     * @return the ageofdgpsdata Time since last DGPS fix
     */
    public Long getAgeofdgpsdata()
    {
        return ageofdgpsdata;
    }

    /**
     * @param ageofdgpsdata Time since last DGPS fix
     */
    public void setAgeofdgpsdata(Long ageofdgpsdata)
    {
        this.ageofdgpsdata = ageofdgpsdata;
    }

    /**
     * @return the dgpsid DGPS station ID
     */
    public Long getDgpsid()
    {
        return dgpsid;
    }

    /**
     * @param dgpsid DGPS station ID
     */
    public void setDgpsid(Long dgpsid)
    {
        this.dgpsid = dgpsid;
    }

    public Coordinates getCoordinate()
    {
        return new Coordinates(lat.getLatitude(), lon.getLatitude());
    }

}
