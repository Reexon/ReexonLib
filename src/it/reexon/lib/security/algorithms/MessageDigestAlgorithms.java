package it.reexon.lib.security.algorithms;

/**
 * The algorithm names in this section can be specified when generating an instance of MessageDigest.
 * @author marco.velluto
 * @see http://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#MessageDigest
 * @since Java 1.8
 */
public enum MessageDigestAlgorithms
{
    MD2("MD2"), //The MD2 message digest algorithm as defined in RFC 1319.
    MD5("MD5"), //The MD5 message digest algorithm as defined in RFC 1321.

    SHA_1("SHA-1"), //Hash algorithms defined in the FIPS PUB 180-2. 

    /*
     * Secure hash algorithms - SHA-1, SHA-224, SHA-256, SHA-384, SHA-512 - +
     * for computing a condensed representation of electronic data (message). 
     * When a message of any length less than 2^64 bits (for SHA-1, SHA-224, and SHA-256) 
     * or less than 2^128 (for SHA-384 and SHA-512) is input to a hash algorithm, 
     * the result is an output called a message digest. A message digest ranges in length from 160 to 512 bits, 
     * depending on the algorithm.
     * */
    SHA_256("SHA-256"),

    SHA_224("SHA-224"),

    SHA_384("SHA-384"),

    SHA_512("SHA-512");

    private String name;

    private MessageDigestAlgorithms(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}
