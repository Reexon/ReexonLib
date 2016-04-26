/**
 * Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.files;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.CRC32;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.IOUtils;


/**
 * @author Marco Velluto.
 * @since Java 1.8
 */
public class FileUtils
{
    public static final String JPEG = "jpeg";
    public static final String JPG = "jpg";
    public static final int BUFFER_SIZE = 4 * 1024;
    public static final boolean CLOCK = true;
    public static final boolean VERIFY = true;
    public static final boolean LOGS = true;

    /**
     * Generate byte[] form file
     * 
     * @param file to be generate byte[]
     * @return byte[] file
     * 
     * @throws IOException
     */
    public static byte[] getByteFromFile(File file) throws IOException
    {
        try (ByteArrayOutputStream ous = new ByteArrayOutputStream(); InputStream ios = new FileInputStream(file);)
        {
            byte[] buffer = new byte[4096];

            int read = 0;
            while ((read = ios.read(buffer)) != -1)
            {
                ous.write(buffer, 0, read);
            }
            return ous.toByteArray();
        }
    }

    /**
     * Check the checksum files with algorithm SHA-256
     * 
     * @param firstFile     file orginal
     * @param secondFile    file to check
     * @return - true if files are equals
     *         - null if there was an error         
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Boolean checkEqualFiles(File firstFile, File secondFile) throws FileNotFoundException, IOException
    {
        return CheckFile.checkEqualsFiles(firstFile, secondFile);
    }

    /**
     * Copy inputstream on file
     * 
     * @param file file you want to copy
     * @param inputStream  that you want to copy
     * 
     * @throws IOException
     */
    public static void copyInputStreamOnFile(File file, InputStream inputStream) throws IOException
    {
        try (OutputStream outputStream = new FileOutputStream(file);)
        {
            IOUtils.copy(inputStream, outputStream);
            outputStream.close();
        }
    }

    /**
     * Deletes a file. 
     * 
     * @param file the path to the file 
     * @return boolean 
     * @throws IOException if an I/O error occurs
     * @throws IllegalArgumentException if file is null or not exists
     */
    public static boolean deleteFile(Path file) throws IOException
    {
        if ((file == null) || Files.exists(file, LinkOption.NOFOLLOW_LINKS))
            throw new IllegalArgumentException("file is null or not exists");
        try
        {
            if (file.toFile().canWrite())
            {
                Files.delete(file);
            }
            return !Files.exists(file, LinkOption.NOFOLLOW_LINKS);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw new IOException("An IO exception occured while deleting file '" + file + "' with error:" + e.getLocalizedMessage());
        }
    }

