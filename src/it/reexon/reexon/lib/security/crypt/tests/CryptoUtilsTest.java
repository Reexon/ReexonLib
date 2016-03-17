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
     * @see {@http://www.codejava.net/coding/file-encryption-and-decryption-simple-example}
     */
    @Test
    public final void cryptoTest()
    {
        File inputFile = new File("resources\\tests\\document.txt");
        File encryptedFile = new File("resources\\tests\\document.encrypted");
        File decryptedFile = new File("resources\\tests\\document.decrypted");

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
}
