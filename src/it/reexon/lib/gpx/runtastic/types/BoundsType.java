/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class BoundsType
{
    private LatitudeType minlat; //[1]
    private LatitudeType minlon; //[1]
    private LatitudeType maxlat; //[1]
    private LatitudeType maxlon; //[1]

    public LatitudeType getMinlat()
    {
        return minlat;
    }

    public void setMinlat(LatitudeType minlat)
    {
        this.minlat = minlat;
    }

    public LatitudeType getMinlon()
    {
        return minlon;
    }

    public void setMinlon(LatitudeType minlon)
    {
        this.minlon = minlon;
    }

    public LatitudeType getMaxlat()
    {
        return maxlat;
    }

    public void setMaxlat(LatitudeType maxlat)
    {
        this.maxlat = maxlat;
    }

    public LatitudeType getMaxlon()
    {
        return maxlon;
    }

    public void setMaxlon(LatitudeType maxlon)
    {
        this.maxlon = maxlon;
    }

}
