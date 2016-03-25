package it.reexon.reexon.lib.security.crypt;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;


public class EncryptionUtils
{
    private static final String UNICODE_FORMAT = "UTF8";
    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private KeySpec ks;
    private SecretKeyFactory skf;
    private Cipher cipher;
    byte[] arrayBytes;
    private String myEncryptionKey;
    private String myEncryptionScheme;
    private SecretKey key;

    public EncryptionUtils(String myEncryptionKey) throws Exception
    {
        this.myEncryptionKey = myEncryptionKey;
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    public EncryptionUtils() throws Exception
    {
        myEncryptionKey = "ThisIsSpartaThisIsSparta";
        myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        ks = new DESedeKeySpec(arrayBytes);
        skf = SecretKeyFactory.getInstance(myEncryptionScheme);
        cipher = Cipher.getInstance(myEncryptionScheme);
        key = skf.generateSecret(ks);
    }

    public String encrypt(String unencryptedString)
    {
        String encryptedString = null;
        try
        {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public String decrypt(String encryptedString)
    {
        String decryptedText = null;
        try
        {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptedString.getBytes());
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return decryptedText;
    }

    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     */
    public static String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt.toString();
    }

    /**
     * 
     */
    public final void destroy()
    {
        this.ks = null;
        this.key = null;
        this.skf = null;
        this.cipher = null;
        this.arrayBytes = null;
        this.myEncryptionKey = null;
    }

}
