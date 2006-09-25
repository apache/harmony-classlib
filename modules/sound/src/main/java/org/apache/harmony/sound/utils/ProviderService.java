package org.apache.harmony.sound.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ProviderService {  
    /**
     * this method return information about default device
     * @param deviceName
     * @return
     */
    public static List<String> getDefaultDeviceDescription(String deviceName) {
        /*
         * obtain the path to the file sound.properties
         */
        String soundPropertiesPath = System.getProperty("java.home") + File.separator + "lib" +
            File.separator + "sound.properties";
        Properties devices = new Properties();
        FileInputStream fstream;
        /*
         * variable that contain information about default device
         */
        List<String> defaultDevice = new ArrayList<String>();
        String str;
        int index;
        /*
         * reading file sound.properties
         */
        try {
            fstream = new FileInputStream(soundPropertiesPath);
            devices.load(fstream);
        } catch (FileNotFoundException e) {
            throw new Error("Configuration file sound.properties doesn't exist!");
        }
        catch (IOException e) {
            throw new Error("An error while reading file sound.properties");
        }
        /*
         * obtain the default device that describes by deviceName
         */
        str = devices.getProperty(deviceName);
        /*
         * if default device doesn't define, than return empty defaultDevice
         */
        if (str == null) {
            return defaultDevice;
        }
        /*
         * the separator between provider and name is '#';
         * find separator of provider and name of device in the notation of default device
         */
        index = str.indexOf("#");
        /*
         * if separator doesn't find, so in the definition of default device contain only 
         * name of provider, and so we add it
         */
        if (index == -1) {
            defaultDevice.add(str);
            defaultDevice.add(null);
        /*
         * if separator is the first symbol, so definition contain only name of device
         */
        } else if (index == 0) {
            defaultDevice.add(null);
            defaultDevice.add(str.substring(index + 1));
        /*
         * if separator is not the first, so we find provider and name of device
         */
        } else {
            defaultDevice.add(str.substring(0, index));
            defaultDevice.add(str.substring(index + 1));
        }
        return defaultDevice;
    }
    
    /**
     * this method return the list of providers
     * @param providerName
     * @return
     */
    public static List<?> getProviders(String providerName) {
        //this variable contain providers
        List<Object> providers = new ArrayList<Object>();
        List<String> providerNames = getProviderNames(providerName);
        Class<?> cl;
        /*
         * obtain classes
         */
        for (int i = 0; i < providerNames.size(); i++) {
            try {
                cl = Class.forName(providerNames.get(i));
                providers.add(cl.newInstance());
            } catch (ClassNotFoundException e) {}
            catch (IllegalAccessException e) {}
            catch (InstantiationException e) {}
        }
        return providers;
    }
    
    /**
     * this method return the list of provider names
     * @param providerPath
     * @return
     */
    public static List<String> getProviderNames(String providerPath) {
        /*
         * this variable contain providers that install in the system
         */
        Properties providers = new Properties();
        /*
         * this variable contain list of all jar-files that are contained in the java-home directory
         */
        List<String> jarFiles = new ArrayList<String>();
        
        File home = new File(System.getProperty("java.home"));
        /*
         * obtain all jar-files that contain in the java home directory;
         * if we already find it, we don't find again
         */
        find(home, jarFiles);
        /*
         * for the each obtained file we search providers in it
         */
        for (int i = 0; i < jarFiles.size(); i++) {
            try {
                JarFile jFile = new JarFile(jarFiles.get(i));
                /*
                 * obtain contents of jar-file
                 */
                Enumeration<JarEntry> files = jFile.entries();

                /*
                 * the list of installed devices be found in the fixed place, and this place describes by
                 * parameter providerPath, so we search this place in each jar-file
                 */
                while (files.hasMoreElements()) {
                    JarEntry filePath = files.nextElement();
                    if (filePath.toString().equals(providerPath)) {
                        /*
                         * if we found such place, we save providers in the variable 'providers' 
                         * and exit from this iteration, because in each file it can be only one time
                         */
                        providers.load(jFile.getInputStream(filePath));
                        break;
                    }
                }           
            } catch (IOException e) {}
        }
        
        List<String> listProviders = new ArrayList<String>();
        Enumeration<?> en = providers.propertyNames();
        while (en.hasMoreElements()) {
            listProviders.add(en.nextElement().toString());
        }
        return listProviders;
    }
    
    /**
     * This function search recursively in the Java home directory all jar-files
     * @param parent
     */
    static private void find(File parent, List<String> jarFiles) {
        /*
         * Obtain the list of files that contains in the directory 'parent'
         */
        String[] listFiles = parent.list();
        /*
         * look through all file names
         */
        for (int i = 0; i < listFiles.length; i++) {
            File file = new File(parent, listFiles[i]);
            /*
             * if new file is directory, than recursively go in it...
             */
            if (file.isDirectory()) {
                find(file, jarFiles);
            } else {
                /*
                 * else if it's file, we check up that it's jar-file. If it's true
                 * we save it
                 */
                if (listFiles[i].endsWith(".jar")) {
                    jarFiles.add(parent.toString() + File.separator + listFiles[i]);
                }
            }
        }
    }
}
