package it.reexon.lib.date.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.date.DateRange;
import it.reexon.lib.date.DateUtils;


public class DateUtilsTest
{
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Test
    public void getDatesBetweenTest()
    {
        try
        {
            final Date date1 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-01 00:00:000");
            final Date date2 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-10 00:00:000");

            List<Date> listOfDate = DateUtils.getDatesBetween(date1, date2);
            Assert.assertNotNull(listOfDate);
            Assert.assertEquals(10, listOfDate.size());
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 1)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 2)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 3)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 4)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 5)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 6)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 7)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 8)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 9)));
            Assert.assertTrue(listOfDate.contains(DateUtils.getDate(2016, 02, 10)));
        }
        catch (Exception e)
        {
            System.err.println("Error in getDatesBetweenTest: " + e.getLocalizedMessage());
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void getDateBeginMonthTest()
    {
        try
        {
            Date date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-10 14:51:26");
            Date dateReturn = DateUtils.getDateBeginningMonth(date);

            Assert.assertEquals("Tue Mar 01 00:00:00 CET 2016", dateReturn.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void getDateEndMonthTest()
    {
        try
        {
            Date date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-03-01 14:51:26");
            Date dateReturn = DateUtils.getDateEndMonth(date);

            Assert.assertEquals("Thu Mar 31 23:59:59 CEST 2016", dateReturn.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void createDateRangeTest()
    {
        try
        {
            Date date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-03 14:51:26");
            DateRange dateReturn = DateUtils.createDateRange(date, Calendar.DAY_OF_YEAR, 1);
            Assert.assertEquals("Mon Jan 04 14:51:26 CET 2016", dateReturn.getDateFrom().toString());
            Assert.assertEquals("Sat Jan 02 14:51:26 CET 2016", dateReturn.getDateTo().toString());

            date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-01 00:00:00");
            dateReturn = DateUtils.createDateRange(date, Calendar.DAY_OF_YEAR, 5);
            Assert.assertEquals("Wed Jan 06 00:00:00 CET 2016", dateReturn.getDateFrom().toString());
            Assert.assertEquals("Sun Dec 27 00:00:00 CET 2015", dateReturn.getDateTo().toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void averageDateRangeTest()
    {
        try
        {
            Date date1 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-03 14:51:26");
            Date date2 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-05 14:51:26");
            DateRange dateRange = new DateRange(date1, date2);

            Date dateReturn = DateUtils.average(dateRange);
            Assert.assertEquals("Mon Jan 04 14:51:26 CET 2016", dateReturn.toString());

            dateRange.setDateFrom(null);
            dateReturn = DateUtils.average(dateRange);
            Assert.assertEquals("Tue Jan 05 14:51:26 CET 2016", dateReturn.toString());

            try
            {
                dateRange.setDateTo(null);
                dateReturn = DateUtils.average(dateRange);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NoSuchElementException.class);
            }

            try
            {
                dateRange = null;
                DateUtils.average(dateRange);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NullPointerException.class);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void averageDateRangeListTest()
    {
        try
        {
            Date date1 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-03 14:51:26");
            Date date2 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-05-05 14:51:26");
            Date date3 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-01-08 14:51:26");
            Date date4 = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse("2016-05-02 14:51:26");

            List<Date> dates = new LinkedList<>();
            dates.add(date1);
            dates.add(date2);
            dates.add(date3);
            dates.add(date4);

            Date dateReturn = DateUtils.average(dates);
            Assert.assertEquals("Sat Mar 05 14:21:26 CET 2016", dateReturn.toString());

            dates.removeIf(p -> p.getTime() != date1.getTime());
            dateReturn = DateUtils.average(dates);
            Assert.assertEquals("Sun Jan 03 14:51:26 CET 2016", dateReturn.toString());

            try
            {
                dates = new LinkedList<>();
                dateReturn = DateUtils.average(dates);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NoSuchElementException.class);
            }

            try
            {
                dates = null;
                DateUtils.average(dates);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(e.getClass(), NullPointerException.class);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());

        }
    }
}
