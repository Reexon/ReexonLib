package it.reexon.lib.security.crypt.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import it.reexon.lib.security.crypt.EncryptionFileUtil;


public class EncryptionFileUtilTest
{

    @Test
    public final void EncryptTest() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException
    {
        EncryptionFileUtil.encrypt();
    }

    @Test
    public final void DecryptTest() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException
    {
        EncryptionFileUtil.decrypt();
    }

    /**
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchPaddingException 
     * @throws InvalidKeyException 
     * @throws InvalidParameterSpecException 
     * @throws UnsupportedEncodingException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws InvalidAlgorithmParameterException 
     * @see {@http://stackoverflow.com/questions/992019/java-256-bit-aes-password-based-encryption}
     */
    public final void cryptFile()
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException
    {
        /* Derive the key, given password and salt. */
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        char[] password = "PBKDF2WithHmacSHA256".toCharArray();
        byte[] salt = { //@f:off
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            }; //@f:on

        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        /* Encrypt the message. */
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] ciphertext = cipher.doFinal("Hello, World!".getBytes("UTF-8"));

        /* Decrypt the message, given derived key and initialization vector. */
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher2.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        String plaintext = new String(cipher2.doFinal(ciphertext), "UTF-8");
        System.out.println(plaintext);
    }
}
