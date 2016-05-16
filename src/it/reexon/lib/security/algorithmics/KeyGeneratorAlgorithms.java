/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.algorithmics;

import java.util.List;

import it.reexon.lib.list.ListUtils;


/**
 * The following algorithm names can be specified when requesting an instance of KeyGenerator.
 * 
 * @author Marco Velluto
 * @since Java 1.8
 */
//TODO make to enum
public class KeyGeneratorAlgorithms
{
    private KeyGeneratorAlgorithms()
    {}

    /**
     * Key generator for use with the AES algorithm.
     */
    public static final String AES = "AES";

    /**
     * Key generator for use with the ARCFOUR (RC4) algorithm.
     */
    public static final String ARCFOUR = "ARCFOUR";

    /**
     *  Key generator for use with the Blowfish algorithm. 
     */
    public static final String Blowfish = "Blowfish";

    //    /**
    //     * Key generator for use with the DES algorithm.
    //     */
    //    public static final String DES = "DES";

    //    /**
    //     *  Key generator for use with the DESede (triple-DES) algorithm. 
    //     */
    //    public static final String DESede = "DESede";

    /**
     * Key generator for use with the HmacMD5 algorithm.
     */
    public static final String HmacMD5 = "HmacMD5";

    /**
     * Keys generator for use with the various flavors of the HmacSHA algorithms.
     */
    public static final String HmacSHA1 = "HmacSHA1";

    /**
     * Keys generator for use with the various flavors of the HmacSHA algorithms.
     */
    public static final String HmacSHA224 = "HmacSHA224";

    /**
     * Keys generator for use with the various flavors of the HmacSHA algorithms.
     */
    public static final String HmacSHA256 = "HmacSHA256";

    /**
     * Keys generator for use with the various flavors of the HmacSHA algorithms.
     */
    public static final String HmacSHA384 = "HmacSHA384";

    /**
     * Keys generator for use with the various flavors of the HmacSHA algorithms.
     */
    public static final String HmacSHA512 = "HmacSHA512";

    /**
     * Key generator for use with the RC2 algorithm.
     */
    public static final String RC2 = "RC2";

    public static final List<String> getAlgorithms()
    {
        return ListUtils.createList(AES, ARCFOUR, Blowfish, HmacMD5, HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA512, RC2);
    }
}
