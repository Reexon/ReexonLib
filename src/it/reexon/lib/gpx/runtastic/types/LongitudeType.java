/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class LongitudeType
{
    private Float latitude;

    public LongitudeType(Float latitude)
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
            throw new IllegalArgumentException("The longitude must be between -90.0 and 90.0");
        this.latitude = latitude;
    }
}
