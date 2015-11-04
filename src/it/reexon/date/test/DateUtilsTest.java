/**
 * 
 */
package it.reexon.date.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
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
    public void testDateParseWithTiemZone()
    {
        try
        {
            Date date = DateUtils.parseDate("Tue Nov 10 2015 00:00:00 GMT+0100", new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzz"));
            assertNotNull(date);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testDateParseWithoutTiemZone()
    {
        try
        {
            Date date = DateUtils.parseNewDate("Tue Nov 10 2015 00:00:00 GMT+0100");
            assertNotNull(date);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }

    @Test
    public void testNewDate()
    {
        try
        {
            Date date = DateUtils.parseDate("Tue Nov 10 2015 00:00:00", new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss"));
            assertNotNull(date);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }   
    
    @Test
    public void testDateFormat()
    {
        try
        {
            Date date = DateFormat.getDateInstance().parse("Tue Nov 10 2015 00:00:00");
            StringUtils.abbreviate("sss", 3);
            assertNotNull(date);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }
    
    
    
    

}
