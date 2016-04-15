/** Copyright (c) 2016 Marco Velluto
 * 
 */
package it.reexon.lib.system.commands.ping;

import org.apache.commons.lang3.StringUtils;

import it.reexon.lib.system.commands.ping.enums.SystemEnum;


/**
 * @author marco.velluto
 */
public class PingArguments
{
    private String url;
    private Integer count;
    private Long timeout;
    private Integer payload_bytes;
    private Integer interval;
    private Integer ttl;
    private SystemEnum system;

    public static class Builder
    {

        private PingArguments arguments;

        public Builder(SystemEnum systemEnum)
        {
            this.arguments = new PingArguments();
            this.arguments.setSystem(systemEnum);
        }

        public Builder setUrl(String url)
        {
            this.arguments.url = url;
            return this;
        }

        public Builder setCount(int count)
        {
            this.arguments.count = count;
            return this;
        }

        public Builder setTimeout(long timeout)
        {
            this.arguments.timeout = timeout;
            return this;
        }

        public Builder setBytes(int bytes)
        {
            this.arguments.payload_bytes = bytes;
            return this;
        }

        public Builder setTtl(int ttl)
        {
            this.arguments.ttl = ttl;
            return this;
        }

        /**
         * -i
         * 
         * @param interval
         * @return
         */
        public Builder setInterval(Integer interval)
        {
            this.arguments.interval = interval;
            return this;
        }

        public PingArguments build()
        {
            return arguments;
        }
    }

    /**
     * -c       Send N packets specified with -c
     * @return
     */
    //Unix Systems
    public String getCommand()
    {
        if (StringUtils.isBlank(this.url))
            throw new IllegalArgumentException("URL is mandatory");

        if (this.system == SystemEnum.UNIX)
        {
            return this.unixCommand();
        }
        else if (this.system == SystemEnum.WINDOWS)
        {
            return this.windowsCommand();
        }
        else
        {
            throw new RuntimeException("Not foud command to system: " + this.system);
        }
    }

    /**
     * -n Count         Determines the number of echo requests to send. The default is 4 requests.
     * -w Timeout       Enables you to adjust the time-out (in milliseconds). The default is 1,000 (a 1-second time-out).
     * -l Size          Enables you to adjust the size of the ping packet. The default size is 32 bytes.
     * -f               Sets the Do Not Fragment bit on the ping packet. By default, the ping packet allows fragmentation.
     * 
     * @see https://technet.microsoft.com/en-us/library/cc737478(v=ws.10).aspx
     * @return
     */
    private String windowsCommand()
    {
        return this.createCommand("-n", "-w", "-l");
    }

    /**
     * @see http://www.thegeekstuff.com/2009/11/ping-tutorial-13-effective-ping-command-examples/
     * @return
     */
    private String unixCommand()
    {
        return createCommand("-c", "-W", "-s");
    }

    /**
     * 
     * @param coutn 
     *          * (Windows: -c) 
     *          * (Unix: -n)
     *          
     * @param timeout 
     *                  * (Windows: -w) 
     *                  * (Unix: -W)
     *                  
     * @param payload_bytes
     *                  * (Windows: -l)
     *                  * (Unix: -s)
     * @return
     */
    private String createCommand(String count, String timeout, String payload_bytes)
    {
        if (StringUtils.isBlank(this.url))
            throw new IllegalArgumentException("URL is mandatory");

        StringBuilder command = new StringBuilder();

        command.append("ping").append(" ");
        if (this.count != null)
            command.append(count).append(" ").append(this.count).append(" ");
        if (this.timeout != null)
            command.append(timeout).append(" ").append(this.timeout).append(" ");
        if (this.payload_bytes != null)
            command.append(payload_bytes).append(" ").append(this.payload_bytes).append(" ");
        command.append(url);

        return command.toString();
    }

    public String getUrl()
    {
        return url;
    }

    public PingArguments setUrl(String url)
    {
        this.url = url;
        return this;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public Long getTimeout()
    {
        return timeout;
    }

    public void setTimeout(Long timeout)
    {
        this.timeout = timeout;
    }

    public Integer getPayload_bytes()
    {
        return payload_bytes;
    }

    public void setPayload_bytes(Integer payload_bytes)
    {
        this.payload_bytes = payload_bytes;
    }

    public SystemEnum getSystem()
    {
        return system;
    }

    public void setSystem(SystemEnum system)
    {
        this.system = system;
    }

    public Integer getInterval()
    {
        return interval;
    }

    public void setInterval(Integer interval)
    {
        this.interval = interval;
    }

    public Integer getTtl()
    {
        return ttl;
    }

    public void setTtl(Integer ttl)
    {
        this.ttl = ttl;
    }

}
