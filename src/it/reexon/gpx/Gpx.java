/**
 * 
 */
package it.reexon.gpx;

/**
 * @author marco.velluto
 *
 */
public class Gpx
{
    private String version;
    
    private String creator;
    
    /**
     * @param version is required
     * @param creator is required
     */
    public Gpx(String version, String creator)
    {
        super();
        this.version = version;
        this.creator = creator;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }
}
