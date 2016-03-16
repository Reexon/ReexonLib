/**
 * 
 */
package it.reexon.reexon.lib.kml;

import java.util.Iterator;

import org.apache.commons.lang3.RandomUtils;


/**
 * @author marco.velluto
 *
 */
public class Kml
{
    public final static int ALPHA = 25;
    public final static int RANDOM_MAP_COLOURS = 35;
    public final static int TRESHOLD_MAP_COLOURS = 3;
    public final static String MAPPROGRESSCOLOUR_STYLE_PREFIX = "mapProgress_";

    /**
     * @param args
     */
    public static void main(String[] args)
    {

        String coloreArea = "" + ALPHA + "5410E6";
        int numerRandom = RandomUtils.nextInt(1, 35);
        String color = getRandomColours().next();

        coloreArea = getRandomColours().next();
    }

    public static final Iterator<String> getRandomColours()
    {
        return new Iterator<String>()
        {
            int i = 0;

            @Override
            public void remove()
            {}

            @Override
            public String next()
            {
                return MAPPROGRESSCOLOUR_STYLE_PREFIX + ((i++) % Kml.RANDOM_MAP_COLOURS);
            }

            @Override
            public boolean hasNext()
            {
                return true;
            }
        };
    }
}
