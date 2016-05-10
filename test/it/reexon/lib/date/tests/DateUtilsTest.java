/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.date.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.date.DateRange;
import it.reexon.lib.date.DateUtils;
import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DateUtilsTest
{
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static Date date01;
    private static Date date02;
    private static Date date03;
    private static Date date04;
    private static Date date05;
    private static Date date10;

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
    {
        date01 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-01 00:00:000");
        date02 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-02 00:00:000");
        date03 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-03 00:00:000");
        date04 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-04 00:00:000");
        date05 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-05 00:00:000");
        date10 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-10 00:00:000");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {}

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#addDays(java.util.Date, int)}.
     */
    @Test
    public final void testAddDays()
    {
        try
        {
            Date dateReturned = DateUtils.addDays(date01, 2);
            Assert.assertEquals(date03.getTime(), dateReturned.getTime());

            dateReturned = DateUtils.addDays(date10, -5);
            Assert.assertEquals(date05.getTime(), dateReturned.getTime());

            try
            {
                dateReturned = DateUtils.addDays(null, -5);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#addOneDay(java.util.Date)}.
     */
    @Test
    public final void testAddOneDay()
    {
        try
        {
            Date dateReturned = DateUtils.addOneDay(date01);
            Assert.assertEquals(date02.getTime(), dateReturned.getTime());
            try
            {
                dateReturned = DateUtils.addOneDay(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#between(java.util.Date, java.util.Date, java.util.Date)}.
     */
    @Test
    public final void testBetween()
    {
        try
        {
            Boolean between = DateUtils.between(date05, date01, date10);
            Assert.assertTrue(between);

            between = DateUtils.between(date01, date05, date10);
            Assert.assertFalse(between);

            try
            {
                DateUtils.between(date05, null, date10);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                DateUtils.between(null, null, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#getCalendarFromNow()}.
     */
    @Test
    public final void testGetCalendarFromNow()
    {}

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#getDatesBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public final void testGetDatesBetween()
    {
        try
        {
            List<Date> listOfDate = DateUtils.getDatesBetween(date01, date10);
            Assert.assertNotNull(listOfDate);
            Assert.assertEquals(10, listOfDate.size());
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 1)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 2)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 3)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 4)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 5)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 6)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 7)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 8)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 9)));
            Assert.assertTrue(listOfDate.contains(getDate(2016, 02, 10)));

            listOfDate = DateUtils.getDatesBetween(date01, date01);
            Assert.assertNotNull(listOfDate);
            Assert.assertEquals(1, listOfDate.size());
            Assert.assertEquals(getDate(2016, 02, 01).getTime(), listOfDate.get(0).getTime());

            try
            {
                DateUtils.getDatesBetween(null, date10);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                DateUtils.getDatesBetween(null, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }

            try
            {
                DateUtils.getDatesBetween(date10, date01);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#setTimeToBeginningOfDay(java.util.Calendar)}.
     */
    @Test
    public final void testSetTimeToBeginningOfDayCalendar()
    {
        try
        {
            Calendar timeToEndOfDay = DateUtils.setTimeToBeginningOfDay(new Calendar.Builder().setInstant(date01).build());
            Calendar cal = new Calendar.Builder().setInstant(timeToEndOfDay.getTimeInMillis()).build();
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 000);
            Assert.assertEquals(cal.getTimeInMillis(), timeToEndOfDay.getTimeInMillis());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#setTimeToBeginningOfDay(java.util.Date)}.
     */
    @Test
    public final void testSetTimeToBeginningOfDayDate()
    {
        try
        {
            Date timeToEndOfDay = DateUtils.setTimeToBeginningOfDay(date01);
            Calendar cal = new Calendar.Builder().setInstant(timeToEndOfDay).build();
            cal.set(Calendar.HOUR_OF_DAY, 00);
            cal.set(Calendar.SECOND, 00);
            cal.set(Calendar.MILLISECOND, 000);
            Assert.assertEquals(cal.getTimeInMillis(), timeToEndOfDay.getTime());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#setTimeToEndofDay(java.util.Calendar)}.
     */
    @Test
    public final void testSetTimeToEndofDayCalendar()
    {
        try
        {
            Calendar timeToEndOfDay = DateUtils.setTimeToEndofDay(new Calendar.Builder().setInstant(date01).build());
            Calendar cal = new Calendar.Builder().setInstant(timeToEndOfDay.getTimeInMillis()).build();
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            Assert.assertEquals(cal.getTimeInMillis(), timeToEndOfDay.getTimeInMillis());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#setTimeToEndofDay(java.util.Date)}.
     */
    @Test
    public final void testSetTimeToEndofDayDate()
    {
        try
        {
            Date timeToEndOfDay = DateUtils.setTimeToEndofDay(date01);
            Calendar cal = new Calendar.Builder().setInstant(timeToEndOfDay).build();
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            Assert.assertEquals(cal.getTimeInMillis(), timeToEndOfDay.getTime());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#getDateBeginningMonth(java.util.Date)}.
     */
    @Test
    public final void testGetDateBeginningMonth()
    {
        try
        {
            Date endDate = DateUtils.getDateBeginningMonth(date10);
            Calendar expectedCal = new Calendar.Builder().setInstant(date10).build();
            expectedCal.set(Calendar.DAY_OF_MONTH, 1);
            Assert.assertEquals(expectedCal.getTime().getTime(), endDate.getTime());

            try
            {
                DateUtils.getDateBeginningMonth(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#getDateEndMonth(java.util.Date)}.
     */
    @Test
    public final void testGetDateEndMonth()
    {
        try
        {
            Date endDate = DateUtils.getDateEndMonth(date10);
            Date expectedDate = new Calendar.Builder().setDate(2016, 2, 31).set(Calendar.HOUR_OF_DAY, 23).set(Calendar.MINUTE, 59)
                                                      .set(Calendar.SECOND, 59).set(Calendar.MILLISECOND, 999).build().getTime();
            Assert.assertEquals(expectedDate.getTime(), endDate.getTime());

            try
            {
                DateUtils.getDateEndMonth(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#average(it.reexon.lib.date.DateRange)}.
     */
    @Test
    public final void testAverageDateRange()
    {
        try
        {
            DateRange dateRange = new DateRange(date01, date05);
            Date averageDate = DateUtils.average(dateRange);
            Assert.assertEquals(date03.getTime(), averageDate.getTime());

            try
            {
                dateRange.setDateFrom(null);
                DateUtils.average(Collections.emptyList());
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                dateRange.setDateFrom(null);
                dateRange.setDateTo(null);
                DateUtils.average(Collections.emptyList());
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#average(java.util.List)}.
     */
    @Test
    public final void testAverageListOfDate()
    {
        try
        {
            List<Date> dates = new LinkedList<Date>(ListUtils.createList(date01, date02, date03, date04, date05));
            Date averageDate = DateUtils.average(dates);
            Assert.assertEquals(date03.getTime(), averageDate.getTime());

            try
            {
                DateUtils.average(Collections.emptyList());
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.date.DateUtils#untilDate(java.util.List, java.util.Date)}.
     */
    @Test
    public final void testUntilDate()
    {
        try
        {
            List<Date> dates = new LinkedList<>(ListUtils.createList(date01, date02, date03, date04, date04, date05));
            Date dateReturned = DateUtils.untilDate(dates, date04);
            Assert.assertEquals(date04.getTime(), dateReturned.getTime());

            dates = new LinkedList<>(ListUtils.createList(date01, date02, date03, date04, date04, date05));
            dateReturned = DateUtils.untilDate(dates, date10);
            Assert.assertEquals(date05.getTime(), dateReturned.getTime());

            dates = new LinkedList<>(ListUtils.createList(date01, date02, date03, date05));
            dateReturned = DateUtils.untilDate(dates, date04);
            Assert.assertEquals(date03.getTime(), dateReturned.getTime());

            try
            {
                DateUtils.untilDate(null, date04);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                DateUtils.untilDate(null, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                DateUtils.untilDate(Collections.emptyList(), null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }
            try
            {
                DateUtils.untilDate(Collections.emptyList(), date04);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), IllegalArgumentException.class);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    private static Date getDate(int year, int month, int day)
    {
        if (year < 0 || month < 0 || day < 0)
            throw new IllegalArgumentException("Values must be greater of 0");

        final Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        return DateUtils.setTimeToBeginningOfDay(c).getTime();
    }
}
