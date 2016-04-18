/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;

import it.reexon.lib.types.NonNegativeInteger;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class RteType
{
    private String name; //[0..1]
    private String cmt; // [0..1] 
    private String desc;// [0..1]
    private String src; //[0..1] 
    private List<LinkType> link; // [0..*]
    private NonNegativeInteger number; //[0..1] 
    private String type; //[0..1]
    private ExtensionType extensions; //[0..1]
    private List<WptType> rtept; //[0..*]

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getCmt()
    {
        return cmt;
    }

    public void setCmt(String cmt)
    {
        this.cmt = cmt;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public List<LinkType> getLink()
    {
        return link;
    }

    public void setLink(List<LinkType> link)
    {
        this.link = link;
    }

    public NonNegativeInteger getNumber()
    {
        return number;
    }

    public void setNumber(NonNegativeInteger number)
    {
        this.number = number;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public ExtensionType getExtensions()
    {
        return extensions;
    }

    public void setExtensions(ExtensionType extensions)
    {
        this.extensions = extensions;
    }

    public List<WptType> getRtept()
    {
        return rtept;
    }

    public void setRtept(List<WptType> rtept)
    {
        this.rtept = rtept;
    }
}
