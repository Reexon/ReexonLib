/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.math.BigDecimal;


/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class LatitudeType
{
    private BigDecimal latitude;

    public LatitudeType(BigDecimal latitude)
    {
        super();
        setLatitude(latitude);
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude)
    {
        if (latitude == null)
            return;
        if (latitude.floatValue() > 90.0 || latitude.floatValue() < -90.0)
            throw new IllegalArgumentException("the latitude must be between -90.0 and 90.0");
        this.latitude = latitude;
    }
}