    /**
     * Utility method for copying file 
     *  
     * @param srcFile - source file 
     * @param destFile - destination file 
     * @author A. Weinberger
     * 
     * @throws IOException If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.
     */
    public static void copyFile(File srcFile, File destFile) throws IOException
    {
        //TODO REMOVE
        //        if (!srcFile.getPath().toLowerCase().endsWith(JPG) && !srcFile.getPath().toLowerCase().endsWith(JPEG))
        //        {
        //            return;
        //        }

        try (final InputStream in = new FileInputStream(srcFile); final OutputStream out = new FileOutputStream(destFile);)
        {
            long millis = System.currentTimeMillis();
            CRC32 checksum;
            if (VERIFY)
            {
                checksum = new CRC32();
                checksum.reset();
            }
            final byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = in.read(buffer);
            while (bytesRead >= 0)
            {
                if (VERIFY)
                {
                    checksum.update(buffer, 0, bytesRead);
                }
                out.write(buffer, 0, bytesRead);
                bytesRead = in.read(buffer);
            }
            if (CLOCK)
            {
                millis = System.currentTimeMillis() - millis;
                if (LOGS)
                    System.out.println("Copy file '" + srcFile.getPath() + "' on " + millis / 1000L + " second(s)");
            }
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    /**
     * Utility method for copying directory 
     *  
     * @param srcDir - source directory 
     * @param dstDir - destination directory 
     * 
     * @throws IllegalArgumentException if srcDir is null or is not exists or srcDir is not a directory
     * @throws IOException  If the first byte cannot be read for any reason other than the end of the file, if the input stream has been closed, or if some other I/O error occurs.Author:
     */
    public static void copyDirectory(File srcDir, File dstDir) throws IOException
    {
        if (srcDir == null)
            throw new IllegalArgumentException("SrcDir is null");
        if (!srcDir.exists())
            throw new IllegalArgumentException("SrcDir is not exists");
        if (!srcDir.isDirectory())
            throw new IllegalArgumentException("SrcDir is not a direcory");

        //TODO VERIFICARE
        if (".svn".equals(srcDir.getName()))
        {
            return;
        }

        if (srcDir.isDirectory())
        {
            if (!dstDir.exists())
            {
                dstDir.mkdir();
            }

            for (String aChildren : srcDir.list())
            {
                copyDirectory(new File(srcDir, aChildren), new File(dstDir, aChildren));
            }
        }
        else
        {
            copyFile(srcDir, dstDir);
        }
    }

    /**
     * Utility method for delete directory 
     *  
     * @param dir - directory to delete 
     * @param isInitialDelete - determine if the deleting process running at startup or on destroy of application 
     * @return true if directory succesfully deleted 
     *   
     * @throws IllegalArgumentException if srcDir is null or is not exists or srcDir is not a directory
     */
    public static boolean deleteDirectory(File dir, boolean isInitialDelete)
    {
        if (dir == null)
            throw new IllegalArgumentException("dir is null");
        if (!dir.exists())
            throw new IllegalArgumentException("dir is not exists");
        if (!dir.isDirectory())
            throw new IllegalArgumentException("dir is not a direcory");

        for (File child : dir.listFiles())
        {
            try
            {
                deleteDirectory(child, isInitialDelete);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                if (isInitialDelete)
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
        }
        dir.delete();
        return true;
    }

    /**
     * Utility method for concatenation names of collection of files 
     *  
     * @param files - array of strings to concatenate 
     * @return concatenated string 
     * 
     * @author A. Weinberger
     */
    public static String joinFiles(String... files)
    {
        final StringBuilder res = new StringBuilder();
        for (String file : files)
        {
            res.append(file).append(File.separatorChar);
        }

        return res.substring(0, res.length() - 1);
    }

    /**
     * Utility method to read image from disk and transform image to BufferedImage object 
     *  
     * @param data - relative path to the image 
     * @param format - file prefix of the image 
     * @return BufferedImage representation of the image 
     *  
     */
    public static BufferedImage bitmapToImage(InputStream is, String format) throws IOException
    {
        final ImageReader rdr = ImageIO.getImageReadersByFormatName(format).next();
        final ImageInputStream imageInput = ImageIO.createImageInputStream(is);
        rdr.setInput(imageInput);
        final BufferedImage image = rdr.read(0);
        is.close();
        return image;
    }

    /**
    * Utility method to write BufferedImage object to disk 
    *  
    * @param image - BufferedImage object to save. 
    * @param data - relative path to the image 
    * @param format - file prefix of the image 
    * @return BufferedImage representation of the image 
    *  
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
     * Convenience method that returns a scaled instance of the provided {@code BufferedImage}. 
     *  
     * @param img the original image to be scaled 
     * @param targetWidth the desired width of the scaled instance, in pixels 
     * @param targetHeight the desired height of the scaled instance, in pixels 
     * @param hint one of the rendering hints that corresponds to {@code RenderingHints.KEY_INTERPOLATION} (e.g. 
     *        {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR}, {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR}, 
     *        {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC}) 
     * @param higherQuality if true, this method will use a multi-step scaling technique that provides higher quality than the 
     *        usual one-step technique (only useful in downscaling cases, where {@code targetWidth} or {@code targetHeight} is 
     *        smaller than the original dimensions, and generally only when the {@code BILINEAR} hint is specified) 
     * @return a scaled version of the original {@code BufferedImage} 
     */
    public static BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint, boolean higherQuality)
    {
        final int type = img.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = img;
        int w;
        int h;
        if (higherQuality)
        {
            // Use multi-step technique: start with original size, then 
            // scale down in multiple passes with drawImage() 
            // until the target size is reached 
            w = img.getWidth();
            h = img.getHeight();
        }
        else
        {
            // Use one-step technique: scale directly from original 
            // size to target size with a single drawImage() call 
            w = targetWidth;
            h = targetHeight;
        }

        do
        {
            if (higherQuality && w > targetWidth)
            {
                w /= 2;
                if (w < targetWidth)
                {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight)
            {
                h /= 2;
                if (h < targetHeight)
                {
                    h = targetHeight;
                }
            }

            final BufferedImage tmp = new BufferedImage(w, h, type);
            final Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        }
        while (w != targetWidth || h != targetHeight);

        return ret;
    }

    /**
     * Utility method for creation of directory 
     *  
     * @param directory - directory to create 
     *  
     */
    public static void addDirectory(File directory)
    {
        if (directory.exists())
        {
            deleteDirectory(directory, false);
        }
        directory.mkdirs();
    }

    /**
     * Moves a file to a destination. 
     * 
     * @param file the path to the file to move 
     * @param destination the destination path 
     * @throws IOException  if an I/O error occurs
     * @throws IllegalArgumentException if file is null or not exists, destination is null
     */
    public static void moveFile(Path file, Path destination) throws IOException, IllegalArgumentException
    {
        if ((file == null) || !Files.exists(file, LinkOption.NOFOLLOW_LINKS) || (destination == null))
        {
            throw new IllegalArgumentException("The filepath is null or points to an invalid location! " + file);
        }

        Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}
