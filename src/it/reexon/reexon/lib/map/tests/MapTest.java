/**
 * 
 */
package it.reexon.reexon.lib.map.tests;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author marco.velluto
 *
 */
public class MapTest
{

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void treeMapIntegerTest()
    {
        System.out.println("treeMapIntegerTest");

        SortedMap<Integer, String> map = new TreeMap<Integer, String>();

        // Add Items to the TreeMap
        map.put(new Integer(3), "Three");
        map.put(new Integer(1), "One");
        map.put(new Integer(2), "Two");

        // Iterate over them
        Iterator<String> iterator = map.values().iterator();

        for (Map.Entry<Integer, String> entry : map.entrySet())
        {
            final String entryValue = entry.getValue().toString();
            final String mapValue = iterator.next().toString();

            System.out.println(entry.getKey() + " => " + entry.getValue());
            System.out.println(entryValue + " => " + mapValue);
            assertEquals(entryValue, mapValue);
        }
    }

    @Test
    public final void mapToTreeMapIntegerTest()
    {
        System.out.println("mapToTreeMapIntegerTest");

        Map<Integer, String> map = new HashMap<>();
        map.put(new Integer(3), "Three");
        map.put(new Integer(1), "One");
        map.put(new Integer(2), "Two");

        map = new TreeMap<Integer, String>(map);
        Iterator<String> iterator = map.values().iterator();

        for (Entry<Integer, String> entry : map.entrySet())
        {
            final String entryValue = entry.getValue().toString();
            final String mapValue = iterator.next().toString();

            System.out.println(entry.getKey() + " => " + entry.getValue());
            System.out.println(entryValue + " => " + mapValue);
            assertEquals(entryValue, mapValue);
        }
    }

    @Test
    public final void mapToTreeMapStringTest()
    {
        System.out.println("mapToTreeMapIntegerTest");

        Map<String, String> map = new HashMap<>();
        map.put("CODE 3", "Three");
        map.put("CODE 1", "One");
        map.put("CODE 2", "Two");

        map = new TreeMap<String, String>(map);
        Iterator<String> iterator = map.values().iterator();
        for (Entry<String, String> entry : map.entrySet())
        {
            final String entryValue = entry.getValue().toString();
            final String mapValue = iterator.next().toString();

            System.out.println(entry.getKey() + " => " + entry.getValue());
            System.out.println(entryValue + " => " + mapValue);
            assertEquals(entryValue, mapValue);
        }
    }
}
