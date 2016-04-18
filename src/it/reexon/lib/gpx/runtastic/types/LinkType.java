/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.net.URI;


/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class LinkType
{
    private URI anyURI; //Required

    private String text; //[0..1]
    private String type; //[0..1]       

    public URI getAnyURI()
    {
        return anyURI;
    }

    public void setAnyURI(URI anyURI)
    {
        this.anyURI = anyURI;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
