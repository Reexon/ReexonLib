package it.reexon.lib.images.tests;

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
