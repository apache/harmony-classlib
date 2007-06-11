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

#include <string>

#include "T1Font.h"
#include "T1Glyph.h"

T1Font::T1Font(wchar_t *family, StyleName sn, char* pathToFile):Font() {
	//this->_famName = family;
	_style = sn;   

    fullName = NULL;

	FILE *inputFile;

	if( inputFile = fopen(pathToFile, "rb")) {	

		try {
			initFont(inputFile);
		} catch (char*) {
			//printf("%s", str);
		} 
		
		fclose(inputFile);	

        //set default ascent and descent
        _ascent = 649;
        _descent = 195;


        char path[MAX_STR_LENGHT];
        size_t length = strlen(pathToFile) - 3;

        strncpy(path, pathToFile, length);
        strcpy(path + length, "afm");        

        if( inputFile = fopen(path, "rb")) {

		    try {
			    parseAFM(inputFile);
		    } catch (...) {
			    //printf("%s", str);
		    } 
    		
		    fclose(inputFile);
	    }

	}

}

T1Font::~T1Font(void) {

	for( Type1Map::iterator iter = subrsMap.begin(); iter != subrsMap.end(); iter++ ) {		
		delete iter->second;		
	}

	for( Type1Map::iterator iter = charStringMap.begin(); iter != charStringMap.end(); iter++ ) {		
		delete iter->second;		
	}

    /*for( Type1AFMMap::iterator iter = afmMap.begin(); iter != afmMap.end(); iter++ ) {		
		delete[] iter->second;		
	}*/

    delete fullName;
}

Glyph* T1Font::createGlyph(unsigned short unicode, unsigned short size) {    

    /*float floatMas[4];
    
    Type1AFMMap::iterator iter = afmMap.find(unicode); 

    //return iter == glyphCodeMap.end()? NULL : (unsigned short)iter->second;

    memcpy(floatMas, _boundingBox, 4 * sizeof(float));    

    if (iter != afmMap.end()) {
        //printf("%s\n", iter->second);
        char* curValue = strstr( iter->second, " B ");
        if (curValue != NULL) {

            curValue += 3; 

            floatMas[0] = (float) atof(curValue);
            //printf("0 = %f\n", floatMas[0]);

            while (*(++curValue) != ' ') {
			}

            floatMas[1] = (float) atof(curValue);
            //printf("1 = %f\n", floatMas[1]);

            while (*(++curValue) != ' ') {
			}

            floatMas[2] = (float) atof(curValue);           
            //printf("2 = %f\n", floatMas[2]);

            while (*(++curValue) != ' ') {
			}

            floatMas[3] = (float) atof(curValue);
            //printf("3 = %f\n", floatMas[3]);
        }
    }*/

    Glyph *glyph = new T1Glyph(&(this->charStringMap), &(this->subrsMap), unicode, size, size / matrixScale, _boundingBox);
	
	return glyph;
}

//TODO: owerwrite this:
wchar_t* T1Font::getPSName() {
	return fullName;
}

float* T1Font::getLineMetrics() {
    /*
     * metrics[0] - ascent<p>
     * metrics[1] - descent<p>
     * metrics[2] - external leading<p>
     * metrics[3] - underline thickness<p>
     * metrics[4] - underline offset<p>
     * metrics[5] - strikethrough thickness<p>
     * metrics[6] - strikethrough offset<p>
     * metrics[7] - maximum char width<p>*/

    float* floatMas = new float[FONT_METRICS_QUANTITY];

    floatMas[0] = _ascent / matrixScale; //ascent
    floatMas[1] = _descent / matrixScale; //descent
    floatMas[2] = (_height - _ascent - _descent) / matrixScale;//_externalLeading;

	floatMas[3] = _underlineThickness / matrixScale;
	floatMas[4] = _underlineOffset / matrixScale;
	
    floatMas[5] = _underlineThickness / matrixScale;//_strikeOutSize;
	floatMas[6] = -_ascent/(2 * matrixScale);//_strikeOutOffset;

	floatMas[7] = ((float)(_boundingBox[3] - _boundingBox[1])) / matrixScale;
	
	return floatMas;
}

