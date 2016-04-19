/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.List;

import org.bouncycastle.crypto.tls.ExtensionType;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class TrkSegType
{
    private List<PtType> trkpt; //[0..*]
    private ExtensionType extensions; //[0..1] 

    public List<PtType> getTrkpt()
    {
        return trkpt;
    }

    public void setTrkpt(List<PtType> trkpt)
    {
        this.trkpt = trkpt;
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
