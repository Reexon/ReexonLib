/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.types;

/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class PersonType
{
    //@f:off
    private String name;        //[0..1]
    private EmailType email;    //[0..1]
    private LinkType link;      //[0..1]
    //@f:off
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public EmailType getEmail()
    {
        return email;
    }
    public void setEmail(EmailType email)
    {
        this.email = email;
    }
    public LinkType getLink()
    {
        return link;
    }
    public void setLink(LinkType link)
    {
        this.link = link;
    }
    
    
}
