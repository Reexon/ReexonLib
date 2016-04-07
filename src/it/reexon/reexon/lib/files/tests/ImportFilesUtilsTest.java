package it.reexon.reexon.lib.files.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import it.reexon.reexon.lib.files.ImportFilesUtils;


public class ImportFilesUtilsTest
{

    @Test()
    public final void test() throws IOException
    {
        File directory = new File("temp");
        if (!directory.exists())
            directory.mkdirs();

        try
        {

            File.createTempFile("file1", ".txt", directory);
            File.createTempFile("file2", ".txt", directory);
            File.createTempFile("file3", ".txt", directory);
            File.createTempFile("file4", ".txt", directory);

            Collection<File> files = ImportFilesUtils.importDocument(directory);

            assertNotNull(files);
            assertEquals(4, files.size());
        }
        catch (Exception e)
        {
            System.err.println("Errore in testCheckFiles " + e.getMessage());
            e.printStackTrace();
            fail();
        }
        finally
        {
            FileUtils.forceDeleteOnExit(directory);
        }
    }

}
