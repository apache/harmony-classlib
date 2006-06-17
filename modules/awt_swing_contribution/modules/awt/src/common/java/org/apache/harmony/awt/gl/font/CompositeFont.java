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
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import org.apache.harmony.awt.gl.CommonGraphics2DFactory;
import org.apache.harmony.awt.gl.font.FontPeerImpl;
import org.apache.harmony.awt.gl.font.FontProperty;

/**
  * CompositeFont class is the implementation of logical font classes. 
  * Every logical font consists of several physical fonts that described 
  * in font.properties file according to the face name of this logical font.
  */
public class CompositeFont extends FontPeerImpl{
    
    // a number of physical fonts that CompositeFont consist of 
    int numFonts;
    
    // font name
    String name;

    // font family name
    String family;

    // font face name
    String face;

    // font postscript name
    String psName;

    // font style
    int style;

    // font size
    float size;
    
    // an array of font properties applicable to this CompositeFont
    FontProperty[] fontProperties;
    
    // an array of font peers applicable to this CompositeFont
    public FontPeerImpl[] physicalFonts;
    
    // missing glyph code field
    int missingGlyphCode = -1;
    
    // line metrics of this font
    LineMetricsImpl nlm = null;
    
    // cached num glyphs parameter of this font that is the sum of num glyphs of 
    // font peers composing this font
    int cachedNumGlyphs = -1;
    
    /**
     * Creates CompositeFont object that is corresponding to the specified logical 
     * family name.
     * 
     * @param font Font object CompositeFont is to be created from
     * @param fProperties an array of FontProperties describing physical fonts - 
     * parts of logical font
     * @param familyName family name of the logical font
     */
    public CompositeFont(Font font, FontProperty[] fProperties, String familyName){
        this.size = font.getSize();
        this.name = font.getName();
        this.family = familyName;
        this.style = font.getStyle();
        this.face = family.concat(".").concat(FontManager.STYLE_NAMES[style]);
        this.psName = family.toLowerCase().concat(".").concat(FontManager.STYLE_NAMES[style]);
        this.fontProperties = fProperties;// !! Supposed that fProperties parameter != null
        this.numFonts = fontProperties.length;
        physicalFonts = new FontPeerImpl[numFonts];
        for (int i = 0; i < numFonts; i++){
            FontProperty fp = fontProperties[i];
            physicalFonts[i] = (FontPeerImpl)CommonGraphics2DFactory.inst.getFontManager().createFont(fp, (int)size);
        }

        setDefaultLineMetrics("", null);
        this.uniformLM = false;
    }
    
