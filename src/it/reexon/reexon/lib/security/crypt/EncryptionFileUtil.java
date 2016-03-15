package it.reexon.reexon.lib.security.crypt;

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

import it.reexon.reexon.lib.security.algorithms.CipherAlgorithms;
import it.reexon.reexon.lib.security.algorithms.KeyGeneratorAlgorithms;
import it.reexon.reexon.lib.security.algorithms.SecretKeySpecAlgorithms;


/**
 * @author marco.velluto
 * @version v1.0
 */
public class EncryptionFileUtil
{
    public static void encrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException
    {
        KeyGenerator kg = KeyGenerator.getInstance(KeyGeneratorAlgorithms.AES);
        kg.init(128);
        SecretKey key = kg.generateKey();
        Cipher c = Cipher.getInstance(CipherAlgorithms.AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        FileInputStream fis;
        FileOutputStream fos;
        CipherOutputStream cos;
        fis = new FileInputStream("resources/FileTo.encrypt");
        fos = new FileOutputStream("resources/Encrypted.file");

        //write encrypted to file
        cos = new CipherOutputStream(fos, c);
        byte[] b = new byte[16];
        int i = fis.read(b);
        while (i != -1)
        {
            cos.write(b, 0, i);
            i = fis.read(b);
        }
        cos.close();

        //write key to file
        byte[] keyEncoded = key.getEncoded();
        FileOutputStream kos = new FileOutputStream("resources/crypt.key");
        kos.write(keyEncoded);
        kos.close();
    }

    public static void decrypt() throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException
    {
        //Load Key
        FileInputStream fis2 = new FileInputStream("resources/crypt.key");
        File f = new File("resources/crypt.key");
        long l = f.length();
        byte[] b1 = new byte[(int) l];
        fis2.read(b1, 0, (int) l);

        SecretKeySpec ks2 = new SecretKeySpec(b1, SecretKeySpecAlgorithms.AES);

        Cipher c1 = Cipher.getInstance(CipherAlgorithms.AES);
        c1.init(Cipher.DECRYPT_MODE, ks2);
        FileInputStream fis1 = new FileInputStream("resources/Encrypted.file");
        CipherInputStream in = new CipherInputStream(fis1, c1);
        FileOutputStream fos0 = new FileOutputStream("resources/decrypted.file");
        byte[] b3 = new byte[1];
        int ia = in.read(b3);
        while (ia >= 0)
        {
            fos0.write(b3, 0, ia);
            ia = in.read(b3);
        }
        in.close();
        fos0.flush();
        fos0.close();
    }
}
