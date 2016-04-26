/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.list.tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ListUtilsTest
{
    static List<Integer> listInt3 = new ArrayList<>(3);
    static List<Integer> listInt6 = new ArrayList<>(6);
    static List<Integer> listInt7 = new ArrayList<>(7);

    static List<Double> listDobule3 = new ArrayList<>(3);
    static List<Double> listDobule6 = new ArrayList<>(6);
    static List<Double> listDobule7 = new ArrayList<>(7);

    static List<Long> listLong3 = new ArrayList<>(3);
    static List<Long> listLong6 = new ArrayList<>(6);
    static List<Long> listLong7 = new ArrayList<>(7);

    @Before
    public void setUp() throws Exception
    {
        listInt3.add(1);
        listInt3.add(2);
        listInt3.add(3);

        listInt6.add(1);
        listInt6.add(2);
        listInt6.add(3);
        listInt6.add(4);
        listInt6.add(5);
        listInt6.add(6);

        listInt7.add(1);
        listInt7.add(2);
        listInt7.add(3);
        listInt7.add(4);
        listInt7.add(5);
        listInt7.add(6);
        listInt7.add(7);

        listDobule3.add(1.5d);
        listDobule3.add(2.5d);
        listDobule3.add(3.5d);

        listDobule6.add(1.5d);
        listDobule6.add(2.5d);
        listDobule6.add(3.5d);
        listDobule6.add(4.5d);
        listDobule6.add(5.5d);
        listDobule6.add(6.5d);

        listDobule6.add(1.5d);
        listDobule6.add(2.5d);
        listDobule6.add(3.5d);
        listDobule6.add(4.5d);
        listDobule6.add(5.5d);
        listDobule6.add(6.5d);
        listDobule6.add(7.5d);

        listLong3.add(1L);
        listLong3.add(2L);
        listLong3.add(3L);

        listLong6.add(1L);
        listLong6.add(2L);
        listLong6.add(3L);
        listLong6.add(4L);
        listLong6.add(5L);
        listLong6.add(6L);

        listLong7.add(1L);
        listLong7.add(2L);
        listLong7.add(3L);
        listLong7.add(4L);
        listLong7.add(5L);
        listLong7.add(6L);
        listLong7.add(7L);
    }

    @After
    public void tearDown() throws Exception
    {
        listInt3.clear();
        listInt6.clear();
        listInt7.clear();

        listDobule3.clear();
        listDobule6.clear();
        listDobule7.clear();

        listLong3.clear();
        listLong6.clear();
        listLong7.clear();
    }

    /**
     * Test method for {@link it.reexon.lib.list.ListUtils#castList(java.lang.Class, java.util.Collection)}.
     */
    @Test
    public final void testCastList()
    {/*TODO*/}

    /**
     * Test method for {@link it.reexon.lib.list.ListUtils#findAverageElementInt(java.util.List)}.
     */
    @Test
    public final void testFindEverageElementInt()
    {
        try
        {
            Integer everageElement3 = ListUtils.findAverageElementInt(listInt3);
            Assert.assertEquals(2, everageElement3.intValue());

            Integer everageElement6 = ListUtils.findAverageElementInt(listInt6);
            Assert.assertEquals(3, everageElement6.intValue());

            Integer everageElement7 = ListUtils.findAverageElementInt(listInt7);
            Assert.assertEquals(4, everageElement7.intValue());

            try
            {
                ListUtils.findAverageElementInt(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NullPointerException.class);
            }
            Assert.assertNull(ListUtils.findAverageElementInt(Collections.emptyList()));
            Assert.assertNull(ListUtils.findAverageElementInt(new LinkedList<>()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for {@link it.reexon.lib.list.ListUtils#findAverageElementLong(java.util.List)}.
     */
    @Test
    public final void testFindEverageElementLong()
    {
        try
        {
            Long everageElement3 = ListUtils.findAverageElementLong(listLong3);
            Assert.assertEquals(2L, everageElement3.longValue());

            Long everageElement6 = ListUtils.findAverageElementLong(listLong6);
            Assert.assertEquals(3L, everageElement6.longValue());

            Long everageElement7 = ListUtils.findAverageElementLong(listLong7);
            Assert.assertEquals(4L, everageElement7.longValue());

            try
            {
                ListUtils.findAverageElementLong(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NullPointerException.class);
            }
            Assert.assertNull(ListUtils.findAverageElementLong(Collections.emptyList()));
            Assert.assertNull(ListUtils.findAverageElementLong(new LinkedList<>()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

}
