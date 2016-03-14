package it.reexon.reexon.lib.date.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @author marco.velluto
 */
public class DateUtils
{
    /**
     * 
     * @param date1 min date
     * @param date2 max date
     * @return list of dates
     * @throws ParseException
     */
    public static Collection<Date> getDatesBetween(Date date1, Date date2)
    {
        if (date2.getTime() < date1.getTime())
            throw new IllegalArgumentException("Date2 have to max date1");

        List<Date> dates = new ArrayList<Date>();

        Calendar calendarDate1 = Calendar.getInstance();
        calendarDate1.setTimeInMillis(date1.getTime());

        Calendar calendarDate2 = Calendar.getInstance();
        calendarDate2.setTimeInMillis(date2.getTime());

        Long diffDay = (calendarDate2.getTimeInMillis() - calendarDate1.getTimeInMillis()) / (1000 * 60 * 60 * 24);

        for (int c = 0; c <= diffDay; c++)
        {
            calendarDate1.add(Calendar.DATE, 1);
            dates.add(new Date(calendarDate1.getTimeInMillis()));
        }
        return dates;
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date getMidnight(Date date)
    {
        if (date == null)
            return null;

        Calendar calendarInizio = Calendar.getInstance();
        calendarInizio.setTime(date);
        calendarInizio.set(Calendar.HOUR_OF_DAY, 0);
        calendarInizio.set(Calendar.MINUTE, 0);
        calendarInizio.set(Calendar.SECOND, 0);
        calendarInizio.set(Calendar.MILLISECOND, 0);

        return calendarInizio.getTime();
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date get23_59(Date date)
    {
        if (date == null)
            return null;

        Calendar calendarInizio = Calendar.getInstance();
        calendarInizio.setTime(date);
        calendarInizio.set(Calendar.HOUR_OF_DAY, 23);
        calendarInizio.set(Calendar.MINUTE, 59);
        calendarInizio.set(Calendar.SECOND, 59);
        calendarInizio.set(Calendar.MILLISECOND, 999);

        return calendarInizio.getTime();
    }

    /**
     * 
     * @param dateFrom
     */
    public static void addOneDay(Date date)
    {
        addDays(date, 1);
    }

    /**
     * 
     * @param date
     * @param numberDays
     */
    public static void addDays(Date date, int numberDays)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numberDays);
        date = c.getTime();
    }

    public static boolean between(Date data, Date dataInizio, Date dataFine)
    {
        return data.compareTo(dataInizio) >= 0 && data.compareTo(dataFine) <= 0;
    }

}