    /**
     * Returns the index of the FontPeer in array of physical fonts that is applicable 
     * for the given character. This font has to have the highest priority among fonts
     * that can display this character and don't have exclusion range covering 
     * specified character. If there is no desired fonts -1 is returned.
     * 
     * @param chr specified character
     * @return index of the font from the array of physical fonts that will be used 
     * during processing of the specified character. 
     */
    public int getCharFontIndex(char chr){
        for (int i = 0; i < numFonts; i++){
            if (fontProperties[i].isCharExcluded(chr)){
                continue;
            }
            if (physicalFonts[i].canDisplay(chr)){
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the index of the FontPeer in array of physical fonts that is applicable 
     * for the given character. This font has to have the highest priority among fonts
     * that can display this character and don't have exclusion range covering 
     * specified character. If there is no desired fonts default value is returned.
     * 
     * @param chr specified character
     * @param defaultValue default index that is returned if the necessary font couldn't be found.
     * @return index of the font from the array of physical fonts that will be used 
     * during processing of the specified character. 
     */
    public int getCharFontIndex(char chr, int defaultValue){
        for (int i = 0; i < numFonts; i++){
            if (fontProperties[i].isCharExcluded(chr)){
                continue;
            }
            if (physicalFonts[i].canDisplay(chr)){
                return i;
            }
        }

        return defaultValue;
    }

    /**
     * Returns true if one of the physical fonts composing this font CompositeFont 
     * can display specified character.
     *   
     * @param chr specified character
     */
    public boolean canDisplay(char chr){
        return (getCharFontIndex(chr) != -1);
    }
    
    /**
     * Returns logical ascent (in pixels)
     */
    public int getAscent(){
        return nlm.getLogicalAscent();
    }
    
    /**
     * Returns LineMetrics instance scaled according to the specified transform.  
     * 
     * @param str specified String 
     * @param frc specified FontRenderContext 
     * @param at specified AffineTransform
     */
    public LineMetrics getLineMetrics(String str, FontRenderContext frc , AffineTransform at){
        LineMetricsImpl lm = (LineMetricsImpl)(this.nlm.clone());
        lm.setNumChars(str.length());

        if ((at != null) && (!at.isIdentity())){
            lm.scale((float)at.getScaleX(), (float)at.getScaleY());
        }

        return lm;
    }

    /**
     * Returns cached LineMetrics instance for the null string or creates it if
     * it wasn't cached yet.
     */
    public LineMetrics getLineMetrics(){
        if (nlm == null){
            setDefaultLineMetrics("", null);
        }

        return this.nlm;
    }

    /**
     * Creates LineMetrics instance and set cached LineMetrics field to it.
     * Created LineMetrics has maximum values of the idividual metrics of all
     * composing physical fonts. If there is only one physical font - it's 
     * LineMetrics object is returned.
     * 
     * @param str specified String 
     * @param frc specified FontRenderContext 
     */
    private void setDefaultLineMetrics(String str, FontRenderContext frc){
        LineMetrics lm = physicalFonts[0].getLineMetrics(str, frc, null);
        float maxCharWidth = (float)physicalFonts[0].getMaxCharBounds(frc).getWidth();

        if (numFonts == 1) {
            this.nlm = (LineMetricsImpl)lm;
            return;
        }

        float[] baselineOffsets = lm.getBaselineOffsets();
        int numChars = str.length();

        // XXX: default value - common for all Fonts
        int baseLineIndex = lm.getBaselineIndex();

        float maxUnderlineThickness = lm.getUnderlineThickness();
        float maxUnderlineOffset = lm.getUnderlineOffset();
        float maxStrikethroughThickness = lm.getStrikethroughThickness();
        float minStrikethroughOffset = lm.getStrikethroughOffset();
        float maxLeading = lm.getLeading();  // External leading
        float maxHeight = lm.getHeight();   // Height of the font ( == (ascent + descent + leading))
        float maxAscent = lm.getAscent();   // Ascent of the font
        float maxDescent = lm.getDescent(); // Descent of the font

        for (int i = 1; i < numFonts; i++){
            lm = physicalFonts[i].getLineMetrics(str, frc, null);
            if (maxUnderlineThickness < lm.getUnderlineThickness()){
                maxUnderlineThickness = lm.getUnderlineThickness();
            }

            if (maxUnderlineOffset < lm.getUnderlineOffset()){
                maxUnderlineOffset = lm.getUnderlineOffset();
            }

            if (maxStrikethroughThickness < lm.getStrikethroughThickness()){
                maxStrikethroughThickness = lm.getStrikethroughThickness();
            }

            if (minStrikethroughOffset > lm.getStrikethroughOffset()){
                minStrikethroughOffset = lm.getStrikethroughOffset();
            }

            if (maxLeading < lm.getLeading()){
                maxLeading = lm.getLeading();
            }

            if (maxAscent < lm.getAscent()){
                maxAscent = lm.getAscent();
            }

            if (maxDescent < lm.getDescent()){
                maxDescent = lm.getDescent();
            }

            float width = (float)physicalFonts[i].getMaxCharBounds(frc).getWidth();
            if(maxCharWidth < width){
                maxCharWidth = width;
            }
            for (int j =0; j < baselineOffsets.length; j++){
                float[] offsets = lm.getBaselineOffsets();
                if (baselineOffsets[j] > offsets[j]){
                    baselineOffsets[j] = offsets[j];
                }
            }

        }
        maxHeight = maxAscent + maxDescent + maxLeading;

        this.nlm =  new LineMetricsImpl(
                numChars,
                baseLineIndex,
                baselineOffsets,
                maxUnderlineThickness,
                maxUnderlineOffset,
                maxStrikethroughThickness,
                minStrikethroughOffset,
                maxLeading,
                maxHeight,
                maxAscent,
                maxDescent,
                maxCharWidth);

    }

    /**
     * Returns the number of glyphs in this CompositeFont object.
     */
    public int getNumGlyphs(){
        if (this.cachedNumGlyphs == -1){

            this.cachedNumGlyphs = 0;

            for (int i = 0; i < numFonts; i++){
                this.cachedNumGlyphs += physicalFonts[i].getNumGlyphs();
            }
        }

        return this.cachedNumGlyphs;
    }

    /**
     * Returns the italic angle of this object.
     */
    public float getItalicAngle(){
        // !! only first physical font used to get this value
        return physicalFonts[0].getItalicAngle();
    }

    /**
     * Returns rectangle that bounds the specified string in terms of composite line metrics.
     * 
     * @param chars an array of chars
     * @param start the initial offset in array of chars
     * @param end the end offset in array of chars
     * @param frc specified FontRenderContext
     */
    public Rectangle2D getStringBounds(char[] chars, int start, int end, FontRenderContext frc){

        int finish = chars.length;

        LineMetrics lm = getLineMetrics();
        float minY = -lm.getAscent();
        float minX = 0;
        float height = lm.getHeight();
        float width = 0;

        for (int i = start; i < end; i++){
            width += charWidth(chars[i]);
        }

        Rectangle2D rect2D = new Rectangle2D.Float(minX, minY, width, height);
        return rect2D;

    }

    /**
     * Returns maximum rectangle that encloses all maximum char bounds of 
     * physical fonts composing this CompositeFont.
     *  
     * @param frc specified FontRenderContext
     */
    public Rectangle2D getMaxCharBounds(FontRenderContext frc){

        Rectangle2D rect2D = physicalFonts[0].getMaxCharBounds(frc);

        if (numFonts == 1){
            return rect2D;
        }

        for (int i = 1; i < numFonts; i++){
            if (physicalFonts[i] != null){
                Rectangle2D currRect2D = physicalFonts[i].getMaxCharBounds(frc);
                float minY = (float)(rect2D.getY() < currRect2D.getY()? rect2D.getY() : currRect2D.getY());
                float maxWidth = (float)(rect2D.getWidth() > currRect2D.getWidth()? rect2D.getWidth() : currRect2D.getWidth());
                float maxHeight = (float)(rect2D.getHeight() > currRect2D.getHeight()? rect2D.getHeight() : currRect2D.getHeight());
                rect2D = new Rectangle2D.Float(0, minY, maxWidth, maxHeight);
            }
        }

        return rect2D;
    }

    /**
     * Returns font name.
     */
    public String getFontName(){
        return face;
    }

    /**
     * Returns font postscript name.
     */
    public String getPSName(){
        return psName;
    }

    /**
     * Returns font family name.
     */
    public String getFamily(){
        return family;
    }

    /**
     * Returns the code of the missing glyph.
     */
    public int getMissingGlyphCode(){
        // !! only first physical font used to get this value
        return physicalFonts[0].getMissingGlyphCode();
    }

    /**
     * Returns Glyph object corresponding to the specified character.
     * 
     * @param ch specified char
     */
    public Glyph getGlyph(char ch){
        for (int i = 0; i < numFonts; i++){
            if (fontProperties[i].isCharExcluded(ch)){
                    continue;
            }
            if (physicalFonts[i].canDisplay(ch)){
                return physicalFonts[i].getGlyph(ch);
            }
        }
        return getDefaultGlyph();
    }
    
    /**
     * Disposes resources. 
     */
    public void dispose(){
        if (true) throw new RuntimeException("Method is not implemented");
    }

    /**
     * Returns width of the char with specified index.
     * 
     * @param ind specified index of the character 
     */
    public int charWidth(int ind){
        if (true) throw new RuntimeException("Method is not implemented");
        return 0;

    }

    /**
     * Returns width of the specified char.
     * 
     * @param c specified character 
     */
    public int charWidth(char c){
        Glyph gl = this.getGlyph(c);
        return (int)gl.getGlyphPointMetrics().getAdvanceX();
    }
    
    /**
     * Returns debug information about this class.
     */
    public String toString(){
    return new String(this.getClass().getName() +
            "[name=" + this.name +
            ",style="+ this.style +
            ",fps=" + this.fontProperties + "]");
    }

    /**
     * Returns Glyph object corresponding to the default glyph.
     */
    public Glyph getDefaultGlyph(){
        // !! only first physical font used to get this value
        return physicalFonts[0].getDefaultGlyph();
    }

}
