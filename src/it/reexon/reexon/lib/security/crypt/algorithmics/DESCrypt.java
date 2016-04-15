package it.reexon.reexon.lib.security.crypt.algorithmics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

import it.reexon.reexon.lib.security.algorithms.CipherAlgorithmsNames;
import it.reexon.reexon.lib.security.crypt.exceptions.CryptoException;


/**
 * DES (the Data Encryption Standard) is a symmetric block cipher developed by IBM. 
 * The algorithm uses a 56-bit key to encipher/decipher a 64-bit block of data. 
 * The key is always presented as a 64-bit block, every 8th bit of which is ignored. 
 * However, it is usual to set each 8th bit so that each group of 8 bits has an odd number of bits set to 1
 * 
 * @author Marco Velluto
 * @since Java 1.8
 */
public class DESCrypt
{
    public static void encrypt(Key key, File inputFile, File outputFile) throws CryptoException
    {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(Key key, File inputFile, File outputFile) throws CryptoException
    {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    public static byte[] encrypt(Key key, String cripText) throws CryptoException
    {
        return doCrypto(Cipher.ENCRYPT_MODE, key, cripText);
    }

    public static byte[] decrypt(Key key, String criptedText) throws CryptoException
    {
        return doCrypto(Cipher.DECRYPT_MODE, key, criptedText);
    }

    private static byte[] doCrypto(int cipherMode, Key secretKey, String cripText) throws CryptoException
    {
        try
        {
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            byte iv[] = cipher.getIV(); 
            IvParameterSpec dps = new IvParameterSpec(iv);
            
            cipher.init(cipherMode, secretKey);
            
            byte[] outputBytes = cipher.doFinal(cripText.getBytes());
            return outputBytes;
        }
        catch (BadPaddingException e)
        {
            throw new RuntimeException("Wrong key: " + e.getLocalizedMessage());
        }
        catch (IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e)
        {
            throw new CryptoException("Error encrypting/decrypting file", e);
        }
    }

    private static void doCrypto(int cipherMode, Key secretKey, File inputFile, File outputFile) throws CryptoException
    {
        try
        {
            Cipher cipher = Cipher.getInstance(CipherAlgorithmsNames.DES);
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
        catch (BadPaddingException e)
        {
            throw new RuntimeException("Wrong key: " + e.getLocalizedMessage());
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | IOException ex)
        {
            throw new CryptoException("Error encrypting/decrypting file", ex);
        }
    }
}