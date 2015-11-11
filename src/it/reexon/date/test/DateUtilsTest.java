/**
 * 
 */
package it.reexon.date.test;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import it.reexon.date.utils.DateUtils;


/**
 * @author marco.velluto
 *
 */
public class DateUtilsTest
{
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {}

    @Test
    public void testParseDataYMD()
    {
        try
        {
            Date date = DateUtils.parseDataYMD("20151102");
            if (date != null)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                assert year == 1 && month == 11 && day == 2;
            }
            else
            {
                fail("la data è nulla");
            }

        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }
}
