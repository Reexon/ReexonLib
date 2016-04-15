package it.reexon.lib.security.algorithms;

/**
 * The following names can be specified as the mode component in a transformation when requesting an instance of Cipher.
 * @author Marco Velluto
 * @since Java 1.8
 */
public class CipherAlgorithmsModes
{
    private CipherAlgorithmsModes()
    {
        // cannot be instantiated.
    }

    /**
     *  No mode.
     */
    public static final String NONE = "NONE";

    /**
     *  Cipher Block Chaining Mode, as defined in FIPS PUB 81.
     */
    public static final String CBC = "CBC";

    /**
     *  Counter/CBC Mode, as defined in  NIST Special Publication SP 800-38C.
     */
    public static final String CCM = "CCM";

    /**
     * Cipher Feedback Mode, as defined in FIPS PUB 81.
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size. 
     * When requesting such a mode, you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding" and "DES/OFB32/PKCS5Padding" transformations. 
     * If no such number is specified, a provider-specific default is used. (For example, the SunJCE provider uses a default of 64 bits for DES.) 
     * Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    public static final String CFB = "CFB";

    /**
     * Cipher Feedback Mode, as defined in FIPS PUB 81.
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size. 
     * When requesting such a mode, you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding" and "DES/OFB32/PKCS5Padding" transformations. 
     * If no such number is specified, a provider-specific default is used. (For example, the SunJCE provider uses a default of 64 bits for DES.) 
     * Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    public static final String CFBx = "CFBx";

    /**
     * A simplification of OFB, Counter mode updates the input block as a counter.
     */
    public static final String CTR = "CTR";

    /**
     * Cipher Text Stealing, as described in Bruce Schneier's book Applied Cryptography-Second Edition, John Wiley and Sons, 1996.
     */
    public static final String CTS = "CTS";

    /**
     * Electronic Codebook Mode, as defined in FIPS PUB 81 (generally this mode should not be used for multiple blocks of data).
     */
    public static final String ECB = "ECB";

    /**
     * Galois/Counter Mode, as defined in  NIST Special Publication SP 800-38D.
     */
    public static final String GCM = "GCM";

    /**
     * Output Feedback Mode, as defined in FIPS PUB 81.
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size. 
     * When requesting such a mode, you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding" and "DES/OFB32/PKCS5Padding" transformations. 
     * If no such number is specified, a provider-specific default is used. (For example, the SunJCE provider uses a default of 64 bits for DES.) 
     * Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    public static final String OFB = "OFB";

    /**
     * Output Feedback Mode, as defined in FIPS PUB 81.
     * Using modes such as CFB and OFB, block ciphers can encrypt data in units smaller than the cipher's actual block size. 
     * When requesting such a mode, you may optionally specify the number of bits to be processed at a time by appending this number to the mode name as shown in the "DES/CFB8/NoPadding" and "DES/OFB32/PKCS5Padding" transformations. 
     * If no such number is specified, a provider-specific default is used. (For example, the SunJCE provider uses a default of 64 bits for DES.) 
     * Thus, block ciphers can be turned into byte-oriented stream ciphers by using an 8-bit mode such as CFB8 or OFB8.
     */
    public static final String OFBx = "OFBx";

    /**
     * Propagating Cipher Block Chaining, as defined by Kerberos V4.
     */
    public static final String PCBC = "PCBC";
}
