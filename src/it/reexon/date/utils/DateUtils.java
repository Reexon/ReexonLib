package it.reexon.date.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    /**
     * 
     * @param dateString
     * @param format
     * @return
     */
    public static Date parseDate(String dateString, SimpleDateFormat format)
    {
        Date parsed = new Date();
        try
        {                                                       // Tue Nov 10 2015 00:00:00 GMT+0100
//            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss zzz");
            parsed = format.parse(dateString);
        }
        catch (ParseException pe)
        {
            throw new IllegalArgumentException(pe);
        }
        return parsed;
    }
    
    /**
     * 
     * @param dateString
     * @param format
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date parseNewDate(String dateString)
    {
        Date parsed = new Date();
        parsed = new Date(dateString);
        return parsed;
    }
}
