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
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*#ifdef WIN32
#include <io.h>
#else
#include <glob.h>
#endif*/

#include "Tables.h"
#include "Environment.h"

static FontHeader* fhArray = NULL;

//#ifdef WIN32
//static bool getFonts = false;//true;
//#endif

int Environment::_length = 0;

FontHeader::FontHeader()
{
	_familyName = NULL;
	_filePath = NULL;
    _font = NULL;
    _nextHeader = NULL;
	_head = NULL;
}

FontHeader::FontHeader(char** filePath, FontType fType)
{
	_filePath = *filePath;
	_familyName = NULL;
    _fType = fType;
	_font = NULL;
	_nextHeader = NULL;
	_head = NULL;
}

FontHeader::~FontHeader()
{
    //printf("now = %s , %u \n", (char *)_filePath, _fType);
	delete _familyName;
	delete _filePath;
    //delete _font;
	delete _nextHeader;
}

bool fntType(char* font, char* type)
{
	bool ret=0;
	unsigned char j=0;

	int len = (int)strlen(font);
	for (int i = 0; i<=len; i++)
	{
		if (tolower(font[i]) == tolower(type[j])) 
		{
			ret = 1;
			j++;
		}
		else 
		{
			ret = 0;
			j=0;
		}
	}
	return ret;
}

void static inline getNewLexeme(char* str, FILE* font) {
	unsigned char ch;
	unsigned short count = 0;

	while (!feof(font) && ((ch = getc(font)) == ' ' || ch == '\n' || ch == '\r')) {
	}

	str[count ++] = ch;	
	while (!feof(font) && (ch = getc(font)) != ' ' && ch != '\n' && ch != '\r') {
		str[count ++] = ch;
	}

	str[count] = '\0';	
}

int static inline getT1Name(char *pathToFile,wchar_t** fontFamilyName, StyleName* style) {

	FILE *font;
	char curStr[1024];
	char *ptr;
	unsigned char ch;

	if( font = fopen(pathToFile, "rb")) {	

		ch = getc(font);

		if (ch == 0x80 && getc(font) == 0x01) {
			//isASCII = false;	
		} else if (ch == '%' && getc(font) == '!') {
			//isASCII = true;	
		} else {
			fclose(font);
			return -1;
		}	

		//printf("\nstart parsing %s\n", pathToFile);

		while (!feof(font)) {
			getNewLexeme(curStr, font);
			if (!strcmp(curStr, "/FontInfo")) {
				break;
			}
		}

		while (!feof(font)) {
			getNewLexeme(curStr, font);
			if (!strcmp(curStr, "/FamilyName")) {

				unsigned short count = 0;

				while (!feof(font) && ((ch = getc(font)) == '(')) {
				}

				curStr[count ++] = ch;	
				while (!feof(font) && (ch = getc(font)) != ')') {
					curStr[count ++] = ch;
				}

				curStr[count] = '\0';	

				ptr = curStr;

				//printf("fontFamilyName = ");
				ch = 0;
				*fontFamilyName = new wchar_t[count + 1];
				while (*ptr != '\0') {
					(*fontFamilyName)[ch ++] = *ptr;
					//printf("%c", *ptr);
					ptr ++;
				}

				(*fontFamilyName)[ch] = L'\0';

				//strncpy(*fontFamilyName, ptr, strlen(ptr) - 1);
				//printf("fontFamilyName = %s\n", curStr);
				//printf("\n");

			} else if (!strcmp(curStr, "/Weight")) {

				getNewLexeme(curStr, font);
				ptr = curStr + 1;
				if (!strncmp(ptr, "Regular",7)) {
					*style = Regular;
				} else if (!strncmp(ptr, "Bold",4)) {
					if (*style == Italic) {
						*style = BoldItalic;
					} else {
						*style = Bold;
					}
				} else if (!strncmp(ptr, "Normal",6)) {
					*style = Regular;
				} else {
					*style = Regular;
				}
				//printf("style = %u\n", *style);

			} else if (!strcmp(curStr, "/ItalicAngle")) {
				getNewLexeme(curStr, font);				
				double italicAngle = atof(curStr);
				if (italicAngle) {
					//printf("\n%f\n",italicAngle);
					if (*style == Bold) {
						*style = BoldItalic;
					} else {
						*style = Italic;
					}
				}
			} else if (!strcmp(curStr, "end")) {
				//getNewLexeme(curStr, font);
				fclose(font);
				return 0;
			}
		}
	} 

	fclose(font);

    return -1;
}

