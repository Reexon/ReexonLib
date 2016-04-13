package it.reexon.reexon.lib.files.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.reexon.lib.files.IOUtils;


public class IOUtilsTest
{
    private static final String DIRECTORY_TEMP = "temp";
    private static final File DIRECTORY = new File(DIRECTORY_TEMP);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        if (!DIRECTORY.exists())
            DIRECTORY.mkdirs();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        FileUtils.forceDeleteOnExit(DIRECTORY);
    }

    @Before
    public void setUp() throws Exception
    {}

    @After
    public void tearDown() throws Exception
    {}

    @Test
    public final void testImportFile()
    {
        try
        {
            File.createTempFile("file1", ".txt", DIRECTORY);
            File.createTempFile("file2", ".txt", DIRECTORY);
            File.createTempFile("file3", ".txt", DIRECTORY);
            File.createTempFile("file4", ".txt", DIRECTORY);

            Collection<File> files = IOUtils.importFiles(DIRECTORY);

            assertNotNull(files);
            assertEquals(4, files.size());
        }
        catch (Exception e)
        {
            System.err.println("Errore in testCheckFiles " + e.getMessage());
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testMoveFile()
    {
        try
        {
            File fileToMove = File.createTempFile("fileMV1", ".txt", DIRECTORY);
            IOUtils.moveFile(fileToMove, DIRECTORY_TEMP + "/testMove");
        }
        catch (Exception e)
        {
            System.err.println("Errore in testMoveFile " + e.getMessage());
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    @Test
    public final void testWriteLines()
    {}

    @Test
    public final void testCopyInputStreamOnFile()
    {}

}
