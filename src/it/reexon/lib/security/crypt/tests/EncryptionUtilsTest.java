package it.reexon.lib.security.crypt.tests;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import it.reexon.lib.security.GenerateSecureString;
import it.reexon.lib.security.crypt.EncryptionUtils;
import it.reexon.lib.security.crypt.algorithmics.SecureRandomAlgorithmics;


public class EncryptionUtilsTest
{

    @Test
    public final void test() throws Exception
    {
        String password = GenerateSecureString.secureString(1024, SecureRandomAlgorithmics.SHA1PRNG);
        System.out.println(password);
        final EncryptionUtils td = new EncryptionUtils(password);

        String target = "password@123";
        String encrypted = td.encrypt(target);
        String decrypted = td.decrypt(encrypted);

        System.out.println("String To Encrypt: " + target);
        System.out.println("Encrypted String: " + encrypted);
        System.out.println("Decrypted String: " + decrypted);

        Assert.assertEquals(target, decrypted);
    }

    @Test
    public final void testGeneral() throws Exception
    {
        String password = "ThisIsSpartaThisIsSparta";
        String stringToEncrypted = "TeSt";
        String encrypted = testEncrypt(stringToEncrypted, password);
        String descryted = testDecrypt(encrypted, password);
        Assert.assertEquals(stringToEncrypted, descryted);
    }

    public final String testEncrypt(String stringToEncrypted, String password) throws Exception
    {
        final EncryptionUtils td = new EncryptionUtils(password);
        return td.encrypt(stringToEncrypted);
    }

    public final String testDecrypt(String encrypted, String password) throws Exception
    {
        final EncryptionUtils td = new EncryptionUtils(password);
        return td.decrypt(encrypted);
    }

    public final void fileCryptTest() throws Exception
    {
        try
        {
            File f = new File("test/tetsss.TXT");
            IOUtils.write("CIAO", new FileOutputStream(f));
            final EncryptionUtils tf = new EncryptionUtils("myP4ss0rD");
            tf.encryptFile(f, new File("f.crypt"));

            File file = new File("test/f.crypt");
            final EncryptionUtils fff = new EncryptionUtils("myP4ss0rD");
            fff.decryptFile(file, new File("test/f22.txt"));
        }
        catch (Exception e)
        {
            e.getLocalizedMessage();
            e.printStackTrace();
            Assert.fail();
        }

    }

}
