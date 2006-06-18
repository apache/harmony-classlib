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
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Hashtable;
import java.util.Locale;

import org.apache.harmony.awt.ContextStorage;
import org.apache.harmony.awt.gl.CommonGraphics2DFactory;
import org.apache.harmony.awt.gl.font.FontManager;
import org.apache.harmony.awt.gl.font.FontPeerImpl;
import org.apache.harmony.awt.gl.font.Glyph;
import org.apache.harmony.awt.gl.font.LineMetricsImpl;
import org.apache.harmony.awt.wtk.linux.LinuxWindowFactory;


/**
 * Linux platform font peer implementation based on Xft and FreeType libraries.
 */
public class LinuxFont extends FontPeerImpl {

    // pitch value of this font
    private int fPitch;

    // available pitch constants
    private static final int PITCH_DONTCARE = 0;
    private static final int PITCH_MONO = 1;
    private static final int PITCH_PROPORTIONAL = 2;

    // Pairs of [begin, end],[..].. unicode ranges values 
    private int[] fontUnicodeRanges;
    
    // table with loaded cached Glyphs
    private Hashtable glyphs = new Hashtable();
    
    // X11 display value
    private long display = 0;

    // X11 screen value
    private int screen = 0;
    
    // font style name of this font peer
    private String fontStyle;

    public LinuxFont(Font font, boolean isLogical, String familyName, String faceName, String fontStyle) {
        /*
         * Workaround : to initialize awt platform-dependent fields and libraries.
         */
        Toolkit.getDefaultToolkit();

        this.name = font.getName();
        this.size = font.getSize();
        this.style = font.getStyle();

        this.display = ((LinuxWindowFactory)ContextStorage.getWindowFactory()).getDisplay();
        this.screen = ((LinuxWindowFactory)ContextStorage.getWindowFactory()).getScreen();

        String family = familyName;
        this.fontFamilyName = familyName;
        this.faceName = faceName;
        this.fontStyle = fontStyle;

        if (isLogical){
            this.psName = familyName;
            int index = FontManager.getLogicalIndex(familyName.toLowerCase());
            this.setPitch(index);
            
            /* Xft chooses Luxi Sans font with fixed width parameter as monospaced 
             * font. At first we check if Courier font is on the system, if it is
             * we create this font as monospaced, otherwise we create XFT font with
             * XFT_MONO spacing.
             */
            if (index == MONOSPACED){
                if (CommonGraphics2DFactory.inst.getFontManager().isFamilyExist("Courier")){
                    family = "Courier";
                }
            }

        }

        pFont = LinuxNativeFont.initializeFont(this, family, style, size, fontStyle);

        initLinuxFont();
    }

    public LinuxFont(LinuxFontProperty lfp, int size) {
        /*
         * Workaround : to initialize awt platform-dependent fields and libraries.
         */
        Toolkit.getDefaultToolkit();

        this.name = lfp.getName();
        this.size = size;
        this.style = lfp.getStyle();

        this.display = ((LinuxWindowFactory)ContextStorage.getWindowFactory()).getDisplay();
        this.screen = ((LinuxWindowFactory)ContextStorage.getWindowFactory()).getScreen();

        pFont = LinuxNativeFont.initializeFontFromFP(this, lfp.getXLFD(), size);
        if (pFont == 0){
            int index = FontManager.getLogicalIndex(lfp.getLogicalName().toLowerCase());
            this.setPitch(index);

            this.fontFamilyName = FontManager.LOGICAL_FONT_FAMILIES[index];
            this.faceName       = FontManager.LOGICAL_FONT_NAMES[index];
            this.psName         = FontManager.LOGICAL_FONT_NAMES[index];

            String family = FontManager.LOGICAL_FONT_FAMILIES[index];

            /* Xft chooses Luxi Sans font with fixed width parameter as monospaced 
             * font. At first we check if Courier font is on the system, if it is
             * we create this font as monospaced, otherwise we create XFT font with
             * XFT_MONO spacing.
             */
            if ((index == MONOSPACED) &&
                    CommonGraphics2DFactory.inst.getFontManager().isFamilyExist("Courier")){
                family = "Courier";
            }

            pFont = LinuxNativeFont.initializeFont(this, family, style, size, fontStyle);
        }
        initLinuxFont();
    }

