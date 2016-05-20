/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.aspose.imaging.internal.Exceptions.IO.FileNotFoundException;

import it.reexon.lib.security.exceptions.UnsupportedAlgorithmKeyException;


/**
 *  It is a utility class that allows you to encrypt and decrypt strings and test files .
 *  
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CryptoUtils
{
    private static final String PBKDF2_WITH_HMAC_SHA256 = "PBKDF2WithHmacSHA256";
    private static final String AES_ALGORITHM = "AES";
    private static final String RSA_ALGORITHM = "RSA";
    private static final String UNICODE_FORMAT = "UTF8";

    /**
     * Encrypt file and write new encrypted file on output file with a key and AES algorithm.
     * 
     * @param inputFile                         Input file to encrypt or decrypt.         
     * @param outputFile                        Output file to encrypt or decrypt file.   
     * @param key                               Secret key to encrypt or decrypt file.
     * @throws UnsupportedAlgorithmKeyException If the algorithm of the secret key is one of these (HmacMD5, HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA384).
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void encrypt(File inputFile, File outputFile, Key key) throws UnsupportedAlgorithmKeyException
    {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        String algorithm = key.getAlgorithm();
        if (isSupportedKeyAlgorithm(algorithm))
            throw new UnsupportedAlgorithmKeyException("Key algorithm " + algorithm + " doesn't supported");

        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, key.getAlgorithm());
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
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, key.getAlgorithm());
    }

    /**
     * 
     * 
     * Encrypt file and write new encrypted file on output file with a key and algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.                   
     * @param key               Secret key to encrypt or decrypt file.
     * @param cipherAlgorithm   Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void encrypt(File inputFile, File outputFile, Key key, String cipherAlgorithm)
    {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile, cipherAlgorithm);
    }

    /**
     * 
     * 
     * Decrypt file and write new decrypted file on output file with a key and algorithm.
     * 
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.                   
     * @param key               Secret key to encrypt or decrypt file.
     * @param cipherAlgorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    public static void decrypt(File inputFile, File outputFile, Key key, String cipherAlgorithm)
    {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile, cipherAlgorithm);
    }

    /**
     * Encrypt or decrypt file and write new encrypted file on output file with a key and algorithm.
     * 
     * @param cipherMode        Cipher mode (Cipher.ENCRYPT_MODE to encypt or Cipher.DECRYPT_MODE to decrypt).
     * @param secretKey         Secret key to encrypt or decrypt file.
     * @param inputFile         Input file to encrypt or decrypt.
     * @param outputFile        Output file to encrypt or decrypt file.
     * @param cipherAlgorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * 
     * @throws IllegalArgumentException If at least one parameter is null.
     * @throws FileNotFoundException    If file doesn't exist.
     */
    private static void doCrypto(int cipherMode, Key secretKey, File inputFile, File outputFile, String cipherAlgorithm)
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null");

        if (inputFile == null)
            throw new IllegalArgumentException("Input file cannot be null");
        if (!inputFile.exists())
            throw new FileNotFoundException("Input file must be exists");

        if (outputFile == null)
            throw new IllegalArgumentException("Output file cannot be null");

        if (!outputFile.exists())
            throw new FileNotFoundException("Output file must be exists");

        if (StringUtils.isBlank(cipherAlgorithm))
            throw new IllegalArgumentException("Algorithm cannot be null");

        try (FileInputStream inputStream = new FileInputStream(inputFile); FileOutputStream outputStream = new FileOutputStream(outputFile);)
        {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
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
     * @throws NoSuchPaddingException           If transformation contains a padding scheme that is not available.         
     * @throws NoSuchAlgorithmException         If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * @throws InvalidKeyException              If the given key is inappropriate for initializing this cipher, or requires algorithm parameters that cannot be determined from the given key, or if the given key has a keysize that exceeds the maximum allowable keysize (as determined from the configured jurisdiction policy files).
     * @throws BadPaddingException              If this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytesAEADBadTagException - if this cipher is decrypting in an AEAD mode (such as GCM/CCM), and the received authentication tag does not match the calculated value.
     * @throws IllegalBlockSizeException        If this cipher is a block cipher, no padding has been requested (only in encryption mode), and the total input length of the data processed by this cipher is not a multiple of block size; or if this encryption algorithm is unable to process the input data provided.BadPaddingException - if this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytes.
     * @throws UnsupportedAlgorithmKeyException If the algorithm of the secret key is one of these (HmacMD5, HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA384).
     * @throws IllegalArgumentException         If at least one parameter is null.
     */
    public static String encrypt(String unencryptedString, Key secretKey) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedAlgorithmKeyException
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null!");

        String algorithm = secretKey.getAlgorithm();
        if (isSupportedKeyAlgorithm(algorithm))
            throw new UnsupportedAlgorithmKeyException("Key algorithm " + algorithm + " doesn't supported");

        return encrypt(unencryptedString, secretKey, algorithm);
    }

    /**
     * Encrypt plane text with a key and algorithm.
     * 
     * @param planeText         Plane text to encrypt.       
     * @param secretKey         Secret key to encrypt file.
     * @param algorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * @return String           Encrypted string
     * 
     * @throws NoSuchPaddingException           If transformation contains a padding scheme that is not available.         
     * @throws NoSuchAlgorithmException         If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * @throws InvalidKeyException              If the given key is inappropriate for initializing this cipher, or requires algorithm parameters that cannot be determined from the given key, or if the given key has a keysize that exceeds the maximum allowable keysize (as determined from the configured jurisdiction policy files).
     * @throws BadPaddingException              If this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytesAEADBadTagException - if this cipher is decrypting in an AEAD mode (such as GCM/CCM), and the received authentication tag does not match the calculated value.
     * @throws IllegalBlockSizeException        If this cipher is a block cipher, no padding has been requested (only in encryption mode), and the total input length of the data processed by this cipher is not a multiple of block size; or if this encryption algorithm is unable to process the input data provided.BadPaddingException - if this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytes.
     * @throws IllegalArgumentException         If at least one parameter is null.
     */
    public static String encrypt(String planeText, Key secretKey, String algorithm)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
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
        catch (UnsupportedEncodingException e)
        {
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
     * @throws NoSuchPaddingException           If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.   
     * @throws NoSuchAlgorithmException         If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * @throws InvalidKeyException              If the given key is inappropriate for initializing this cipher, or requires algorithm parameters that cannot be determined from the given key, or if the given key has a keysize that exceeds the maximum allowable keysize (as determined from the configured jurisdiction policy files).
     * @throws BadPaddingException              If this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytes.
     * @throws IllegalBlockSizeException        If this cipher is a block cipher, no padding has been requested (only in encryption mode), and the total input length of the data processed by this cipher is not a multiple of block size; or if this encryption algorithm is unable to process the input data provided.
     * @throws UnsupportedAlgorithmKeyException If the algorithm of the secret key is one of these (HmacMD5, HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA384).
     * @throws IllegalArgumentException         If at least one parameter is null.
     */
    public static String decrypt(String encryptedString, Key secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedAlgorithmKeyException
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret key cannot be null!");

        String algorithm = secretKey.getAlgorithm();
        if (isSupportedKeyAlgorithm(algorithm))
            throw new UnsupportedAlgorithmKeyException("Key algorithm " + algorithm + " doesn't supported");

        return decrypt(encryptedString, secretKey, algorithm);
    }

    private static boolean isSupportedKeyAlgorithm(String algorithm)
    {
        return algorithm.equalsIgnoreCase("HmacMD5") || algorithm.equalsIgnoreCase("HmacSHA1") || algorithm.equalsIgnoreCase("HmacSHA224")
                || algorithm.equalsIgnoreCase("HmacSHA256") || algorithm.equalsIgnoreCase("HmacSHA384") || algorithm.equalsIgnoreCase("HmacSHA512");
    }

    /**
     * Decrypt plane text with a key and algorithm.
     * 
     * @param planeText         Plane text to decrypt.       
     * @param secretKey         Secret key to decrypt file.
     * @param cipherAlgorithm         Algorithm to use. Please use: {@link it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames}.
     * @return String           Decrypted string
     * 
     * @throws NoSuchPaddingException    If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.   
     * @throws NoSuchAlgorithmException  If transformation is null, empty, in an invalid format, or if no Provider supports a CipherSpi implementation for the specified algorithm.
     * @throws InvalidKeyException       If the given key is inappropriate for initializing this cipher, or requires algorithm parameters that cannot be determined from the given key, or if the given key has a keysize that exceeds the maximum allowable keysize (as determined from the configured jurisdiction policy files).
     * @throws BadPaddingException       If this cipher is in decryption mode, and (un)padding has been requested, but the decrypted data is not bounded by the appropriate padding bytes.
     * @throws IllegalBlockSizeException If this cipher is a block cipher, no padding has been requested (only in encryption mode), and the total input length of the data processed by this cipher is not a multiple of block size; or if this encryption algorithm is unable to process the input data provided.
     * @throws IllegalArgumentException  If at least one parameter is null.
     */
    public static String decrypt(String encryptedString, Key secretKey, String cipherAlgorithm)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    {
        if (secretKey == null)
            throw new IllegalArgumentException("Secret Key cannot be null");

        if (StringUtils.isBlank(cipherAlgorithm))
            throw new IllegalArgumentException("Algorithm cannot be null or blank");

        if (encryptedString == null)
            throw new IllegalArgumentException("Encrypted string cannot be null");

        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedText = Base64.decodeBase64(encryptedString.getBytes());
        byte[] plainText = cipher.doFinal(encryptedText);
        return new String(plainText);
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
        return getSecretKey(password, AES_ALGORITHM);
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
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with AES Algorithm and salt.
     * 
     * @param password          Plane text to generate a secret key. 
     * @param salt              The salt.
     * 
     * @return The Secret Key
     * @throws IllegalArgumentException If at least one parameter is null or empty.
     */
    public static SecretKey getSecretKey(char[] password, byte[] salt)
    {
        try
        {
            return getSecretKey(password, salt, AES_ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generate a Secret Key to encrypt or decrypt with PBKDF2_WITH_HMAC_SHA256 Algorithm and salt.
     * 
     * @param password          Plane text to generate a secret key. 
     * @param salt              The salt.
     * @param algorithm         Algorithm to generate secret key. Please use: {@link it.reexon.lib.security.algorithmics.KeyGeneratorAlgorithms}.
     * 
     * @return The Secret Key
     * @throws NoSuchAlgorithmException If no Provider supports a SecretKeyFactorySpi implementation for the specified algorithm.
     * @throws IllegalArgumentException If at least one parameter is null. The salt cannot be empty
     * 
     */
    public static SecretKey getSecretKey(char[] password, byte[] salt, String algorithm) throws NoSuchAlgorithmException
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
        catch (InvalidKeySpecException e)
        {
            throw new RuntimeException(e);
        }
    }
}
