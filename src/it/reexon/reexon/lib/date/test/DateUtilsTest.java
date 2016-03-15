/**
 * 
 */
package it.reexon.reexon.lib.date.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            final Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-01 14:51:26");
            final Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-03-10 14:51:26");

            List<Date> listOfDate = (List<Date>) DateUtils.getDatesBetween(date1, date2);
            Assert.assertNotNull(listOfDate);
            Assert.assertEquals(10, listOfDate.size());
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
}
