/**
 * 
 */
package it.reexon.files.exceptions;

/**
 * @author marco.velluto
 *
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
