/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.types;

import java.net.URI;
import java.time.Year;


/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class CopyrightType
{
    private String author; //Required [1]

    private Year year; //[0..1]
    private URI licence; //[0..1]

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Year getYear()
    {
        return year;
    }

    public void setYear(Year year)
    {
        this.year = year;
    }

    public URI getLicence()
    {
        return licence;
    }

    public void setLicence(URI licence)
    {
        this.licence = licence;
    }

}
