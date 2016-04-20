/**
 * 
 */
package it.reexon.lib.gpx;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import it.reexon.lib.gpx.types.MetadataType;
import it.reexon.lib.gpx.types.points.WayPoint;
import it.reexon.lib.gpx.types.tracks.Route;
import it.reexon.lib.gpx.types.tracks.Track;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class Gpx
{
    /**Required Information   */
    private String version; //GPX schema version used in file
    private String creator; //Program used to create file

    /**Optional file informations */
    private MetadataType metadata; //Metada Information

    /**Optional file informations */
    private List<WayPoint> wayPoints; //List of Waypoints
    private List<Route> routes; //List of Routes
    private List<Track> tracks; //List of Tracks

    /**
     * @param version   GPX schema version used in file. Is mandatory
     * @param creator   Program used to create file. Is mandatory
     *
     * @throws IllegalArgumentException if version or creator are blank or null
     */
    public Gpx(String version, String creator) throws IllegalArgumentException
    {
        if (StringUtils.isBlank(version))
            throw new IllegalArgumentException("Version is required");
        if (StringUtils.isBlank(creator))
            throw new IllegalArgumentException("Creator is required");

        this.version = version;
        this.creator = creator;
    }

    /**
     * 
     * @param version   GPX schema version used in file
     * @param creator   Program used to create file. Is mandatory
     * @param metadata  Metada Information
     * @param wayPoints List of Waypoints
     * @param routes    List of Routes
     * @param tracks    List of Tracks
     * 
     * @throws IllegalArgumentException if version or creator are blank or null
     */
    public Gpx(String version, String creator, MetadataType metadata, List<WayPoint> wayPoints, List<Route> routes, List<Track> tracks)
    {
        if (StringUtils.isBlank(version))
            throw new IllegalArgumentException("Version is required");
        if (StringUtils.isBlank(creator))
            throw new IllegalArgumentException("Creator is required");

        this.version = version;
        this.creator = creator;
        this.metadata = metadata;
        this.wayPoints = wayPoints;
        this.routes = routes;
        this.tracks = tracks;
    }

    /**
     * @return version   GPX schema version used in file
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version   GPX schema version used in file
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * @return creator   Program used to create file
     */
    public String getCreator()
    {
        return creator;
    }

    /**
     * @param creator      Program used to create file
     */
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    /**
     * @return the wayPoints
     */
    public List<WayPoint> getWayPoints()
    {
        return wayPoints;
    }

    /**
     * @param wayPoints the wayPoints to set
     */
    public void setWayPoints(List<WayPoint> wayPoints)
    {
        this.wayPoints = wayPoints;
    }

    /**
     * @return the routes
     */
    public List<Route> getRoutes()
    {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<Route> routes)
    {
        this.routes = routes;
    }

    /**
     * @return the tracks
     */
    public List<Track> getTracks()
    {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }

    /**
     * @return the metadata
     */
    public MetadataType getMetadata()
    {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(MetadataType metadata)
    {
        this.metadata = metadata;
    }
}
