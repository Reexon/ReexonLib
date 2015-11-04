/**
 * 
 */
package it.reexon.files.exceptions;

/**
 * @author marco.velluto
 *
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
