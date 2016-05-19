package it.reexon.lib.securityOLD.crypt.enums;
@Deprecated
public class SecretKeyFactoryAlgorithms
{
    public static String AES = "AES"; // Constructs secret keys for use with the AES algorithm.
    public static String ARCFOUR = "ARCFOUR"; //Constructs secret keys for use with the ARCFOUR algorithm.
    public static String DES = "DES"; //Constructs secrets keys for use with the DES algorithm.
    public static String DESede = "DESede"; //Constructs secrets keys for use with the DESede (Triple-DES) algorithm.

    public static String RSA = "RSA";

    /**
     * PBEWith<digest>And<encryption>
     * PBEWith<prf>And<encryption>
     * 
     * Secret-key factory for use with PKCS5 password-based encryption, where <digest> is a message digest, <prf> is a pseudo-random function, and <encryption> is an encryption algorithm. 
     *   Examples:
     *   ##PBEWithMD5AndDES (PKCS #5, 1.5),
     *   ##PBEWithHmacSHA256AndAES_128 (PKCS #5, 2.0)
     *   Note: These all use only the low order 8 bits of each password character.       
     */
    public static String PBEWithMD5AndDES = "PBEWithMD5AndDES";
    public static String PBEWithHmacSHA256AndAES_128 = "PBEWithHmacSHA256AndAES_128";

    /**
    *------------------------**PBKDF2With<prf>*
    *Password-based key- derivation algorithm found in PKCS#5 2.0 using the specified pseudo-random function (<prf>). 
    */
    public static String PBKDF2WithHmacSHA256 = "PBKDF2WithHmacSHA256"; //Password-based key-derivation algorithm found in PKCS #5 2.0 using the specified pseudo-random function 
}