    /**
     * Initializes some native dependent font information, e.g. number of glyphs, 
     * font metrics, italic angle etc. 
     */
    public void initLinuxFont(){
        this.numGlyphs = LinuxNativeFont.getNumGlyphsNative(pFont);
        this.italicAngle = LinuxNativeFont.getItalicAngleNative(pFont, this.fontType);

        this.nlm = new LinuxLineMetrics(this, null, " ");

        this.ascent = nlm.getLogicalAscent();
        this.descent = nlm.getLogicalDescent();
        this.height = nlm.getHeight();
        this.leading = nlm.getLogicalLeading();
        this.maxAdvance = nlm.getLogicalMaxCharWidth();

        if (this.fontType == FontManager.FONT_TYPE_T1){
            this.defaultChar = 1;
        } else {
            this.defaultChar = 0;
        }

        this.maxCharBounds = new Rectangle2D.Float(0, -nlm.getAscent(), nlm.getMaxCharWidth(), this.height);

        addGlyphs((char) 0x20, (char) 0x7E);
    }


    public boolean canDisplay(char chr) {
        // TODO: to improve performance there is a sence to implement get
        // unicode ranges to check if char can be displayed without
        // native calls in isGlyphExists() method

        return isGlyphExists(chr);
    }

    public LineMetrics getLineMetrics(String str, FontRenderContext frc, AffineTransform at) {
        //TODO: frc isn't used now
        LineMetricsImpl lm = (LineMetricsImpl)(this.nlm.clone());
        lm.setNumChars(str.length());

        if ((at != null) && (!at.isIdentity())){
            lm.scale((float)at.getScaleX(), (float)at.getScaleY());
        }

        return lm;
    }

    public String getPSName() {
        if (psName == null){
            psName = LinuxNativeFont.getFontPSNameNative(this.getFontHandle());
        }
        return psName;
    }

    public String getFamily(Locale l) {
        // TODO: implement localized family
        if (fontType == FontManager.FONT_TYPE_TT){
            return this.getFamily();
        }

        return this.fontFamilyName;
    }

    public String getFontName(Locale l) {
        // TODO: implement localized font name
        if (this.fontType == FontManager.FONT_TYPE_T1){
            return this.name;
        }

        return this.getFontName();
    }


    public int getMissingGlyphCode() {
        return getDefaultGlyph().getGlyphCode();
    }

    public Glyph getGlyph(char index) {
        Glyph result = null;

        Object key = new Integer(index);
        if (glyphs.containsKey(key)) {
            result = (Glyph) glyphs.get(key);
        } else {
            if (this.addGlyph(index)) {
                result = (Glyph) glyphs.get(key);
            } else {
                result = this.getDefaultGlyph();
            }
        }

        return result;
    }

    public Glyph getDefaultGlyph() {
        Glyph result;
        Object key = new Integer(defaultChar);
        if (glyphs.containsKey(key)) {
            result = (Glyph) glyphs.get(key);
        } else {
            if (this.fontType == FontManager.FONT_TYPE_T1){
                // !! Type1 has no default glyphs
                glyphs.put(key, new LinuxGlyph(defaultChar, defaultChar));
                result = (Glyph) glyphs.get(key);
            } else {
                glyphs.put(key, new LinuxGlyph(this.pFont,
                    this.getSize(), defaultChar, LinuxNativeFont.getGlyphCodeNative(this.pFont, defaultChar, this.display)));
                result = (Glyph) glyphs.get(key);
            }
        }

        return result;
    }

