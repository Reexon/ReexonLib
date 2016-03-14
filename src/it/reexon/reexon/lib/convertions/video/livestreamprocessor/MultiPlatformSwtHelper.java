/* This file is part of Livestream Processor.
 * Copyright (C) 2013  Andrew Kallmeyer 
 *  
 * Livestream Processor is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 *   
 * Livestream Processor is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
 * GNU General Public License for more details. 
 *  
 * You should have received a copy of the GNU General Public License 
 * along with Livestream Processor.  If not, see <http://www.gnu.org/licenses/>. 
 */
 
package it.reexon.reexon.lib.convertions.video.livestreamprocessor; 
 
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.Manifest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.reexon.reexon.lib.eclipse.RsrcURLStreamHandlerFactory; 
 
public class MultiPlatformSwtHelper { 
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPlatformSwtHelper.class); 
    private static final String DEFAULT_JAR_FILES_PATH = ""; 
 
    private final String jarFilesPath; 
 
    public MultiPlatformSwtHelper() { 
        this(null); 
    } 
 
    public MultiPlatformSwtHelper(final String jarFilesPath) { 
        if (jarFilesPath == null) { 
            this.jarFilesPath = DEFAULT_JAR_FILES_PATH; 
        } else { 
            this.jarFilesPath = jarFilesPath; 
        } 
    } 
 
    public void addSwtPlatformDependentJarURLToSystemClassLoader() { 
        this.addSwtPlatformDependentJarURLToURLClassLoader((URLClassLoader) ClassLoader.getSystemClassLoader()); 
    } 
 
    public void addSwtPlatformDependentJarURLToURLClassLoader(final URLClassLoader classLoader) { 
        try { 
            URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(classLoader)); 
            final URL swtFileUrl = this.getSwtPlatformDependentJarFileUrl(); 
            LOGGER.debug("Adding swt jar file {} on classpath.", swtFileUrl); 
             
             
            final Method addUrlMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class); 
            addUrlMethod.setAccessible(true); 
            addUrlMethod.invoke(classLoader, swtFileUrl); 
        } catch (final Exception e) { 
            LOGGER.error("Unable to add the swt jar", e); 
        } 
    } 
 
    public String getSwtPlatformDependentJarPath() { 
        return jarFilesPath + "" + "org.eclipse.swt" + getSwtFileNameOsSuffix() + getSwtFileArchSuffix() + getSwtVersionSuffix() + ".jar"; 
    } 
 
    public URL getSwtPlatformDependentJarFileUrl() throws MalformedURLException { 
        return new URL("rsrc:" + getSwtPlatformDependentJarPath()); 
    } 
 
    public String getSwtFileArchSuffix() { 
        final String osName = System.getProperty("os.name").toLowerCase(); 
        final String osArch = System.getProperty("os.arch").toLowerCase(); 
        final String swtFileNameArchSuffix; 
        if (osArch.contains("64")) { 
            swtFileNameArchSuffix = ".x86_64"; 
        } else { 
            swtFileNameArchSuffix = osName.contains("mac") ? "" : ".x86"; 
        } 
        return swtFileNameArchSuffix; 
    } 
 
    public String getSwtFileNameOsSuffix() { 
        final String osName = System.getProperty("os.name").toLowerCase(); 
        final String swtFileNameOsPart; 
        if (osName.contains("win")) { 
            swtFileNameOsPart = ".win32.win32"; 
        } else if (osName.contains("mac")) { 
            swtFileNameOsPart = ".cocoa.macosx"; 
        } else if (osName.contains("linux") || osName.contains("nix")) { 
            swtFileNameOsPart = ".gtk.linux"; 
        } else { 
            throw new RuntimeException("Unsupported OS name: " + osName); 
        } 
        return swtFileNameOsPart; 
    } 
 
    public String getSwtVersionSuffix() { 
        Enumeration<URL> resources = null; 
        try { 
            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF"); 
        } catch (final IOException e) { 
            LOGGER.error("Unable to reat swt version from manifest file", e); 
        } 
 
        String swtVersion = null; 
        if (resources != null) { 
            while (resources.hasMoreElements()) { 
                try { 
                    final Manifest manifest = new Manifest(resources.nextElement().openStream()); 
                    final String tempSwtVersion = manifest.getMainAttributes().getValue("SWT-Version"); 
                    if (tempSwtVersion != null) { 
                        swtVersion = tempSwtVersion; 
                    } 
                } catch (final IOException e) { 
                    LOGGER.warn("Unable to read manifest", e); 
                } 
            } 
        } 
        return (swtVersion != null) ? "-" + swtVersion : ""; 
    } 
}
