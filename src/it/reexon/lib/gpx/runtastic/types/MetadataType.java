/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.Date;
import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;

import it.reexon.lib.gpx.types.EmailType;


/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class MetadataType
{
    private String name; //Descriptive name of the GPX file
    private String desc; //Description of the GPX file
    private EmailType email; //Email address of the file's creator
    private String bounds; //Bounding rectangle for the data in the file.
    private PersonType author; //Name of the file's creator
    private CopyrightType copyright;//[0..1]
    private List<LinkType> link; //URL associated with the file
    private String linkName; //Text to display on the <url> hyperlink
    private Date time; //Creation date/time of the GPX file
    private List<String> keywords; //Keywords for categorizing the GPX file in a database or search engine
    private List<ExtensionType> extensionType; //[0..*]

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the email
     */
    public EmailType getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(EmailType email)
    {
        this.email = email;
    }

    /**
     * @return the bounds
     */
    public String getBounds()
    {
        return bounds;
    }

    /**
     * @param bounds the bounds to set
     */
    public void setBounds(String bounds)
    {
        this.bounds = bounds;
    }

    /**
     * @return the author
     */
    public PersonType getAuthor()
    {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(PersonType author)
    {
        this.author = author;
    }

    /**
     * @return the copyright
     */
    public CopyrightType getCopyright()
    {
        return copyright;
    }

    /**
     * @param copyright the copyright to set
     */
    public void setCopyright(CopyrightType copyright)
    {
        this.copyright = copyright;
    }

    /**
     * @return the link
     */
    public List<LinkType> getLink()
    {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(List<LinkType> link)
    {
        this.link = link;
    }

    /**
     * @return the linkName
     */
    public String getLinkName()
    {
        return linkName;
    }

    /**
     * @param linkName the linkName to set
     */
    public void setLinkName(String linkName)
    {
        this.linkName = linkName;
    }

    /**
     * @return the time
     */
    public Date getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time)
    {
        this.time = time;
    }

    /**
     * @return the keywords
     */
    public List<String> getKeywords()
    {
        return keywords;
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(List<String> keywords)
    {
        this.keywords = keywords;
    }

    /**
     * @return the extensionType
     */
    public List<ExtensionType> getExtensionType()
    {
        return extensionType;
    }

    /**
     * @param extensionType the extensionType to set
     */
    public void setExtensionType(List<ExtensionType> extensionType)
    {
        this.extensionType = extensionType;
    }
}
