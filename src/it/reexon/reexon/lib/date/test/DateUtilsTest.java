/**
 * 
 */
package it.reexon.reexon.lib.date.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.date.utils.DateUtils;


/**
 * @author marco.velluto
 *
 */
public class DateUtilsTest
{
    @Test
    public void getDatesBetween()
    {
        try
        {
            final Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-01 00:00:000");
            final Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-10 00:00:000");

            List<Date> listOfDate = (List<Date>) DateUtils.getDatesBetween(date1, date2);
            Assert.assertNotNull(listOfDate);
            Assert.assertEquals(10, listOfDate.size());
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 1)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 2)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 3)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 4)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 5)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 6)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 7)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 8)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 9)));
            Assert.assertTrue(listOfDate.contains(this.getDate(2016, 02, 10)));
        }
        catch (ParseException e)
        {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void getDateBeginMonthTest()
    {
        try
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-10 14:51:26");
            Date dateReturn = DateUtils.getDateBeginMonth(date);

            Assert.assertEquals("Tue Mar 01 00:00:00 CET 2016", dateReturn.toString());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getDateEndMonthTest()
    {
        try
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-01 14:51:26");
            Date dateReturn = DateUtils.getDateEndMonth(date);

            Assert.assertEquals("Thu Mar 31 23:59:59 CEST 2016", dateReturn.toString());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * 
     * @param year 
     * @param month month star 0 (January = 0)
     * @param day
     * @return
     */
    private Date getDate(int year, int month, int day)
    {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        DateUtils.setTimeToBeginningOfDay(c);
        return c.getTime();
    }
}
