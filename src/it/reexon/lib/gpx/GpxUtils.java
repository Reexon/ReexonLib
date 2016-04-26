/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import it.reexon.lib.date.DateRange;
import it.reexon.lib.gpx.parser.GpxParser;
import it.reexon.lib.gpx.types.points.TrackPoint;
import it.reexon.lib.gpx.types.tracks.Track;
import it.reexon.lib.gpx.types.tracks.TrackSegment;
import it.reexon.lib.types.Coordinates;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class GpxUtils
{
    public static void main(String args[])
    {
        getCoordinate(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//runtastic.xml"),
                      new DateTime("2016-01-31T10:09:06.000Z").toDate());
    }

    public static Coordinates getCoordate(File gpxFile, DateRange dateRange)
    {
        List<TrackPoint> trackPoints = getTrackPoint(gpxFile);

        Map<Date, Coordinates> map = trackPoints.stream()
                                                .filter(p -> p.getTime().equals(dateRange.getDateFrom()) || p.getTime().equals(dateRange.getDateTo())
                                                        || (p.getTime().before(dateRange.getDateTo()) && p.getTime().after(dateRange.getDateFrom())))
                                                .collect(Collectors.toMap(TrackPoint::getTime, TrackPoint::getCoordinate));

        return null;
    }

    public static Coordinates getCoordinate(File gpxFile, Date date) throws NullPointerException
    {
        if (date == null)
            throw new NullPointerException("DateRange is null");

        List<TrackPoint> trackPoints = getTrackPoint(gpxFile);
        Optional<Coordinates> coordinates = trackPoints.stream()
                                                       .filter(p -> p.getTime().equals(date) || (p.getTime().before(date) && p.getTime().after(date)))
                                                       .map(p -> p.getCoordinate()).distinct().findAny();
        if (coordinates.isPresent())
            return coordinates.get();
        return null;
    }

    public static final List<TrackPoint> getTrackPoint(File gpxFile)
    {
        List<TrackPoint> trackPoints = new LinkedList<>();
        List<Track> tracks = getGpx(gpxFile).getTracks();
        if (tracks.isEmpty())
            return null;

        for (Track track : tracks)
        {
            List<TrackSegment> trackSegments = track.getTrackSegment();
            if (trackSegments.isEmpty())
                continue;

            for (TrackSegment trackSegment : trackSegments)
            {
                List<TrackPoint> trackPointsTemp = trackSegment.getTrackPoints();
                if (trackPointsTemp.isEmpty())
                    continue;
                trackPoints.addAll(trackPointsTemp);
            }
        }

        return trackPoints;
    }

    public final static Gpx getGpx(File gpxFile)
    {
        try
        {
            GpxParser gpxParser = new GpxParser(gpxFile);
            return gpxParser.getGpx();
        }
        catch (Exception e)
        {
            //TODO
            throw new RuntimeException();
        }
    }
}
