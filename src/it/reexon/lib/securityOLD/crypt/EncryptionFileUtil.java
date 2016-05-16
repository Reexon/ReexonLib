package it.reexon.lib.securityOLD.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import it.reexon.lib.securityOLD.algorithms.CipherAlgorithmsNames;
import it.reexon.lib.securityOLD.algorithms.KeyGeneratorAlgorithms;
import it.reexon.lib.securityOLD.algorithms.SecretKeySpecAlgorithms;


/**
 * Encrypt file form password 
 * 
 * @author Marco Velluto
 * @since Java 1.8
 * 
 * *** CAREFUL *** 
 * NOT USE THIS AGAIN 
 */
@Deprecated
public class EncryptionFileUtil
{
    /**
     * 
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public static void encrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException
    {
        CipherOutputStream cos = null;
        try (FileInputStream fis = new FileInputStream("resources/tests/FileTo.encrypt");
                FileOutputStream fos = new FileOutputStream("resources/tests/Encrypted.file");
                FileOutputStream kos = new FileOutputStream("resources/tests/crypt.key");)
        {
            KeyGenerator kg = KeyGenerator.getInstance(KeyGeneratorAlgorithms.AES);
            kg.init(128);
            SecretKey key = kg.generateKey();

            Cipher c = Cipher.getInstance(CipherAlgorithmsNames.AES);
            c.init(Cipher.ENCRYPT_MODE, key);

            //write encrypted to file
            cos = new CipherOutputStream(fos, c);
            byte[] b = new byte[16];
            int i = fis.read(b);
            while (i != -1)
            {
                cos.write(b, 0, i);
                i = fis.read(b);
            }

            cos.flush();

            //write key to file
            byte[] keyEncoded = key.getEncoded();
            kos.write(keyEncoded);
            kos.flush();
        }
        finally
        {
            if (cos != null)
                cos.close();
        }
    }

    /**
     * 
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    public static void decrypt() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
    {
        CipherInputStream in = null;
        try (/*Load Key*/ FileInputStream fis2 = new FileInputStream("resources/tests/crypt.key");
                FileInputStream fis1 = new FileInputStream("resources/tests/Encrypted.file");
                FileOutputStream fos0 = new FileOutputStream("resources/tests/decrypted.file");)
        {
            File f = new File("resources/tests/crypt.key");
            long l = f.length();
            byte[] b1 = new byte[(int) l];
            fis2.read(b1, 0, (int) l);

            SecretKeySpec ks2 = new SecretKeySpec(b1, SecretKeySpecAlgorithms.AES);

            Cipher c1 = Cipher.getInstance(CipherAlgorithmsNames.AES);
            c1.init(Cipher.DECRYPT_MODE, ks2);

            in = new CipherInputStream(fis1, c1);

            byte[] b3 = new byte[1];
            int ia = in.read(b3);
            while (ia >= 0)
            {
                fos0.write(b3, 0, ia);
                ia = in.read(b3);
            }
            fos0.flush();
        }
        finally
        {
            if (in != null)
                in.close();
        }
    }
}
