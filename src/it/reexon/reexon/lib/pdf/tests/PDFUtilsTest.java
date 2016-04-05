package it.reexon.reexon.lib.pdf.tests;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.pdf.PDFUtils;


public class PDFUtilsTest
{

    @Test(timeout = 100000)
    public final void createImages()
    {
        try
        {
            PDFUtils.createImages(new File("C:\\temp\\4.pdf"), new File("C:\\temp\\output"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
