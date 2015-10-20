package it.reexon.gpx;

import java.net.URL;
import java.util.ArrayList;


public class Route
{

    /**Optional Information: */
    private String name; //GPS route name
    private String cmt; //GPS route comment
    private String desc; //Description of the route
    private String src; //Source of the route data
    private URL url; //URL associated with the route
    private String urlname; //Text to display on the <url> hyperlink
    private int number; //GPS route number
    private ArrayList<RoutePoint> routepoints; //List of Routepoint

    /**
     * @param name: GPS route name
     * @param cmt: GPS route comment
     * @param desc: Description of the route
     * @param src: Source of the route data
     * @param url: URL associated with the route
     * @param urlname: Text to display on the <url> hyperlink
     * @param number: GPS route number
     * @param routepoints: List of Routepoint
     */
    public Route(String name, String cmt, String desc, String src, URL url, String urlname, int number, ArrayList<RoutePoint> routepoints)
    {
        super();
        this.name = name;
        this.cmt = cmt;
        this.desc = desc;
        this.src = src;
        this.url = url;
        this.urlname = urlname;
        this.number = number;
        this.routepoints = routepoints;
    }

    /**
     * @return the name: GPS route name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name: GPS route name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the cmt: GPS route comment
     */
    public String getCmt()
    {
        return cmt;
    }

    /**
     * @param cmt: GPS route comment
     */
    public void setCmt(String cmt)
    {
        this.cmt = cmt;
    }

    /**
     * @return the desc: Description of the route
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc: Description of the route
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the src: Source of the route data
     */
    public String getSrc()
    {
        return src;
    }

    /**
     * @param src: Source of the route data
     */
    public void setSrc(String src)
    {
        this.src = src;
    }

    /**
     * @return the url: URL associated with the route
     */
    public URL getUrl()
    {
        return url;
    }

    /**
     * @param url: URL associated with the route
     */
    public void setUrl(URL url)
    {
        this.url = url;
    }

    /**
     * @return the urlname: Text to display on the <url> hyperlink
     */
    public String getUrlname()
    {
        return urlname;
    }

    /**
     * @param urlname: Text to display on the <url> hyperlink
     */
    public void setUrlname(String urlname)
    {
        this.urlname = urlname;
    }

    /**
     * @return the number: GPS route number
     */
    public int getNumber()
    {
        return number;
    }

    /**
     * @param number: GPS route number
     */
    public void setNumber(int number)
    {
        this.number = number;
    }

    /**
     * @return the routepoints: List of Routepoint
     */
    public ArrayList<RoutePoint> getRoutepoints()
    {
        return routepoints;
    }

    /**
     * @param routepoints: List of Routepoint
     */
    public void setRoutepoints(ArrayList<RoutePoint> routepoints)
    {
        this.routepoints = routepoints;
    }

}
