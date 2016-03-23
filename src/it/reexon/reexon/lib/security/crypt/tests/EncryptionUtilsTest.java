package it.reexon.reexon.lib.security.crypt.tests;

import org.junit.Assert;
import org.junit.Test;

import it.reexon.reexon.lib.security.crypt.EncryptionUtils;


public class EncryptionUtilsTest
{

    @Test
    public final void test() throws Exception
    {
        final EncryptionUtils td = new EncryptionUtils("ThisIsSpartaThisIsSparta");

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

}
