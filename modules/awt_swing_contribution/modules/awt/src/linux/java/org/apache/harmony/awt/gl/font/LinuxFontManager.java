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
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.apache.harmony.awt.gl.font.CompositeFont;
import org.apache.harmony.awt.gl.font.FontManager;
import org.apache.harmony.awt.gl.font.FontProperty;

public class LinuxFontManager extends FontManager {

    // set of all available faces supported by a system
    String faces[];

    // weight names according to xlfd structure
    public static final String[] LINUX_WEIGHT_NAMES = {
            "black", "bold", "demibold", "medium", "light"
    };

    // slant names according to xlfd structure
    public static final String[] LINUX_SLANT_NAMES = {
            "i", "o", "r"
    };

    /** Singleton LinuxFontManager instance */
    public static final LinuxFontManager inst = new LinuxFontManager();

    private LinuxFontManager() {
        super();
        faces = LinuxNativeFont.getFaces();
        initFontProperties();
    }

    public void initLCIDTable(){
        LinuxNativeFont.initLCIDsTable(this.tableLCID);
    }

    /**
     * Returns temporary File object to store data from InputStream.
     * This File object saved to `~/.fonts/' folder that is included in the 
     * list of folders searched for font files, and this is where user-specific 
     * font files should be installed.
     */
    public File getTempFontFile()throws IOException{
        File fontFile = File.createTempFile("jFont", ".ttf", new File(System.getProperty("user.home") +"/.fonts"));
        fontFile.deleteOnExit();

        return fontFile;
    }

    /**
     * Creates and returns logical font peer. 
     * 
     * @param logicalName logical font family name
     * @param font specified Font object
     * @return In case of non-null fontproperties for the specified logical 
     * name CompositeFont object is returned, otherwise LinuxFont object is 
     * returned.
     */
    private FontPeer CreateLogicalFont(String logicalName, Font font){
        FontProperty[] fps = getFontProperties(logicalName.toLowerCase() + "." + font.getStyle());
        if (fps != null){
            return new CompositeFont(font, fps, logicalName);
        }
        return new LinuxFont(font, true, logicalName, logicalName, null);
    }

    public FontPeer createFont(Font font){
        String fontName = font.getName();
        if (isFontLogical(font)){
            return CreateLogicalFont(fontName, font);
        }

        String fontStyle = null;
        String family = null;
        String face = null;

        int faceIndex = getFaceIndex(fontName);
        // Check if name parameter is face name
        if (faceIndex != -1){
            fontStyle = LinuxNativeFont.getFontStyle(faceIndex);
            family = LinuxNativeFont.getFamily(faceIndex);
            face = fontName;
        } else
            // Check if name parameter is family name
            if(isFamilyExist(fontName)){
                family = fontName;
            } else {
                return CreateLogicalFont(FontManager.LOGICAL_FONT_NAMES[3], font);  // "dialog"
            }
        return new LinuxFont(font, false, family, face, fontStyle);
    }

    public FontPeer createFont(FontProperty fp, int size){
        LinuxFontProperty lfp = (LinuxFontProperty)fp;
        return new LinuxFont(lfp, size);
    }

