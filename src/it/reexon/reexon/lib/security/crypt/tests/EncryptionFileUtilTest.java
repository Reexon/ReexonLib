package it.reexon.reexon.lib.security.crypt.tests;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import org.junit.Test;

import it.reexon.reexon.lib.security.crypt.EncryptionFileUtil;


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
}
