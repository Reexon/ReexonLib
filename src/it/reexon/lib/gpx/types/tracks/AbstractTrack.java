package it.reexon.lib.gpx.types.tracks;

import it.reexon.lib.types.NonNegativeInteger;


/**
 * 
 * @author Marco Velluto
 * @since Java 1.8
 *
 */
public abstract class AbstractTrack
{
    /** Optional Information */
    protected String name; //GPS route name
    protected String cmt; //GPS route comment
    protected String desc; //Description of the route
    protected String src; //Source of the route data
    protected NonNegativeInteger number; //GPS route number

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
     * @return the number
     */
    public NonNegativeInteger getNumber()
    {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(NonNegativeInteger number)
    {
        this.number = number;
    }
}
