/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.network.tests;

import static org.junit.Assert.assertNotNull;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.network.ImageUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ImageUtilsTest
{
    private static final Logger logger = LogManager.getLogger(ImageUtilsTest.class);

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {}

    /**
     * Test method for {@link it.reexon.lib.network.ImageUtils#imageFormURL(java.lang.String)}.
     */
    @Test
    public final void testImageFormURL()
    {
        try
        {
            Image img = ImageUtils.imageFormURL("https://i.ytimg.com/vi/p-ORlc2b4-0/maxresdefault.jpg");
            assertNotNull(img);
            
            try
            {
                ImageUtils.imageFormURL(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                ImageUtils.imageFormURL("");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                ImageUtils.imageFormURL(" ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (IOException e)
        {
            logger.error("Errore in imageFormURLTest: " + e);
            throw new RuntimeException(e);
        }

    }

    /**
     * Test method for {@link it.reexon.lib.network.ImageUtils#formatName(java.lang.String)}.
     */
    @Test
    public final void testFormatName()
    {
        try
        {
            String formatName = ImageUtils.formatName("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Magnificent_CME_Erupts_on_the_Sun_-_August_31.jpg/1024px-Magnificent_CME_Erupts_on_the_Sun_-_August_31.jpg");
            Assert.assertEquals("JPEG", formatName.toUpperCase());
            formatName = ImageUtils.formatName("http://static.tumblr.com/d12666874e731500c2d4da3e0982a6b5/szn6mus/0nMo00irn/tumblr_static_cank3ajxf3sww0s44ocooocw0.png");
            Assert.assertEquals("PNG", formatName.toUpperCase());

            formatName = ImageUtils.formatName("http://www.keymethods.eu:8004/Qrsid/photo.html?photo=21190");
            Assert.assertEquals("jpeg", formatName.toLowerCase());

            try
            {
                ImageUtils.formatName(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                ImageUtils.formatName("");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                ImageUtils.formatName(" ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Errore in imageFormURLTest: " + e);
            throw new RuntimeException(e);
        }
    }

    @Test
    public final void testFormatName2()
    {
        try
        {
            URL url = new URL("http://www.keymethods.eu:8004/Qrsid/photo.html?photo=21190");
            URLConnection conn = url.openConnection();
            String contentType = conn.getContentType();
            String estensione = StringUtils.substringAfterLast(contentType, "/");
            Assert.assertEquals("jpeg", estensione.toLowerCase());
        }
        catch (Exception e)
        {
            logger.error("Errore in imageFormURLTest: " + e);
            throw new RuntimeException(e);
        }

    }

}
