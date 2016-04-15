/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.InputStream;

import org.junit.Test;

import it.reexon.lib.network.NetworkUtils;


/**
 * @author marco.velluto
 *
 */
public class NetworkUtilsTest
{
    private static final String URL_TEST = "https://i.ytimg.com/vi/p-ORlc2b4-0/maxresdefault.jpg";

    /**
     * Test method for {@link it.reexon.lib.network.NetworkUtils#inputStreamFromURL(java.lang.String)}.
     */
    @Test
    public final void testInputStreamFromURL()
    {
        try
        {
            InputStream in = NetworkUtils.inputStreamFromURL(URL_TEST);
            assertNotNull(in);
        }
        catch (Exception e)
        {
            System.err.println("Errore in testInputStreamFromURL: " + e.getLocalizedMessage());
            fail(e.getLocalizedMessage()); // TODO
            e.printStackTrace();
        }
    }

    /**
     * Test method for {@link it.reexon.lib.network.NetworkUtils#byteFromURL(java.lang.String)}.
     */
    @Test
    public final void testByteFromURL()
    {
        try
        {
            byte[] b = NetworkUtils.byteFromURL(URL_TEST);
            assertTrue(b.length > 0);
        }
        catch (Exception e)
        {
            System.err.println("Errore in testByteFromURL: " + e.getLocalizedMessage());
            fail(e.getLocalizedMessage()); // TODO
            e.printStackTrace();
        }
    }

}
