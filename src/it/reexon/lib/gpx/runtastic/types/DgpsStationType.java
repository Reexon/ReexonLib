/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DgpsStationType
{
    private Integer dgpsStation;

    public Integer getDgpsStation()
    {
        return dgpsStation;
    }

    public void setDgpsStation(Integer dgpsStation)
    {
        if (dgpsStation != null)
            if (dgpsStation.compareTo(0) < 0 || dgpsStation.compareTo(1023) > 0)
                throw new IllegalArgumentException("DgpsStation must be between 0 and 1023");

        this.dgpsStation = dgpsStation;
    }

}
