/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
/**
 * @author Ilya S. Okomin
 * @version $Revision$
 */
package org.apache.harmony.awt.gl.font;

import java.awt.Font;
import java.awt.peer.FontPeer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import org.apache.harmony.awt.gl.CommonGraphics2DFactory;


public abstract class FontManager {
    
    /**
     * array of font families names
     */
    public String[] allFamilies;

    /**
     * Set of constants applicable to the TrueType 'name' table.
     */
    public static final byte  FAMILY_NAME_ID  = 1;      /* Family name identifier   */
    public static final byte  FONT_NAME_ID  = 4;        /* Full font name identifier    */
    public static final byte  POSTSCRIPT_NAME_ID = 6;   /* PostScript name identifier   */
    public static final short ENGLISH_LANGID = 0x0409;  /* English (United States)language identifier   */
    
    /**
     * Set of constants describing font type.
     */
    public static final byte  FONT_TYPE_TT  = 4;        /* TrueType type (TRUETYPE_FONTTYPE)    */
    public static final byte  FONT_TYPE_T1  = 2;        /* Type1 type    (DEVICE_FONTTYPE)      */
    public static final byte  FONT_TYPE_UNDEF  = 0;     /* Undefined type                       */

    /**
     * FontProperty related constants. 
     */

    public static final String PLATFORM_FONT_NAME = "PlatformFontName";
    public static final String LOGICAL_FONT_NAME = "LogicalFontName";
    public static final String COMPONENT_INDEX = "ComponentIndex";
    public static final String STYLE_INDEX = "StyleIndex";

    public static final String[] FONT_MAPPING_KEYS = {
            "LogicalFontName.StyleName.ComponentIndex", "LogicalFontName.ComponentIndex"
    };

    public static final String FONT_CHARACTER_ENCODING = "fontcharset.LogicalFontName.ComponentIndex";

    public static final String EXCLUSION_RANGES = "exclusion.LogicalFontName.ComponentIndex";

    public static final String FONT_FILE_NAME = "filename.PlatformFontName";

    /**
     * Available font names.
     */
    public static final String[] LOGICAL_FONT_NAMES = {
            "serif", "sansserif", "monospaced", "dialog", "dialoginput"
    };
    
    /**
     * Available font families names.
     */
    public static final String[] LOGICAL_FONT_FAMILIES = {
            "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput"
    };

    /**
     * Set of font style names.
     * Font.getStyle() corresponds to indexes in STYLE_NAMES array.
     */
    public static final String[] STYLE_NAMES = {
            "plain", "bold", "italic", "bolditalic"
    };
    
    /**
     * Logical font names table where font names used as the key
     * and the value is the index of this name.
     */
    private static Hashtable keys = new Hashtable(5);
    
    /**
     * Initialize keys table.
     */
    static {
        for (int i = 0; i < LOGICAL_FONT_NAMES.length; i++){
            keys.put(LOGICAL_FONT_NAMES[i], new Integer(i));
        }
    }
    
    /**
     * Returns index in the LOGICAL_FONT_NAMES array of the logical name specified.
     * 
     * @param lName specified logical font name
     */
    public static int getLogicalIndex(String lName){
        Integer value = (Integer) keys.get(lName);
        return value != null ? value.intValue(): -1;
    }

    /**
     * Set of possible "os" property values.
     */
    public static final String[] OS_VALUES = {
            "NT", "98", "2000", "Me", "XP", // For Windows
            "Redhat", "Turbo", "SuSE"       // For Linux
    };

