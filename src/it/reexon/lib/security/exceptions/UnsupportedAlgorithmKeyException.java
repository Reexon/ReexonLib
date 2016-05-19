/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.security.exceptions;

/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class UnsupportedAlgorithmKeyException extends Exception
{
    private static final long serialVersionUID = 8447341670547006886L;

    public UnsupportedAlgorithmKeyException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UnsupportedAlgorithmKeyException(String message)
    {
        super(message);
    }

    public UnsupportedAlgorithmKeyException(Throwable cause)
    {
        super(cause);
    }

}
