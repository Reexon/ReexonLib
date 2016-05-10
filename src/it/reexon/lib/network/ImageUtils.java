/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.network;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;


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
     * @throws IOException              If no protocol is specified, or an unknown protocol is found, or spec is null.
     *                                  If an error occurs during reading.
     * @throws IllegalArgumentException If urlStr is blank or nul
     *
     */
    public static BufferedImage imageFormURL(String urlStr) throws IOException
    {
        if (StringUtils.isBlank(urlStr))
            throw new IllegalArgumentException("urlStr cannot be null");

        URL url = new URL(urlStr);
        return ImageIO.read(url);
    }

    /**
     * Fetch the extension image 
     * 
     * @param urlStr            url image to fetch the extensions
     * @return                  - the extension.
     * @throws IOException              If a cache file is needed but cannot be created
     * @throws IllegalArgumentException If urlStr is blank or nul
     */
    public static String formatName(String urlStr) throws IOException
    {
        if (StringUtils.isBlank(urlStr))
            throw new IllegalArgumentException("urlStr cannot be null");

        URL url = new URL(urlStr);
        try (ImageInputStream iis = ImageIO.createImageInputStream(url.openStream());)
        {
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);

            while (imageReaders.hasNext())
            {
                ImageReader reader = imageReaders.next();
                String formatName = reader.getFormatName();
                System.out.printf("formatName: %s%n", formatName);
                return formatName;
            }
            return null;
        }
    }
}
