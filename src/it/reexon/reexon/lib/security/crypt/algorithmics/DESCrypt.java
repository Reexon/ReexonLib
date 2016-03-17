package it.reexon.reexon.lib.security.crypt.algorithmics;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import it.reexon.reexon.lib.security.crypt.enums.AlgorithmsEnum;


/**
 * DES (the Data Encryption Standard) is a symmetric block cipher developed by IBM. 
 * The algorithm uses a 56-bit key to encipher/decipher a 64-bit block of data. 
 * The key is always presented as a 64-bit block, every 8th bit of which is ignored. 
 * However, it is usual to set each 8th bit so that each group of 8 bits has an odd number of bits set to 1
 *
 */
public class DESCrypt
{
    Cipher cipher;

    public DESCrypt(SecretKey key)
    {
        try
        {
            cipher = Cipher.getInstance(AlgorithmsEnum.DES.name());
            cipher.init(Cipher.ENCRYPT_MODE, key);

        }
        catch (javax.crypto.NoSuchPaddingException e)
        {}
        catch (java.security.NoSuchAlgorithmException e)
        {}
        catch (java.security.InvalidKeyException e)
        {}
    }

    public String encrypt(String stringa)
    {
        try
        {
            // Salt
            byte[] salt = { //@f:off
                (byte)0xc7, (byte)0x73, (byte)0x21, (byte)0x8c,
                (byte)0x7e, (byte)0xc8, (byte)0xee, (byte)0x99
            }; //@f:on

            byte[] enc = cipher.doFinal(stringa.getBytes("UTF8"));

            Decoder decoder = Base64.getDecoder();
            byte[] saltArray = decoder.decode(salt);
            byte[] ciphertextArray = decoder.decode("ciphertext");

            //for encoding
            Encoder encoder = Base64.getEncoder();
            String saltString = encoder.encodeToString(salt);
            String ciphertextString = encoder.encodeToString("ciphertext".getBytes());
        }
        catch (javax.crypto.BadPaddingException e)
        {}
        catch (IllegalBlockSizeException e)
        {}
        catch (UnsupportedEncodingException e)
        {}
        catch (IOException e)
        {}
        return null;
    }
}