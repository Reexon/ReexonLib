package it.reexon.reexon.lib.crypt;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;


/**
 * @infoby http://www.cryptographyworld.com/rsa.htm
 * 
 * RSA is a public key algorithm invented by Rivest, Shamir and Adleman. 
 * The key used for encryption is different from (but related to) the key used for decryption.
 *
 * The algorithm is based on modular exponentiation. Numbers e, d and N are chosen with the property that if A is a number less than N, then (Ae mod N)d mod N = A.
 * This means that you can encrypt A with e and decrypt using d. Conversely you can encrypt using d and decrypt using e (though doing it this way round is usually referred to as signing and verification).
 * •       The pair of numbers (e,N) is known as the public key and can be published.
 * •       The pair of numbers (d,N) is known as the private key and must be kept secret.

 * The number e is known as the public exponent, the number d is known as the private exponent, and N is known as the modulus. When talking of key lengths in connection with RSA, what is meant is the modulus length.
 * An algorithm that uses different keys for encryption and decryption is said to be asymmetric.
 * Anybody knowing the public key can use it to create encrypted messages, but only the owner of the secret key can decrypt them.
 * Conversely the owner of the secret key can encrypt messages that can be decrypted by anybody with the public key. Anybody successfully decrypting such messages can be sure that only the owner of the secret key could have encrypted them. This fact is the basis of the digital signature technique.
 * Without going into detail about how e, d and N are related, d can be deduced from e and N if the factors of N can be determined. Therefore the security of RSA depends on the difficulty of factorizing N. Because factorization is believed to be a hard problem, the longer N is, the more secure the cryptosystem. 
 * Given the power of modern computers, a length of 768 bits is considered reasonably safe, but for serious commercial use 1024 bits is recommended.
 * The problem with choosing long keys is that RSA is very slow compared with a symmetric block cipher such as DES, and the longer the key the slower it is. The best solution is to use RSA for digital signatures and for protecting DES keys. Bulk data encryption should be done using DES.
 */
public class RSACrypt
{
    private final static SecureRandom random = new SecureRandom();
    private final static BigInteger one = new BigInteger("1");

    private BigInteger modulus;

    private BigInteger publicKey;
    private BigInteger privateKey;

    public RSACrypt(int bitLength)
    {
        BigInteger p = BigInteger.probablePrime(bitLength / 2, new Random());
        BigInteger q = BigInteger.probablePrime(bitLength / 2, new Random());
//        BigInteger p = BigInteger.probablePrime(bitLength, new Random());
//        BigInteger q = BigInteger.probablePrime(bitLength, new Random());
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        this.modulus = p.multiply(q);
        this.publicKey = new BigInteger("65537");
        this.privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message)
    {
        return message.modPow(publicKey, modulus);
    }

    public BigInteger decrypt(BigInteger encrypted)
    {
        return encrypted.modPow(privateKey, modulus);
    }

    public String encrypt(String message)
    {
        return this.encrypt(new BigInteger(message)).toString();
    }

    public String decrypt(String message)
    {
        return this.decrypt(new BigInteger(message)).toString();
    }

    public BigInteger getModulus()
    {
        return this.modulus;
    }

    public String getPublicKey()
    {
        return this.publicKey.toString() + this.modulus.toString();
    }

    @Override
    public String toString()
    {
        //@f:off
        return  "Public:\t" + this.publicKey + 
                "Private:\t" + this.privateKey + 
                "Modulus:\t" + this.modulus;
        //@f:on
    }

}
