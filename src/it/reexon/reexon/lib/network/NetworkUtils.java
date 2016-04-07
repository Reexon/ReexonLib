package it.reexon.reexon.lib.network;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NetworkUtils
{
    public static final Document documentFormUrl(String url) throws IOException
    {
        return Jsoup.connect(url).userAgent("Chrome").get();
    }

    public static final Elements linkFromUrl(String url) throws IOException
    {
        return documentFormUrl(url).select("a[href]");
    }

    public static final Elements mediaFromUrl(String url) throws IOException
    {
        return documentFormUrl(url).select("[src]");
    }

    public static final Elements importsFromUrl(String url) throws IOException
    {
        return documentFormUrl(url).select("link[href]");
    }

    public static final Elements imagesFromUrl(String url) throws IOException
    {
        Document doc = documentFormUrl(url);
        return doc.getElementsByTag("img");
    }

    /**
     * Saves all the images found in the past link
     * 
     * @param source link from qualse download all images
     * @param prefixFileName path and prefix of all files
    
     * @throws IOException
     */
    public static void images(String source, String prefixFileName) throws IOException
    {
        Elements elements = imagesFromUrl(source);
        int i = 0;
        for (Element element : elements)
        {
            try
            {
                String urlStr = element.getElementsByTag("img").attr("src");
                String extension = StringUtils.substringAfterLast(urlStr, ".");
                if (StringUtils.isBlank(extension) || extension.length() > 4)
                    extension = ".jpg";
                URL url = new URL(urlStr);
                File file = new File(prefixFileName + "_" + i + extension);
                writeResource(url, file);
                i++;
            }
            catch (@SuppressWarnings("unused") MalformedURLException e)
            {}
        }
    }

    /**
     * Writes the link resource file
     * 
     * @param source link of resource (ex: https://risehighershinebrighter.files.wordpress.com/2014/11/magic-of-blue-universe-images.jpg) 
     * @param destination file destination
     * 
     * @throws IOException
     */
    public static final void writeResource(URL source, File destination) throws IOException
    {
        FileUtils.copyURLToFile(source, destination);
    }

    /**
     * Writes the link resource file
     * 
     * @param sourcesource link of resource (ex: https://risehighershinebrighter.files.wordpress.com/2014/11/magic-of-blue-universe-images.jpg)
     * @param destination file destination
     * @param connectionTimeout millisecond timeout connection
     * @param readTimeout millisecond timeout read resource 
     * 
     * @throws IOException
     */
    public static final void writeResource(URL source, File destination, int connectionTimeout, int readTimeout) throws IOException
    {
        FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
    }
}
