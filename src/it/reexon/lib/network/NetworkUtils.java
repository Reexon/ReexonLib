/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


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
     * @throws IOException      - if no protocol is specified, or an unknown protocol is found, or spec is null.
     *                          - if an I/O exception occurs.
     */
    public static InputStream inputStreamFromURL(String urlStr) throws IOException
    {
        URL url = new URL(urlStr);
        InputStream in = new BufferedInputStream(url.openStream());
        return in;
    }

    /**
     * Fetch byte[] from resource from url
     * 
     * @param urlStr string url
     * @return byte[] from url
     * 
     * @throws IOException       - if no protocol is specified, or an unknown protocol is found, or spec is null. - if an I/O exception occurs.
     *                           - if the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.                   
     */
    public static byte[] byteFromURL(String urlStr) throws IOException
    {
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
