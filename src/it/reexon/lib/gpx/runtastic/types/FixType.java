/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.gpx.runtastic.types;

import java.util.LinkedList;
import java.util.List;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class FixType
{
    private List<String> fixList = new LinkedList<>();

    public List<String> getFixList()
    {
        return fixList;
    }

    public void setFixList(List<String> fixList)
    {
        this.fixList = fixList;
    }
}
