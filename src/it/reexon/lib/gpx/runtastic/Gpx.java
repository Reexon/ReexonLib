/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic;

import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;

import it.reexon.lib.gpx.runtastic.types.MetadataType;
import it.reexon.lib.gpx.runtastic.types.RteType;
import it.reexon.lib.gpx.runtastic.types.TrkType;
import it.reexon.lib.gpx.runtastic.types.WptType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class Gpx
{
    private String version; //[0] must be 1.1
    private String creator; //[0]
    private MetadataType metadata; //[0..1]
    private List<WptType> wpt; //[0..*]
    private List<RteType> rte; //[0..*]
    private List<TrkType> trk; //[0..*]
    private ExtensionType extensions;//[0..*]

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    public MetadataType getMetadata()
    {
        return metadata;
    }

    public void setMetadata(MetadataType metadata)
    {
        this.metadata = metadata;
    }

    public List<WptType> getWpt()
    {
        return wpt;
    }

    public void setWpt(List<WptType> wpt)
    {
        this.wpt = wpt;
    }

    public List<RteType> getRte()
    {
        return rte;
    }

    public void setRte(List<RteType> rte)
    {
        this.rte = rte;
    }

    public List<TrkType> getTrk()
    {
        return trk;
    }

    public void setTrk(List<TrkType> trk)
    {
        this.trk = trk;
    }

    public ExtensionType getExtensions()
    {
        return extensions;
    }

    public void setExtensions(ExtensionType extensions)
    {
        this.extensions = extensions;
    }
}
