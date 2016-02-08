/**
 * 
 */
package it.reexon.reexonlib.gpx.validators;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;

import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import it.reexon.reexonlib.gpx.validators.implementations.GPXValidator;

/**
 * @author marco.velluto
 *
 */
public class GPXValidaroImpl extends Validator implements GPXValidator
{

    @Override
    public boolean isValid(File file)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ErrorHandler getErrorHandler()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LSResourceResolver getResourceResolver()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setResourceResolver(LSResourceResolver resourceResolver)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void validate(Source source, Result result) throws SAXException, IOException
    {
        // TODO Auto-generated method stub
        
    }


}
