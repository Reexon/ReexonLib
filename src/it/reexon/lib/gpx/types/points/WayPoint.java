/**
 * 
 */
package it.reexon.lib.gpx.types.points;

import java.math.BigDecimal;
import java.util.Date;

import it.reexon.lib.gpx.types.LatitudeType;
import it.reexon.lib.gpx.types.LongitudeType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class WayPoint extends AbstractPoint
{
    /**
     * @param lat latitude is required
     * @param lon Longitude is required
     */
    public WayPoint(LatitudeType lat, LongitudeType lon)
    {
        super(lat, lon);
    }

    /**
     * @param lat   Latitude of the waypoint.
     * @param lon   Longitude of the waypoint.
     * @param ele   Elevation of the waypoint.
     * @param time   Creation date/time of the waypoint
     * @param magvar   Magnetic variation of the waypoint
     * @param geoidheight   Geoid height of the waypoint
     * @param fix   Type of GPS fix
     * @param sat   Number of satellites
     * @param hdop   Horizontal Diluition Of Precision
     * @param vdop   Vertical Diluition Of Precision
     * @param pdop   Position Diluition Of Precision
     * @param ageofdgpsdata   Time since last DGPS fix
     * @param dgpsid   DGPS station ID
     */
    public WayPoint(LatitudeType lat, LongitudeType lon, BigDecimal ele, Date time, String magvar, Long geoidheight, String fix, int sat, Long hdop,
                    Long vdop, Long pdop, Long ageofdgpsdata, Long dgpsid)
    {
        super(lat, lon, ele, time, magvar, geoidheight, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid);
    }

}
