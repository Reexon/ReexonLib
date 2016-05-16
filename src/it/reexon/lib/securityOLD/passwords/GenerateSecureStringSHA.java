package it.reexon.lib.securityOLD.passwords;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.reexon.lib.strings.StringUtils;


/**
 * Medium password security using SHA algorithms
 * @author marco.velluto
 *
 * <p>
 * Note: 
 * The SHA (Secure Hash Algorithm) is a family of cryptographic hash functions. 
 * It is very similar to MD5 except it generates more strong hashes. 
 * However these hashes are not always unique, and it means that for two different inputs we could have equal hashes. 
 * When this happens it’s called a "collision". Chances of collision in SHA is less than MD5. 
 * But, do not worry about these collisions because they are really very rare.
 * Java has 4 implementations of SHA algorithm. They generate following length hashes in comparison to MD5 (128 bit hash):
 * - SHA-1 (Simplest one – 160 bits Hash)
 * - SHA-256 (Stronger than SHA-1 – 256 bits Hash)
 * - SHA-384 (Stronger than SHA-256 – 384 bits Hash)
 * - SHA-512 (Stronger than SHA-384 – 512 bits Hash)
 * A longer hash is more difficult to break. That’s core idea.
 * 
 * To get any implementation of algorithm, pass it as parameter to MessageDigest. e.g.
 * MessageDigest md = MessageDigest.getInstance("SHA-1");
 *   OR
 *   MessageDigest md = MessageDigest.getInstance("SHA-256");
 * </p>
 */
@Deprecated

public class GenerateSecureStringSHA
{
    /**
     * Password crypting using SHA-512 algorithm
     * 
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String generateSecurePassword(String passwordToHash, byte[] salt)
    {
        return get_SHA_512_SecurePassword(passwordToHash, salt);
    }

    /**
     * Password crypting using SHA-1 algorithm
     * 
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt)
    {
        String generatedPassword = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            generatedPassword = StringUtils.toHexString(bytes);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * Password crypted using SHA-256 algorithm
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt)
    {
        String password = null;
        try
        {
            password = getSecurePassowrd(passwordToHash, salt, "SHA-256");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Password crypted using SHA-384 algorithm
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String get_SHA_384_SecurePassword(String passwordToHash, byte[] salt)
    {
        String password = null;
        try
        {
            password = getSecurePassowrd(passwordToHash, salt, "SHA-384");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * Password crypted using SHA-512 algorithm
     * @param passwordToHash
     * @param salt
     * @return
     */
    public static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt)
    {
        String password = null;
        try
        {
            password = getSecurePassowrd(passwordToHash, salt, "SHA-512");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return password;
    }

    /**
     * 
     * @param passwordToHash
     * @param salt
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static final String getSecurePassowrd(String passwordToHash, byte[] salt, String algorithm) throws NoSuchAlgorithmException
    {
        String generatedPassword = null;

        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(salt);
        byte[] bytes = md.digest(passwordToHash.getBytes());
        generatedPassword = StringUtils.toHexString(bytes);

        return generatedPassword;

    }

}