    /**
     * Set of possible font.property file names.
     * Language, Country, Encoding, OS, Version should be replaced with
     * the values from current configuration.
     */
    public static final String[] FP_FILE_NAMES = {
            "/lib/font.properties.Language_Country_Encoding.OSVersion",
            "/lib/font.properties.Language_Country_Encoding.OS",
            "/lib/font.properties.Language_Country_Encoding.Version",
            "/lib/font.properties.Language_Country_Encoding",
            "/lib/font.properties.Language_Country.OSVersion",
            "/lib/font.properties.Language_Country.OS",
            "/lib/font.properties.Language_Country.Version",
            "/lib/font.properties.Language_Country",
            "/lib/font.properties.Language_Encoding.OSVersion",
            "/lib/font.properties.Language_Encoding.OS",
            "/lib/font.properties.Language_Encoding.Version",
            "/lib/font.properties.Language_Encoding",
            "/lib/font.properties.Language.OSVersion",
            "/lib/font.properties.Language.OS",
            "/lib/font.properties.Language.Version",
            "/lib/font.properties.Language",
            "/lib/font.properties.Encoding.OSVersion",
            "/lib/font.properties.Encoding.OS",
            "/lib/font.properties.Encoding.Version",
            "/lib/font.properties.Encoding",
            "/lib/font.properties.OSVersion",
            "/lib/font.properties.OS",
            "/lib/font.properties.Version",
            "/lib/font.properties"
    };

    public Hashtable fProperties = new Hashtable();

