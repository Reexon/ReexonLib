/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.tests;

import java.security.Key;

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

import it.reexon.lib.security.CryptoUtils;
import it.reexon.lib.security.SecuritySaltUtils;
import it.reexon.lib.security.SecurityStringUtils;
import it.reexon.lib.security.algorithmics.KeyGeneratorAlgorithms;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CryptoUtilsTest
{

    private static final String DEFAULT_PASSWORD = "PassOrd!23";
    private static final Logger logger = LogManager.getLogger(CryptoUtilsTest.class);

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {}

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {}

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
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.security.Key, java.io.File, java.io.File)}.
     */
    @Test
    public final void testEncryptKeyFileFile()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.security.Key, java.io.File, java.io.File)}.
     */
    @Test
    public final void testDecryptKeyFileFile()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#encrypt(java.security.Key, java.io.File, java.io.File, java.lang.String)}.
     */
    @Test
    public final void testEncryptKeyFileFileString()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.security.Key, java.io.File, java.io.File, java.lang.String)}.
     */
    @Test
    public final void testDecryptKeyFileFileString()
    {
        // TODO
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
            try
            {
                CryptoUtils.decrypt(null, null);
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
            for (String algorithm : KeyGeneratorAlgorithms.getAlgorithms())
            {
                logger.info("Algorithm: {} to crypt: {}", algorithm, planeText);
                final Key secretKey = CryptoUtils.getSecretKey(DEFAULT_PASSWORD, algorithm);
                String crypted1 = CryptoUtils.encrypt(planeText, secretKey, algorithm);
                Assert.assertNotEquals(crypted1, planeText);

                String crypted2 = CryptoUtils.encrypt(planeText, secretKey, algorithm);
                Assert.assertEquals(crypted1, crypted2);
            }
        }
        catch (Exception e)
        {
            logger.error("Error in testEncryptStringKeyString", e);
            throw new RuntimeException(e);
        }
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.lang.String, java.security.Key)}.
     */
    @Test
    public final void testDecryptStringKey()
    {
        // TODO
    }

    /**
     * Test method for {@link it.reexon.lib.security.CryptoUtils#decrypt(java.lang.String, java.security.Key, java.lang.String)}.
     */
    @Test
    public final void testDecryptStringKeyString()
    {
        // TODO
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
                CryptoUtils.getSecretKey(SecurityStringUtils.secureString(i));

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
            byte[] salt = SecuritySaltUtils.getSalt();
            SecretKey key1 = CryptoUtils.getSecretKey(password, salt);
            SecretKey key2 = CryptoUtils.getSecretKey(password, salt);
            Assert.assertEquals(key1, key2);

            salt = SecuritySaltUtils.getSalt();
            logger.debug("password({}): {}", password.length, password);
            logger.debug("salt({}): {}", salt.length, salt);

            key1 = CryptoUtils.getSecretKey(password, salt);
            key2 = CryptoUtils.getSecretKey(password, salt);
            Assert.assertEquals(key1, key2);

            password = RandomStringUtils.random(64, true, true).toCharArray();
            salt = SecuritySaltUtils.getSalt();
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
                byte[] salt = SecuritySaltUtils.getSalt();

                logger.debug("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length, passwordToHash);
                Key secretKey1 = CryptoUtils.getSecretKey(passwordToHash, salt, algorithm);
                Key secretKey2 = CryptoUtils.getSecretKey(passwordToHash, salt, algorithm);
                Assert.assertEquals(secretKey1, secretKey2);

                logger.debug("Algorithm: {} - password({}): {}", algorithm, passwordToHash.length, passwordToHash);
                Key secretKey0 = CryptoUtils.getSecretKey(passwordToHash, SecuritySaltUtils.getSalt(), algorithm);
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
}
