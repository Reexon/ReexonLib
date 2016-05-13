/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;


/**
* @author Marco Velluto
* @since Java 1.8
*/
public class NetworkUtils
{
    /**
     * Fetch input resource stream form url
     * @param urlStr string url
     * @return InputStream from downloadFile
     * @throws IOException              If no protocol is specified, or an unknown protocol is found, or spec is null.<br>
     *                                  If an I/O exception occurs.
     * @throws IllegalArgumentException If urlStr is blank or null 
     */
    public static InputStream inputStreamFromURL(String urlStr) throws IOException
    {
        if (StringUtils.isBlank(urlStr))
            throw new IllegalArgumentException("urlStr cannot be null");

        URL url = new URL(urlStr);
        InputStream in = new BufferedInputStream(url.openStream());
        return in;
    }

    public static void fileFromURL(String url, File destFile)
    {
        try (InputStream inputStream = inputStreamFromURL(url); OutputStream outputStream = new FileOutputStream(destFile))
        {
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1)
            {
                outputStream.write(bytes, 0, read);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Fetch byte[] from resource from url
     * 
     * @param urlStr string url
     * @return byte[] from url
     * 
     * @throws IOException              If no protocol is specified, or an unknown protocol is found, or spec is null. - if an I/O exception occurs.<br>
     *                                  If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.                   
     * @throws IllegalArgumentException If urlStr is blank or null 
     */
    public static byte[] byteFromURL(String urlStr) throws IOException
    {
        if (StringUtils.isBlank(urlStr))
            throw new IllegalArgumentException("urlStr cannot be null");

        byte[] buf = new byte[1024];
        byte[] response;
        int n = 0;

        try (InputStream in = inputStreamFromURL(urlStr); ByteArrayOutputStream out = new ByteArrayOutputStream();)
        {
            while (-1 != (n = in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            response = out.toByteArray();
        }
        return response;
    }
}
