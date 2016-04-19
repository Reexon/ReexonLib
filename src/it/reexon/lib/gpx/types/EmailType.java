/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.types;

/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class EmailType
{
    private String id; //Required [1]
    private String domain; //Required [1]

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

}
