package it.reexon.lib.security.crypt.tests;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import com.ibm.icu.impl.Assert;

import it.reexon.lib.security.crypt.enums.SecretKeyFactoryAlgorithms;
import it.reexon.lib.strings.StringUtils;


//http://stackoverflow.com/questions/3451670/java-aes-and-using-my-own-key
public class DESCryptTest
{

    private final static String PASSWORD = "PaSSw0Rd!";

    @Test
    public final void testCrypt()
    {
        try
        {
            final String textToCrypt = "HelloWorld!";
            SecretKey key = new SecretKeySpec(PASSWORD.getBytes(), "DESede");

            final IvParameterSpec iv = new IvParameterSpec(new byte[4]);
            final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] buffer = new byte[1024];
            byte[] result = cipher.doFinal(buffer);
            String abc = new sun.misc.BASE64Encoder().encode(result);

            System.out.print(abc);
        }
        catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e)
        {
            System.err.println("Error in testCrypt: " + e.getLocalizedMessage());
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }

    @Test
    public final void testCryptV1()
    {
        try
        {
            PBEKeySpec pbeKeySpec;
            PBEParameterSpec pbeParamSpec;
            SecretKeyFactory keyFac;

            // Salt
            byte[] salt = { //@f:off
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            }; //@f:on

            // Iteration count
            int count = 20;

            // Create PBE parameter set
            pbeParamSpec = new PBEParameterSpec(salt, count);

            // Prompt user for encryption password.
            // Collect user password as char array (using the
            // "readPassword" method from above), and convert
            // it into a SecretKey object, using a PBE key
            // factory.
            System.out.print("Enter encryption password:  ");
            System.out.flush();

            //        pbeKeySpec = new PBEKeySpec(readPassword(System.in));
            pbeKeySpec = new PBEKeySpec(PASSWORD.toCharArray());
            keyFac = SecretKeyFactory.getInstance(SecretKeyFactoryAlgorithms.PBEWithMD5AndDES);
            SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

            // Create PBE Cipher
            Cipher pbeCipher = Cipher.getInstance(SecretKeyFactoryAlgorithms.PBEWithMD5AndDES);

            // Initialize PBE Cipher with key and parameters
            pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

            // Our cleartext
            byte[] cleartext = "This is another example".getBytes();

            // Encrypt the cleartext
            byte[] ciphertext = pbeCipher.doFinal(cleartext);
            System.out.println("ciphertext: " + StringUtils.toHexString(ciphertext));
        }
        catch (Exception e)
        {
            System.err.println("Error in testCryptV1: " + e.getLocalizedMessage());
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        }
    }
}
