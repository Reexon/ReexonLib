/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.images;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.spi.ImageWriterSpi;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;

import com.aspose.imaging.FontStyle;
import com.aspose.imaging.Image;
import com.aspose.imaging.fileformats.metafile.EmfMetafileImage;
import com.aspose.imaging.imageoptions.PngOptions;
import com.sun.imageio.plugins.common.I18N;

import it.reexon.lib.Constants;
import it.reexon.lib.files.FileUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ImageUtils
{
    private static final int BYTE = 262144;
    private static final int MAXWIDTH = 2000;

    /**
     * Converts an image to another format
     *
     * @param inputImagePath Path of the source image
     * @param outputImagePath Path of the destination image
     * @param formatName the format to be converted to, one of: 
     * <br>jpeg, 
     * <br>png,
     * <br>bmp, 
     * <br>wbmp,
     * <br>gif.
     * 
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

    /**
     * Utility method to write BufferedImage object to disk
     * 
     * @param image - BufferedImage object to save.
     * @param data - relative path to the image
     * @param format - file prefix of the image
     * @return BufferedImage representation of the image
     * @author A. Weinberger 
     */
    public static void imageToBitmap(BufferedImage image, String data, String format) throws IOException
    {
        final OutputStream inb = new FileOutputStream(data);
        final ImageWriter wrt = ImageIO.getImageWritersByFormatName(format).next();
        final ImageInputStream imageInput = ImageIO.createImageOutputStream(inb);
        wrt.setOutput(imageInput);
        wrt.write(image);
        inb.close();
    }

    /**
     * Scales image to 256kb
     *
     * @param img Imagepath
     * @throws IOException - if an error occurs during reading.
     * @author A. Weinberger 
     */
    public static void scaleImageTo256kb(Path img) throws IOException
    {
        File imgFile = img.toFile();
        if (imgFile.length() <= BYTE)
        {
            return;
        }

        File newFile = Constants.PROGRAM_TMP_PATH.resolve(img.getFileName()).toFile();

        BufferedImage i = ImageIO.read(imgFile);
        if (i.getWidth() > MAXWIDTH)
        {
            BufferedImage j = new BufferedImage(MAXWIDTH, (int) (i.getHeight() * ((float) MAXWIDTH / i.getWidth())), i.getType());
            Graphics2D g2 = (Graphics2D) j.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.drawImage(i, 0, 0, j.getWidth(), j.getHeight(), null);
            ImageIO.write(j, "jpg", imgFile);
        }

        float quality = 1f;
        float eps = 0.2f;
        int c = 0;

        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = iter.next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        FileImageOutputStream output;
        IIOImage image;

        do
        {
            c++;
            FileUtils.deleteFile(newFile.toPath());
            iwp.setCompressionQuality(quality);
            output = new FileImageOutputStream(newFile);
            writer.setOutput(output);
            image = new IIOImage(ImageIO.read(imgFile), null, null);
            writer.write(null, image, iwp);
            writer.reset();
            output.flush();
            output.close();
            quality -= eps / c;

        }
        while ((newFile.length() > BYTE) && (quality > 0.1));

        FileUtils.deleteFile(img);
        FileUtils.moveFile(newFile.toPath(), img);
    }

    /** Checks that the provided <code>ImageWriter</code> can encode
     * the provided <code>ImageTypeSpecifier</code> or not.  If not, an
     * <code>IIOException</code> will be thrown.
     * @param writer The provided <code>ImageWriter</code>.
     * @param type The image to be tested.
     * @throws IIOException If the writer cannot encoded the provided image.
     * @author A. Weinberger 
     */
    public static final void canEncodeImage(ImageWriter writer, ImageTypeSpecifier type) throws IIOException
    {
        ImageWriterSpi spi = writer.getOriginatingProvider();

        if (type != null && spi != null && !spi.canEncodeImage(type))
        {
            throw new IIOException(I18N.getString("ImageUtil2") + " " + writer.getClass().getName());
        }
    }

    public static void addWatermark(String sourceFilePath)
    {
        addWatermark(sourceFilePath, sourceFilePath);
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
        finally
        {
            //Dispose image
            image.dispose();
        }
    }
}