    /**
     * Initializes fProperties array field for the current system configuration font
     * property file.
     * 
     * RuntimeException is thrown if font property contains incorrect format of 
     * xlfd string.
     * 
     * @return true is success, false if font property doesn't exist or doesn't
     * contain roperties. 
     */
    public boolean initFontProperties(){
        File fpFile = getFontPropertyFile();
        if (fpFile == null){
            return false;
        }

        Properties props = getProperties(fpFile);
        if (props == null){
            return false;
        }

        for (int i=0; i < LOGICAL_FONT_NAMES.length; i++){
            String lName = LOGICAL_FONT_NAMES[i];
            for (int j=0; j < STYLE_NAMES.length; j++){
                String styleName = STYLE_NAMES[j];
                Vector propsVector = new Vector();

                // Number of entries for a logical font
                int numComp = 0;
                // Is more entries for this style and logical font name left
                boolean moreEntries = true;
                String value = null;

                while(moreEntries){
                    // Component Font Mappings property name
                    String property = FONT_MAPPING_KEYS[0].replaceAll("LogicalFontName", lName).replaceAll("StyleName", styleName).replaceAll("ComponentIndex", String.valueOf(numComp));
                    value = props.getProperty(property);

                    // If the StyleName is omitted, it's assumed to be plain
                    if ((j == 0) && (value == null)){
                        property = FONT_MAPPING_KEYS[1].replaceAll("LogicalFontName", lName).replaceAll("ComponentIndex", String.valueOf(numComp));
                        value = props.getProperty(property);
                    }

                    if (value != null){
                        String[] fields = parseXLFD(value);

                        if (fields == null){
                            throw new RuntimeException("xfld parse string error: " + value);
                        }
                        
                        String fontName = fields[1];
                        String weight = fields[2];
                        String italic = fields[3];

                        int style = getBoldStyle(weight) | getItalicStyle(italic);
                        // Component Font Character Encodings property value
                        String encoding = props.getProperty(FONT_CHARACTER_ENCODING.replaceAll("LogicalFontName", lName).replaceAll("ComponentIndex", String.valueOf(numComp)));

                        // Exclusion Ranges property value
                        String exclString = props.getProperty(EXCLUSION_RANGES.replaceAll("LogicalFontName", lName).replaceAll("ComponentIndex", String.valueOf(numComp)));
                        int[] exclRange = parseIntervals(exclString);

                        FontProperty fp = new LinuxFontProperty(lName, styleName, null, fontName, value, style, exclRange, encoding);

                        propsVector.add(fp);
                        numComp++;
                    } else {
                        moreEntries = false;
                    }
                }
                fProperties.put(LOGICAL_FONT_NAMES[i] + "." + j, propsVector);
            }
        }

        return true;

    }

    /**
     * Returns style according to the xlfd weight string.
     * If weight string is incorrect returned value is Font.PLAIN
     * 
     * @param str weight name String
     */
    private int getBoldStyle(String str){
        for (int i = 0; i < LINUX_WEIGHT_NAMES.length;i++){
            if (str.equalsIgnoreCase(LINUX_WEIGHT_NAMES[i])){
                return (i < 3) ? Font.BOLD : Font.PLAIN;
            }
        }
        return Font.PLAIN;
    }
    
    /**
     * Returns style according to the xlfd slant string.
     * If slant string is incorrect returned value is Font.PLAIN
     * 
     * @param str slant name String
     */
    private int getItalicStyle(String str){
        for (int i = 0; i < LINUX_SLANT_NAMES.length;i++){
            if (str.equalsIgnoreCase(LINUX_SLANT_NAMES[i])){
                return (i < 2) ? Font.ITALIC : Font.PLAIN;
            }
        }
        return Font.PLAIN;
    }

    /**
     * Parse xlfd string and returns array of Strings with separate xlfd 
     * elements.<p>
     * 
     * xlfd format:
     *      -Foundry-Family-Weight-Slant-Width-Style-PixelSize-PointSize-ResX-ResY-Spacing-AvgWidth-Registry-Encoding
     * @param xlfd String parameter in xlfd format
     */
    public static String[] parseXLFD(String xlfd){
        int fieldsCount = 14;
        String fieldsDelim = "-";
        String[] res = new String[fieldsCount];
        if (!xlfd.startsWith(fieldsDelim)){
            return null;
        }

        xlfd = xlfd.substring(1);
        int i=0;
        int pos;
        for (i=0; i < fieldsCount-1; i++){
            pos = xlfd.indexOf(fieldsDelim);
            if (pos != -1){
                res[i] = xlfd.substring(0, pos);
                xlfd = xlfd.substring(pos + 1);
            } else {
                return null;
            }
        }
        pos = xlfd.indexOf(fieldsDelim);

        // check if no fields left
        if(pos != -1){
            return null;
        }
        res[fieldsCount-1] = xlfd;

        return res;
    }

    public int getFaceIndex(String faceName){
        for (int i = 0; i < faces.length; i++) {
            if(faces[i].equals(faceName)){
                return i;
            }
        }
        return -1;
    }

    public String[] getAllFamilies(){
        if (allFamilies == null){
            allFamilies = LinuxNativeFont.getFamilies();
        }
        return allFamilies;
    }

    public Font[] getAllFonts(){
        Font[] fonts = new Font[faces.length];
        for (int i =0; i < fonts.length;i++){
            fonts[i] = new Font(faces[i], Font.PLAIN, 1);
        }
        return fonts;
    }

}
