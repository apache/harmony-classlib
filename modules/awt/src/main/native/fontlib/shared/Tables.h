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
#ifndef __TABLES_H__
#define __TABLES_H__


#include "TTCurve.h"
#include "TTFont.h"

#define CMAP_TABLE "cmap"   /* character to glyph mapping   */
#define GLYF_TABLE "glyf"   /* glyph data                   */
#define HEAD_TABLE "head"   /* font header                  */
#define HHEA_TABLE "hhea"   /* horizontal header            */
#define HMTX_TABLE "hmtx"   /* horizontal metrics           */
#define LOCA_TABLE "loca"   /* index to location            */
#define MAXP_TABLE "maxp"   /* maximum profile              */
#define NAME_TABLE "name"   /* naming                       */
#define POST_TABLE "post"   /* PostScript                   */
#define TTCF_TABLE "ttcf"   /* TrueType font collection     */
#define KERN_TABLE "kern"   /* Kerning table                */
#define BASE_TABLE "BASE"   /* Baseline table               */
#define OS2_TABLE  "OS/2"   /* OS-metrics table             */

#define WINDOWS_PLATFORM_ID 3   /* Windows platform identifier  */
#define FAMILY_NAME_ID 1        /* Family name identifier       */
#define SUBFAMILY_NAME_ID 2     /* Family name identifier       */
#define FULL_NAME_ID 4          /* Family name identifier       */
#define POSTSCRIPT_NAME_ID 6    /* Family name identifier       */

#define SYMBOL_ENCODING 0
#define UNICODE_ENCODING 1
#define SHIFT_JIS_ENCODING 2
#define BIG5_ENCODING 3
#define PRC_ENCODING 4

typedef long Fixed;
typedef long long LONGDT;

typedef enum
{
	ON_CURVE	= 0x01, // on curve or not
	REPEAT		= 0x08,	// next byte specifies the number of 
						//additional times this set of flags is to be repeated
    X_POSITIVE	= 0x12, // positive x-value
	X_NEGATIVE	= 0x02, // negative x-value
	X_SAME		= 0x10,	// x is same
	X_DWORD		= 0x00,	    

	Y_POSITIVE	= 0x24, // positive y-value
	Y_NEGATIVE	= 0x04, // negative y-value
	Y_SAME		= 0x20,	// y is same
	Y_DWORD		= 0x00,	    
};

/* From Win GDI 
typedef struct { 
  unsigned char bFamilyType; 
  unsigned char bSerifStyle; 
  unsigned char bWeight; 
  unsigned char bProportion; 
  unsigned char bContrast; 
  unsigned char bStrokeVariation; 
  unsigned char bArmStyle; 
  unsigned char bLetterform; 
  unsigned char bMidline; 
  unsigned char bXHeight; 
} PANOSE; */

typedef struct
{
    Fixed version;
    unsigned short num_tables;
    unsigned short search_range;
    unsigned short entry_selector;
    unsigned short range_shift;
} Table_Offset;

typedef struct
{
    char    tag[4];
    unsigned long   checkSum;
    unsigned long   offset;
    unsigned long   length;
} Table_Directory;

typedef struct
{
    unsigned short platformID;
    unsigned short encodingID;
    unsigned short languageID;
    unsigned short nameID;
    unsigned short string_length;
    unsigned short string_offset;
} Name_Entry;

typedef struct 
{
    unsigned short format;
    unsigned short num_name_records;
    unsigned short storage_offset;
    Name_Entry name_record[1];
} Table_name; 

/* TrueType 'maxp' table */
typedef struct
{
    Fixed  version;
    unsigned short numGlyphs; // number of Glyphs
    unsigned short maxPoints;
    unsigned short maxContours;
    unsigned short maxCompositePoints;
    unsigned short maxCompositeContours;
    unsigned short maxZones;
    unsigned short maxTwilightPoints;
    unsigned short maxStorage;
    unsigned short maxFunctionDefs;
    unsigned short maxInstructionDefs;
    unsigned short maxStackElements;
    unsigned short maxSizeOfInstructions;
    unsigned short maxComponentElements;
    unsigned short maxComponentDepth;
} Table_maxp;

/* TrueType Font Header table */
typedef struct
{
    Fixed table_version;
    Fixed font_revision;
    unsigned long checksum_adjust;
    unsigned long magic_number;
    unsigned short flags;
    unsigned short units_per_EM;
	LONGDT created;
	LONGDT modified;
    short xMin;
    short yMin;
    short xMax;
    short yMax;
    unsigned short mac_style;
    unsigned short lowest_rec_PPEM;
    short font_direction;
    short index_to_loc_format;  // format of 'loca' table
    short glyph_data_format;
} Table_head;

typedef struct
{
    Fixed table_version;
	short ascender;	             /* typographic ascent */
	short descender;             /* typographic descent */
	short line_gap;              /* typographic line gap */
	unsigned short advance_width_max;    /* Maximum advance width value in ‘hmtx’ table */
	short min_left_sidebearing;
	short min_right_sidebearing; /* Min(aw - lsb - (xMax - xMin)) */
	short xMaxExtent;            /* Max(lsb + (xMax - xMin)) */
	short caret_slope_rise;
	short caret_slope_run;
	short first_reserved;
	short second_reserved;
	short third_reserved;
	short fourth_reserved;
	short fifth_reserved;
    short metric_data_format;
	unsigned short number_of_hMetrics;
} Table_hhea;

