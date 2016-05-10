/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.network.NetworkUtils;


/**
 * @author marco.velluto
 *
 */
public class NetworkUtilsTest
{
    private static final Logger logger = LogManager.getLogger(NetworkUtilsTest.class);

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

            try
            {
                NetworkUtils.inputStreamFromURL(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                NetworkUtils.inputStreamFromURL("");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                NetworkUtils.inputStreamFromURL(" ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testInputStreamFromURL: ", e);
            throw new RuntimeException(e);
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

            try
            {
                NetworkUtils.byteFromURL(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                NetworkUtils.byteFromURL("");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                NetworkUtils.byteFromURL(" ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in testInputStreamFromURL: ", e);
            throw new RuntimeException(e);
        }
    }

}
