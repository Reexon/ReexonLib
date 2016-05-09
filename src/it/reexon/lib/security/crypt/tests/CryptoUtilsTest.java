package it.reexon.lib.security.crypt.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.security.crypt.CryptoUtils;


public class CryptoUtilsTest
{
    private static final String myPassword = "MyPa22w0rD";

    /**
     * @exception java.security.InvalidKeyException: Illegal key size or default parameters
     * @see {@http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html}
     * Install the file in ${java.home}/jre/lib/security/
     */
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
            System.err.println(ex.getMessage());
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

    public final void cryptoSaltFileTest()
    {
        try
        {
            Key key = CryptoUtils.getSecretKey(myPassword);
            new File("test").mkdirs();
            File fileToEncrypt = new File("test/file.txt");
            File fileEncrypted = new File("test/file.crypt");
            IOUtils.write("Ciao".getBytes(), new FileOutputStream(fileToEncrypt));
            CryptoUtils.encrypt(key, fileToEncrypt, fileEncrypted);

            Key key2 = CryptoUtils.getSecretKey(myPassword);
            File fileToDecrypt = new File("test/file.crypt");
            File fileEncecrypted = new File("test/file233.txt");
            CryptoUtils.decrypt(key2, fileToDecrypt, fileEncecrypted);
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

    /**
     * Test of method {@link it.reexon.lib.security.crypt.CryptoUtils#getSecretKey(String)}
     */
    @Test
    public final void getSecretKeyTest()
    {
        try
        {
            String key = "PaSSw0rD!";
            if (null == key || key.length() == 0)
            {
                throw new NullPointerException("key can't be null");
            }
            SecretKeySpec secretKeySpec = null;
            try
            {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256, new SecureRandom(key.getBytes()));
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
                System.out.println(it.reexon.lib.strings.StringUtils.toHexString(secretKey.getEncoded()));
            }
            catch (NoSuchAlgorithmException e)
            {
                throw new NoSuchAlgorithmException();
            }

        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
