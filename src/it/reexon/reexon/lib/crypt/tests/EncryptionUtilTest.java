package it.reexon.reexon.lib.crypt.tests;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.crypt.EncryptionUtil;


public class EncryptionUtilTest
{

    @Test
    public final void testRSA()
    {
        try (ObjectInputStream publicInputStream = new ObjectInputStream(new FileInputStream(EncryptionUtil.PUBLIC_KEY_FILE));
                ObjectInputStream privateInputStream = new ObjectInputStream(new FileInputStream(EncryptionUtil.PRIVATE_KEY_FILE));)
        {

            // Check if the pair of keys are present else generate those.
            if (!EncryptionUtil.areKeysPresent())
            {
                // Method generates a pair of keys using the RSA algorithm and stores it
                // in their respective files
                EncryptionUtil.generateKey();
            }

            final String originalText = "Text to be encrypted ";
            // Encrypt the string using the public key

            final PublicKey publicKey = (PublicKey) publicInputStream.readObject();
            final byte[] cipherText = EncryptionUtil.encrypt(originalText, publicKey);

            // Decrypt the cipher text using the private key.

            final PrivateKey privateKey = (PrivateKey) privateInputStream.readObject();
            final String plainText = EncryptionUtil.decrypt(cipherText, privateKey);

            // Printing the Original, Encrypted and Decrypted Text
            System.out.println("Original: " + originalText);
            System.out.println("Encrypted: " + cipherText.toString());
            System.out.println("Decrypted: " + plainText);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testEncrypt()
    {
        try (ObjectInputStream publicInputStream = new ObjectInputStream(new FileInputStream(EncryptionUtil.PUBLIC_KEY_FILE));)
        {
            final PublicKey publicKey = (PublicKey) publicInputStream.readObject();
            byte[] cypt = EncryptionUtil.encrypt("CIAO!!!!", publicKey);
            System.out.println("Crypt: " + cypt.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testDecrypt()
    {
        try (ObjectInputStream privateInputStream = new ObjectInputStream(new FileInputStream(EncryptionUtil.PRIVATE_KEY_FILE));)
        {
            final PrivateKey privateKey = (PrivateKey) privateInputStream.readObject();
            String str = EncryptionUtil.decrypt("[B@b97c004".getBytes(), privateKey);
            System.out.println(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }
}
