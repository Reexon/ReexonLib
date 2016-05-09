/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.pdf;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class PdfUtilsTest
{
    private static final Logger logger = LogManager.getLogger(PdfUtilsTest.class);

    private static final String PASSWORD = "Passw0rD!";

    private static final String SRC_ENCRYPT = "resources\\tests\\pdfUtilsTest\\pdf.pdf";
    private static final String DST_ENCRYPT = "resources\\tests\\pdfUtilsTest\\pdf_crypt.pdf";

    private static final String SRC_DECRYPT = "resources\\tests\\pdfUtilsTest\\pdf_1_crypt.pdf";
    private static final String DST_DECRYPT = "resources\\tests\\pdfUtilsTest\\pdf_1.pdf";

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        PDFUtils.encryptPdf(SRC_ENCRYPT, SRC_DECRYPT, PASSWORD);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        FileUtils.forceDelete(new File(DST_ENCRYPT));
        //        FileUtils.forceDelete(new File(DST_DECRYPT));
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception
    {}

    /**
     * Test method for {@link it.reexon.lib.pdf.PDFUtils#createImages(java.io.File, java.io.File)}.
     */
    @Test
    public final void testCreateImages()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.pdf.PDFUtils#encryptPdf(String, String, String)}.
     * @throws Exception 
     */
    @Test
    public final void testEncryptPdf() throws Exception
    {
        try
        {
            PDFUtils.encryptPdf(SRC_ENCRYPT, DST_ENCRYPT, PASSWORD);
            boolean isEncrypted = PDFUtils.isEncrypted(DST_ENCRYPT);
            Assert.assertTrue(isEncrypted);

            try
            {
                PDFUtils.encryptPdf(null, DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(SRC_ENCRYPT, null, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(null, null, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf("", DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(" ", DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(SRC_ENCRYPT, DST_ENCRYPT, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(SRC_ENCRYPT, DST_ENCRYPT, "");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.encryptPdf(SRC_ENCRYPT, DST_ENCRYPT, " ");
                isEncrypted = PDFUtils.isEncrypted(DST_ENCRYPT);
                Assert.assertTrue(isEncrypted);
            }
            catch (Exception e)
            {
                logger.error(e);
                Assert.fail("Should havent thrown an exception");
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.pdf.PDFUtils#decrypt(String, String, String)}.
     * @throws Exception 
     */
    @Test
    public final void testDecryptPdf() throws Exception
    {
        try
        {
            PDFUtils.decrypt(SRC_DECRYPT, DST_DECRYPT, PASSWORD);
            boolean isEncrypted = PDFUtils.isEncrypted(DST_DECRYPT);
            Assert.assertFalse(isEncrypted);
            try
            {
                PDFUtils.decrypt(null, DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(SRC_ENCRYPT, null, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(null, null, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt("", DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(" ", DST_ENCRYPT, PASSWORD);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(SRC_ENCRYPT, DST_ENCRYPT, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(SRC_ENCRYPT, DST_ENCRYPT, "");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.decrypt(SRC_ENCRYPT, DST_ENCRYPT, " ");
            }
            catch (Exception e)
            {
                logger.error(e);
                Assert.fail("Should havent thrown an exception");
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Test method for {@link it.reexon.lib.pdf.PDFUtils#isEncrypted(String)}.
     * @throws Exception 
     */
    @Test
    public final void testIsEncrypted() throws Exception
    {
        try
        {
            PDFUtils.encryptPdf(SRC_ENCRYPT, DST_ENCRYPT, PASSWORD);
            boolean isEncrypted = PDFUtils.isEncrypted(DST_ENCRYPT);
            Assert.assertTrue(isEncrypted);

            isEncrypted = PDFUtils.isEncrypted(SRC_ENCRYPT);
            Assert.assertFalse(isEncrypted);

            try
            {
                PDFUtils.isEncrypted("");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.isEncrypted(" ");
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                PDFUtils.isEncrypted(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw e;
        }
    }
}
