/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.types;

import java.math.BigDecimal;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class Coordinates implements Comparable<Coordinates>
{
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Coordinates()
    {
        super();
    }

    public Coordinates(BigDecimal latitude, BigDecimal longitude)
    {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude()
    {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude()
    {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public int compareTo(Coordinates o)
    {
        if (this.longitude.longValue() == o.getLongitude().longValue())
            return (int) (this.latitude.longValue() - o.getLatitude().longValue());
        else
            return (int) (o.getLatitude().longValue() - this.longitude.longValue());
    }
}