bool T1Font::canDisplay(unsigned short ch) {    
	return this->charStringMap.find(ch) != this->charStringMap.end();
}

unsigned short T1Font::getUnicodeByIndex(unsigned short index) {
    Type1GlyphCodeMap::iterator iter = glyphCodeMap.find(index); 

    return iter == glyphCodeMap.end()? 0 : (unsigned short)iter->second;
}

void error(){
	//printf("invalidfont");
	throw "invalidfont";
}

unsigned inline short hexCharToUShort(char ch){
	return  ch >= '0' && ch <= '9' ? ch - '0' :
			ch >= 'A' && ch <= 'F' ? ch - 'A' + 10 :
			ch >= 'a' && ch <= 'f' ? ch - 'a' + 10 :			
			0;   
}

void static getNextLine(char* str, FILE* font) {
	unsigned short count = 0;
	unsigned char ch = ' ';

    while (!feof(font) && (ch == ' ' || ch == '\n' || ch == '\r')) {
        ch = getc(font);
	}

    str[count] = ch;
	while (!feof(font) && ch != '\r' && ch != '\n') {
		str[++count] = (ch = getc(font));
	}
	
	str[count] = '\0';
}

unsigned static char getNextChar(FILE* font) {
	unsigned char ch;	
	do {
		ch = getc(font);
	} while (ch == '\r' || ch == '\n');

	return ch;
}

unsigned static char decryptNextSimbol(FILE* font, unsigned short* r, bool isASCII) {
	unsigned char clipher = (unsigned char)(
		isASCII ? 
		(unsigned char) ((hexCharToUShort(getNextChar(font)) << 4 ) + (hexCharToUShort(getNextChar(font)))) : 
		getc(font)
	);

	unsigned char plain = (unsigned char)(clipher ^ (*r >> 8));
	*r = ( (unsigned char)clipher + *r ) * C1 + C2;	
	return plain;
}

void static decodeASCIILine(char* str, unsigned short* r, unsigned short n, unsigned short length) {
	char* p = str;
	unsigned char plain;
	unsigned char clipher;
	unsigned short count = 0;	
	length /= 2;	
	
	while(count < length) {
		clipher = (unsigned char)((hexCharToUShort(*p) << 4 ) + (hexCharToUShort(*(p + 1))));

		plain = (unsigned char)(clipher ^ (*r >> 8));
		*r = ( (unsigned char)clipher + *r ) * C1 + C2;

		if (count >= n) {
			str[count - n] = plain;
		}

		count ++;

		p+= 2;
	}		
	str[count - n] = '\0';
}

void static decodeBinaryLine(char* str, unsigned short* r, unsigned short n, unsigned short length) {
	char* p = str;
	unsigned char plain;
	unsigned char clipher;
	unsigned short count = 0;		
	while(count < length) {
		clipher = (unsigned char)*p;
		
		plain = (unsigned char)(clipher ^ (*r >> 8));
		*r = ( (unsigned char)clipher + *r ) * C1 + C2;

		if (count >= n) {
			str[count - n] = plain;
		}

		count ++;

		p ++;
	}		
	str[count - n] = '\0';
}

void static decodeLine(char* str, unsigned short* r, unsigned short n, bool isASCII, unsigned short length) {

	if (isASCII) {
		decodeASCIILine(str,r,n,length);
	} else {
		decodeBinaryLine(str,r,n,length);
	}
	return;
	/*char* p = str;
	unsigned char plain;
	unsigned char clipher;
	unsigned short count = 0;	
	if (isASCII) {
		length /= 2;	
	}
	while(count < length) {
		clipher = (unsigned char)(isASCII ? ((hexCharToUShort(*p) << 4 ) + (hexCharToUShort(*(p + 1)))) : *p);

		plain = (unsigned char)(clipher ^ (*r >> 8));
		*r = ( (unsigned char)clipher + *r ) * C1 + C2;

		//printf("%u ---- %u,%u\n", clipher, plain, *r);
		if (count >= n) {
			str[count - n] = plain;
		}

		count ++;

		p+= isASCII ? 2 : 1;
	}		
	str[count - n] = '\0';//*/
}

