/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;

import com.ibm.icu.math.BigDecimal;

import it.reexon.lib.types.NonNegativeInteger;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class WptType
{
    private LatitudeType lat; //[1]
    private LongitudeType lon; //[1]

    private BigDecimal ele; //[0..1]
    private BigDecimal time; //[0..1]
    private DegreesType magvar; //[0..1]
    private BigDecimal geoidheight; //[0..1]
    private String name; //[0..1]
    private String cmt; //[0..1]
    private String desc; //[0..1]
    private String src; //[0..1]
    private List<LinkType> link; //[0..*]
    private String sym; //[0..1]
    private String type; //[0..1]
    private FixType fix; //[0..1]    
    private NonNegativeInteger sat; //[0..1]
    private BigDecimal hdop; //[0..1]
    private BigDecimal vdop; //[0..1]
    private BigDecimal pdop; //[0..1]
    private BigDecimal ageofdgpsdata; //[0..1]
    private DgpsStationType dgpsid; //[0..1]
    private ExtensionType extensions; //[0..1]

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

    public BigDecimal getTime()
    {
        return time;
    }

    public void setTime(BigDecimal time)
    {
        this.time = time;
    }

    public DegreesType getMagvar()
    {
        return magvar;
    }

    public void setMagvar(DegreesType magvar)
    {
        this.magvar = magvar;
    }

    public BigDecimal getGeoidheight()
    {
        return geoidheight;
    }

    public void setGeoidheight(BigDecimal geoidheight)
    {
        this.geoidheight = geoidheight;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCmt()
    {
        return cmt;
    }

    public void setCmt(String cmt)
    {
        this.cmt = cmt;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public List<LinkType> getLink()
    {
        return link;
    }

    public void setLink(List<LinkType> link)
    {
        this.link = link;
    }

    public String getSym()
    {
        return sym;
    }

    public void setSym(String sym)
    {
        this.sym = sym;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public FixType getFix()
    {
        return fix;
    }

    public void setFix(FixType fix)
    {
        this.fix = fix;
    }

    public NonNegativeInteger getSat()
    {
        return sat;
    }

    public void setSat(NonNegativeInteger sat)
    {
        this.sat = sat;
    }

    public BigDecimal getHdop()
    {
        return hdop;
    }

    public void setHdop(BigDecimal hdop)
    {
        this.hdop = hdop;
    }

    public BigDecimal getVdop()
    {
        return vdop;
    }

    public void setVdop(BigDecimal vdop)
    {
        this.vdop = vdop;
    }

    public BigDecimal getPdop()
    {
        return pdop;
    }

    public void setPdop(BigDecimal pdop)
    {
        this.pdop = pdop;
    }

    public BigDecimal getAgeofdgpsdata()
    {
        return ageofdgpsdata;
    }

    public void setAgeofdgpsdata(BigDecimal ageofdgpsdata)
    {
        this.ageofdgpsdata = ageofdgpsdata;
    }

    public DgpsStationType getDgpsid()
    {
        return dgpsid;
    }

    public void setDgpsid(DgpsStationType dgpsid)
    {
        this.dgpsid = dgpsid;
    }

    public ExtensionType getExtensions()
    {
        return extensions;
    }

    public void setExtensions(ExtensionType extensions)
    {
        this.extensions = extensions;
    }
}
