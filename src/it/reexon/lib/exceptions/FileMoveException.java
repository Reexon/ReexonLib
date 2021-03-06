/**
 *  Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.exceptions;

/**
 * @author Marco Velluto
 * @since Java 1.7
 */
public class FileMoveException extends RuntimeException
{

    private static final long serialVersionUID = 1L;

    public FileMoveException()
    {
        super();
    }

    public FileMoveException(String message, Throwable error)
    {
        super(message, error);
    }

    public FileMoveException(String message)
    {
        super(message);
    }

    public FileMoveException(Throwable error)
    {
        super(error);
    }
}
