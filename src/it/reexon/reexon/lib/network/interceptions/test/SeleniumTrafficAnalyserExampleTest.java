package it.reexon.reexon.lib.network.interceptions.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.server.SeleniumServer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.selenium.DefaultSelenium;

import it.reexon.reexon.lib.network.interceptions.HTMLRequestFromSelenium;
import it.reexon.reexon.lib.network.interceptions.TrafficAnalyser;


public class SeleniumTrafficAnalyserExampleTest
{
    @Test(timeout = 100000L)
    public void testProfileEvilTester() throws Exception
    {

        // Start the Selenium Server
        SeleniumServer srvr = new SeleniumServer();
        srvr.start();

        // Create a Selenium Session with captureNetworkTraffic ready
        String site = "http://www.eviltester.com";
        //                String site = "https://www.500px.com/popular";

        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", site);

        selenium.start("captureNetworkTraffic=true");

        // open a page to get the traffic
        selenium.open("/");

        // dump the traffic into a variable in Json format
        String trafficOutput = selenium.captureNetworkTraffic("json");
        System.out.println(trafficOutput);

        // parse the json using Gson
        Gson gson = new Gson();
        Type collectionOfHTMLRequestsType = new TypeToken<Collection<HTMLRequestFromSelenium>>()
        {}.getType();
        Collection<HTMLRequestFromSelenium> seleniumRequests = gson.fromJson(trafficOutput, collectionOfHTMLRequestsType);

        // get ready to analyse the traffic
        TrafficAnalyser ta = new TrafficAnalyser(seleniumRequests);

        // this is pretty much copied from Corey's python example
        int num_requests = ta.get_num_requests();
        int total_size = ta.get_content_size();
        HashMap<Integer, Integer> status_map = ta.get_http_status_codes();
        HashMap<String, Object[]> file_extension_map = ta.get_file_extension_stats();

        System.out.println("\n\n--------------------------------");
        System.out.println(String.format("results for %s", site));
        System.out.println(String.format("content size: %d kb", total_size));
        System.out.println(String.format("http requests: %d", num_requests));

        Iterator<Integer> statusIterator = status_map.keySet().iterator();
        while (statusIterator.hasNext())
        {
            int key = statusIterator.next();
            System.out.println(String.format("status %d: %d", key, status_map.get(key)));
        }

        System.out.println("\nfile extensions: (count, size)");
        Iterator<String> extensionIterator = file_extension_map.keySet().iterator();
        while (extensionIterator.hasNext())
        {
            String key = extensionIterator.next();
            System.out.println(String.format("%s: %d, %f", key, file_extension_map.get(key)[0], file_extension_map.get(key)[1]));
        }

        System.out.println("\nhttp timing detail: (status, method, url, size(bytes), time(ms))");
        for (Iterator<HTMLRequestFromSelenium> iterator = seleniumRequests.iterator(); iterator.hasNext();)
        {
            HTMLRequestFromSelenium hr = iterator.next();
            //totalContentSize += hr.bytes;
            System.out.println(String.format("%d, %s, %s, %d, %d", hr.getStatusCode(), hr.getMethod(), hr.getUrl(), hr.getBytes(),
                                             hr.getTimeInMillis()));
        }

        // close everything down
        selenium.close();
        selenium.stop();
        srvr.stop();
    }

