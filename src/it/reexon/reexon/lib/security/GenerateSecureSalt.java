package it.reexon.reexon.lib.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class GenerateSecureSalt
{
    /**
     * Create a random salt using SHA1PRNG algorithm
     * 
     * <p>
     * Note:
     * SHA1PRNG algorithm is used as cryptographically strong pseudo-random number generator based on the SHA-1 message digest algorithm. 
     * Note that if a seed is not provided, it will generate a seed from a true random number generator (TRNG).
     * </p>
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt()
    {
        try
        {
            //Always use a SecureRandom generator
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

            //Create array for salt
            byte[] salt = new byte[16];

            //Get a random salt
            sr.nextBytes(salt);

            //return salt
            return salt;

        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
