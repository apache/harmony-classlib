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
import java.util.Properties;
import java.util.Vector;

import org.apache.harmony.awt.gl.font.FontManager;
import org.apache.harmony.awt.gl.font.FontProperty;

/**
 *
 * Windows FontManager implementation.
 */
public class WinFontManager extends FontManager {

    static final int DEFAULT_PITCH = 0; // GDI DEFAULT_PITCH
    static final int FIXED_PITCH = 1;   // GDI FIXED_PITCH
    static final int VARIABLE_PITCH = 2; // GDI VARIABLE_PITCH

    static final int FF_DONTCARE = (0<<4);  // GDI FF_DONTCARE
    static final int FF_SWISS = (2<<4);     // GDI FF_SWISS
    static final int FF_MODERN = (3<<4);    // GDI FF_MODERN
    static final int FF_ROMAN = (1<<4);     // GDI FF_ROMAN


    /** Available windows charset names */
    public static final String[] WINDOWS_CHARSET_NAMES = {
            "ANSI_CHARSET", "DEFAULT_CHARSET", "SYMBOL_CHARSET", "SHIFTJIS_CHARSET",
            "GB2312_CHARSET", "HANGEUL_CHARSET", "CHINESEBIG5_CHARSET",
            "OEM_CHARSET", "JOHAB_CHARSET", "HEBREW_CHARSET", "ARABIC_CHARSET",
            "GREEK_CHARSET", "TURKISH_CHARSET", "VIETNAMESE_CHARSET", "THAI_CHARSET",
            "EASTEUROPE_CHARSET", "RUSSIAN_CHARSET", "MAC_CHARSET", "BALTIC_CHARSET"
    };

    /** WinFontManager singleton instanse */
    public static final WinFontManager inst = new WinFontManager();

    private WinFontManager() {
        super();
        initFontProperties();
    }

    public void initLCIDTable(){
        NativeFont.initLCIDsTable(this.tableLCID);
    }

    /**
     * Initializes fProperties array field for the current system configuration font
     * property file.
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
            for (int j=0; j < STYLE_NAMES.length; j++){
                Vector propsVector = new Vector();

                // Number of entries for a logical font
                int numComp = 0;
                // Is more entries for this style and logical font name left
                boolean moreEntries = true;
                String value = null;

                while(moreEntries){
                    // Component Font Mappings property name
                    String property = FONT_MAPPING_KEYS[0].replaceAll("LogicalFontName", LOGICAL_FONT_NAMES[i]).replaceAll("StyleName", STYLE_NAMES[j]).replaceAll("ComponentIndex", String.valueOf(numComp));
                    value = props.getProperty(property);

                    // If the StyleName is omitted, it's assumed to be plain
                    if ((j == 0) && (value == null)){
                        property = FONT_MAPPING_KEYS[1].replaceAll("LogicalFontName", LOGICAL_FONT_NAMES[i]).replaceAll("ComponentIndex", String.valueOf(numComp));
                        value = props.getProperty(property);
                    }

                    if (value != null){

                        String fontName = value.substring(0, value.indexOf(","));
                        int ind = fontName.lastIndexOf("Bold");
                        if (ind != -1){
                            fontName = fontName.substring(0, ind-1);
                        } else {
                            ind = fontName.lastIndexOf("Italic");
                            if(ind != -1){
                                fontName = fontName.substring(0, ind-1);
                            }
                        }


                        String charset = value.substring(value.indexOf(",") + 1, value.length());

                        // Font File Names property value
                        String fileName = props.getProperty(FONT_FILE_NAME.replaceAll("PlatformFontName", fontName).replaceAll(" ", "_"));

                        // Exclusion Ranges property value
                        String exclString = props.getProperty(EXCLUSION_RANGES.replaceAll("LogicalFontName", LOGICAL_FONT_NAMES[i]).replaceAll("ComponentIndex", String.valueOf(numComp)));
                        int[] exclRange = parseIntervals(exclString);

                        // Component Font Character Encodings property value
                        String encoding = props.getProperty(FONT_CHARACTER_ENCODING.replaceAll("LogicalFontName", LOGICAL_FONT_NAMES[i]).replaceAll("ComponentIndex", String.valueOf(numComp)));

                        FontProperty fp = new WinFontProperty(fileName, fontName, charset, j, exclRange, encoding);
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
    
    public int getFaceIndex(String faceName){
        for (int i=0; i<NativeFont.faces.length; i++ ){
            if (NativeFont.faces[i].equalsIgnoreCase(faceName))
                return i;
        }
        return -1;
    }

    public Font[] getAllFonts() {
        String faces[] = NativeFont.getAvailableFaces();
        int count = faces.length;
        Font[] fonts = new Font[count];

        for (int i = 0; i < count; i++){
            fonts[i] = new Font(faces[i], Font.PLAIN, 1);
        }

        return fonts;
    }

    public String[] getAllFamilies() {
        if (allFamilies == null){
            allFamilies = NativeFont.getFamilies();
        }
        return allFamilies;
    }

    public FontPeer createPhysicalFontPeer(String name, int style, int size) {
        WindowsFont peer;
        if (isFamilyExist(name)){
            peer = new WindowsFont(name, style, size);
            peer.setFamily(name);
            return peer;
        }
        int faceIndex = getFaceIndex(name); 
        if (faceIndex != -1){
            style |= NativeFont.fontStyles[faceIndex];
            name = NativeFont.getFamily(faceIndex);

            peer = new WindowsFont(name, style, size);
            return peer;
        }
        
        return null;
    }

    public FontPeer createDefaultFont(int style, int size) {
        return new WindowsFont(DEFAULT_NAME, style, size);
    }

}