    /**
     * Disposes native font handle. If this font peer was created from InputStream 
     * temporary created font resource file is deleted.
     */
    public void dispose(){
        String tempDirName;
        LinuxNativeFont.pFontFree(this.pFont,this.display);
        
        if (this.isCreatedFromStream()) {
            File fontFile = new File(this.getTempFontFileName());
            tempDirName = fontFile.getParent();
            fontFile.delete();
            LinuxNativeFont.RemoveFontResource(tempDirName);
        }
    }

    /**
     * Add glyph to cached Glyph objects in this LinuxFont object.
     * 
     * @param uChar the specified character
     * @return true if glyph of the specified character exists in this
     * LinuxFont or this character is escape sequence character.
     */
   public boolean addGlyph(char uChar) {
        boolean result = false;
        boolean isEscape = false;

        isEscape = ((uChar == '\t') || (uChar == '\n') || (uChar == '\r'));

        int glyphCode = LinuxNativeFont.getGlyphCodeNative(this.pFont, uChar, display);
        if (isEscape || (glyphCode != 0xFFFF)) {
                glyphs.put(new Integer(uChar), new LinuxGlyph(this.pFont,
                            this.getSize(), uChar, glyphCode));
                result = true;
        }

        return result;
    }

   /**
    * Adds range of existing glyphs to this LinuxFont object
    * 
    * @param uFirst the lowest range's bound, inclusive 
    * @param uLast the highest range's bound, exclusive
    */
    public void addGlyphs(char uFirst, char uLast) {
        char index = uFirst;
        if (uLast < uFirst) {
            throw new IllegalArgumentException(
                    "min range bound value is grater than max range bound");
        }
        while (index < uLast) {
            addGlyph(index);
            index++;
        }
    }

    /**
     * Returns true if specified character has corresopnding glyph, false otherwise.  
     * 
     * @param uIndex specified char
     */
    public boolean isGlyphExists(char uIndex) {

/*      for (int i = 0; i < fontUnicodeRanges.length - 1; i += 2) {
            if (uIndex <= fontUnicodeRanges[i + 1]) {
                if (uIndex >= fontUnicodeRanges[i]) {
                    return true;
                } else {
                    return false;
                }
            }
        }
*/
        return (LinuxNativeFont.getGlyphCodeNative(this.getFontHandle(), uIndex, display) != 0xFFFF);
    }

    /**
     *  Returns an array of unicode ranges that are supported by this LinuxFont. 
     */
    public int[] getUnicodeRanges() {
        int[] ranges = new int[fontUnicodeRanges.length];
        System.arraycopy(fontUnicodeRanges, 0, ranges, 0,
                fontUnicodeRanges.length);

        return ranges;
    }

    /**
     * Returns Physical font name in case of predefined in Java logical names
     * If there is no matches - the return value is the string parameter without 
     * changes, in this case GDI chooses font that match to Font parent object 
     * parameters.
     * 
     * @param logFontNameIndex index of the logical font family name
     */
    private void setPitch(int logFontNameIndex){
        switch (logFontNameIndex){
            case DIALOG:
            case SANSSERIF:
            case SERIF:
                this.fPitch = PITCH_PROPORTIONAL;
                break;
            case DIALOGINPUT:
            case MONOSPACED:
                this.fPitch = PITCH_MONO;
                break;
            default:
                this.fPitch = PITCH_DONTCARE;
        }

    }
    
    /**
     * Returns pitch of this font peer. 
     */
    public int getPitch(){
        return this.fPitch;
    }

    /**
     * Return Font object if it was successfully embedded in System
     */
    public static Font embedFont(String absolutePath){
        return LinuxNativeFont.embedFont(absolutePath);
    }

    public String getFontName(){
        if (faceName == null){
            if (this.fontType == FontManager.FONT_TYPE_T1){
                faceName = getFamily();
            } else {
                faceName = LinuxNativeFont.getFontNameNative(pFont);
            }
        }
        return faceName;
    }

    public String getFamily() {
        if (fontFamilyName == null){
            fontFamilyName = LinuxNativeFont.getFamilyNative(pFont);
        }
        return fontFamilyName;
    }
}
