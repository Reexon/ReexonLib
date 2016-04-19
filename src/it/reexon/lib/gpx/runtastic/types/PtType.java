/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.math.BigDecimal;
import java.util.Date;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class PtType
{
    private LatitudeType lat; //[1]
    private LongitudeType lon; //[1]
    private BigDecimal ele; //[0..1]
    private Date time; //[0..1]

    public LatitudeType getLat()
    {
        return lat;
    }

    public void setLat(LatitudeType lat)
    {
        this.lat = lat;
    }

    public LongitudeType getLon()
    {
        return lon;
    }

    public void setLon(LongitudeType lon)
    {
        this.lon = lon;
    }

    public BigDecimal getEle()
    {
        return ele;
    }

    public void setEle(BigDecimal ele)
    {
        this.ele = ele;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }
}
