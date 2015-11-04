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
    public void testParseDataYMD()
    {
        try
        {
            Date date = DateUtils.parseDataYMD("20151102");
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
