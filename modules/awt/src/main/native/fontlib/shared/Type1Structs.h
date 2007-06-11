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
 * @author Viskov Nikolay 
 * @version $Revision$
 */

#ifndef __TYPE_1_STRUCTS_H
#define __TYPE_1_STRUCTS_H

#include <map>
#include "EncodedValue.h"
#include "AGL.h"

typedef std::map<const unsigned short, EncodedValue*> Type1Map;

typedef std::map<unsigned short, unsigned short> Type1GlyphCodeMap;

typedef std::map<unsigned short, char*> Type1AFMMap;

typedef enum DecodeStateTag {HEADER, PRIVATE_DIR, SUBRS_MASSIVE, CHAR_STRING} DecodeState;

typedef std::map<const char*, const unsigned short> Type1CharMap;//inner glyph number -- unicode

static const unsigned short MAX_STR_LENGHT = 1024;
static const unsigned short C1 = 52845;
static const unsigned short C2 = 22719;
static const unsigned short DEF_R_EXEC = 55665;
static const unsigned short DEF_LENIV = 4;
static const unsigned short DEF_R_CHARSTRING = 4330;

static const unsigned char CH_STR_HSTEM = 1;
static const unsigned char CH_STR_VSTEM = 3;
static const unsigned char CH_STR_VMOVETO = 4;
static const unsigned char CH_STR_RLINETO = 5;
static const unsigned char CH_STR_HLINETO = 6;
static const unsigned char CH_STR_VLINETO = 7;
static const unsigned char CH_STR_RRCURVETO = 8;
static const unsigned char CH_STR_CLOSEPATH = 9;
static const unsigned char CH_STR_CALLSUBR = 10;
static const unsigned char CH_STR_RETURN = 11;
static const unsigned char CH_STR_ESCAPE = 12;
static const unsigned char CH_STR_HSBW = 13;
static const unsigned char CH_STR_ENDCHAR = 14;
static const unsigned char CH_STR_RMOVETO = 21;
static const unsigned char CH_STR_HMOVETO = 22;
static const unsigned char CH_STR_VHCURVETO = 30;
static const unsigned char CH_STR_HVCURVETO = 31;

static const unsigned char CH_STR_ESCAPE_DOTSECTION = 0;
static const unsigned char CH_STR_ESCAPE_VSTEM3 = 1;
static const unsigned char CH_STR_ESCAPE_HSTEM3 = 2;
static const unsigned char CH_STR_ESCAPE_SEAC = 6;
static const unsigned char CH_STR_ESCAPE_SBW = 7;
static const unsigned char CH_STR_ESCAPE_DIV = 12;
static const unsigned char CH_STR_ESCAPE_CALLOTHERSUBR = 16;
static const unsigned char CH_STR_ESCAPE_POP = 17;
static const unsigned char CH_STR_ESCAPE_SETCURRENTPOINT = 33;

//#define GLYPH_OUTLINE_CREATE_DEBUG

#endif //__TYPE_1_STRUCTS_H
