package it.reexon.lib.gpx;

import java.util.ArrayList;

/**
 * 
 * @author marco.velluto
 * 
 * A Track Segment holds a list of Track Points which are logically connected in order. 
 * To represent a single GPS track where GPS reception was lost, or the GPS receiver was turned off, 
 * start a new Track Segment for each continuous span of track data.
 */
public class TrackSegment
{
    /**Optional Information   */
    private ArrayList<TrackPoint> trackPoints; //List of Track Points

    
    public TrackSegment()
    {
        super();
    }


    /**
     * @param trackPoints   List of Track Points
     */
    public TrackSegment(ArrayList<TrackPoint> trackPoints)
    {
        super();
        this.trackPoints = trackPoints;
    }


    /**
     * @return the trackPoints   List of Track Points
     */
    public ArrayList<TrackPoint> getTrackPoints()
    {
        return trackPoints;
    }


    /**
     * @param trackPoints   List of Track Points
     */
    public void setTrackPoints(ArrayList<TrackPoint> trackPoints)
    {
        this.trackPoints = trackPoints;
    }
}
