package it.reexon.lib.images.tests;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.images.ConvertType;
import it.reexon.lib.images.ImageUtils;


public class ImageUtilsTest
{

    @Test
    public final void test()
    {
        try
        {
            final BufferedImage image = it.reexon.lib.network.ImageUtils.imageFormURL("https://www.google.it/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
            String format = "png";
            ImageWriter write = ImageIO.getImageWritersBySuffix(format).next();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageOutputStream imageos = ImageIO.createImageOutputStream(out);
            write.setOutput(imageos);
            write.write(image);
            imageos.flush();
            imageos.close(); // or imageos.flush();

            ImageUtils.convertFormat("C:\\Users\\Marco.Velluto\\Downloads\\HashRateStore_Icon-1.png",
                                     "C:\\Users\\Marco.Velluto\\Downloads\\HashRateStore_Icon-1.gif", ConvertType.gif);
        }
        catch (Exception e)
        {
            Assert.fail(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
