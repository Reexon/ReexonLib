/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class KeyFileUtils
{
    private static final Logger logger = LogManager.getLogger(KeyFileUtils.class);
    private static final String ALGORITHM = "AES";

    /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
    
     * @param privateKeyFile            Private Key File
     * @param publicKeyFile             Public Key File
     * @param algorithm                 Key Pair Generator Algorithm
     * @throws FileNotFoundException    If the file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     * @throws IOException              If an I/O error occurred.
     * @throws IllegalArgumentException If at least one parameter is null. If Algorithm is blank or null.
     */
    public static void generateKeyFile(File privateKeyFile, File publicKeyFile) throws FileNotFoundException, IOException
    {
        try
        {
            generateKeyFile(privateKeyFile, publicKeyFile, ALGORITHM);
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.error(e);
        }
    }

    /**
     * Generate key which contains a pair of private and public key using 1024
     * bytes. Store the set of keys in Prvate.key and Public.key files.
    
     * @param privateKeyFile            Private Key File
     * @param publicKeyFile             Public Key File
     * @param algorithm                 Key Pair Generator Algorithm
     * @throws FileNotFoundException    If the file exists but is a directory rather than a regular file, does not exist but cannot be created, or cannot be opened for any other reason.
     * @throws IOException              If an I/O error occurred.
     * @throws NoSuchAlgorithmException If no Provider supports a KeyPairGeneratorSpi implementation for the specified algorithm.
     * @throws IllegalArgumentException If at least one parameter is null. If Algorithm is blank or null.
     */
    public static void generateKeyFile(File privateKeyFile, File publicKeyFile, String algorithm)
            throws FileNotFoundException, IOException, NoSuchAlgorithmException
    {
        if (privateKeyFile == null)
            throw new IllegalArgumentException("Private Key cannot be null");

        if (publicKeyFile == null)
            throw new IllegalArgumentException("Public Key cannot be null");

        if (StringUtils.isBlank(algorithm))
            throw new IllegalArgumentException("Algorithm cannot be blank");

        try (ObjectOutputStream publicKeyOS = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
                ObjectOutputStream privateKeyOS = new ObjectOutputStream(new FileOutputStream(privateKeyFile));)
        {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
            keyGen.initialize(1024);
            final KeyPair key = keyGen.generateKeyPair();

            // Create files to store public and private key
            if (privateKeyFile.getParentFile() != null)
            {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null)
            {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            // Saving the Public key in a file
            publicKeyOS.writeObject(key.getPublic());

            // Saving the Private key in a file
            privateKeyOS.writeObject(key.getPrivate());
        }

    }
}
