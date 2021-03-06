/**
 *  Copyright (c) 2016 Marco Velluto
 */
package it.reexon.lib.exceptions;

/**
 * 
 * @author Marco Velluto
 * @since Java 1.7
 */
public class NotFoundException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private Throwable throwable;
    private String message;

    public NotFoundException(String message)
    {
        super();
        this.message = message;
    }

    public NotFoundException(Throwable throwable, String message)
    {
        super();
        this.throwable = throwable;
        this.message = message;
    }

    public Throwable getThrowable()
    {
        return throwable;
    }

    public void setThrowable(Throwable throwable)
    {
        this.throwable = throwable;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
