package it.reexon.reexon.lib.date.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * @author marco.velluto
 */
public class DateUtils
{
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

    /**
     * @param dateFrom
     */
    public static void addOneDay(Date date)
    {
        addDays(date, 1);
    }

    public static boolean between(Date data, Date dataInizio, Date dataFine)
    {
        return data.compareTo(dataInizio) >= 0 && data.compareTo(dataFine) <= 0;
    }

    public static Calendar getCalendarForNow()
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    /**
     * @author marco.velluto
     * 
     * @param date1 min date
     * @param date2 max date
     * @return list of dates 
     * @throws ParseException
     * 
     * @Example
     *  @param date1 = 2016/03/01
     *  @param date2 = 2016/03/31
     *  
     *  @return Collection{2016/03/01, 2016/03/02, ... , 2016/03/31} 
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

        dates.add(new Date(calendarDate1.getTimeInMillis()));
        for (int c = 0; c < diffDay; c++)
        {
            calendarDate1.add(Calendar.DATE, 1);
            dates.add(new Date(calendarDate1.getTimeInMillis()));
        }
        return dates;
    }

    public static void setTimeToBeginningOfDay(Calendar calendar)
    {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date setTimeToBeginningOfDay(Date date)
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

    public static void setTimeToEndofDay(Calendar calendar)
    {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date setTimeToEndofDay(Date date)
    {
        if (date == null)
            return null;

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        return calendarEnd.getTime();
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date getDateBeginMonth(Date date)
    {
        if (date == null)
            return null;

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(date);
        calendarBegin.set(Calendar.DAY_OF_MONTH, calendarBegin.getActualMinimum(Calendar.DAY_OF_MONTH));

        setTimeToBeginningOfDay(calendarBegin);

        return calendarBegin.getTime();

    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date getDateEndMonth(Date date)
    {
        if (date == null)
            return null;

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

        setTimeToEndofDay(calendarEnd);

        return calendarEnd.getTime();
    }

    /**
     * 
     * @param date
     * @return
     */
    public static DateRange getDateRangeBeginEndMonth(Date date)
    {
        if (date == null)
            return null;

        return new DateRange(getDateBeginMonth(date), getDateBeginMonth(date));
    }

    public static class DateRange
    {
        private Date dateFrom;
        private Date dateTo;

        public DateRange(Date dateFrom, Date dateTo)
        {
            super();
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
        }

        public Date getDateFrom()
        {
            return dateFrom;
        }

        public void setDateFrom(Date dateFrom)
        {
            this.dateFrom = dateFrom;
        }

        public Date getDateTo()
        {
            return dateTo;
        }

        public void setDateTo(Date dateTo)
        {
            this.dateTo = dateTo;
        }
    }
}
