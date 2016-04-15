/**
 * 
 */
package it.reexon.lib.gpx;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author marco.velluto
 *
 */
public class Gpx
{
    /**Required Information   */
    private String version; //GPX schema version used in file
    private String creator; //Program used to create file

    /**Optional file informations */
    private String name; //Descriptive name of the GPX file
    private String desc; //Description of the GPX file
    private String author; //Name of the file's creator
    private String email; //Email address of the file's creator
    private URL url; //URL associated with the file
    private String urlName; //Text to display on the <url> hyperlink
    private Date time; //Creation date/time of the GPX file
    private ArrayList<String> keywords; //Keywords for categorizing the GPX file in a database or search engine
    private String bounds; //Bounding rectangle for the data in the file.

    /**Optional file informations */
    private List<WayPoint> wayPoints; //List of Waypoints
    private List<Route> routes; //List of Routes
    private List<Track> tracks; //List of Tracks

    /**
     * @param version   GPX schema version used in file. Is mandatory
     * @param creator   Program used to create file. Is mandatory
     */
    public Gpx(String version, String creator)
    {
        super();
        this.version = version;
        this.creator = creator;
    }

    /**
     * @param version   GPX schema version used in file
     * @param creator   Program used to create file
     * @param name      Descriptive name of the GPX file
     * @param desc      Description of the GPX file
     * @param author    Name of the file's creator
     * @param email     Email address of the file's creator
     * @param url       URL associated with the file
     * @param urlName   Text to display on the <url> hyperlink
     * @param time      Creation date/time of the GPX file
     * @param keywords  Keywords for categorizing the GPX file in a database or search engine
     * @param bounds    Bounding rectangle for the data in the file.
     * @param wayPoints List of Waypoints
     * @param routes    List of Routes
     * @param tracks    List of Tracks
     */
    public Gpx(String version, String creator, String name, String desc, String author, String email, URL url, String urlName, Date time,
               ArrayList<String> keywords, String bounds, List<WayPoint> wayPoints, List<Route> routes, List<Track> tracks)
    {
        super();
        this.version = version;
        this.creator = creator;
        this.name = name;
        this.desc = desc;
        this.author = author;
        this.email = email;
        this.url = url;
        this.urlName = urlName;
        this.time = time;
        this.keywords = keywords;
        this.bounds = bounds;
        this.wayPoints = wayPoints;
        this.routes = routes;
        this.tracks = tracks;
    }

    /**
     * @return version   GPX schema version used in file
     */
    public String getVersion()
    {
        return version;
    }

    /**
     * @param version   GPX schema version used in file
     */
    public void setVersion(String version)
    {
        this.version = version;
    }

    /**
     * @return creator   Program used to create file
     */
    public String getCreator()
    {
        return creator;
    }

    /**
     * @param creator      Program used to create file
     */
    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    /**
     * @return the name   Descriptive name of the GPX file
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name   Descriptive name of the GPX file
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the desc   Description of the GPX file
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc   Description of the GPX file
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the author   Name of the file's creator
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * @param author   Name of the file's creator
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }

    /**
     * @return the email   Email address of the file's creator
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email   Email address of the file's creator
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the url   URL associated with the file
     */
    public URL getUrl()
    {
        return url;
    }

    /**
     * @param url   URL associated with the file
     */
    public void setUrl(URL url)
    {
        this.url = url;
    }

    /**
     * @return the urlName   Text to display on the <url> hyperlink
     */
    public String getUrlName()
    {
        return urlName;
    }

    /**
     * @param urlName   Text to display on the <url> hyperlink
     */
    public void setUrlName(String urlName)
    {
        this.urlName = urlName;
    }

    /**
     * @return the time   Creation date/time of the GPX file
     */
    public Date getTime()
    {
        return time;
    }

    /**
     * @param time   Creation date/time of the GPX file
     */
    public void setTime(Date time)
    {
        this.time = time;
    }

    /**
     * @return the keywords   Keywords for categorizing the GPX file in a database or search engine
     */
    public ArrayList<String> getKeywords()
    {
        return keywords;
    }

    /**
     * @param keywords   Keywords for categorizing the GPX file in a database or search engine
     */
    public void setKeywords(ArrayList<String> keywords)
    {
        this.keywords = keywords;
    }

    /**
     * @return the bounds   Bounding rectangle for the data in the file.
     */
    public String getBounds()
    {
        return bounds;
    }

    /**
     * @param bounds   Bounding rectangle for the data in the file.
     */
    public void setBounds(String bounds)
    {
        this.bounds = bounds;
    }

    /**
     * @return the wayPoints
     */
    public List<WayPoint> getWayPoints()
    {
        return wayPoints;
    }

    /**
     * @param wayPoints the wayPoints to set
     */
    public void setWayPoints(List<WayPoint> wayPoints)
    {
        this.wayPoints = wayPoints;
    }

    /**
     * @return the routes
     */
    public List<Route> getRoutes()
    {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<Route> routes)
    {
        this.routes = routes;
    }

    /**
     * @return the tracks
     */
    public List<Track> getTracks()
    {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }
}
