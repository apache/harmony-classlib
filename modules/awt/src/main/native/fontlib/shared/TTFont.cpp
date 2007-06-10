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
#include "TTFont.h"
#include "Tables.h"

TTFont::TTFont(char* pathToFile):Font()
{
	_pathToFile=pathToFile;
	_glyphOffsets.offsets = NULL;
	_tableEncode.TableEncode = NULL;
	_famName = NULL;
	_ttfile = NULL;
	_hMetrics = NULL;
	_psName = NULL;

	_ttfile = fopen(_pathToFile,"rb");
	parseNameTable(_ttfile, &_famName, &_psName, NULL);
	parseCmapTable(_ttfile, &_tableEncode); 
	parseMaxpTable(_ttfile, &_numGlyphs);
	parseHeadTable(_ttfile, _boundingBox, &(_glyphOffsets.format), &_unitsPerEm);
	for (int i=0; i<4; i++)
		_boundingBox[i]/=(float)(_unitsPerEm);
	parseLocaTable(_ttfile, &(_glyphOffsets), _numGlyphs);
	parseHheaTable(_ttfile, &_numOfHMetrics, &_ascent, &_descent, &_externalLeading);

	_ascent/=(float)(_unitsPerEm);
	_descent = ((_descent>0)?_descent:(-_descent))/_unitsPerEm;
	_externalLeading/=(float)(_unitsPerEm);

	parseHmtxTable(_ttfile, _numOfHMetrics, &_hMetrics);
	fclose(_ttfile);
}

TTFont::~TTFont(void)
{
	delete[] _glyphOffsets.offsets;
	delete[] (int*)(_tableEncode.TableEncode);
	delete[] _psName;
	delete[] _hMetrics;
}

Glyph* TTFont::createGlyph(unsigned short unicode, unsigned short size) 
{
	TTGlyph *gl = new TTGlyph(this, unicode, size);
	if (gl->_index < _numOfHMetrics)
		gl->_advanceX = _hMetrics[gl->_index].adwance_width*(float)(size)/(float)(_unitsPerEm);
	else
		gl->_advanceX = _hMetrics[_numOfHMetrics-1].adwance_width*(float)(size)/(float)(_unitsPerEm);
	gl->_advanceY = 0;
	return gl;
}

unsigned short TTFont::getGlyphIndex(unsigned short symb)
{
	unsigned short index = 0;

	if (_tableEncode.format == 0)
	{
		unsigned char* te = (unsigned char*)_tableEncode.TableEncode;
		index = te[symb];
	
	}else if (_tableEncode.format == 4)
	{
		unsigned short segCountX2;
		unsigned short segCount;
//		unsigned short search_range;     
//		unsigned short entry_selector;   
//		unsigned short range_shift;     
		unsigned short* end_count;
		unsigned short* start_count;
		unsigned short* idDelta;
		unsigned short* idRangeOffset;
		unsigned short reservedPad;      
		unsigned short* te = (unsigned short*)(_tableEncode.TableEncode);
		int i;

		segCountX2 = te[0];
		segCount = segCountX2/2;
		end_count = te+4;
		reservedPad = te[segCount+4];
		start_count = te+segCount+5;
		idDelta = te+2*segCount+5;
		idRangeOffset = te+3*segCount+5;

		for (i=0;i<segCount;i++)
		{
			if (symb<=end_count[i] && symb>=start_count[i])
			{
				break;
			}
		}

		if (idRangeOffset[i] != 0)
			index = (*(idRangeOffset[i]/2 + (symb - start_count[i]) + &idRangeOffset[i]) + idDelta[i]) % 65536;
		else
			index = (symb + idDelta[i]) % 65536;
	
	}
	return index;
}

unsigned short TTFont::getUnicodeByIndex(unsigned short ind)
{
	unsigned short symb = 0;

	if (_tableEncode.format == 0)
	{
		unsigned char* te = (unsigned char*)_tableEncode.TableEncode;
		for (unsigned short i = 0; i<=_numGlyphs; i++)
		{
			if (ind = te[i]) 
			{
				symb = i;
				break;
			}
		}
	}else 
	if (_tableEncode.format == 4)
	{
		unsigned short segCountX2;
		unsigned short segCount;
		unsigned short* end_count;
		unsigned short* start_count;
		unsigned short* idDelta;
		unsigned short* idRangeOffset;
		unsigned short reservedPad;      
		unsigned short* te = (unsigned short*)(_tableEncode.TableEncode);
		int i;

		segCountX2 = te[0];
		segCount = segCountX2/2;
		end_count = te+4;
		reservedPad = te[segCount+4];
		start_count = te+segCount+5;
		idDelta = te+2*segCount+5;
		idRangeOffset = te+3*segCount+5;

		for (i=0;i<segCount;i++)
		{
			if (idRangeOffset[i] != 0)
				for (int j=0; j< _numGlyphs; j++)
				{
					if (ind - idDelta[i] == idRangeOffset[j] )
						symb = (unsigned short)(&idRangeOffset[j] - idRangeOffset[i]/2 + start_count[i] - &idRangeOffset[i]);
				}
			else
				symb = (ind - idDelta[i]) % 65536;

			if (symb<=end_count[i] && symb>=start_count[i])
			{
				break;
			}
		}
	}
	return symb;
}

