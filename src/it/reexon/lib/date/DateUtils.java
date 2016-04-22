/**
 *  Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DateUtils
{
    /**
     * Add days to the date
     * 
     * @param date Date on which to add the days
     * @param numberDays number of days
     * @return date - Date on which you have added days 
     */
    public static Date addDays(final Date date, int numberDays)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numberDays);
        return c.getTime();
    }

    /**
     * Add one day to the date
     * 
     * @param dateFrom Date on which to add one day
     * @return date - Date on which you have added one day 
     */
    public static Date addOneDay(final Date date)
    {
        return addDays(date, 1);
    }

    /**
     * Checks whether the date is between the start date and end date
     * 
     * @param dateToCheck date to check
     * @param startDate start date
     * @param endDate end date
     * 
     * @return true - if the date is within the range provided
     * @throws if dateToCheck, startDate, endDate are null.
     */
    public static boolean between(Date dateToCheck, Date startDate, Date endDate) throws NullPointerException
    {
        if (dateToCheck == null)
            throw new NullPointerException("date to check can not be null");
        if (startDate == null)
            throw new NullPointerException("start date can not be null");
        if (endDate == null)
            throw new NullPointerException("end date can not be null");

        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;
    }

    /**
     * Generate Calendar from new
     * 
     * @return calendar - calendar from now
     */
    public static Calendar getCalendarFromNow()
    {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    /**
     * Generates an list of data from first to last
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
     *  @return Set{2016/03/01, 2016/03/02, ... , 2016/03/31} 
     */
    public static List<Date> getDatesBetween(final Date date1, final Date date2)
    {
        if (date2.getTime() < date1.getTime())
            throw new IllegalArgumentException("Date2 have to max date1");

        List<Date> dates = new LinkedList<>();
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

    /**
     * Set Time To Beginning Of Day
     * 
     * The Calendar sets with the following parameters of the day at 0 :
     * * HOUR_OF_DAY
     * * MINUTE
     * * SECOND
     * * MILLISECOND
     * @param calendar calendar to be set
     * @return date - date set as the beginning of the day
     * 
     * @throws if calendar is null
     */
    public static Calendar setTimeToBeginningOfDay(final Calendar calendar) throws NullPointerException
    {
        if (calendar == null)
            throw new NullPointerException("Calendar can not be null");

        Calendar c = (Calendar) calendar.clone();

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c;
    }

    /**
     * Set Time To Beginning Of Day
     * 
     * The Date sets with the following parameters of the day at 0 :
     * * HOUR_OF_DAY
     * * MINUTE
     * * SECOND
     * * MILLISECOND
     * 
     * @param date date to be set
     * @return date - Date set as the beginning of the day
     * 
     * @throws NullPointerException if date is null
     */
    public static Date setTimeToBeginningOfDay(final Date date) throws NullPointerException
    {
        if (date == null)
            throw new NullPointerException("date can not be null");

        Calendar calendarInizio = Calendar.getInstance();
        calendarInizio.setTime(date);
        calendarInizio.set(Calendar.HOUR_OF_DAY, 0);
        calendarInizio.set(Calendar.MINUTE, 0);
        calendarInizio.set(Calendar.SECOND, 0);
        calendarInizio.set(Calendar.MILLISECOND, 0);

        return calendarInizio.getTime();
    }

    /**
     * Set Time To End of Day
     *
     * The Calendar sets with the following parameters of the day at:
     * * HOUR_OF_DAY -> 23
     * * MINUTE -> 59
     * * SECOND -> 59
     * * MILLISECOND -> 999
     * 
     * @param calendar calendar to be set
     * @return caledar - calendar set as the ending of the day
     * @throws NullPointerException if calendar is null
     */
    public static Calendar setTimeToEndofDay(final Calendar calendar) throws NullPointerException
    {
        if (calendar == null)
            throw new NullPointerException("Calendar can not be null");

        Calendar c = (Calendar) calendar.clone();

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c;
    }

    /**
     * Set Time To End of Day
     *
     * The Calendar sets with the following parameters of the day at:
     * * HOUR_OF_DAY -> 23
     * * MINUTE -> 59
     * * SECOND -> 59
     * * MILLISECOND -> 999
     * 
     * @param date date to be set
     * @return date - date set as the ending of the day
     * 
     * @throws NullPointerException if date is null
     */
    public static Date setTimeToEndofDay(Date date) throws NullPointerException
    {
        if (date == null)
            throw new NullPointerException("Date can not be null");

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        return calendarEnd.getTime();
    }

    /**
     * Returns the date with the beginning of the month
     * 
     * @param date date to get beginning month
     * @return data - date of beginning month 
     */
    public static Date getDateBeginningMonth(final Date date)
    {
        if (date == null)
            return null;

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(date);
        calendarBegin.set(Calendar.DAY_OF_MONTH, calendarBegin.getActualMinimum(Calendar.DAY_OF_MONTH));

        calendarBegin = setTimeToBeginningOfDay(calendarBegin);

        return calendarBegin.getTime();

    }

    /**
     * Returns the date with the ending of the month
     * 
     * @param date date to get ending month
     * @return data - date of ending month 
     */
    public static Date getDateEndMonth(final Date date)
    {
        if (date == null)
            return null;

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

        calendarEnd = setTimeToEndofDay(calendarEnd);

        return calendarEnd.getTime();
    }

    /**
     * Returns a range of dates that between the beginning and the end of the month
     * 
     * @param date date to fetch range of dates
     * @return dateRange - date range of dates that between the beginning and the end of the month
     */
    public static DateRange getDateRangeBeginEndMonth(Date date)
    {
        if (date == null)
            return null;

        return new DateRange(getDateBeginningMonth(date), getDateBeginningMonth(date));
    }

    /**
     * Create a Date Range from fild e value
     * 
     * <p>
     * Example:
     *  date is 2016-01-03
     *  field is Calendar.DAY
     *  value is 1
     *  return: DateRange with 2016-01-02 and 2016-01-04
     *  
     * @param date      date to between range date.
     * @param field     the calendar field.
     * @param value     the value of date or time to be added to the field.
     * @return
     * @throws NullPointerException if date is null.
     */
    public static DateRange createDateRange(Date date, int field, int value) throws NullPointerException
    {
        if (date == null)
            throw new NullPointerException("Date is null");

        Calendar c1 = new Calendar.Builder().setInstant(date).build();
        c1.add(field, value);

        Calendar c2 = new Calendar.Builder().setInstant(date).build();
        c2.add(field, -value);

        DateRange dr = new DateRange(c1.getTime(), c2.getTime());

        return dr;
    }
}
