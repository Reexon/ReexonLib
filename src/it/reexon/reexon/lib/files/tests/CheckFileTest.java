package it.reexon.reexon.lib.files.tests;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.files.CheckFile;


public class CheckFileTest
{

    @Test
    public final void testCheckFiles()
    {
        System.out.println("getChecksumWithAlgoritmsTest");
        try
        {
            Assert.assertTrue(CheckFile.checkEqualsFiles("C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng",
                                                   "C:\\Users\\Marco.Velluto\\Downloads\\IMG_5341.dng"));
        }
        catch (IOException e)
        {
            System.err.println("Errore in testCheckFiles " + e.getMessage());
            e.printStackTrace();
            fail();
        }

    }

}
