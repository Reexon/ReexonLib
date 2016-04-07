package it.reexon.reexon.lib.network.tests;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.reexon.lib.network.NetworkUtils;


public class NetworkUtilsTest
{
    private static final String TEST_DIRECTORY = "test";
    private static final File testDirectory = new File(TEST_DIRECTORY);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {

        if (testDirectory.exists())
        {
            testDirectory.mkdirs();
        }
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        if (testDirectory.exists())
        {
            FileUtils.forceDeleteOnExit(testDirectory);
        }
    }

    @Before
    public void setUp() throws Exception
    {}

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void downloadResourceFromTest()
    {
        try
        {
            Elements media = NetworkUtils.mediaFromUrl("https://www.google.it/search?q=huawei+p9&biw=1218&bih=599&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjn-O7MqvzLAhXH-A4KHcf3DLUQ_AUIBygC#tbm=isch&q=huawei+p9+prezzo&imgrc=-XRWTUSbx1wt_M%3A");
            System.out.println(media.toString());
        }
        catch (Exception e)
        {
            System.err.println("Errore in testCheckFiles " + e.getLocalizedMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public final void writeResourceTest()
    {
        try
        {
            NetworkUtils.writeResource(new URL("https://risehighershinebrighter.files.wordpress.com/2014/11/magic-of-blue-universe-images.jpg"),
                                       new File(TEST_DIRECTORY + "/image.jpg"));

            File result = new File(TEST_DIRECTORY + "/image.jpg");
            if (!result.exists())
                fail();
        }
        catch (Exception e)
        {
            System.err.println("Errore in writeResourceTest " + e.getLocalizedMessage());
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public final void imagesTest()
    {
        try
        {
            
            NetworkUtils.images("http://mvnrepository.com/artifact/commons-io/commons-io/2.4", TEST_DIRECTORY + "/img");
            NetworkUtils.images("https://500px.com/popular", "immagini/" + "/img");
        }
        catch (Exception e)
        {
            System.err.println("Errore in writeResourceTest " + e.getLocalizedMessage());
            e.printStackTrace();
            fail();
        }
    }

}
