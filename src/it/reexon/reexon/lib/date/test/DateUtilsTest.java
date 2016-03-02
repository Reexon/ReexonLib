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

}
