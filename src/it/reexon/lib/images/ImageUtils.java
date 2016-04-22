/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.images;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.aspose.imaging.FontStyle;
import com.aspose.imaging.Image;
import com.aspose.imaging.fileformats.metafile.EmfMetafileImage;
import com.aspose.imaging.imageoptions.PngOptions;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ImageUtils
{

    /**
     * Converts an image to another format
     *
     * @param inputImagePath Path of the source image
     * @param outputImagePath Path of the destination image
     * @param formatName the format to be converted to, one of: jpeg, png,
     * bmp, wbmp, and gif
     * @return true if successful, false otherwise
     * @throws IOException if errors occur during writing
     */
    public static boolean convertFormat(String inputImagePath, String outputImagePath, ConvertType formatName) throws IOException
    {
        FileInputStream inputStream = new FileInputStream(inputImagePath);
        FileOutputStream outputStream = new FileOutputStream(outputImagePath);

        // reads input image from file
        BufferedImage inputImage = ImageIO.read(inputStream);

        // writes to the output image in specified format
        boolean result = ImageIO.write(inputImage, formatName.name(), outputStream);

        // needs to close the streams
        outputStream.close();
        inputStream.close();

        return result;
    }

    public static void addWatermark(String sourceFilePath, String destinationFilePath)
    {

        EmfMetafileImage image = (EmfMetafileImage) Image.load(sourceFilePath);
        try
        {
            //Create an instance of Graphics2D by calling EmfMetafileImage.getWatermarkDrawer
            java.awt.Graphics2D drawer = image.getWatermarkDrawer();

            //Creates an instance of Font and initialize it with font name, style and size
            java.awt.Font font = new java.awt.Font("Times New Roman", FontStyle.Bold, 32);

            //Set font to the instance of Graphics2D
            drawer.setFont(font);

            //Set the Paint attribute for the Graphics2D context with an instance of Color
            drawer.setPaint(new java.awt.Color(0, 0, 255, 127));

            //Create an instance of Rectangle2D by getting the string bounds
            java.awt.geom.Rectangle2D rect = font.getStringBounds("This is the custom", drawer.getFontRenderContext());

            //Estimate the X & Y positions where watermark will render on image
            float posx = (float) (image.getWidth() - rect.getWidth()) / 2;
            float posy = (float) (image.getHeight() - rect.getHeight()) / 2;

            //Draw watermark on the image
            drawer.drawString("This is the custom", posx, posy);
            drawer.drawString("watermark string!", posx, posy + 30);

            //Save the result in raster image format
            image.save(destinationFilePath, new PngOptions());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            //Dispose image
            image.dispose();
        }
    }
}
