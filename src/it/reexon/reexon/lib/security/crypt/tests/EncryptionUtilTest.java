package it.reexon.reexon.lib.security.crypt.tests;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Assert;
import org.junit.Test;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import it.reexon.reexon.lib.security.crypt.EncryptionKeyFilesUtil;


public class EncryptionUtilTest
{

    @Test
    public final void testRSA()
    {
        try (ObjectInputStream publicInputStream = new ObjectInputStream(new FileInputStream(EncryptionKeyFilesUtil.PUBLIC_KEY_FILE));
                ObjectInputStream privateInputStream = new ObjectInputStream(new FileInputStream(EncryptionKeyFilesUtil.PRIVATE_KEY_FILE));)
        {

            // Check if the pair of keys are present else generate those.
            if (!EncryptionKeyFilesUtil.areKeysPresent())
            {
                // Method generates a pair of keys using the RSA algorithm and stores it
                // in their respective files
                EncryptionKeyFilesUtil.generateKeyFile();
            }

            final String originalText = "Text to be encrypted ";
            // Encrypt the string using the public key

            final PublicKey publicKey = (PublicKey) publicInputStream.readObject();
            final byte[] cipherText = EncryptionKeyFilesUtil.encrypt(originalText, publicKey);

            // Decrypt the cipher text using the private key.

            final PrivateKey privateKey = (PrivateKey) privateInputStream.readObject();
            final String plainText = EncryptionKeyFilesUtil.decrypt(cipherText, privateKey);

            // Printing the Original, Encrypted and Decrypted Text
            System.out.println("Original: " + originalText);
            System.out.println("Encrypted: " + Base64.encode(cipherText).toString());
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
        try (ObjectInputStream publicInputStream = new ObjectInputStream(new FileInputStream(EncryptionKeyFilesUtil.PUBLIC_KEY_FILE));)
        {
            final PublicKey publicKey = (PublicKey) publicInputStream.readObject();
            byte[] cypt = EncryptionKeyFilesUtil.encrypt("CIAO!!!!", publicKey);
            System.out.println("Crypt: " + Base64.encode(cypt).toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }
}
