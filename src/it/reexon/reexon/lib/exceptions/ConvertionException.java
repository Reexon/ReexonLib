/**
 *  Copyright (c) 2016 Marco Velluto
 */
package it.reexon.reexon.lib.exceptions;

/**
 * @author Marco Velluto
 * @since Java 1.7
 */
public class ConvertionException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public ConvertionException()
    {
        super();
    }

    public ConvertionException(String message, Throwable error)
    {
        super(message, error);
    }

    public ConvertionException(String message)
    {
        super(message);
    }

    public ConvertionException(Throwable error)
    {
        super(error);
    }
}
