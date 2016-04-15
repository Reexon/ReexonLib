package it.reexon.reexon.lib.security.algorithms;

/**
 * The following names can be specified as the algorithm component in a transformation when requesting an instance of Cipher.
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CipherAlgorithmsNames
{
    private CipherAlgorithmsNames()
    {
        // cannot be instantiated.
    }

    /**
     *  Advanced Encryption Standard as specified by NIST in FIPS 197.
     */
    public static final String AES = "AES";

    /**
     *  Advanced Encryption Standard 128 bit as specified by NIST in FIPS 197.
     */
    public static final String AES_128 = "AES_128";

    /**
     *  Advanced Encryption Standard 192 bit as specified by NIST in FIPS 197.
     */
    public static final String AES_192 = "AES_192";

    /**
     *  Advanced Encryption Standard 256 bit as specified by NIST in FIPS 197.
     */
    public static final String AES_256 = "AES_256";

    /**
     * The AES 128 bit key wrapping algorithm as described in RFC 3394.
     */
    public static final String AESWrap_128 = "AESWrap_128";

    /**
     * The AES 192 bit key wrapping algorithm as described in RFC 3394.
     */
    public static final String AESWrap_192 = "AESWrap_192";

    /**
     * The AES 256 bit key wrapping algorithm as described in RFC 3394.
     */
    public static final String AESWrap_256 = "AESWrap_256";

    /**
     * A stream cipher believed to be fully interoperable with the RC4 cipher developed by Ron Rivest.
     * For more information, see K. Kaukonen and R. Thayer, "A Stream Cipher Encryption Algorithm 'Arcfour'"
     */
    public static final String ARCFOUR = "ARCFOUR";

    /**
     * The Blowfish block cipher designed by Bruce Schneier.
     */
    public static final String Blowfish = "Blowfish";

    /**
     * The Digital Encryption Standard as described in FIPS PUB 46-3.
     */
    public static final String DES = "DES";

    /**
     * Triple DES Encryption (also known as DES-EDE, 3DES, or Triple-DES). Data is encrypted using the DES algorithm three separate times. 
     * It is first encrypted using the first subkey, then decrypted with the second subkey, and encrypted with the third subkey.
     */
    public static final String DESede = "DESede";

    /**
     * The DESede key wrapping algorithm as described in RFC 3217.
     */
    public static final String DESedeWrap = "DESedeWrap";

    /**
     * Elliptic Curve Integrated Encryption Scheme
     */
    public static final String ECIES = "ECIES";

    /**
     * <p>
     * NOTE: PBEWith<digest>And<encryption> PBEWith<prf>And<encryption>
     * </p>
     * The password-based encryption algorithm found in (PKCS5), using the specified message digest (<digest>) or pseudo-random function (<prf>) and encryption algorithm (<encryption>).  
     */

    /**
     * The password-based encryption algorithm as defined in RSA Laboratories, "PKCS #5: Password-Based Encryption Standard," version 1.5, Nov 1993. 
     * Note that this algorithm implies CBC as the cipher mode and PKCS5Padding as the padding scheme and cannot be used with any other cipher modes or padding schemes.
     */
    public static final String PBEWithMD5AndDES = "PBEWithMD5AndDES";

    /**
     * The password-based encryption algorithm as defined in RSA Laboratories, "PKCS #5: Password-Based Cryptography Standard," version 2.0, September 2000 .
     */
    public static final String PBEWithHmacSHA256AndAES_128 = "PBEWithHmacSHA256AndAES_128";

    /**
     * Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc.
     */
    public static final String RC2 = "RC2";

    /**
     * Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc. (See note prior for ARCFOUR.)
     */
    public static final String RC4 = "RC4";

    /**
     * Variable-key-size encryption algorithms developed by Ron Rivest for RSA Data Security, Inc.
     */
    public static final String RC5 = "RC5";

    /**
     * The RSA encryption algorithm as defined in PKCS #1
     */
    public static final String RSA = "RSA";
}
