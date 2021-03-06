/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.algorithmics;

import java.util.List;

import it.reexon.lib.list.ListUtils;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public final class SecureRandomAlgorithmics
{
    private SecureRandomAlgorithmics()
    {}

    //          NOT WORKING in JAVA 1.8
    //    /**
    //     * Obtains random numbers from the underlying native OS.
    //     * No assertions are made as to the blocking nature of generating these numbers.
    //     */
    //    public static final String NativePRNG = "NativePRNG";

    //    /**
    //     * Obtains random numbers from the underlying native OS, blocking if necessary.
    //     * For example, /dev/random on UNIX-like systems.
    //     */
    //    public static final String NativePRNGBlocking = "NativePRNGBlocking";

    //    /**
    //     * Obtains random numbers from the underlying native OS, without blocking to prevent applications from excessive stalling.
    //     * For example, /dev/urandom on UNIX-like systems.
    //     */
    //    public static final String NativePRNGNonBlocking = "NativePRNGNonBlocking";

    //    /**
    //     * Obtains random numbers from the underlying installed and configured PKCS11 library.
    //     */
    //    public static final String PKCS11 = "PKCS11";

    /**
     * The name of the pseudo-random number generation (PRNG) algorithm supplied by the SUN provider.                                            
     * This algorithm uses SHA-1 as the foundation of the PRNG.                                                                                  
     * It computes the SHA-1 hash over a true-random seed value concatenated with a 64-bit counter which is incremented by 1 for each operation. 
     * From the 160-bit SHA-1 output, only 64 bits are used.                                                                                  
     */
    public static final String SHA1PRNG = "SHA1PRNG";

    /**
     * Obtains random numbers from the underlying Windows OS.
     */
    public static final String Windows_PRNG = "Windows-PRNG";

    /**
     * Get all algorithms
     * 
     * @return list of algorithms
     */
    public static final List<String> getAlorithms()
    {
        return ListUtils.createList(SHA1PRNG, Windows_PRNG);
    }
}