void static getNextDecodeLine(char* str, FILE* font, unsigned short* r, bool isASCII) {
	unsigned short count = 0;
	unsigned char plain;
	while(!feof(font)) {		
		plain = decryptNextSimbol(font, r, isASCII);

		str[count ++] = plain;		

		if (plain == '\r' || plain == '\n') {
			break;		
		}
	}		
	
	str[count] = '\0';
}

void static getNextDecodeLexeme(char* str, FILE* font, unsigned short* r, bool isASCII) {
	unsigned char ch;
	unsigned short count = 0;

	while (!feof(font) && ((ch = decryptNextSimbol(font, r, isASCII)) == ' ' || ch == '\n' || ch == '\r')) {
	}

	str[count ++] = ch;	
	while (!feof(font) && (ch = decryptNextSimbol(font, r, isASCII)) != ' ' && ch != '\n' && ch != '\r') {
		str[count ++] = ch;
	}

	str[count] = '\0';	
}

void static getNextLexeme(char* str, FILE* font) {
	unsigned char ch;
	unsigned short count = 0;

	while (!feof(font) && ((ch = getc(font)) == ' ' || ch == '\n' || ch == '\r' && ch != '{')) {
	}

	str[count ++] = ch;	
    while (!feof(font) && (ch = getc(font)) != ' ' && ch != '\n' && ch != '\r' && ch != '{') {
		str[count ++] = ch;
	}

	str[count] = '\0';	
}

unsigned short static findUnicode(const char *str) {
	
	unsigned short count = 0;
	unsigned short strCount = 0;
	unsigned short lastStrCount = 0;

	while(true) {
		if (GLYPH_LIST[count] == str[strCount]) {
			count ++;
			strCount ++;
			//next iteration				
		} else if ((GLYPH_LIST[count] ^ (1 << 7)) == str[strCount]) {
			strCount ++;
			if (str[strCount] == '\0') {
				return (unsigned short)((GLYPH_LIST[count + 1] << 8) + GLYPH_LIST[count + 2]);
			}

			count = ((GLYPH_LIST[count + 5] << 8) + GLYPH_LIST[count + 6]);

			lastStrCount = strCount;

			if (!count) {
				return FONT_NOT_FOUND_UNICODE_VALUE;
			}

			//on next level
		} else {
			strCount = lastStrCount;

			for (;!(GLYPH_LIST[count] & (1 << 7)) ; ) {
				count ++;
			}

			count = ((GLYPH_LIST[count + 3] << 8) + GLYPH_LIST[count + 4]);

			if (!count) {
				return FONT_NOT_FOUND_UNICODE_VALUE;
			}

			//next on this level
		}
	}
}

unsigned short static getUnicode(char *name) {
	if (!strncmp(name, ".notdef", 7)) {
		return 0;	
	}

	return findUnicode(name);
}

