/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.Date;
import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;


/**
 * @author Marco Velluto
 * @version GPX 1.1
 * @since Java 1.8
 */
public class MetadataType
{
    private String name; //[0..1]
    private String desc; //[0..1]
    private PersonType author; //[0..1]
    private CopyrightType copyright;//[0..1]
    private List<LinkType> link; //[0..*]
    private Date time; //[0..1]
    private String keywords; //[0..1]
    private List<ExtensionType> extensionType; //[0..*]
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getDesc()
    {
        return desc;
    }
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    public PersonType getAuthor()
    {
        return author;
    }
    public void setAuthor(PersonType author)
    {
        this.author = author;
    }
    public CopyrightType getCopyright()
    {
        return copyright;
    }
    public void setCopyright(CopyrightType copyright)
    {
        this.copyright = copyright;
    }
    public List<LinkType> getLink()
    {
        return link;
    }
    public void setLink(List<LinkType> link)
    {
        this.link = link;
    }
    public Date getTime()
    {
        return time;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }
    public String getKeywords()
    {
        return keywords;
    }
    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }
    public List<ExtensionType> getExtensionType()
    {
        return extensionType;
    }
    public void setExtensionType(List<ExtensionType> extensionType)
    {
        this.extensionType = extensionType;
    }
}
