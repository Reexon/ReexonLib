/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class LatitudeType
{
    private Float latitude;

    public LatitudeType(Float latitude)
    {
        super();
        setLatitude(latitude);
    }

    public Float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Float latitude)
    {
        if (latitude > 90.0 || latitude < -90.0)
            throw new IllegalArgumentException("the latitude must be between -90.0 and 90.0");
        this.latitude = latitude;
    }
}