void T1Font::parseAFM(FILE *font) {
    char curStr[MAX_STR_LENGHT];

    while (!feof(font)) {
        getNextLexeme(curStr, font);
        //printf("%s\n",curStr);

        if (!strcmp(curStr, "EndFontMetrics") || !strcmp(curStr, "StartCharMetrics")) {

            return;
        } else if (!strcmp(curStr, "Ascender")) {
            getNextLexeme(curStr, font);
            _ascent = (float) fabs(atof(curStr));
            //printf("ascend = %f\n", _ascent);
            
        } else if (!strcmp(curStr, "Descender")) {
            getNextLexeme(curStr, font);
            _descent = (float) fabs(atof(curStr));
            //printf("descent = %f\n", _descent);
            
        } /*else if (!strcmp(curStr, "StartCharMetrics")) {
            getNextLexeme(curStr, font);            
            char* curValue;
            char psName[MAX_STR_LENGHT];
            unsigned short count = (unsigned short) atoi(curStr);
            for (unsigned short i = 0; i < count; i ++) {
                getNextLine(curStr, font);

                //printf("%s\n",curStr);
                curValue = strstr( curStr, " N ");
                if (curValue != NULL) {

                    curValue += 3;

                    //printf("%s\n", curValue);
                    
                    unsigned short i;
                    for (i = 0; curValue[i] != ' ' && curValue[i] != '\0'; i ++) {
                        psName[i] = curValue[i];
                    }
                    psName[i] = '\0';                    

                    curValue = new char[strlen(curStr) + 1]; 

                    strcpy(curValue, curStr);

                    //printf("%u = %s = %s\n",findUnicode(psName),curValue,psName);

				    afmMap[getUnicode(psName)] = curValue;
                }
            }
            
        }*/ else {
            getNextLine(curStr, font);
        }
    }
}