wchar_t* TTFont::getPSName()
{
	return _psName;
}

float* TTFont::getLineMetrics()
{
//printf("reading file...\n");
	_ttfile = fopen(_pathToFile,"rb");
	
	float* ret = new float[8];
	ret[0] = _ascent;
	ret[1] = _descent;
    ret[2] = _externalLeading;

	short uOffset, uThickness;

//printf("parsing POST table...\n");
    parsePostTable(_ttfile, &uOffset, &uThickness);
	ret[3] = (float)uThickness/(float)(_unitsPerEm);
	ret[4] = (float)uOffset/(float)(_unitsPerEm);

//printf("parsing OS2 table...\n");	
	parseOs2Table(_ttfile, &_strikeOutSize, &_strikeOutOffset);

	ret[5] = _strikeOutSize;
	ret[6] = _strikeOutOffset;

	float width = _boundingBox[3]-_boundingBox[1];
	ret[7] = (width>0)?width:(-width);
	
	fclose(_ttfile);
	
	return ret;
}

bool TTFont::canDisplay(unsigned short c)
{
	unsigned short index = getGlyphIndex(c);
#ifdef WIN32
	bool isComposite = isCompositeGlyph(_ttfile, _glyphOffsets, _numGlyphs, index);
	if (index == 0 || index >= _numGlyphs || isComposite)
		return false;
#else
	if (index == 0 || index >= _numGlyphs)
		return false;
#endif
	return true;
}

/* *************** */
/* TTGlyph methods */
/* *************** */
TTGlyph::TTGlyph(TTFont* font, unsigned short unicode, unsigned short size):Glyph() 
{
	_ttfont = font;
	_unicode = unicode;
	_size = size;
	_curve = new TTCurve();
	_boundingRect[0]=0;
	_boundingRect[1]=0;
	_boundingRect[2]=0;
	_boundingRect[3]=0;

	_ttfont->_ttfile = fopen(_ttfont->_pathToFile,"rb");
	_index = _ttfont->getGlyphIndex(_unicode);
	parseGlyphData(_ttfont->_ttfile, _ttfont->_glyphOffsets,_ttfont->_numGlyphs,_index, _curve, _boundingRect, (float)_size/(float)(_ttfont->_unitsPerEm));
    fclose(_ttfont->_ttfile);
}

TTGlyph::~TTGlyph() 
{
	delete _curve;
}

float* TTGlyph::getGlyphMetrics(void){
	float* gMetrics = new float[6];

	gMetrics[0]=_advanceX;
	gMetrics[1]=_advanceY;
	gMetrics[2]=_boundingRect[0];
    gMetrics[3]=_boundingRect[1];
	gMetrics[4]=_boundingRect[2];
	gMetrics[5]=_boundingRect[3];

	return gMetrics;
}

Outline* TTGlyph::getOutline(void)
{
	Outline* outline = new Outline(_curve->_len,_curve->_outlineCommandsNumb); 

	for (int i = 0; i<_curve->_len; i+=2)
	{
		switch(_curve->_flags[i/2])
		{
		case OPEN_FLAG: 
			if (i!=0) outline->closePath(); 
			outline->moveTo(_curve->_coords[i],_curve->_coords[i+1]);
			break;
		case FLAG_ONCURVE: 
			if (_curve->_flags[i/2-1] & FLAG_ONCURVE || _curve->_flags[i/2-1] & OPEN_FLAG) 
				outline->lineTo(_curve->_coords[i],_curve->_coords[i+1]);
			else
				outline->quadTo(_curve->_coords[i-2],_curve->_coords[i-1],_curve->_coords[i],_curve->_coords[i+1]);
		}
	}
	outline->closePath();

	return outline;
}
