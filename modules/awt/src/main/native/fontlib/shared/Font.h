/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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
 * @author Dmitriy S. Matveev, Viskov Nikolay 
 * @version $Revision$
 */
#ifndef __FONT_H__
#define __FONT_H__

#include <map>
#include "Glyph.h"

typedef enum StyleNameTag {
    Regular = 0, 
    Bold = 1, 
    Italic = 2, 
    BoldItalic = 3
} StyleName;

typedef enum FlagsTag {ANGLE, IS_FIXED_PITCH, BOLD} Flags;

static const unsigned char FONT_METRICS_QUANTITY = 8;
static const unsigned char GLYPH_METRICS_QUANTITY = 6;

/*
typedef struct
{
	int numChars;
	int baselineIndex;
	float underlineThickness;
	float underlineOffset;
	float strikethroughThickness;
    float strikethroughOffset;
	float leading;
	float height;
	float descent;
	float ascent;
	float baseLineOffsets[1];
}LineMetrics;
*/

class Font {
public:
	Font();
	virtual ~Font();

	Glyph* getGlyph(unsigned short unicode, unsigned short size);
	Glyph* getDefaultGlyph();
	int	getMissingGlyphCode();

	virtual Glyph* createGlyph(unsigned short unicode, unsigned short size);
	virtual	float* getLineMetrics(); 
	virtual wchar_t* getPSName();
	virtual bool canDisplay(unsigned short c);
	virtual unsigned short getUnicodeByIndex(unsigned short ind);


//protected:
	unsigned short _numGlyphs; // Number of available glyphs
	wchar_t *_famName; // (unsigned short*) Family name
    StyleName _style; // Font style 
	//short *_bitmaps; // - (?)
	float _boundingBox[4]; // Glyphs bounding box - array of 4 shorts
	float _ascent; 
	float _descent;
	float _externalLeading; //lineGap in TrueType
	float _height;
	float _strikeOutSize;
	float _strikeOutOffset;
    float _underlineOffset;
    float _underlineThickness;
	unsigned short _size;
	std::map<const unsigned long, Glyph*> _glyphMap;//(size << 16 + unicode) -- Glyph	
	Flags _flags;	

//	virtual unsigned short* getBitmap();
//	virtual unsigned short* getOutline();
//	virtual unsigned short* getGlyph();
private:
	inline Glyph* findGlyph(unsigned short unicode, unsigned short size, unsigned long id);
};

Font* createFont(wchar_t* family, StyleName sn);
Font* createFont(char* family, StyleName sn);

#endif //__FONT_H__