void T1Font::initFont(FILE *font) {
	char curStr[MAX_STR_LENGHT];

	DecodeState state = HEADER;

	unsigned short r = DEF_R_EXEC;
	unsigned short n = 4;

	unsigned short lenIV = DEF_LENIV;	
	unsigned short charStringR;	

	unsigned short count = 0;
	unsigned short tempShort = 0;
	unsigned short length = 0;
	unsigned short valueLength = 0;

    matrixScale = 1000;

	bool isASCII = true;

	unsigned char ch;	
	EncodedValue *curValue;

	ch = getc(font);

	if (ch == 0x80 && getc(font) == 0x01) {
		isASCII = false;	
	} else if (ch == '%' && getc(font) == '!') {
		isASCII = true;	
	} else {
		error();	
	}	

	while (!feof(font)) {
		switch (state) {
		case HEADER: {
			getNextLexeme(curStr, font);

            if (!strcmp(curStr, "/UnderlinePosition")) {
                getNextLexeme(curStr, font);
                _underlineOffset = (float) - atof(curStr);
		    } else if (!strcmp(curStr, "/UnderlineThickness")) {
                getNextLexeme(curStr, font);
                _underlineThickness = (float) atof(curStr);			
		    } else if (strstr(curStr, "/FontBBox") != NULL) {
                //getNextLexeme(curStr, font);

                while (!feof(font) && ((ch = getc(font)) == '{')) {
				}

                ungetc(ch, font);

                getNextLexeme(curStr, font);
                _boundingBox[0] = (float) atof(curStr);

                getNextLexeme(curStr, font);
                _boundingBox[1] = (float) atof(curStr);

                getNextLexeme(curStr, font);
                _boundingBox[2] = (float) atof(curStr);

                getNextLexeme(curStr, font);                
                _boundingBox[3] = (float) atof(curStr);

                _height = ((float)(_boundingBox[2] -_boundingBox[0]));

		    } else if (!strcmp(curStr, "/FullName")) {
                //getNextLexeme(curStr, font);                

				while (!feof(font) && ((ch = getc(font)) == '(')) {
				}

				curStr[count ++] = ch;	
				while (!feof(font) && (ch = getc(font)) != ')') {
					curStr[count ++] = ch;
				}

				curStr[count] = '\0';	

				char *ptr = curStr;

				ch = 0;
				fullName = new wchar_t[count + 1];
				while (*ptr != '\0') {
					fullName[ch ++] = *ptr;
					ptr ++;
				}

				fullName[ch] = L'\0';
		    } else if (!strcmp(curStr, "eexec")) {
				state = PRIVATE_DIR;			
			
				if (isASCII) {
					for (count = 0; count < lenIV * 2; count ++) {
						if (!isascii(ch = getc(font))) {
							error();					
						}					
						curStr[count] = ch;
					}
					decodeASCIILine(curStr, &r, n, count);
				} else {
					for (count = 0; count < 6; count ++) {					
						curStr[count] = getc(font);
					}
					
					if (curStr[0] != (char)0x80 || curStr[1] != 0x02) {
						error();
					}
					for (count = 0; count < lenIV; count ++) {
						curStr[count] = getc(font);
					}
					decodeBinaryLine(curStr, &r, n, count);
				}
			}
			break;			
		}
		case PRIVATE_DIR: {
			getNextDecodeLexeme(curStr, font, &r, isASCII);
			
			if (!strcmp(curStr, "/Subrs")) {

				getNextDecodeLexeme(curStr, font, &r, isASCII);
				valueLength = atoi(curStr);
				
				count = 0;
				state = SUBRS_MASSIVE;	 
				getNextDecodeLine(curStr, font, &r, isASCII);
			} else if (!strcmp(curStr, "/CharStrings")) {

				getNextDecodeLexeme(curStr, font, &r, isASCII);
				valueLength = atoi(curStr);		
				
				count = 0;
				state = CHAR_STRING;
				getNextDecodeLine(curStr, font, &r, isASCII);
			}
			break;
		}
		case SUBRS_MASSIVE: {			
			curValue = new EncodedValue();
			getNextDecodeLexeme(curStr, font, &r, isASCII);

			getNextDecodeLexeme(curStr, font, &r, isASCII);
			curValue->number = (unsigned short) atoi(curStr);	

			getNextDecodeLexeme(curStr, font, &r, isASCII);
			length = (unsigned short) atoi(curStr);
			curValue->length = length - lenIV;

			getNextDecodeLexeme(curStr, font, &r, isASCII);
			
			for (tempShort = 0; tempShort - length < 0; tempShort ++) {
				curStr[tempShort] = decryptNextSimbol(font, &r, isASCII);
			}

			charStringR = DEF_R_CHARSTRING;
			decodeBinaryLine(curStr, &charStringR, lenIV, length);

			curValue->text = new char[curValue->length];
			for (tempShort = 0; tempShort - curValue->length < 0; tempShort ++) {
				curValue->text[tempShort] = curStr[tempShort];
			}

			subrsMap[curValue->number] = curValue;

			getNextDecodeLine(curStr, font, &r, isASCII);

			if (++count >= valueLength) {
				state = PRIVATE_DIR;
				count = 0;
			}
			
			break;
		}
		case CHAR_STRING: {
			getNextDecodeLexeme(curStr, font, &r, isASCII);
			tempShort = getUnicode(curStr + 1);

			if (tempShort != FONT_NOT_FOUND_UNICODE_VALUE) {

                glyphCodeMap[count] = tempShort;

				curValue = new EncodedValue(); 

				curValue->number = tempShort;

				getNextDecodeLexeme(curStr, font, &r, isASCII);
				length = (unsigned short) atoi(curStr);
				curValue->length = length - lenIV;	

				getNextDecodeLexeme(curStr, font, &r, isASCII);
				for (tempShort = 0; tempShort - length < 0; tempShort ++) {
					curStr[tempShort] = decryptNextSimbol(font, &r, isASCII);
				}

				charStringR = DEF_R_CHARSTRING;
				decodeBinaryLine(curStr, &charStringR, lenIV, length);

				curValue->text = new char[curValue->length];
				for (tempShort = 0; tempShort - curValue->length < 0; tempShort ++) {
					curValue->text[tempShort] = curStr[tempShort];
				}
				charStringMap[curValue->number] = curValue;
			} else {
				getNextDecodeLexeme(curStr, font, &r, isASCII);
				length = (unsigned short) atoi(curStr);							

				getNextDecodeLexeme(curStr, font, &r, isASCII);
				for (tempShort = 0; tempShort - length < 0; tempShort ++) {
					decryptNextSimbol(font, &r, isASCII);
				}
			}			

			getNextDecodeLine(curStr, font, &r, isASCII);

			if (++count >= valueLength) {
				return;		
			}
			
			break;
		}
	}
	}
}
