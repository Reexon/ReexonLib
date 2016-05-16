package it.reexon.lib.securityOLD.passwords;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import it.reexon.lib.securityOLD.GenerateSecureSalt;
import it.reexon.lib.strings.StringUtils;


/**
 * Advanced password security using PBKDF2WithHmacSHA1 algorithm
 * 
 * <p>
 * Note:
 * So far we learned about creating secure hashes for password, and using salt to make it even more secure. 
 * But the problem today is that hardwares have become so much fast that any brute force attack using dictionary and rainbow tables, 
 * any password can be cracked in some less or more time.
 * 
 * To solve this problem, general idea is to make this brute force attack slower so that damage can be minimized. 
 * Our next algorithm, works on this very concept. The goal is to make the hash function slow enough to impede attacks, 
 * but still fast enough to not cause a noticeable delay for the user.
 * 
 * This feature is essentially implemented using some CPU intensive algorithms such as PBKDF2, Bcrypt or Scrypt. 
 * These algorithms take a work factor (also known as security factor) or iteration count as an argument. 
 * This value determines how slow the hash function will be. 
 * When computers become faster next year we can increase the work factor to balance it out.
 * 
 * Java has implementation of "PBKDF2" algorithm as "PBKDF2WithHmacSHA1".
 * </p>
 * 
 *
 */
@Deprecated

public class GenerateSecureStringPBKDF2WithHmacSHA1
{
    /**
     * Genrate secure string by algorithm PBKDF2WithHmacSHA1
     * 
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static String generateSecureSting(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = GenerateSecureSalt.getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + StringUtils.toHexString(salt) + ":" + StringUtils.toHexString(hash);
    }

    /**
     * Is a function which can be used to validate the password again when user comes back and login.
     * 
     * @param originalPassword
     * @param storedPassword
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static boolean validatePassword(String originalPassword, String storedPassword) throws InvalidKeySpecException
    {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        try
        {
            byte[] salt = StringUtils.toHex(parts[1]);
            byte[] hash = StringUtils.toHex(parts[2]);
            PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            int diff = hash.length ^ testHash.length;
            for (int i = 0; i < hash.length && i < testHash.length; i++)
            {
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
