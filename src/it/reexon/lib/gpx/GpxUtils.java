/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import com.aspose.imaging.internal.Exceptions.NotSupportedException;

import it.reexon.lib.gpx.parser.GpxParser;
import it.reexon.lib.gpx.types.points.TrackPoint;
import it.reexon.lib.gpx.types.tracks.Track;
import it.reexon.lib.gpx.types.tracks.TrackSegment;
import it.reexon.lib.list.ListUtils;
import it.reexon.lib.types.Coordinates;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class GpxUtils
{
    private static final Logger logger = LogManager.getLogger(GpxUtils.class);

    public static void main(String args[])
    {
        logger.info("Start");
        Coordinates c = getCoordinate(new File("C://Users//Marco.Velluto//git//ReexonLib//src//it//reexon//lib//gpx//parser//runtastic.xml"),
                                      new DateTime("2016-01-31T10:09:11.000Z").toDate());
        logger.info("Coordinates: x:{} y:{}", c.getLatitude(), c.getLongitude());
        logger.info("End");
    }

    public static Coordinates getCoordinate(File gpxFile, Date date) throws NullPointerException
    {
        if (date == null)
            throw new NullPointerException("DateRange is null");

        List<TrackPoint> trackPoints = getTrackPoint(gpxFile);
        Map<Date, Coordinates> coordinates = trackPoints.stream().collect(Collectors.toMap(TrackPoint::getTime, TrackPoint::getCoordinate));
        Set<Long> times = trackPoints.stream().map(p -> p.getTime().getTime()).collect(Collectors.toSet());
        List<Long> sortedTimes = ListUtils.orderByElementLong(times, date.getTime());
        if (sortedTimes == null)
            throw new NotSupportedException(); //TODO
        Date dateSelected = new Date(sortedTimes.get(0));
        Coordinates coordinateSelected = coordinates.get(dateSelected);

        return coordinateSelected;
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
            logger.error(e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
