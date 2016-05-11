package it.reexon.lib.securityOLD.passwords;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.lib.strings.StringUtils;


/**
 * Simple and more secure password security using MD5 algorithm
 * @author marco.velluto
 *
 */
public class GenerateSecureStringMD5
{

    /**
     * Create a secure string using MD5 algorithm
     * 
     * <p>
     * Note :
     * The MD5 Message-Digest Algorithm is a widely used cryptographic hash function that produces a 128-bit (16-byte) hash value. 
     * It’s very simple and straight forward; the basic idea is to map data sets of variable length to data sets of a fixed length. 
     * In order to do this, the input message is split into chunks of 512-bit blocks. 
     * A padding is added to the end so that it’s length can be divided by 512. 
     * Now these blocks are processed by the MD5 algorithm, which operates in a 128-bit state, and the result will be a 128-bit hash value. 
     * After applying MD5, generated hash is typically a 32-digit hexadecimal number.
     * 
     * Here, the password to be encoded is often called the "message" and the generated hash value is called the message digest or simply "digest"
     * 
     * Although MD5 is a widely spread hashing algorithm, is far from being secure, 
     * MD5 generates fairly weak hashes. It’s main advantages are that it is fast, and easy to implement. 
     * But it also means that it is susceptible to brute-force and dictionary attacks. 
     * Rainbow tables with words and hashes generated allows searching very quickly for a known hash and getting the original word.
     * 
     * Also, It is not collision resistant: this means that different passwords can eventually result in the same hash.
     * Still, if you are using MD5 hash then consider adding some salt to your security.
     * </p>
     * 
     * @param passwordToHash
     * @return Secure String
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecurePassword(String passwordToHash)
    {
        String generatedPassword = null;

        try
        {
            //Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            //Add password bytes to digest
            md.update(passwordToHash.getBytes());

            //Get the hash's bytes 
            byte[] bytes = md.digest();

            //This bytes[] has byte in decimal format
            //Convert it to hexadecimal format
            //Get complete hashed password in hex format
            generatedPassword = StringUtils.toHexString(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    /**
     * Create a secure string using MD5 algorithm and salt
     * 
     * <p>
     * Note: 
     * Wikipedia defines salt as random data that are used as an additional input to a one-way function that hashes a password or pass-phrase. 
     * In more simple words, salt is some randomly generated text, which is appended to password before obtaining hash.
     * The original intent of salting was primarily to defeat pre-computed rainbow table attacks 
     * that could otherwise be used to greatly improve the efficiency of cracking the hashed password database. 
     * A greater benefit now is to slow down parallel operations that compare the hash of a password guess against 
     * many password hashes at once.
     * 
     * <b>Important: </b> We always need to use a SecureRandom to create good Salts, and in Java, 
     * the SecureRandom class supports the "SHA1PRNG" pseudo random number generator algorithm, 
     * and we can take advantage of it.
     * <b>Important: </b> Please note that now you have to store this salt value for every password you hash. 
     * Because when user login back in system, you must use only originally generated salt to again create the 
     * hash to match with stored hash. If a different salt is used (we are generating random salt), then generated hash will be different.
     * 
     * @param passwordToHash
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateSecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            //Get complete hashed password in hex format
            generatedPassword = StringUtils.toHexString(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        // Create MessageDigest instance for MD5
        return generatedPassword;
    }
}
