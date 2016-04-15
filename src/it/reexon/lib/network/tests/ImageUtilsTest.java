package it.reexon.lib.network.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.awt.Image;
import java.io.IOException;

import org.junit.Test;

import it.reexon.lib.network.ImageUtils;


public class ImageUtilsTest
{
    @Test
    public final void imageFormURLTest()
    {
        try
        {
            Image img = ImageUtils.imageFormURL("https://i.ytimg.com/vi/p-ORlc2b4-0/maxresdefault.jpg");
            assertNotNull(img);
        }
        catch (IOException e)
        {
            System.err.println("Errore in imageFormURLTest: " + e.getLocalizedMessage());
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

}
