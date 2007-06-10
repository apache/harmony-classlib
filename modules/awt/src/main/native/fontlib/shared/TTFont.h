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
 * @author Dmitriy S. Matveev
 * @version $Revision$
 */
#ifndef __TTFONT_H__
#define __TTFONT_H__

#include "Font.h"
#include "Glyph.h"
#include "Outline.h"
#include "TTCurve.h"

typedef struct
{
	short format; //format of the 'loca' table
	unsigned long* offsets;
} GlyphOffsets;

typedef struct
{
	short format;
	void* TableEncode;
} TableEncode;

typedef struct
{
	unsigned short adwance_width;
	short lsb;
}HMetrics;

class TTGlyph;

class TTFont:public Font
{
friend class TTGlyph;
private:
	char* _pathToFile; // path to font file
	wchar_t *_psName; // postscript name of font
	GlyphOffsets _glyphOffsets; //glyphs offsets in font file
	TableEncode _tableEncode; // table with indexes of glyphs
	unsigned short _unitsPerEm; //size of em-square
	unsigned short _numOfHMetrics; // for 'hmtx' table
	HMetrics* _hMetrics; // horizontal metrics for all glyphs
	FILE* _ttfile;

	unsigned short getGlyphIndex(unsigned short symb);
	unsigned short getUnicodeByIndex(unsigned short ind);
//	friend unsigned short TTGlyph::getGlyphIndex(unsigned short symb);
//	friend int TTGlyph::initialize();

public:
	TTFont(char* pathToFile);
	~TTFont(void);
	
	Glyph* createGlyph(unsigned short unicode, unsigned short size);
	wchar_t* getPSName();
	float* getLineMetrics(); 
	bool canDisplay(unsigned short c);

//	float* GetExtraMetrics();
};


class TTGlyph : public Glyph {
private:
	TTFont* _ttfont;			
	unsigned short _index; 
	TTCurve* _curve;
	short _boundingRect[4]; 

	
	friend Glyph* TTFont::createGlyph(unsigned short unicode, unsigned short size);

public:
	TTGlyph(TTFont *font, unsigned short unicode, unsigned short size);
	~TTGlyph();
	Outline* getOutline(void);
	float* getGlyphMetrics(void);
};

#endif //__TTFONT_H__
