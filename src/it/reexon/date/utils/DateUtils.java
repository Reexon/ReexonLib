package it.reexon.date.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author marco.velluto
 */
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
        {
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
     * @param dateString        date string in format yyyymmdd (ex. 20151210)
     * @return
     */
    public static Date parseDataYMD(String dateString)
    {
        Date date = DateUtils.parseDate(dateString, new SimpleDateFormat("yyyymmdd"));
        return date;
    }
}
