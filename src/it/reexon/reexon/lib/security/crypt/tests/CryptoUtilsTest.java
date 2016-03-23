package it.reexon.reexon.lib.security.crypt.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.crypt.CryptoUtils;


public class CryptoUtilsTest
{
    

    /**
     * @exception java.security.InvalidKeyException: Illegal key size or default parameters
     * @see {@http://www.codejava.net/coding/file-encryption-and-decryption-simple-example}
     */
    @Test
    public final void cryptoTextTest()
    {
        File inputFile = new File("resources\\tests\\CryptoUtilsTest\\document.txt");
        File encryptedFile = new File("resources\\tests\\CryptoUtilsTest\\document.encrypted");
        File decryptedFile = new File("resources\\tests\\CryptoUtilsTest\\document.decrypted.txt");

        try
        {
            Key key = CryptoUtils.getSecretKey();
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            Assert.fail();
        }

        try (FileReader inputFileReader = new FileReader(inputFile);
                FileReader encryptedFileReader = new FileReader(encryptedFile);
                FileReader decryptedFileReader = new FileReader(decryptedFile);
                BufferedReader inputFileBufferReader = new BufferedReader(inputFileReader);
                BufferedReader encryptedFileBufferReader = new BufferedReader(encryptedFileReader);
                BufferedReader decryptedFileBufferReader = new BufferedReader(decryptedFileReader);)
        {
            String inputFileLine = inputFileBufferReader.readLine();
            inputFileLine.trim();

            String encryptedFileLine = encryptedFileBufferReader.readLine();
            encryptedFileLine.trim();

            String decryptedFileLine = decryptedFileBufferReader.readLine();
            decryptedFileLine.trim();

            Assert.assertEquals(inputFileLine, decryptedFileLine);
            Assert.assertNotEquals(inputFileLine, encryptedFileLine);
            Assert.assertNotEquals(decryptedFileLine, encryptedFileLine);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            Assert.fail();
        }
    }

    public final void cryptoPDFTest()
    {
        File inputFile = new File("resources\\tests\\CryptoUtilsTest\\documentPdf.pdf");
        File encryptedFile = new File("resources\\tests\\CryptoUtilsTest\\documentPdf.encrypted");
        File decryptedFile = new File("resources\\tests\\CryptoUtilsTest\\documentPdf.decrypted.pdf");

        try
        {
            Key key = CryptoUtils.getSecretKey();
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
            Assert.assertTrue(true);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            Assert.fail();
        }
    }
}
