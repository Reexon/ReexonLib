package it.reexon.lib.date;

/**
 * Copyright (c) 2016 Marco Velluto
 */
import java.util.Date;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DateRange
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
