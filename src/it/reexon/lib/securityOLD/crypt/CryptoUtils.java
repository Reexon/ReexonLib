package it.reexon.lib.securityOLD.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import it.reexon.lib.securityOLD.crypt.exceptions.CryptoException;


/**
 * A utility class that encrypts or decrypts a file.
 * if @throws "Illegal key size or default parameters" 
 *      @see this {@http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters}
 * 
 * @since Java 1.8
 */
@Deprecated
public class CryptoUtils
{
    private static final String PBKDF2_WITH_HMAC_SHA256 = "PBKDF2WithHmacSHA256";
    private static final String ALGORITHM = "AES";

    public static void encrypt(Key key, File inputFile, File outputFile) throws CryptoException
    {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(Key key, File inputFile, File outputFile) throws CryptoException
    {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, Key secretKey, File inputFile, File outputFile) throws CryptoException
    {
        try
        {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        }
        catch (@SuppressWarnings("unused") BadPaddingException e)
        {
            throw new RuntimeException("Wrong key");
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | IOException ex)
        {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }

    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static SecretKey getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        final char[] password = "lZQPxt8E5ZaG{x9tm:2o:3JZwucTl".toCharArray();
        final byte[] salt = { //@f:off
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            }; //@f:on

        final SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA256);
        final KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        final SecretKey tmp = factory.generateSecret(spec);
        final SecretKey secret = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

        return secret;
    }

    /**
     * STABLE 
     * @param password
     * @return
     */
    public static SecretKey getSecretKey(String password)
    {
        if (StringUtils.isBlank(password))
        {
            throw new IllegalArgumentException("Password can't be null or blank");
        }
        SecretKeySpec secretKeySpec = null;
        try
        {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(256, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            secretKeySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
            return secretKeySpec;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param password
     * @param salt
     * @return
     */
    public static SecretKey getSecretKey(char[] password, byte[] salt)
    {
        try
        {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA256);
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), ALGORITHM);

            return secret;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * @param password
     * @param salt
     * @param secretKeyAlgorithm - the name of the secret-key algorithm to be associated with the given key material. See Appendix A in the  Java Cryptography Architecture Reference Guide for information about standard algorithm names.
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static SecretKey getSecretKey(String password, byte[] salt, String secretKeyAlgorithm)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        if (StringUtils.isBlank(password))
            throw new IllegalArgumentException("Password can't be null or blank");

        if (salt == null || salt.length == 0)
            throw new IllegalArgumentException("Salt cannot be null");

        try
        {
            SecretKeySpec secretKeySpec = null;
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(256, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            secretKeySpec = new SecretKeySpec(enCodeFormat, ALGORITHM);
            return secretKeySpec;
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

}