    public FontManager(){
        allFamilies = getAllFamilies();
        /*
         * Creating and registering shutdown hook to free resources
         * before object is destroyed.
         */
        DisposeNativeHook shutdownHook = new DisposeNativeHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
    /**
     * 
     */
    public static final int EMPTY_FONTS_CAPACITY = 10;

    /**
     * Locale - Language ID hash table.
     */
    Hashtable tableLCID = new Hashtable();

    /**
     * Hash table that contains FontPeers instances.
     */
    public Hashtable fonts = new Hashtable();

    /**
     * Vector of keys objects for the fonts Hashtable,
     * that have no references to Java font objects.
     */
    public Vector unrefs = new Vector();

    /**
     * Singleton instance
     */
    public final static FontManager inst = CommonGraphics2DFactory.inst.getFontManager();


    /**
     * Gets singleton instance of FontManager
     * 
     * @return instanse of FontManager implementation
     */
    public static FontManager getInstance() {
        return inst;
    }

    /**
     * Returns platform-dependent Font peer from the specified Font.
     * 
     * @param font the specified Font object
     * @return platform dependent FontPeer implementation created from Font
     */
    public abstract FontPeer createFont(Font font);

    /**
     * Creates platform-dependent Font peer from the specified FontProperty
     *  and size.
     * 
     * @param fp the specified FontProperty object
     * @param size the point size of the Font
     * 
     * @return platform dependent FontPeer implementation with specified  
     * FontProperty and size parameters
     */
    public abstract FontPeer createFont(FontProperty fp, int size);

    /**
     * Returns platform-dependent Font peer created from the specified 
     * FontProperty and size from the table with cached FontPeers instanses.
     * 
     * Note, this method checks whether FontPeer with specified parameters 
     * exists in the table with cached FontPeers' instanses. If there is no needed 
     * instanse - it is created and cached. Required FontPeer instanse 
     * fetched from the table with cached FontPeers' instanses.  
     * 
     * @param fontID string that uniquily describes font 
     * (fontname+fontstyle+fontsize)
     * @param fp the specified FontProperty object
     * @param size the point size of the Font
     * @return platform dependent FontPeer implementation created from 
     * FontProperty and size parameters
     */
    public FontPeer setFont(String fontID, FontProperty fp, int size){
        if (!fonts.containsKey(fontID)){
            FontPeer peer = createFont(fp, size);
            fonts.put(fontID, peer);
        }
        return (FontPeer)fonts.get(fontID);

    }

    /**
     * Returns platform-dependent Font peer created from the specified 
     * Font object from the table with cached FontPeers instanses.
     * 
     * Note, this method checks whether FontPeer with specified parameters 
     * exists in the table with cached FontPeers' instanses. If there is no needed 
     * instanse - it is created and cached. Required FontPeer instanse 
     * fetched from the table with cached FontPeers' instanses. 
     * @param fontStr string that uniquily describes font 
     * (fontname+fontstyle+fontsize)
     * @param font specified Font
     *
     * @return platform dependent FontPeer implementation created from 
     * the specified Font object
     */
    public FontPeer setFont(String fontStr, Font font) {
        if (!fonts.containsKey(fontStr)){
            FontPeer peer = createFont(font);
            fonts.put(fontStr, peer);
        }
        return (FontPeer)fonts.get(fontStr);
    }


    /**
     * Returns font peer instance corresponding to the specified 
     * font string id.
     * 
     * If number of Font references after inrcrement in FontPeer
     * object is equal to 1 it means, that FontPeer was in list of
     * fonts that have no references to Font objects. For this
     * reason, this FontPeer can be deleted from unrefs list.
     * 
     * @param fontStr specified font string id
     */
    public FontPeer getFont(String fontStr){
        FontPeerImpl peer = (FontPeerImpl)fonts.get(fontStr);

        if (peer != null){
            int numRef = peer.addRef();
            if (numRef == 1)
                unrefs.remove(fontStr);
        }
        return peer;
    }

    /**
     * Moves font with specified font string id to the unreferenced font 
     * objects list.
     * 
     * If number of Font references after decrement in FontPeer
     * object is equal to 0 it means, that FontPeer has 
     * no references to Font objects. For this
     * reason, this FontPeer must be added to the first
     * position in unrefs list. If the number of emelemnts in list
     * is equals to it's size - the last element must be deleted, as
     * the low-activity object.
     * 
     * @param fontStr specified font string id
     */
    public void deleteFont(String fontStr){
        FontPeerImpl peer = (FontPeerImpl)fonts.get(fontStr);
        if (peer != null){
            int numRef = peer.removeRef();
            if (numRef == 0){
                if (unrefs.size() == EMPTY_FONTS_CAPACITY){
                    Object obj = unrefs.remove(EMPTY_FONTS_CAPACITY - 1);
                    FontPeerImpl delPeer = (FontPeerImpl)fonts.remove(obj);

                    delPeer.dispose();
                }
                unrefs.add(0, peer);
            }
        }
    }

    /**
     * Return language Id from LCID hash corresponding to the specified locale
     * 
     * @param l specified locale
     */
    public Short getLCID(Locale l){
        if (this.tableLCID.size() == 0){
            initLCIDTable();
        }

        return (Short)tableLCID.get(l.toString());
    }

    /**
     * Platform-dependent LCID table init.
     */
    public abstract void initLCIDTable();

    /**
     * Freeing native resources. This hook is used to avoid 
     * sudden application exit and to free resources created in native code.
     */
    private class DisposeNativeHook extends Thread {

        public void run() {
            try{
                /* Disposing native font peer's resources */
                Enumeration kEnum = fonts.keys();

                while(kEnum.hasMoreElements()){
                    Object key = kEnum.nextElement();
                    FontPeerImpl delPeer = (FontPeerImpl)fonts.remove(key);
                    delPeer.dispose();
                }
            } catch (Throwable t){

            }
        }
      }

    /**
     * Returns File object, created in a directory
     * according to the System, where JVM is being ran.
     *
     * In Linux case we use ".fonts" directory (for fontconfig purpose),
     * where font file from the stream will be stored, hence in LinuxFontManager this
     * method is overridden.
     * In Windows case we use Windows temp directory (default implementation)
     *
     */
    public File getTempFontFile()throws IOException{

        File fontFile = File.createTempFile("jFont", ".ttf");
        fontFile.deleteOnExit();

        return fontFile;
    }

    /**
     * Returns File object with font properties. It's name obtained using current 
     * system configuration properties and locale settings. If no appropriate 
     * file is found method returns null. 
     */
    public static File getFontPropertyFile(){
        File file = null;

        String javaHome = System.getProperty("java.home");
        Locale l = Locale.getDefault();
        String language = l.getLanguage();
        String country = l.getCountry();
        String fileEncoding = System.getProperty("file.encoding");

        String os = System.getProperty("os.name");

        int i = 0;

        // OS names from system properties don't match
        // OS identifiers used in font.peroperty files
        for (; i < OS_VALUES.length; i++){
            if (os.endsWith(OS_VALUES[i])){
                os = OS_VALUES[i];
                break;
            }
        }

        if (i == OS_VALUES.length){
            os = null;
        }

        String version = System.getProperty("os.version");
        String pathname;

        for (i = 0; i < FP_FILE_NAMES.length; i++){
            pathname = FP_FILE_NAMES[i];
            if (os != null){
                pathname = pathname.replaceFirst("OS", os);
            }

            pathname = javaHome + pathname;

            pathname = pathname.replaceAll("Language", language).
                                replaceAll("Country", country).
                                replaceAll("Encoding", fileEncoding).
                                replaceAll("Version", version);

            file = new File(pathname);

            if (file.exists()){
                break;
            }
        }

        return file.exists() ? file : null;
    }

    /**
     * Returns an array of integer range values
     * if the parameter exclusionString has format:
     *          Range
     *          Range [, exclusionString]
     *
     *          Range:
     *              Char-Char
     *
     *          Char:
     *              HexDigit HexDigit HexDigit HexDigit
     * 
     * Method returns null if the specified string is null.
     *  
     * @param exclusionString string parameter in specified format
     */
    public static int[] parseIntervals(String exclusionString){
        int[] results = null;

        if (exclusionString == null){
            return null;
        }

        String[] intervals = exclusionString.split(",");

        if (intervals != null){
            int num = intervals.length;
            if (num > 0){
                results = new int[intervals.length << 1];
                for (int i = 0; i < intervals.length; i++){
                    String ranges[] = intervals[i].split("-");
                    results[i*2] = Integer.parseInt(ranges[0], 16);
                    results[i*2+1] = Integer.parseInt(ranges[1], 16);

                }
            }
        }
        return results;
    }

    /**
     * Returns Properties from the properties file or null if 
     * there is an error with FileInputStream processing.
     * 
     * @param file File object containing properties
     */
    public static Properties getProperties(File file){
        Properties props = null;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            props = new Properties();
            props.load(fis);
        } catch (Exception e){
            System.out.println(e);
        }
        return props;
    }

