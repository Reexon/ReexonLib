package it.reexon.reexon.lib.network.tests;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.network.UrlConnection;


public class UrlConnectionTest
{

    @Test
    public final void sendGetTest()
    {
        System.out.println("START --- sendPostTest");
        try
        {
            UrlConnection http = new UrlConnection();
            System.out.println("Testing 1 - Send Http GET request");
            String url = "http://www.google.com/search?q=mkyong";
            http.sendGet(url);
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            System.out.println("ERROR - sendGetTest:" + e.getMessage());
            e.printStackTrace();
            fail();
        }
        finally
        {
            System.out.println("END --- sendPostTest");
        }
    }

    @Test
    public final void sendGetTestV2()
    {
        System.out.println("START --- sendPostTestV2");
        try
        {
            UrlConnection http = new UrlConnection();
            System.out.println("Testing 1 - Send Http GET request");
            String url = "http://www.mangaeden.com/it/it-manga/bleach/666";
            http.sendGet(url);
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            System.out.println("ERROR - sendGetTest:" + e.getMessage());
            e.printStackTrace();
            fail();
        }
        finally
        {
            System.out.println("END --- sendPostTestV2");
        }
    }

    public final void sendPostTest()
    {
        System.out.println("START --- sendPostTest");
        try
        {
            UrlConnection http = new UrlConnection();
            System.out.println("\nTesting 2 - Send Http POST request");
            http.sendPost();
            Assert.assertTrue(true);
        }
        catch (Exception e)
        {
            System.out.println("ERROR - sendPostTest:" + e.getMessage());
            e.printStackTrace();
            fail();
        }
        finally
        {
            System.out.println("END --- sendPostTest");
        }
    }
}