typedef struct
{
	unsigned short table_version;
	short xAvgCharWidth;
	unsigned short usWeightClass;
	unsigned short usWidthClass;
	short fsType;
	short ySubscriptXSize;
	short ySubscriptYSize;
	short ySubscriptXOffset;
	short ySubscriptYOffset;
	short ySuperscriptXSize;
	short ySuperscriptYSize;
	short ySuperscriptXOffset;
	short ySuperscriptYOffset;
	short yStrikeoutSize;
	short yStrikeoutPosition;
	short sFamilyClass;
//	PANOSE panose;
	unsigned char panose[10];
	unsigned long ulUnicodeRange1;
	unsigned long ulUnicodeRange2;
	unsigned long ulUnicodeRange3;
	unsigned long ulUnicodeRange4;
	unsigned char achVendID[4];
	unsigned short fsSelection;
	unsigned short usFirstCharIndex;
	unsigned short usLastCharIndex;
	unsigned short sTypoAscender;
	unsigned short sTypoDescender;
	unsigned short sTypoLineGap;
	unsigned short sWinAscent;
	unsigned short sWinDescent;
	unsigned long ulCodePageRange1;
	unsigned long ulCodePageRange2;
} Table_os2;

typedef struct
{
	unsigned short platform;	 //identifier of platform
	unsigned short encodingID;	 //identifier fo encoding
	unsigned long  table_offset; //offset of the encoding table	
} Cmap_Entry;

typedef struct
{
	unsigned short table_version;       // =0
    unsigned short numSubTables;        //number subtables
	Cmap_Entry tableHeaders[1]; //headers of subtables
} Table_cmap;

typedef struct
{
    Fixed format; //format type
	Fixed italic_angle;
	short underlineOffset;
	short underlineThickness;
	unsigned long isFixedPitch;
	unsigned long minMemType42;
	unsigned long maxMemType42;
	unsigned long minMemType1;
	unsigned long maxMemType1;
} Table_post;

/* first part of the encoding table identical for all format of them */
typedef struct
{
	unsigned short format;
	unsigned short length; //length in bytes
	unsigned short version;
} Table_encode_header;

/*
typedef struct
{
	unsigned short format; // =0,2,4,6
	unsigned short length; // size
	unsigned short version;
	unsigned char map[256];
} Table_encode_0;


typedef struct
{
//	unsigned short segCountX2;       // 2 x segCount
	unsigned short search_range;     // 2 x (2**floor(log_2(segCount)))
	unsigned short entry_selector;   // log_2(search_range/2)
	unsigned short range_shift;      // 2 x segCount - search_range
	unsigned short end_count[1];     // end characterCode for each segment, last =0xFFFF, length = segCount
	unsigned short reservedPad;      // = 0
	unsigned short start_count[1];   // Start character code for each segment, length = segCount
	unsigned short idDelta[1];       // Delta for all character codes in segment, length = segCount
    unsigned short idRangeOffset[1]; // Offsets into glyphIdArray or 0, length = segCount
	unsigned short glyphIdArray[];  // Glyph index array (arbitrary length)
} Table_encode_4;
*/

typedef struct
{
	short number_of_contours; // <0 for composite glyph
    short xMin;
	short yMin;
	short xMax;
	short yMax;
} Glyph_header;

template<short n> // n = number_of_contours from Glyph_header
struct SimpleGlyphDescription
{
    unsigned short endPtsOfContours[n];
	unsigned short instruction_length;
//	unsigned char instructions[instruction_length];
//	unsigned char flags[n];
//	unsigned char(short) xCoordinates[n];
//	unsigned char(short) yCoordinates[n];
};

int parseCmapTable(FILE* tt_file, TableEncode* te);
int parseNameTable(FILE* tt_file, wchar_t** familyName, wchar_t** psName, StyleName* fontStyle);
int parseHeadTable(FILE* tt_file, float* bbox, short* format, unsigned short* unitsPerEm);
int parseHheaTable(FILE* tt_file, unsigned short* numOfHMetrics, float* ascent, float* descent, float* lineGap);
int parseMaxpTable(FILE* tt_file, unsigned short *numGlyphs);
int parseLocaTable(FILE* tt_file, GlyphOffsets* gOffsets, unsigned short numGlyphs);
int parseOs2Table(FILE* tt_file, float* strikeOutSize, float* strikeOutOffset);
int parsePostTable(FILE* tt_file, short* uOffset, short* uThickness);
int parseGlyphData(FILE* tt_file, const GlyphOffsets gO, unsigned short numGlyphs, unsigned short glyphIndex, TTCurve *curve, short* bRect, float transform);
int parseHmtxTable(FILE* tt_file, unsigned short numOfHMetrics, HMetrics** hm);
bool isCompositeGlyph(FILE* tt_file, const GlyphOffsets gO, unsigned short numGlyphs, unsigned short glyphIndex);

#endif
