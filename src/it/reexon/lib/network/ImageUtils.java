/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ImageUtils
{
    /**
     * Download a image from url
     * 
     * @param urlStr string url
     * @return image downloaded
     * @throws IOException - if no protocol is specified, or an unknown protocol is found, or spec is null.
     *                     - if an error occurs during reading.
     */
    public static BufferedImage imageFormURL(String urlStr) throws IOException
    {
        URL url = new URL(urlStr);
        return ImageIO.read(url);
    }
}
