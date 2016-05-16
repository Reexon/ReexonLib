/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.aspose.imaging.internal.Exceptions.IO.FileNotFoundException;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CryptoUtils
{
    private static final String PBKDF2_WITH_HMAC_SHA256 = "PBKDF2WithHmacSHA256";
    private static final String ALGORITHM = "AES";
    private static final String UNICODE_FORMAT = "UTF8";

    /**
     * Encrypt file and write new encrypted file on output file with a key and AES algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.         
     * @param outputFile        Output file to encrypt or decrypt file.   
     * @param key               Secret key to encrypt or decrypt file.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void encrypt(File inputFile, File outputFile, Key key)
    {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, ALGORITHM);
    }

    /**
     * Decrypt file and write new decrypted file on output file with a key and AES algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.         
     * @param outputFile        Output file to encrypt or decrypt file.   
     * @param key               Secret key to encrypt or decrypt file.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void decrypt(File inputFile, File outputFile, Key key)
    {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, ALGORITHM);
    }

    /**
     * Encrypt file and write new encrypted file on output file with a key and algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.                   
     * @param key               Secret key to encrypt or decrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void encrypt(File inputFile, File outputFile, Key key, String algorithm)
    {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, algorithm);
    }

    /**
     * Decrypt file and write new decrypted file on output file with a key and algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.                   
     * @param key               Secret key to encrypt or decrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void decrypt(File inputFile, File outputFile, Key key, String algorithm)
    {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, algorithm);
    }

    /**
     * Encrypt or decrypt file and write new encrypted file on output file with a key and algorithm.
     * 
     * @param cipherMode        Cipher mode (Cipher.ENCRYPT_MODE to encypt or Cipher.DECRYPT_MODE to decrypt).
     * @param secretKey         Secret key to encrypt or decrypt file.
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    private static void doCrypto(int cipherMode, Key secretKey, File inputFile, File outputFile, String algorithm)
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null");

        if (inputFile == null)
            throw new IllegalArgumentException("Input file cannot be null");
        if (inputFile.exists())
            throw new FileNotFoundException("Input file must be exists");

        if (outputFile == null)
            throw new IllegalArgumentException("Output file cannot be null");

        if (outputFile.exists())
            throw new FileNotFoundException("Output file must be exists");

        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm cannot be null");

        try (FileInputStream inputStream = new FileInputStream(inputFile); FileOutputStream outputStream = new FileOutputStream(outputFile);)
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(cipherMode, secretKey);

            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            outputStream.write(outputBytes);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt plane text with a key and AES algorithm.
     * 
     * @param planeText         Plane text to encrypt.       
     * @param secretKey         Secret key to encrypt file.
     * @return String           Encrypted string
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     */
    public static String encrypt(String unencryptedString, Key secretKey)
    {
        return encrypt(unencryptedString, secretKey, ALGORITHM);
    }

    /**
     * Encrypt plane text with a key and algorithm.
     * 
     * @param planeText         Plane text to encrypt.       
     * @param secretKey         Secret key to encrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * @return String           Encrypted string
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static String encrypt(String planeText, Key secretKey, String algorithm)
    {
        if (planeText == null)
            throw new IllegalArgumentException("Plane text cannot be null");

        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null");

        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm cannot be null or blank");

        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] plainText = planeText.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            return new String(Base64.encodeBase64(encryptedText));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypt plane text with a key and AES algorithm.
     * 
     * @param planeText         Plane text to decrypt.       
     * @param secretKey         Secret key to decrypt file.
     * @return String           Decrypted string
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static String decrypt(String encryptedString, Key secretKey)
    {
        return decrypt(encryptedString, secretKey, ALGORITHM);
    }

    /**
     * Decrypt plane text with a key and algorithm.
     * 
     * @param planeText         Plane text to decrypt.       
     * @param secretKey         Secret key to decrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * @return String           Decrypted string
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static String decrypt(String encryptedString, Key secretKey, String algorithm)
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null");

        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm cannot be null or blank");

        if (encryptedString == null)
            throw new IllegalArgumentException("Encrypted string cannot be null");
        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] encryptedText = Base64.decodeBase64(encryptedString.getBytes());
            byte[] plainText = cipher.doFinal(encryptedText);
            return new String(plainText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with AES Algorithm.
     *  
     * @param password          Plane text to generate a secret key.
     * @return The Secret Key
     * 
     * @throws IllegalArgumentException If password is null.
     */
    public static SecretKey getSecretKey(String password)
    {
        return getSecretKey(password, ALGORITHM);
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with AES Algorithm.
     *  
     * @param password          Plane text to generate a secret key.
     * @param algorithm         Algorithm to generate secret key. Please use: {@link it.reexon.lib.security.algorithmics.KeyGeneratorAlgorithms}.
     * 
     * @return The Secret Key
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     */
    public static SecretKey getSecretKey(String password, String algorithm)
    {
        if (password == null)
            throw new IllegalArgumentException("Password can't be null");

        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm can't be null");

        try
        {
            SecretKeySpec secretKeySpec = null;
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(256, new SecureRandom(password.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            secretKeySpec = new SecretKeySpec(enCodeFormat, algorithm);
            return secretKeySpec;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with AES Algorithm and salt.
     * 
     * @param password          Plane text to generate a secret key. 
     * @param salt              The salt.
     * 
     * @return The Secret Ke
     * @throws IllegalArgumentException If at least one parameter is null or empty.
     */
    public static SecretKey getSecretKey(char[] password, byte[] salt)
    {
        return getSecretKey(password, salt, ALGORITHM);
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with PBKDF2_WITH_HMAC_SHA256 Algorithm and salt.
     * 
     * @param password          Plane text to generate a secret key. 
     * @param salt              The salt.
     * @param algorithm         Algorithm to generate secret key. Please use: {@link it.reexon.lib.security.algorithmics.KeyGeneratorAlgorithms}.
     * 
     * @return The Secret Key
     * @throws IllegalArgumentException If at least one parameter is null. The salt cannot be empty
     * 
     */
    public static SecretKey getSecretKey(char[] password, byte[] salt, String algorithm)
    {
        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm can't be null or blank");
        if (password == null)
            throw new IllegalArgumentException("Password cannot be null");
        if (salt == null)
            throw new IllegalArgumentException("Salt cannot be null");
        try
        {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA256);
            KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), algorithm);

            return secret;
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