    /**
     * Returns an array of FontProperties from the properties set
     * with the specified font property name.
     * 
     * @param fpName key of the font properties in the properties set
     */
    public FontProperty[] getFontProperties(String fpName){
        Vector props = (Vector)fProperties.get(fpName);

        if (props == null){
            return null;
        }

        FontProperty[] fps = new FontProperty[props.size()];
        for (int i=0; i < fps.length; i++){
            fps[i] = (FontProperty)props.elementAt(i);
        }
        return fps;
    }

    /**
     * Returns true if specified font is logical.
     * 
     * @param font the specified font object
     */
    public static boolean isFontLogical(Font font){
        String name = font.getName();
        for (int i=0; i<LOGICAL_FONT_NAMES.length; i++ ){
            if (LOGICAL_FONT_NAMES[i].equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if specified family name is available in this 
     * GraphicsEnvironment. 
     * 
     * @param familyName the specified font family name
     */
    public boolean isFamilyExist(String familyName){
        for (int i=0; i<allFamilies.length; i++ ){
            if (familyName.equalsIgnoreCase(allFamilies[i])){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns index of face name from the array of face names available in 
     * this GraphicsEnvironment or -1 if no face name was found. Default return 
     * value is -1, method must be overridden by FontManager implementation.
     * 
     * @param faceName specified face name
     */
    public int getFaceIndex(String faceName){
        return -1;
    }

    /**
     * Returns correct logical family name from the specified font name.
     * 
     * @param fontName specified font name 
     */
    public static String getLogicalFamilyName(String fontName){
        for (int i=0; i<LOGICAL_FONT_NAMES.length; i++ ){
            if (LOGICAL_FONT_NAMES[i].equalsIgnoreCase(fontName)){
                return LOGICAL_FONT_FAMILIES[i];
            }
        }
        return null;
    }

    public abstract String[] getAllFamilies();

    public abstract Font[] getAllFonts();
}
