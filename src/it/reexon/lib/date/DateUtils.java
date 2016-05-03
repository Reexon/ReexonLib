/**
 ù*  Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import it.reexon.lib.list.ListUtils;


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
     * 
     * @throws IllegalArgumentException if date is null
     */
    public static Date addDays(final Date date, int numberDays)
    {
        if (date == null)
            throw new IllegalArgumentException("date cannot be null");

        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, numberDays);

        return c.getTime();
    }

    /**
     * Add one day to the date
     * 
     * @param dateFrom Date on which to add one day
     * @return date - Date on which you have added one day
     * 
     *  @throws IllegalArgumentException if date is null
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
     * @throws IllegalArgumentException if dateToCheck, startDate, endDate are null.
     */
    public static boolean between(final Date dateToCheck, final Date startDate, final Date endDate)
    {
        if (dateToCheck == null)
            throw new IllegalArgumentException("date to check cannot be null");
        if (startDate == null)
            throw new IllegalArgumentException("start date cannot be null");
        if (endDate == null)
            throw new IllegalArgumentException("end date cannot be null");

        if (startDate.getTime() > endDate.getTime())
            throw new IllegalArgumentException("endDate must be max startDate");

        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;
    }

    /**
     * Generate Calendar from new
     * 
     * @return calendar - calendar from now
     */
    public static Calendar getCalendarFromNow()
    {
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());

        return calendar;
    }

    /**
     * Generates an list of data from first to last
     * 
     * @param date1 min date
     * @param date2 max date
     * @return list of dates 
     * 
     * @Example
     *  @param date1 = 2016/03/01
     *  @param date2 = 2016/03/31
     *  
     *  @return Set{2016/03/01, 2016/03/02, ... , 2016/03/31} 
     *  @throws IllegalArgumentException if date2 > date1
     */
    public static List<Date> getDatesBetween(final Date date1, final Date date2)
    {
        if (date1 == null || date2 == null)
            throw new IllegalArgumentException("Date is null");

        if (date2.getTime() < date1.getTime())
            throw new IllegalArgumentException("Date2 have to max date1");

        List<Date> dates = new LinkedList<>();
        final Calendar calendarDate1 = Calendar.getInstance();
        calendarDate1.setTimeInMillis(date1.getTime());

        final Calendar calendarDate2 = Calendar.getInstance();
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
     * <br>* HOUR_OF_DAY
     * <br>* MINUTE
     * <br>* SECOND
     * <br>* MILLISECOND
     * @param calendar calendar to be set
     * @return date - date set as the beginning of the day
     * 
     * @throws IllegalArgumentException if calendar is null
     */
    public static Calendar setTimeToBeginningOfDay(final Calendar calendar)
    {
        if (calendar == null)
            throw new IllegalArgumentException("Calendar cannot be null");

        final Calendar c = (Calendar) calendar.clone();

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
     * @throws IllegalArgumentException if date is null
     */
    public static Date setTimeToBeginningOfDay(final Date date)
    {
        if (date == null)
            throw new IllegalArgumentException("date cannot be null");

        final Calendar calendarInizio = Calendar.getInstance();
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
     * @throws IllegalArgumentException if calendar is null
     */
    public static Calendar setTimeToEndofDay(final Calendar calendar)
    {
        if (calendar == null)
            throw new IllegalArgumentException("Calendar cannot be null");

        final Calendar c = (Calendar) calendar.clone();

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
     * @throws IllegalArgumentException if date is null
     */
    public static Date setTimeToEndofDay(final Date date)
    {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");

        final Calendar calendarEnd = Calendar.getInstance();
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
     * @throws IllegalArgumentException if date is null
     */
    public static Date getDateBeginningMonth(final Date date)
    {
        if (date == null)
            throw new IllegalArgumentException("Date cannot be null");

        final Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.setTime(date);
        calendarBegin.set(Calendar.DAY_OF_MONTH, calendarBegin.getActualMinimum(Calendar.DAY_OF_MONTH));

        return setTimeToBeginningOfDay(calendarBegin).getTime();

    }

    /**
     * Returns the date with the ending of the month
     * 
     * @param date date to get ending month
     * @return data - date of ending month
     * @throws IllegalArgumentException if date is null 
     */
    public static Date getDateEndMonth(final Date date)
    {
        if (date == null)
            throw new IllegalArgumentException("Date is null");

        final Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));

        return setTimeToEndofDay(calendarEnd).getTime();
    }

    /**
     * Calculates the average of the proposed dates
     * 
     * @param dateRange dates by which to calculate the average
     * @return - average date
     * @throws NullPointerException if date is null
     * @throws NoSuchElementException if all the dates in the date range are null
     * @throws IllegalArgumentException if dates in date range is null
     */
    public static Date average(DateRange dateRange)
    {
        if (dateRange == null)
            throw new NullPointerException("DateRange is null");

        if (dateRange.getDateFrom() == null || dateRange.getDateTo() == null)
            throw new IllegalArgumentException("No element found in dates");

        List<Date> dateList = new LinkedList<>();
        dateList.add(dateRange.getDateFrom());
        dateList.add(dateRange.getDateTo());
        return average(dateList);
    }

    /**
     * Calculates the average of the proposed dates
     * 
     * @param dates dates by which to calculate the average
     * @return average date
     * @throws NullPointerException if date is null
     * @throws IllegalArgumentException if all the dates in the date range are null
     */
    public static Date average(List<Date> dates)
    {
        if (dates == null)
            throw new NullPointerException("Dates is null");

        if (dates.isEmpty() || dates.stream().filter(p -> p != null).count() < 1)
            throw new IllegalArgumentException("No element found in dates");

        Long millisecond = (long) dates.stream().filter(p -> p != null).mapToLong(Date::getTime).average().getAsDouble();

        return new Date(millisecond);
    }

    /**
     * It takes the date that is closest to the one passed as a parameter.
     * 
     * @param dates date list from which take the value
     * @param date  date to looking for
     *       
     * @return date that is closest to that past.
     * @throws IllegalArgumentException if dates is empty
     */
    public static Date untilDate(List<Date> dates, Date date)
    {
        if (dates == null || date == null)
            throw new IllegalArgumentException("Dates cannot be null");
        if (dates.isEmpty())
            throw new IllegalArgumentException("List of date cannot be empty");

        List<Long> datesLong = new ArrayList<>(dates.stream().map(p -> p.getTime()).collect(Collectors.toList()));
        Long dateLong = ListUtils.nearestElement(datesLong, date.getTime());

        return new Date(dateLong);
    }
}