    public void v() throws Exception
    {

        // Start the Selenium Server
        SeleniumServer srvr = new SeleniumServer();
        srvr.start();

        // Create a Selenium Session with captureNetworkTraffic ready
        String site = "http://www.eviltester.com";

        DefaultSelenium selenium = new DefaultSelenium("localhost", 4444, "*firefox", site);

        selenium.start("captureNetworkTraffic=true");

        // open a page to get the traffic
        selenium.open("/");

        // dump the traffic into a variable in Json format
        String trafficOutput = selenium.captureNetworkTraffic("json");
        System.out.println(trafficOutput);

        // parse the json using Gson
        Gson gson = new Gson();
        Type collectionOfHTMLRequestsType = new TypeToken<Collection<HTMLRequestFromSelenium>>()
        {}.getType();
        Collection<HTMLRequestFromSelenium> seleniumRequests = gson.fromJson(trafficOutput, collectionOfHTMLRequestsType);

        // get ready to analyse the traffic
        TrafficAnalyser ta = new TrafficAnalyser(seleniumRequests);

        // this is pretty much copied from Corey's python example
        int num_requests = ta.get_num_requests();
        int total_size = ta.get_content_size();
        HashMap<Integer, Integer> status_map = ta.get_http_status_codes();
        HashMap<String, Object[]> file_extension_map = ta.get_file_extension_stats();

        System.out.println("\n\n--------------------------------");
        System.out.println(String.format("results for %s", site));
        System.out.println(String.format("content size: %d kb", total_size));
        System.out.println(String.format("http requests: %d", num_requests));

        Iterator<Integer> statusIterator = status_map.keySet().iterator();
        while (statusIterator.hasNext())
        {
            int key = statusIterator.next();
            System.out.println(String.format("status %d: %d", key, status_map.get(key)));
        }

        System.out.println("\nfile extensions: (count, size)");
        Iterator<String> extensionIterator = file_extension_map.keySet().iterator();
        while (extensionIterator.hasNext())
        {
            String key = extensionIterator.next();
            System.out.println(String.format("%s: %d, %f", key, file_extension_map.get(key)[0], file_extension_map.get(key)[1]));
        }

        System.out.println("\nhttp timing detail: (status, method, url, size(bytes), time(ms))");
        for (Iterator<HTMLRequestFromSelenium> iterator = seleniumRequests.iterator(); iterator.hasNext();)
        {
            HTMLRequestFromSelenium hr = iterator.next();
            //totalContentSize += hr.bytes;
            System.out.println(String.format("%d, %s, %s, %d, %d", hr.getStatusCode(), hr.getMethod(), hr.getUrl(), hr.getBytes(),
                                             hr.getTimeInMillis()));
        }

        // close everything down
        selenium.close();
        selenium.stop();
        srvr.stop();
    }

    public void test() throws Exception
    {
        /**
         * Getting the Firebug/Net extension
        I want to enable the extension every time my test runs, either in CI or locally. So I have committed both the extensions to source control (Our internal CI does not allow for any external communication). I then add the extensions to the Firefox profile
         */
        File firebug = new File(System.getProperty("user.dir") + "\\resources\\firebug-1.10.6.xpi");
        File netExport = new File(System.getProperty("user.dir") + "\\resources\\netExport-0.9b3.xpi");

        FirefoxProfile profile = new FirefoxProfile();
        try
        {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Setting Firebug preferences
        profile.setPreference("extensions.firebug.currentVersion", "2.0.16");
        profile.setPreference("extensions.firebug.addonBarOpened", true);
        profile.setPreference("extensions.firebug.console.enableSites", true);
        profile.setPreference("extensions.firebug.script.enableSites", true);
        profile.setPreference("extensions.firebug.net.enableSites", true);
        profile.setPreference("extensions.firebug.previousPlacement", 1);
        profile.setPreference("extensions.firebug.allPagesActivation", "on");
        profile.setPreference("extensions.firebug.onByDefault", true);
        profile.setPreference("extensions.firebug.defaultPanelName", "net");

        // Setting netExport preferences
        profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
        profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
        profile.setPreference("extensions.firebug.netexport.Automation", true);
        profile.setPreference("extensions.firebug.netexport.showPreview", false);
        profile.setPreference("extensions.firebug.netexport.defaultLogDir", "C:\\workspace\\CaptureNetworkTraffic");

        /**
         * You can probably get away with setting just a subset of these preferences but I have not gone into doing that yet.
        
        Launch Firefox with the desired capabilites
         */
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        WebDriver driver = new FirefoxDriver(capabilities);

        /**
         * Capture traffic
         */
        try
        {
            Thread.sleep(10000);
            driver.get("http://www.google.com");
            Thread.sleep(10000);
        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        driver.quit();

        /**
         * This will dump traffic into a ‘.har’ extension file at the location you have specified in the ‘extensions.firebug.netexport.defaultLogDir’ firefox preference above. The file reads similar to a json file and jackson even includes harlib which can be used to read/write har files.
         */
    }

}