FontHeader* addFontFile(char** file, FontType ft)
{
	FILE* ttfile;
	StyleName* fStyle;
	int result;

	FontHeader *fh = new FontHeader(file, ft);

	switch(ft)
	{
	case TrueType:
		ttfile = fopen(fh->_filePath,"rb");
		if (ttfile == NULL)
		{
			delete fh;
			return NULL;
		}
		fStyle = new StyleName;
		result = parseNameTable(ttfile, &(fh->_familyName), NULL, fStyle);
		fclose(ttfile);

		if (fStyle == NULL || result == -1)
		{
			delete[] fStyle;
			delete fh;
			return NULL;
		}else {
			fh->_style=*fStyle;
		}

		break;
	case Type1:
		result = getT1Name(fh->_filePath,&(fh->_familyName), &(fh->_style));
		if (result == -1)
		{
            delete fh;
			return NULL;
		}
		break;
	default:
		return NULL;
	}

	Environment::_length++;

	if (fhArray != NULL)
	{
		fh->_head = fhArray->_head;
		fhArray->_nextHeader = fh;
		fhArray = fh;
	} else
	{
		fh->_head = fh;
        fhArray = fh;
	}

	return fh;
}

/*static inline FontHeader* add( char* name, char* dir, FontType ft)
{
	int len = (int)strlen(dir);
	char* fontFile = new char[len+strlen(name)+1];
	strncpy(fontFile, dir, len+1);
	strcat(fontFile, name);
	return addFontFile(&fontFile, ft);		
}*/

FontHeader* Environment::addFile(char* file, FontType ft)
{
	int len = (int)strlen(file);
    char* filepath = new char[len+1];
	strcpy(filepath,file);

	return addFontFile(&filepath, ft);
}

/*int Environment::addPath(char* argPath)
{
	char* tok;
	char fDir[1024];

	char deLimits[] = " ;";
	char currentDir[1024];
	long hFile;

	char path[1024];
    strcpy(path,argPath);

    tok = strtok(path, deLimits);
	while (tok != NULL)
	{
		strcpy(fDir,tok);
		strcpy(currentDir,tok);
		strcat(currentDir, "*.*");
//printf("%s\n",currentDir);

#ifdef WIN32
		struct _finddata_t font_file;
		if( (hFile = (long)_findfirst( currentDir, &font_file )) != -1L )
//			printf("No available fonts in %s\n", currentDir);
//		else
		{
			do {
				if (fntType(font_file.name,".ttf"))
					add(font_file.name, fDir, TrueType);
				else if (fntType(font_file.name,".pfa"))
					add(font_file.name, fDir, Type1);
				else if (fntType(font_file.name,".pfb"))
					add(font_file.name, fDir, Type1);

			}while( _findnext( hFile, &font_file ) == 0 );
		}

        _findclose( hFile );
#else
	    glob_t globbuf;

		globbuf.gl_offs = 5; 
		glob(currentDir, GLOB_NOSORT, NULL, &globbuf); 

		for(char** filesList = globbuf.gl_pathv; *filesList != NULL; filesList++)
		{
			if (fntType(*filesList,".ttf"))
				addFontFile(filesList, TrueType);		
			else if (fntType(*filesList,".pfa"))
				addFontFile(filesList, Type1);		
			else if (fntType(*filesList,".pfb"))
				addFontFile(filesList, Type1);
		}
#endif

		tok = strtok(NULL,deLimits);
	}

	return 0;
}*/

/* Getting all installed fonts from windows and user's defined directories */
FontHeader* Environment::getAllFonts()
{

	/*if (getFonts)
	{
#ifdef WIN32
		char* WINFONTS = "\\Fonts\\";
		char winDir[256];
		strcpy(winDir,getenv("windir"));
		strcat(winDir,WINFONTS);
		addPath(winDir);

#else
	//printf("adding paths...\n");
	addPath("/usr/X11R6/lib/X11/fonts/truetype/;/usr/X11R6/lib/X11/fonts/Type1/");
	//printf("paths added\n");
#endif
		getFonts = 	false;
        }    */

    return fhArray == NULL ? NULL : fhArray->_head;
}
