/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import com.ibm.icu.math.BigDecimal;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DegreesType
{
    private BigDecimal degrees;

    public BigDecimal getDegrees()
    {
        return degrees;
    }

    public void setDegrees(BigDecimal degrees)
    {
        if (degrees != null)
            if (degrees.doubleValue() < 0.0 || degrees.doubleValue() > 360.0)
                throw new IllegalArgumentException("Degrees must be between 0.0 and 360.0");
        this.degrees = degrees;
    }

}
