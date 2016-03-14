package it.reexon.reexon.lib.crypt.enums;

public enum AlgorithmsEnum
{
    AES, // Constructs secret keys for use with the AES algorithm.
    ARCFOUR, //Constructs secret keys for use with the ARCFOUR algorithm.
    DES, //Constructs secrets keys for use with the DES algorithm.
    DESede, //Constructs secrets keys for use with the DESede (Triple-DES) algorithm.
    
    RSA, 
    
    PBEWithMD5AndDES, 
    PBEWithHmacSHA256AndAES_128,
    
    PBKDF2WithHmacSHA256; //Password-based key-derivation algorithm found in PKCS #5 2.0 using the specified pseudo-random function 
}

/**
 * PBEWith<digest>And<encryption>
 * PBEWith<prf>And<encryption>
 * 
 * Secret-key factory for use with PKCS5 password-based encryption, where <digest> is a message digest, <prf> is a pseudo-random function, and <encryption> is an encryption algorithm. 
 *   Examples:
 *   ##PBEWithMD5AndDES (PKCS #5, 1.5),
 *   ##PBEWithHmacSHA256AndAES_128 (PKCS #5, 2.0)
 *   Note: These all use only the low order 8 bits of each password character.       
 * 
 * ------------------------
 * 
 * PBKDF2With<prf>
 * Password-based key-derivation algorithm found in PKCS #5 2.0 using the specified pseudo-random function (<prf>). 
 * Example: PBKDF2WithHmacSHA256.
 * 
 * */
