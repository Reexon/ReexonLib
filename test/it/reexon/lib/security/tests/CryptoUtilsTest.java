/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.tests;

import java.io.File;
import java.security.Key;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.reexon.lib.files.FileUtils;
import it.reexon.lib.files.IOUtils;
import it.reexon.lib.list.ListUtils;
import it.reexon.lib.security.CryptoUtils;
import it.reexon.lib.security.SecureSaltUtils;
import it.reexon.lib.security.SecureStringUtils;
import it.reexon.lib.security.algorithmics.CipherAlgorithmsNames;
import it.reexon.lib.security.algorithmics.KeyGeneratorAlgorithms;
import it.reexon.lib.security.exceptions.UnsupportedAlgorithmKeyException;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CryptoUtilsTest
{

    private static final Logger logger = LogManager.getLogger(CryptoUtilsTest.class);
    private static final String DEFAULT_PASSWORD = "PaSswOrd!23";
    private static final Key KEY = CryptoUtils.getSecretKey(DEFAULT_PASSWORD);
    private static final File DIRECTORY = new File("resources/tests/CryptoUtilsTest");

    private static File inputFileDecrypted;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        if (!DIRECTORY.exists())
            DIRECTORY.mkdirs();
        inputFileDecrypted = File.createTempFile("inputFileDecrypted", ".txt", DIRECTORY);
        List<String> linesA = new LinkedList<>(ListUtils.createList("CiaoA, Ciao1", "HELPS"));
        IOUtils.writeLines(inputFileDecrypted, linesA);
    }

    /**
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unused")
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        try
        {
            FileUtils.forceDelete(DIRECTORY);
        }
        catch (Exception e)
        {
            logger.error("Error while deleting " + DIRECTORY.getName());
        }
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
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.io.File, java.io.File, java.security.Key)}.
     */
    @Test
    public final void testEncryptFileFileKey()
    {
        try
        {
            File encrypted0 = File.createTempFile("outEncrypt", ".txt", DIRECTORY);
            File decrypted0 = File.createTempFile("outDecrypt", ".txt", DIRECTORY);
            CryptoUtils.encrypt(inputFileDecrypted, encrypted0, KEY);
            Assert.assertFalse("The files should be differents", FileUtils.checkEqualFiles(inputFileDecrypted, encrypted0));
            CryptoUtils.decrypt(encrypted0, decrypted0, KEY);
            Assert.assertTrue("The files should be equals", FileUtils.checkEqualFiles(inputFileDecrypted, decrypted0));

            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                File encrypted = File.createTempFile("outEncrypt", ".txt", DIRECTORY);
                File decrypted = File.createTempFile("outDecrypt", ".txt", DIRECTORY);
                try
                {
                    if (isAlgorithmKeyNotSupported(algorithm))
                    {
                        try
                        {
                            final Key secureKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                            CryptoUtils.encrypt(inputFileDecrypted, encrypted, secureKey);
                            Assert.fail("Should have thrown an exception");
                        }
                        catch (Exception e)
                        {
                            Assert.assertEquals(UnsupportedAlgorithmKeyException.class, e.getClass());
                        }
                    }
                    else
                    {
                        final Key secureKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                        CryptoUtils.encrypt(inputFileDecrypted, encrypted, secureKey);
                        Assert.assertFalse("The files should be differents", FileUtils.checkEqualFiles(inputFileDecrypted, encrypted));
                        CryptoUtils.decrypt(encrypted, decrypted, secureKey);
                        logger.debug("SUCCESS -- Key algorithm: {}", algorithm);
                        Assert.assertTrue("The files should be equals", FileUtils.checkEqualFiles(inputFileDecrypted, decrypted));
                    }
                }
                catch (Exception e)
                {
                    logger.debug("FAILURE -- Key algorithm: {} - Error:{}", algorithm, e.getMessage());
                }
                finally
                {
                    try
                    {
                        FileUtils.forceDelete(encrypted);
                    }
                    catch (Exception e2)
                    {
                        logger.error("Error while force deleting file {} - Error: ", encrypted != null ? encrypted.getName() : "encrypted",
                                     e2.getMessage());
                    }
                    try
                    {
                        FileUtils.forceDelete(decrypted);
                    }
                    catch (Exception e2)
                    {
                        logger.error("Error while force deleting file {} - Error: ", decrypted != null ? decrypted.getName() : "decrypted",
                                     e2.getMessage());
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testEncryptKeyFileFile", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.io.File, java.io.File, java.security.Key)}.
     */
    @Test
    public final void testDecryptKeyFileFile()
    {
        try
        {

            File encrypted0 = File.createTempFile("outEncrypt", ".txt", DIRECTORY);
            File decrypted0 = File.createTempFile("outDecrypt", ".txt", DIRECTORY);
            CryptoUtils.encrypt(inputFileDecrypted, encrypted0, KEY);
            Assert.assertFalse("The files should be differents", FileUtils.checkEqualFiles(inputFileDecrypted, encrypted0));
            CryptoUtils.decrypt(encrypted0, decrypted0, KEY);
            Assert.assertTrue("The files should be equals", FileUtils.checkEqualFiles(inputFileDecrypted, decrypted0));

            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                File encrypted = File.createTempFile("outEncrypt", ".txt", DIRECTORY);
                File decrypted = File.createTempFile("outDecrypt", ".txt", DIRECTORY);
                try
                {
                    if (isAlgorithmKeyNotSupported(algorithm))
                    {
                        try
                        {
                            final Key secureKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                            CryptoUtils.encrypt(inputFileDecrypted, encrypted, secureKey);
                            Assert.fail("Should have thrown an exception");
                        }
                        catch (Exception e)
                        {
                            Assert.assertEquals(UnsupportedAlgorithmKeyException.class, e.getClass());
                        }
                    }
                    else
                    {
                        final Key secureKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                        CryptoUtils.encrypt(inputFileDecrypted, encrypted, secureKey);
                        Assert.assertFalse("The files should be differents", FileUtils.checkEqualFiles(inputFileDecrypted, encrypted));
                        CryptoUtils.decrypt(encrypted, decrypted, secureKey);
                        logger.debug("SUCCESS -- Key algorithm: {}", algorithm);
                        Assert.assertTrue("The files should be equals", FileUtils.checkEqualFiles(inputFileDecrypted, decrypted));
                    }
                }
                catch (Exception e)
                {
                    logger.debug("FAILURE -- Key algorithm: {} - Error:{}", algorithm, e.getMessage());
                }
                finally
                {
                    try
                    {
                        FileUtils.forceDelete(encrypted);
                    }
                    catch (Exception e2)
                    {
                        logger.error("Error while force deleting file {} - Error: ", encrypted != null ? encrypted.getName() : "encrypted",
                                     e2.getMessage());
                    }
                    try
                    {
                        FileUtils.forceDelete(decrypted);
                    }
                    catch (Exception e2)
                    {
                        logger.error("Error while force deleting file {} - Error: ", decrypted != null ? decrypted.getName() : "decrypted",
                                     e2.getMessage());
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testDecryptKeyFileFile", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.security.Key, java.io.File, java.io.File, java.lang.String)}.
     */
    @Test
    public final void testEncryptKeyFileFileString()
    {
        // TODO: Deprecated Method
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.security.Key, java.io.File, java.io.File, java.lang.String)}.
     */
    @Test
    public final void testDecryptKeyFileFileString()
    {
        // TODO: Deprecated Method
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.lang.String, java.security.Key)}.
     * {@link http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters}
     */
    @Test
    public final void testEncryptStringKey()
    {
        try
        {
            final String stringToCrypr = "Hello World!";
            final Key key = CryptoUtils.getSecretKey(DEFAULT_PASSWORD);

            logger.debug("String to crypt: {}", stringToCrypr);
            String cryptedString0 = CryptoUtils.encrypt(stringToCrypr, key);
            logger.debug("String crypted: {}", cryptedString0);
            Assert.assertNotEquals(stringToCrypr, cryptedString0);

            String cryptedString1 = CryptoUtils.encrypt(stringToCrypr, key);
            logger.debug("String crypted: {}", cryptedString1);
            Assert.assertNotEquals(stringToCrypr, cryptedString1);
            Assert.assertEquals(cryptedString1, cryptedString0);

            String decryptedString = CryptoUtils.decrypt(cryptedString0, key);
            logger.debug("stringToCrypt: {} - String decrypted: {}", stringToCrypr, decryptedString);
            Assert.assertEquals(stringToCrypr, decryptedString);

            CryptoUtils.decrypt(new String(""), key);
            CryptoUtils.decrypt(new String(" "), key);
            try
            {
                CryptoUtils.decrypt(null, key);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(stringToCrypr, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(null, key);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testEncryptStringKey");
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.lang.String, java.security.Key, java.lang.String)}.
     */
    @Test
    public final void testEncryptStringKeyString()
    {
        try
        {
            final String planeText = new String("Hello World !!");
            List<String> cipherAlgorithmsNames = CipherAlgorithmsNames.values();
            List<String> keyGeneratorAlgorithms = KeyGeneratorAlgorithms.getAlgorithms();

            String encryptA = CryptoUtils.encrypt(planeText, KEY, CipherAlgorithmsNames.AES);
            String encryptB = CryptoUtils.encrypt(planeText, KEY, CipherAlgorithmsNames.AES);
            Assert.assertEquals(encryptA, encryptB);

            String cipherAlgorithm0 = CipherAlgorithmsNames.AES;
            String keyGenerator0 = KeyGeneratorAlgorithms.AES;
            logger.info("Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm0, keyGenerator0);
            Key secretKey0 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm0);
            String encrypted0 = CryptoUtils.encrypt(planeText, secretKey0, keyGenerator0);
            Assert.assertNotEquals(encrypted0, planeText);
            String decrypted0 = CryptoUtils.decrypt(encrypted0, secretKey0, cipherAlgorithm0);
            Assert.assertEquals(planeText, decrypted0);

            cipherAlgorithm0 = CipherAlgorithmsNames.ARCFOUR;
            keyGenerator0 = KeyGeneratorAlgorithms.ARCFOUR;
            logger.info("Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm0, keyGenerator0);
            secretKey0 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm0);
            encrypted0 = CryptoUtils.encrypt(planeText, secretKey0, keyGenerator0);
            Assert.assertNotEquals(encrypted0, planeText);
            decrypted0 = CryptoUtils.decrypt(encrypted0, secretKey0, cipherAlgorithm0);
            Assert.assertEquals(planeText, decrypted0);

            cipherAlgorithm0 = CipherAlgorithmsNames.Blowfish;
            keyGenerator0 = KeyGeneratorAlgorithms.Blowfish;
            logger.info("Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm0, keyGenerator0);
            secretKey0 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm0);
            encrypted0 = CryptoUtils.encrypt(planeText, secretKey0, keyGenerator0);
            Assert.assertNotEquals(encrypted0, planeText);
            decrypted0 = CryptoUtils.decrypt(encrypted0, secretKey0, cipherAlgorithm0);
            Assert.assertEquals(planeText, decrypted0);

            cipherAlgorithm0 = CipherAlgorithmsNames.RC2;
            keyGenerator0 = KeyGeneratorAlgorithms.RC2;
            logger.info("Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm0, keyGenerator0);
            secretKey0 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm0);
            encrypted0 = CryptoUtils.encrypt(planeText, secretKey0, keyGenerator0);
            Assert.assertNotEquals(encrypted0, planeText);
            decrypted0 = CryptoUtils.decrypt(encrypted0, secretKey0, cipherAlgorithm0);
            Assert.assertEquals(planeText, decrypted0);

            cipherAlgorithm0 = CipherAlgorithmsNames.RC4;
            keyGenerator0 = KeyGeneratorAlgorithms.ARCFOUR;
            logger.info("Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm0, keyGenerator0);
            secretKey0 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm0);
            encrypted0 = CryptoUtils.encrypt(planeText, secretKey0, keyGenerator0);
            Assert.assertNotEquals(encrypted0, planeText);
            decrypted0 = CryptoUtils.decrypt(encrypted0, secretKey0, cipherAlgorithm0);
            Assert.assertEquals(planeText, decrypted0);

            CryptoUtils.encrypt(new String(""), secretKey0, keyGenerator0);
            CryptoUtils.encrypt(new String(" "), secretKey0, keyGenerator0);

            try
            {
                CryptoUtils.encrypt(null, secretKey0, keyGenerator0);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.encrypt(planeText, null, keyGenerator0);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.encrypt(planeText, secretKey0, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.encrypt(null, null, keyGenerator0);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.encrypt(null, secretKey0, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.encrypt(planeText, null, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }

            for (String cipherAlgorithm : cipherAlgorithmsNames)
            {
                for (String keyGenerator : keyGeneratorAlgorithms)
                {
                    try
                    {
                        final Key secretKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, cipherAlgorithm);
                        final String crypted1 = CryptoUtils.encrypt(planeText, secretKey, keyGenerator);
                        logger.debug("SUCCESS - Cipher Algorithm: {} - Key Algorithm: {}", cipherAlgorithm, keyGenerator);
                        Assert.assertNotEquals(crypted1, planeText);
                    }
                    catch (Exception e)
                    {
                        logger.debug("FAILURE - Cipher Algorithm: {} - Key Algorithm: {} - Error: {}", cipherAlgorithm, keyGenerator, e.getMessage());
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testEncryptStringKeyString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.lang.String, java.security.Key)}.
     */
    @Test
    public final void testDecryptStringKey()
    {
        try
        {
            final String planeText = new String("Hello World !!");

            String encrypt = CryptoUtils.encrypt(planeText, KEY);
            String decrypt = CryptoUtils.decrypt(encrypt, KEY);
            Assert.assertEquals(planeText, decrypt);

            try
            {
                CryptoUtils.decrypt(null, KEY);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(encrypt, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                logger.debug("Key Algorithm: {}", algorithm);
                if (isAlgorithmKeyNotSupported(algorithm))
                {
                    try
                    {
                        final Key key = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                        encrypt = CryptoUtils.encrypt(planeText, KEY);
                        decrypt = CryptoUtils.decrypt(encrypt, key);
                        Assert.fail("Should have thrown an exception");
                    }
                    catch (Exception e)
                    {
                        Assert.assertEquals(UnsupportedAlgorithmKeyException.class, e.getClass());
                    }
                }
                else
                {
                    final Key key = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                    encrypt = CryptoUtils.encrypt(planeText, key);
                    decrypt = CryptoUtils.decrypt(encrypt, key);
                    Assert.assertEquals(planeText, decrypt);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testDecryptStringKey", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.lang.String, java.security.Key, java.lang.String)}.
     */
    @Test
    public final void testDecryptStringKeyString()
    {
        try
        {
            final String planeText = new String("Hello World !!");

            String encrypt = CryptoUtils.encrypt(planeText, KEY, CipherAlgorithmsNames.AES);
            String decrypt = CryptoUtils.decrypt(encrypt, KEY, CipherAlgorithmsNames.AES);
            Assert.assertEquals(planeText, decrypt);

            try
            {
                CryptoUtils.decrypt(null, KEY, CipherAlgorithmsNames.AES);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(encrypt, null, CipherAlgorithmsNames.AES);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(encrypt, KEY, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(null, null, CipherAlgorithmsNames.AES);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.decrypt(null, KEY, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            for (String cipherAlgorithmsNames : CipherAlgorithmsNames.values())
            {
                for (String keyAlgorithm : KeyGeneratorAlgorithms.getAlgorithms())
                {
                    logger.debug("Key Algorithm: {} - Crypt Algorithm: {}", keyAlgorithm, cipherAlgorithmsNames);
                    CryptoUtils.getSecretKey(DEFAULT_PASSWORD, keyAlgorithm);
                    encrypt = CryptoUtils.encrypt(planeText, KEY, CipherAlgorithmsNames.AES);
                    decrypt = CryptoUtils.decrypt(encrypt, KEY, CipherAlgorithmsNames.AES);
                    Assert.assertEquals(planeText, decrypt);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testDecryptStringKeyString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#getSecretKey(java.lang.String)}.
     */
    @Test
    public final void testGetSecretKeyString()
    {
        try
        {
            Key secretKey1 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD);
            Key secretKey2 = CryptoUtils.getSecretKey(DEFAULT_PASSWORD);
            Assert.assertEquals(secretKey1, secretKey2);

            for (int i = 1; i < 2048; i++)
                CryptoUtils.getSecretKey(SecureStringUtils.secureString(i));

            CryptoUtils.getSecretKey("");
            CryptoUtils.getSecretKey(" ");

            try
            {
                CryptoUtils.getSecretKey(null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSecretKeyString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#getSecretKey(java.lang.String, java.lang.String)}.
     */
    @Test
    public final void testGetSecretKeyStringString()
    {
        try
        {
            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                String passwordToHash = DEFAULT_PASSWORD;

                logger.debug("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length(), passwordToHash);
                Key secretKey1 = CryptoUtils.getSecretKey(passwordToHash, algorithm);
                Key secretKey2 = CryptoUtils.getSecretKey(passwordToHash, algorithm);

                logger.trace("Secret Key 1: {}", secretKey1);
                logger.trace("Secret Key 2: {}", secretKey2);
                Assert.assertEquals(secretKey1, secretKey2);

                for (int i = 1; i < 1024; i++)
                {
                    passwordToHash = RandomStringUtils.random(i, true, true);
                    logger.trace("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length(), passwordToHash);
                    CryptoUtils.getSecretKey(passwordToHash, algorithm);

                }

                CryptoUtils.getSecretKey("", algorithm);
                CryptoUtils.getSecretKey(" ", algorithm);

                try
                {
                    CryptoUtils.getSecretKey(null, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    CryptoUtils.getSecretKey(RandomStringUtils.random(64), null);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSecretKeyString", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#getSecretKey(char[], byte[])}.
     */
    @Test
    public final void testGetSecretKeyCharArrayByteArray()
    {
        try
        {
            char[] password = DEFAULT_PASSWORD.toCharArray();
            byte[] salt = SecureSaltUtils.getSalt();
            SecretKey key1 = CryptoUtils.getSecretKey(password, salt);
            SecretKey key2 = CryptoUtils.getSecretKey(password, salt);
            Assert.assertEquals(key1, key2);

            salt = SecureSaltUtils.getSalt();
            logger.debug("password({}): {}", password.length, password);
            logger.debug("salt({}): {}", salt.length, salt);

            key1 = CryptoUtils.getSecretKey(password, salt);
            key2 = CryptoUtils.getSecretKey(password, salt);
            Assert.assertEquals(key1, key2);

            password = RandomStringUtils.random(64, true, true).toCharArray();
            salt = SecureSaltUtils.getSalt();
            SecretKey key0 = CryptoUtils.getSecretKey(password, salt);
            Assert.assertNotEquals(key0, key2);

            try
            {
                CryptoUtils.getSecretKey(password, null);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.getSecretKey(null, salt);
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
            try
            {
                CryptoUtils.getSecretKey(password, new String().getBytes());
                Assert.fail("Should have thrown an exception");
            }
            catch (Exception e)
            {
                Assert.assertEquals(IllegalArgumentException.class, e.getClass());
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSecretKeyCharArrayByteArray", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#getSecretKey(char[], byte[], java.lang.String)}.
     */
    @Test
    public final void testGetSecretKeyCharArrayByteArrayString()
    {
        try
        {
            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                char[] passwordToHash = DEFAULT_PASSWORD.toCharArray();
                byte[] salt = SecureSaltUtils.getSalt();

                logger.debug("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length, passwordToHash);
                Key secretKey1 = CryptoUtils.getSecretKey(passwordToHash, salt, algorithm);
                Key secretKey2 = CryptoUtils.getSecretKey(passwordToHash, salt, algorithm);
                Assert.assertEquals(secretKey1, secretKey2);

                logger.debug("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length, passwordToHash);
                Key secretKey0 = CryptoUtils.getSecretKey(passwordToHash, SecureSaltUtils.getSalt(), algorithm);
                Assert.assertNotEquals(secretKey0, secretKey1);

                CryptoUtils.getSecretKey("".toCharArray(), salt, algorithm);
                CryptoUtils.getSecretKey(" ".toCharArray(), salt, algorithm);

                try
                {
                    CryptoUtils.getSecretKey(null, salt, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    CryptoUtils.getSecretKey(null, null, algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    CryptoUtils.getSecretKey("".toCharArray(), new String().getBytes(), algorithm);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    CryptoUtils.getSecretKey("".toCharArray(), salt, "");
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
                try
                {
                    CryptoUtils.getSecretKey("".toCharArray(), salt, null);
                    Assert.fail("Should have thrown an exception");
                }
                catch (Exception e)
                {
                    Assert.assertEquals(IllegalArgumentException.class, e.getClass());
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testGetSecretKeyCharArrayByteArrayString", e);
            throw new RuntimeException(e);
        }
    }

    private boolean isAlgorithmKeyNotSupported(String algorithm)
    {
        return algorithm.equalsIgnoreCase("HmacMD5") || algorithm.equalsIgnoreCase("HmacSHA1") || algorithm.equalsIgnoreCase("HmacSHA224")
                || algorithm.equalsIgnoreCase("HmacSHA256") || algorithm.equalsIgnoreCase("HmacSHA384") || algorithm.equalsIgnoreCase("HmacSHA512");
    }
}
