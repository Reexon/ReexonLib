package it.reexon.reexon.lib.files.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.files.utils.FileUtils;
import it.reexon.reexon.lib.files.utils.ZipUtils;


public class ZipUtilsTest
{
    private static final File outputZip = new File("C:\\TEMP.zip");

    @Test(timeout = 10000)
    public final void createZipOfDirectoryTest()
    {
        try
        {
            File file = new File("C:\\TEMP\\test\\c.txt");
            try (OutputStream o = new FileOutputStream(file);)
            {
                IOUtils.write(FileUtils.getByteFromFile(file), o);
            }
            ZipUtils.createZipFileOfDirectory(new File("C:\\TEMP\\test"), outputZip);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(timeout = 10000)
    public final void createZipOfDirectorySoruceDirZipOutLevelTest()
    {}

    @Test(timeout = 10000)
    public final void createZipOutFiles()
    {}

    @Test(timeout = 10000)
    public final void createZipOutFile()
    {}

    @Test(timeout = 10000)
    public final void createZipZoutFile()
    {}

    @Test(timeout = 10000)
    public final void unzip()
    {
        try (final FileInputStream in = new FileInputStream(outputZip))
        {
            ZipUtils.unzip(in, new File("C:\\TEMP\\output"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
