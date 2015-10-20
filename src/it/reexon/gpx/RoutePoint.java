package it.reexon.gpx;

import java.util.Date;

import it.reexon.gpx.abstracts.Point;

/**
 * @author marco.velluto
 */
public class RoutePoint extends Point
{

    /**
     * @param latitude is required
     * @param Longitude is required
     */
    public RoutePoint(Long lat, Long lon)
    {
        super(lat, lon);
    }

    /**
     * 
     * @param lat: Latitude of the waypoint.
     * @param lon: Longitude of the waypoint.
     * @param ele: Elevation of the waypoint.
     * @param time: Creation date/time of the waypoint
     * @param magvar: Magnetic variation of the waypoint
     * @param geoidheight: Geoid height of the waypoint
     * @param fix: Type of GPS fix
     * @param sat: Number of satellites
     * @param hdop: Horizontal Diluition Of Precision
     * @param vdop: Vertical Diluition Of Precision
     * @param pdop: Position Diluition Of Precision
     * @param ageofdgpsdata: Time since last DGPS fix
     * @param dgpsid: DGPS station ID
     */
    public RoutePoint(Long lat, Long lon, Long ele, Date time, String magvar, Long geoidheight, String fix, int sat, Long hdop, Long vdop, Long pdop,
                       Long ageofdgpsdata, Long dgpsid)
    {
        super(lat, lon, ele, time, magvar, geoidheight, fix, sat, hdop, vdop, pdop, ageofdgpsdata, dgpsid);
    }

}
