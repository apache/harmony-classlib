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
 * 
 * Description: This file contains native calls to get font data on Linux 
 * platform. Font data obtained using Xft, FreeType and FontConfig libraries.
 * 
 */
#include <X11/Xft/Xft.h>
#include <freetype/tttables.h>
#include <freetype/t1tables.h>
#if (FREETYPE_MAJOR >= 2 && FREETYPE_MINOR >= 2)

#undef HAS_FREETYPE_INTERNAL
#include <freetype/ftsizes.h>
#include <freetype/ftglyph.h>
#ifndef FALSE
#define FALSE (0)
#define TRUE (1)
#endif /* FALSE */

#else /* FREETYPE_MAJOR ... */

#define HAS_FREETYPE_INTERNAL 1
#include <freetype/internal/tttypes.h>

#endif /* FREETYPE_MAJOR ... */

#include <freetype/ftsnames.h>

#include <stdio.h>
#include <stdlib.h>

#include<string.h>
#include <errno.h>
#include <fcntl.h>
#include <unistd.h>

#include <wchar.h>
#include <math.h>

#include "LinuxNativeFont.h"
#include "org_apache_harmony_awt_gl_font_LinuxNativeFont.h"
/* debug code marker - to print debug information change to DEBUG */
#   define NO_DEBUG

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getFontFamiliesNames
 * Signature: ()[Ljava/lang/String;
 * Returns array of Strings that represents list of all font families names
 * available on the system.  
 */
JNIEXPORT jobjectArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getFontFamiliesNames(JNIEnv *env, jclass jobj){

    jobjectArray families;

    XftObjectSet *os = 0;
    XftPattern *xftPattern;
    XftFontSet *fs;
    int j, i;
    int numFamilies;
    FcChar8 *family;
    char** famList;
    jclass strClass;
    jstring initStr;


    /* Create pattern */
    xftPattern = XftPatternCreate();

    if (!XftPatternAddBool (xftPattern, XFT_OUTLINE, FcTrue)){
        throwNPException(env, "Outline value can't be added to XftPattern");
    }

    /* Just need to add which fields you want to list */
    os = XftObjectSetBuild (XFT_FAMILY, (char *) 0);

    fs = FcFontList (0, xftPattern, os);
    
        FcObjectSetDestroy (os);
    
    if(xftPattern){
            XftPatternDestroy(xftPattern);
    }

        if (!fs){
        throwNPException(env, "Font list can't be created");
    } else  {
        numFamilies = fs->nfont;
        famList = (char** )malloc(numFamilies * sizeof(char *));

        for (j = 0; j < numFamilies; j++)
        {

#ifdef DEBUG
                font = FcNameUnparse (fs->fonts[j]);
            printf ("%s\n", font);
                free (font);
#endif /* DEBUG */

                if (XftPatternGetString (fs->fonts[j], XFT_FAMILY, 0, &family) == XftResultMatch){
#ifdef DEBUG
                    printf ("       %s", family);
#endif /* DEBUG */
                famList[j] = (char*)malloc(sizeof(char) * (strlen(family)+1));
                strcpy(famList[j], family);
            }
                
        }
        XftFontSetDestroy (fs);
        }


    strClass = (*env)->FindClass(env, "java/lang/String");
    initStr = (*env)->NewStringUTF(env, "");

    families = (jobjectArray)(*env)->NewObjectArray(env, 
        numFamilies,  
        strClass,
        initStr);

    if (families == NULL){
        for (i = 0;i < numFamilies;i++){
            free(famList[i]);
        }
        free(famList);
        
        throwNPException(env, "Not enough memory to create families list");
    }

    for (i = 0;i < numFamilies;i++){
        (*env)->SetObjectArrayElement(env, families,i,(*env)->NewStringUTF(env, famList[i])); // number of chars == length of string -1
    }

    return families;
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    embedFontNative
 * Signature: (Ljava/lang/String;)Z
 *
 * Returns true if the new font was added to the system, false otherwise.
 * Methods checks if the number of system fonts changed after font configutation
 * was rebuilded.
 */
JNIEXPORT jboolean JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_embedFontNative(JNIEnv *env, jclass obj, jstring fName){

    int fontAdded = FALSE;
    FcChar8* fileName;  
    jboolean iscopy;
    FcConfig *config;
    FcFontSet *set;
    int numFonts;
    fileName = (FcChar8*)((*env)->GetStringUTFChars(env, fName, &iscopy));

    (*env)->ReleaseStringUTFChars(env, fName, fileName);

    config = FcConfigGetCurrent();

    set = FcConfigGetFonts(config, FcSetSystem);
    if (!set){
        return fontAdded;
    }
        
    numFonts = set->nfont;
    if (!config){
        return fontAdded;
    }

    fontAdded = FcConfigBuildFonts(config);

    set = FcConfigGetFonts(config, FcSetSystem);
    if (!set){
        return fontAdded;
    }
        
    fontAdded = fontAdded && (numFonts < set->nfont);

    return fontAdded;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    initializeFont
 * Signature: (Lorg/apache/harmony/awt/gl/font/LinuxFont;Ljava/lang/String;IILjava/lang/String;)J
 * Initiailzes native Xft font object from specified parameters and returns 
 * font handle, also sets font type to the font peer parameter. 
 * NullPointerException is thrown if there are errors in native code.
 */
JNIEXPORT jlong JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_initializeFont(JNIEnv *env, jclass jobj, jobject linuxFont, jstring jName, jint jStyle, jint size, jstring jFaceStyle){

    jclass cls;
    jfieldID fid;
    jmethodID mid;
    jboolean iscopy;

    int slant;
    int weight;
    const char* name;

    XftPattern *pattern;
    XftPattern *matchPattern;
    XftResult result;
    Display *dpy;
    int scr;
    FcChar8 *faceStyle = NULL;
    XftFont *xftFnt;
    int pitch = 0;
    int font_type = FONT_TYPE_UNDEF;
    int spacing;
    double fSize;
    FT_Face face;
    /* Initialize part */
    
    cls = (*env)->GetObjectClass(env, linuxFont);

    if (jStyle & FONT_BOLD) {
        weight = XFT_WEIGHT_BOLD; // TODO: need to be defined from TextAttributes
#ifdef DEBUG
        printf("Weight is bold");
#endif // DEBUG 
    } else {
        weight = XFT_WEIGHT_MEDIUM; // TODO: need to be defined from TextAttributes
#ifdef DEBUG
        printf("Weight is medium");
#endif // DEBUG 
    }

    if (jStyle & FONT_ITALIC){
        slant = XFT_SLANT_ITALIC;
    } else {
        slant = XFT_SLANT_ROMAN;
    }

    // TODO: to find out about UTF16 strings
    name = (*env)->GetStringUTFChars(env, jName, &iscopy);
    if(jFaceStyle){
        faceStyle = (FcChar8 *)(*env)->GetStringUTFChars(env, jFaceStyle, &iscopy);
    }
    fid=(*env)->GetFieldID(env, cls, "display", "J");
    if (fid == 0) {
        //      printf("Can't find field \"size\"");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }


    dpy = (Display *)(*env)->GetLongField(env, linuxFont, fid);

    fid=(*env)->GetFieldID(env, cls, "screen", "I");
    if (fid == 0) {
        //      printf("Can't find field \"size\"");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }
    scr = (*env)->GetIntField(env, linuxFont, fid);

    mid=(*env)->GetMethodID(env, 
        cls, 
        "getPitch", 
        "()I");
    if (mid == 0) {
        // Can't find method "getPitch()"
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }

    pitch = (*env)->CallIntMethod(env, linuxFont, mid);
    if((*env)->ExceptionOccurred(env)) {
        //      printf("Error occured when getting \"getFPitchAndFamily()\" value");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }

    
    /* Xft part */

    if (dpy == NULL){
        throwNPException(env, "Cannot connect to XServer");
    }   

    /* Create pattern */
    pattern = XftPatternCreate();

    if (!XftPatternAddString (pattern, XFT_FAMILY, name)){
        throwNPException(env, "Error during adding family name to XFTPattern structure");
    }

    if (faceStyle && !XftPatternAddString (pattern, XFT_STYLE, faceStyle)){
        throwNPException(env, "Error during adding style name to XFTPattern structure");
    }

    if (!XftPatternAddInteger(pattern, XFT_SLANT, slant)){
        throwNPException(env, "Error during adding font slant to XFTPattern structure");
    }

    if (!XftPatternAddInteger(pattern, XFT_WEIGHT, weight)){
        throwNPException(env, "Error during adding font weight to XFTPattern structure");
    }

/*  if (!XftPatternAddInteger(pattern, FC_WIDTH, FC_WIDTH_NORMAL)){
        throwNPException(env, "Error during adding font width to XFTPattern structure");
    }
*/
    /* We set antialias mode for simple text rendering without antialiasing */
    if (!XftPatternAddBool(pattern, XFT_ANTIALIAS, False)){
        throwNPException(env, "Error during adding font antialias set to false to XFTPattern structure");
    }

    /* 
     *
     *  To comply with Java specification and results we have  to use DPI value 
     *  equals to 96. Actually, it is properly to use resolution Y value instead of DPI value
     *  the correct formula  resolutionY = (XDisplayHeight(dpy, scr)/(XDisplayHeightMM(dpy, scr)/25.4));
     *  hence size = size / (double)resolutionY * 72;
     */

    fSize = (double)size / 96 * 72;

    if (!XftPatternAddDouble (pattern, XFT_SIZE, fSize)){
        throwNPException(env, "Error during adding font size to XFTPattern structure");
    }

/*  if (!XftPatternAddBool (pattern, FC_HINTING, True)){
        throwNPException(env, "Error during adding font hinting set to false to XFTPattern structure");
    }
*/

    if (!XftPatternAddBool (pattern, XFT_RENDER, True)){
        throwNPException(env, "Error during adding font RENDER set to true to XFTPattern structure");
    }


    if (!XftPatternAddBool (pattern, FC_AUTOHINT, True)){
        throwNPException(env, "Error during adding font autohinting set to false to XFTPattern structure");
    }
        
    if (pitch != 0){
        if (pitch == 2){
          spacing = FC_PROPORTIONAL;
        } else if(pitch == 1){
          spacing = FC_MONO;
        }

        if (!XftPatternAddInteger(pattern, XFT_SPACING, spacing)){
            throwNPException(env, "Error during adding font spacing type to XFTPattern structure");
        }
    }
                                                                   
    (*env)->ReleaseStringUTFChars(env, jName, name);
    if(faceStyle){
        (*env)->ReleaseStringUTFChars(env, jFaceStyle, faceStyle);

}
    matchPattern = XftFontMatch (dpy, scr, pattern, &result);

#ifdef DEBUG
    printf ("Pattern ");
    FcPatternPrint (pattern);
    if (matchPattern)
    {
        printf ("Match ");
        FcPatternPrint (matchPattern);
    }
    else {
        printf ("No Match\n");
        }
#endif /* DEBUG */

    XftPatternDestroy (pattern);
    if (!matchPattern)
        return (long)NULL;
    
    xftFnt = XftFontOpenPattern (dpy, matchPattern);
    if (!xftFnt){
        XftPatternDestroy (matchPattern);
        return (long)NULL;
    }

    /* Set Font type upcall */
    mid=(*env)->GetMethodID(env, cls, "setFontType", "(I)V");

    if (mid == 0) {
#ifdef DEBUG
        printf("Can't find \"setFontType\" method");
#endif // DEBUG 
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }
 
    face = XftLockFace(xftFnt);
        if (face->face_flags & FT_FACE_FLAG_SFNT){
            font_type = FONT_TYPE_TT;
        } else {
            font_type = FONT_TYPE_T1;
        }
    XftUnlockFace(xftFnt);

    (*env)->CallVoidMethod(env, linuxFont, mid, font_type);

    if((*env)->ExceptionOccurred(env)) {
#ifdef DEBUG
        printf("Error occured when calling \"setFontType\" method");
#endif // DEBUG 
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }

    return (long)xftFnt;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    initializeFontFromFP
 * Signature: (Lorg/apache/harmony/awt/gl/font/LinuxFont;Ljava/lang/String;)J
 * Initializes native Xft font object from xlfd string and returns font handle,  
 * also sets font type to the font peer parameter. If font 
 * that is described by the given xlfd doesn't exist onto a system returned value
 * is null. NullPointerException is thrown if there are errors in native code.
 */
JNIEXPORT jlong JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_initializeFontFromFP(JNIEnv *env, jclass jobj, jobject linuxFont, jstring jXLFD, jint size){

    jclass cls;
    jfieldID fid;
    jmethodID mid;
    jboolean iscopy;

    const char* xlfd;
    Display *dpy;
    int scr;
    XftFont *xftFnt;
    int font_type = FONT_TYPE_UNDEF;
    FT_Face face;

    char **fn; 
    int n = 0;
    int buf_size;
    char *buffer;
    // Initialize part
    cls = (*env)->GetObjectClass(env, linuxFont);

    xlfd = (*env)->GetStringUTFChars(env, jXLFD, &iscopy);
    buf_size = (*env)->GetStringLength(env, jXLFD) + 8; 
    buffer = (char *)malloc(buf_size);

    snprintf(buffer, buf_size, xlfd, (int)((double)(size*10) / 96 * 72));
    (*env)->ReleaseStringUTFChars(env, jXLFD, xlfd);
    
    fid=(*env)->GetFieldID(env, cls, "display", "J");
    if (fid == 0) {
        //      printf("Can't find field \"size\"");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }


    dpy = (Display *)(*env)->GetLongField(env, linuxFont, fid);

    if (dpy == NULL){
        throwNPException(env, "Cannot connect to XServer");
    }   
    

    fid=(*env)->GetFieldID(env, cls, "screen", "I");
    if (fid == 0) {
        //      printf("Can't find field \"screen\"");
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }
    scr = (*env)->GetIntField(env, linuxFont, fid);

    // Xft part
    fn = XListFonts( dpy, buffer, 10, &n );
    if (fn != NULL){
        XFreeFontNames(fn);
    }

    if (n == 0){
        free(buffer);
        return 0;
    }

    xftFnt = XftFontOpenXlfd (dpy, scr, buffer);
        free(buffer);

    if (!xftFnt){
        return (long)NULL;
    }

    // Set Font type upcall
    mid=(*env)->GetMethodID(env, cls, "setFontType", "(I)V");

    if (mid == 0) {
#ifdef DEBUG
        printf("Can't find \"setFontType\" method");
#endif // DEBUG 
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }
 
    face = XftLockFace(xftFnt);
        if ((face->face_flags & FT_FACE_FLAG_SCALABLE) &&       
        !(face->face_flags & (FT_FACE_FLAG_FIXED_SIZES | FT_FACE_FLAG_FIXED_WIDTH))){
            if (face->face_flags & FT_FACE_FLAG_SFNT){
                font_type = FONT_TYPE_TT;
            } else {

                font_type = FONT_TYPE_T1;
            }
        }
    XftUnlockFace(xftFnt);
    
    if (font_type == FONT_TYPE_UNDEF){
        XftFontClose (dpy, xftFnt);
        return (jlong)NULL;
    }

    (*env)->CallVoidMethod(env, linuxFont, mid, font_type);

    if((*env)->ExceptionOccurred(env)) {
#ifdef DEBUG
        printf("Error occured when calling \"setFontType\" method");
#endif // DEBUG 
        (*env)->ExceptionDescribe(env);
        (*env)->ExceptionClear(env);
        return (jlong)NULL;
    }

    return (long)xftFnt;

}


/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getNumGlyphsNative
 * Signature: (J)I
 * Returns number of glyphs in specified XftFont if success.
 */
JNIEXPORT jint JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getNumGlyphsNative(JNIEnv *env, jclass obj, jlong fnt){

    jint numGlyphs = 0;
    XftFont *xftFnt = (XftFont *)fnt;
    FT_Face face;
    
        face = XftLockFace(xftFnt);
        numGlyphs = face->num_glyphs;
#ifdef DEBUG
        printf("Num glyphs = %d\n", numGlyphs);
#endif /* DEBUG */

        XftUnlockFace(xftFnt);

    return numGlyphs;   
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    canDisplayCharNative
 * Signature: (JC)Z
 * Returns true, if XftFont object can display specified char.
 */
JNIEXPORT jboolean JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_canDisplayCharNative(JNIEnv *env, jclass obj, jlong fnt, jchar c){

    jboolean canDisplay = 0;

    /* TODO: implement method - or we can use getGlyphCode results to find out, 
     * whether we can display char or not.
     */

    return canDisplay;
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getFamilyNative
 * Signature: (J)Ljava/lang/String;
 * Returns family name of the XftFont object.
 */
JNIEXPORT jstring JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getFamilyNative(JNIEnv *env, jclass obj, jlong fnt){

    XftFont *xftFnt = (XftFont *)fnt;
    
    jstring familyName = NULL;
    char* family;   

        if (XftPatternGetString (xftFnt->pattern, XFT_FAMILY, 0, &family) != XftResultMatch){
        throwNPException(env, "Can not get font family value");
                    
    }

    familyName = (*env)->NewStringUTF(env, family);

    return familyName;  
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getFontNameNative
 * Signature: (J)Ljava/lang/String;
 * Returns face name of the XftFont object.
 */
JNIEXPORT jstring JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getFontNameNative(JNIEnv *env, jclass obj, jlong fnt){


    jstring faceName = NULL;
    XftFont *xftFnt = (XftFont *)fnt;
    FT_Face face;
    const int BUF_SIZE = 64;
    char name[BUF_SIZE];    
    char* fStr = "%s %s";       
    
        // At the moment we use only EN locale font name representing.
    face = XftLockFace(xftFnt);
        snprintf(name, BUF_SIZE, fStr, face->family_name, face->style_name);
#ifdef DEBUG
        printf("Face name = %s\n", name);
#endif // DEBUG 

    XftUnlockFace(xftFnt);

    faceName = (*env)->NewStringUTF(env, name);

    return faceName;    

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getFontPSNameNative
 * Signature: (J)Ljava/lang/String;
 * Returns XftFont's postscript name.
 * Returned value is the name of the font in system default locale or
 * for english langid if there is no results for default locale settings.
 */
JNIEXPORT jstring JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getFontPSNameNative(JNIEnv *env, jclass obj, jlong fnt){

    XftFont *xftFnt = (XftFont *)fnt;
    FT_Face face;
    const char* name;   
    
    jstring psName;
    
        face = XftLockFace(xftFnt);

    name = FT_Get_Postscript_Name(face);
#ifdef DEBUG
        printf("PostScript name = %s\n", name);
#endif /* DEBUG */


        XftUnlockFace(xftFnt);

    psName = (*env)->NewStringUTF(env, name);

    return psName;  

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    pFontFree
 * Signature: (JJ)V
 * Disposes XftFont object.
 */
JNIEXPORT void JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_pFontFree(JNIEnv *env, jclass obj, jlong fnt, jlong display){
    Display *dpy = (Display *)display;
    XftFont *xftFnt = (XftFont *)fnt;

    XftFontClose (dpy, xftFnt);
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getItalicAngleNative
 * Signature: (JI)F
 * Returns tangent of Italic angle of given Font.
 * Returned value is null and NullPointerException is thrown if there is Xft error.
 */
JNIEXPORT jfloat JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getItalicAngleNative(JNIEnv *env, jclass obj, jlong fnt, int fontType){

    jfloat italicAngle = 0.0;
    XftFont *xftFnt = (XftFont *)fnt;
    FT_Face face;
#if HAS_FREETYPE_INTERNAL
    TT_Face tt_face;
#endif
    TT_HoriHeader* hh;
    float rise;
    float run;
    PS_FontInfoRec afont_info;
    FT_Error err;
    double pi = 3.1415926535;

    face = XftLockFace(xftFnt);
    if (fontType == FONT_TYPE_TT){

#if HAS_FREETYPE_INTERNAL
            tt_face = (TT_Face)face;
            hh = &(tt_face->horizontal);
#else
            hh = (TT_HoriHeader*)FT_Get_Sfnt_Table(face, ft_sfnt_hhea);
#endif
            rise = (float)hh->caret_Slope_Rise;
            run = (float)hh->caret_Slope_Run;
            italicAngle = run / rise ;

    } else {
        err =  FT_Get_PS_Font_Info( face, &afont_info);
        if (err){
            throwNPException(env, "Error in FT_Get_PS_Font_Info fucntion");
            XftUnlockFace(xftFnt);
            return italicAngle;
        }
        italicAngle = tan(((double)afont_info.italic_angle * pi) / 180);        

    }
    
#ifdef DEBUG
            printf("Italic angle value = %f\n", italicAngle);
#endif /* DEBUG */

    XftUnlockFace(xftFnt);

    return italicAngle;
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getFonts
 * Signature: ()[Ljava/lang/String;
 * Returns an array of available system fonts names.
 * In case of errors NullPointerException is thrown.
 */
JNIEXPORT jobjectArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getFonts(JNIEnv *env, jclass obj){

    jobjectArray fonts;

    XftObjectSet *os = 0;
    XftPattern *xftPattern;
    int len;
        XftFontSet *fs;
    int j, i;
    int numFonts;
    const int BUF_SIZE = 128;
        FcChar8 font[BUF_SIZE];
        FcChar8 *family;
    FcChar8 *style;
    char** fontList;
    char* fstr="%s-%s"; // family name-style
    jclass strClass;
    jstring initStr;

    /* Create pattern */
    xftPattern = XftPatternCreate();

    if (!XftPatternAddBool (xftPattern, XFT_OUTLINE, FcTrue)){
        throwNPException(env, "Outline value can't be added to XftPattern");
    }

    /* Just need to add which fields you want to list */
    os = XftObjectSetBuild (XFT_FAMILY, XFT_STYLE, (char *) 0);

    fs = FcFontList (0, xftPattern, os);
    
        FcObjectSetDestroy (os);
    
    if(xftPattern){
            XftPatternDestroy(xftPattern);
    }

        if (!fs){
        throwNPException(env, "Font list can't be created");
    } else  {
        numFonts = fs->nfont;
        fontList = (char** )malloc(numFonts * sizeof(char *));

        for (j = 0; j < numFonts; j++)
        {

                if (XftPatternGetString (fs->fonts[j], XFT_FAMILY, 0, &family) != XftResultMatch){
                throwNPException(env, "Couldn't get font family name");
                                                
            }

#ifdef DEBUG
//              font = FcNameUnparse (fs->fonts[j]);
//              printf ("%s\n", font);

#endif /* DEBUG */
//              XftPatternGetInteger (fs->fonts[j], XFT_SLANT, 0, &slant);

//              XftPatternGetInteger (fs->fonts[j], XFT_WEIGHT, 0, &weight);
                        
                XftPatternGetString (fs->fonts[j], XFT_STYLE, 0, &style);

            len = snprintf(font, BUF_SIZE, fstr, family, style);

            if (len < 0){
                len = BUF_SIZE;
            }

            fontList[j] = (char*)malloc(sizeof(char) * (len+1));
            strcpy(fontList[j], font);
                
        }
        XftFontSetDestroy (fs);
        }


    strClass = (*env)->FindClass(env, "java/lang/String");
    initStr = (*env)->NewStringUTF(env, "");

    fonts = (jobjectArray)(*env)->NewObjectArray(env, 
        numFonts,  
        strClass,
        initStr);

    if (fonts == NULL){
        for (i = 0;i < numFonts;i++){
            free(fontList[i]);
        }
        free(fontList);
        
        throwNPException(env, "Not enough memory to create families list");
    }

    for (i = 0;i < numFonts;i++){
        (*env)->SetObjectArrayElement(env, fonts,i,(*env)->NewStringUTF(env, fontList[i])); // number of chars == length of string -1
        free(fontList[i]);
    }
    free(fontList);

    return fonts;

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getNativeLineMetrics
 * Signature: (JIZZI)[F
 * Returns array of values of font metrics corresponding to the given XftFont 
 * font object. NullPointerException is thrown in case of errors.
 */
JNIEXPORT jfloatArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getNativeLineMetrics(JNIEnv *env, jclass obj, jlong fnt, jint fontHeight, jboolean isAntialiased, jboolean usesFractionalMetrics, jint fontType){
    jfloatArray metrics;
    jfloat values[17];


    XftFont *xftFnt = (XftFont *)fnt;
    FT_Face face;
    TT_OS2* os2;
#if HAS_FREETYPE_INTERNAL
    TT_Face tt_face;
#endif
    int units_per_EM;
    FT_Size_Metrics size_metrics;
    FT_Size size;
    float mltpl;

#ifdef DEBUG
    printf("XFT Ascent = %d\n", xftFnt->ascent);
    printf("XFT Descent = %d\n", xftFnt->descent);
    printf("XFT Height = %d\n", xftFnt->height);
#endif /* DEBUG */
        face = XftLockFace(xftFnt);
        units_per_EM = face->units_per_EM;
            if (units_per_EM == 0){
            throwNPException(env, "Units per EM value is equals to zero");
        }
        values[16] = units_per_EM;
        mltpl = (float)fontHeight / units_per_EM;
        values[0] = (float)(face->ascender) * mltpl;    // Ascent value
        values[1] = (float)(face->descender) * mltpl;   // -Descent value
        values[2] = (float)(face->height) * mltpl  - values[0] + values[1]; // External Leading value
        values[3] = (float)(face->underline_thickness) * mltpl;     // Underline size value
        values[4] = (float)(face->underline_position) * mltpl;  // Underline position value

        if (fontType == FONT_TYPE_TT){
#if HAS_FREETYPE_INTERNAL
            tt_face = (TT_Face)face;
            os2 = &(tt_face->os2);
#else
            os2 = (TT_OS2*)FT_Get_Sfnt_Table(face, ft_sfnt_os2);
#endif

            values[5] = (float)(os2->yStrikeoutSize) * mltpl;    // Strikeout size value
            values[6] = (float)(os2->yStrikeoutPosition) * mltpl;    // Strikeout position value
        } else {
            values[5] = values[3];
            // !!Workaround: for Type1 fonts strikethrough position = (-ascent+descent)/2
            values[6] = (-values[0] - values[1])/2;
        }

        values[7] = (float)(face->bbox.xMax - face->bbox.xMin)* mltpl;  // Max char width

#ifdef DEBUG
/*      printf("Ascent = %d\n", face->ascender >> 6);
        printf("Descent = %f\n", values[1]);
        printf("External Leading = %f\n", values[2]);
        printf("Underline size = %f\n", values[3]);
        printf("Underline position = %f\n", values[4]);
        printf("Strikeout size = %f\n", values[5]);
        printf("Strikeout position = %f\n", values[6]);
        printf("Max char width = %f\n", values[7]);
*/
#endif /* DEBUG */
        
        size = face->size;
        size_metrics = size->metrics;
        values[8] = (int)size_metrics.ascender >> 6;    // Ascent value
        values[9] = (int)size_metrics.descender >> 6;   // Descent value
        values[10] = (int)(size_metrics.height >> 6)  - values[8] + values[9]; // External Leading value
        values[11] = (int)values[3];    // Underline size value
        values[12] = (int)values[4];    // Underline position value

        values[13] = (int)values[5];    // Strikeout size value
        values[14] = (int)values[6];    // Strikeout sposition value

        values[15] = (int)values[7];    // Max char width

#ifdef DEBUG_LL
        printf("Ascent = %f\n", values[0]);
        printf("Descent = %f\n", values[1]);
        printf("External Leading = %f\n", values[2]);
        printf("Underline size = %f\n", values[3]);
        printf("Underline position = %f\n", values[4]);
        printf("Strikeout size = %f\n", values[5]);
        printf("Strikeout position = %f\n", values[6]);
        printf("Max char width = %f\n", values[7]);

        printf("Pixel Ascent = %f\n", values[8]);
        printf("Pixel Descent = %f\n", values[9]);
        printf("Pixel External Leading = %f\n", values[10]);
        printf("Pixel Underline size = %f\n", values[11]);
        printf("Pixel Underline position = %f\n", values[12]);
        printf("Pixel Strikeout size = %f\n", values[13]);
        printf("Pixel Strikeout position = %f\n", values[14]);
        printf("Pixel Max char width = %f\n", values[15]);

#endif /* DEBUG */


        XftUnlockFace(xftFnt);

    metrics = (*env)->NewFloatArray(env, 17);
    
    (*env)->SetFloatArrayRegion(env, metrics, 0, 17, values);

    return metrics;
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getGlyphInfoNative
 * Signature: (JCI)[F
 * Returns array of glyph metrics values for the specified character
 * null is returned and NullPointerException is thrown in case of FreeType 
 * errors.
 */
JNIEXPORT jfloatArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getGlyphInfoNative(JNIEnv *env, jclass obj, jlong fnt, jchar chr, jint fontHeight){

    jfloatArray results;
    jfloat values[11];

    XftFont *font = (XftFont *)fnt;
    const float CONST_96_DIV_76 = (float)96 / 72;
    float mltpl;
    FT_Face face;
    FT_GlyphSlot glyphslot;
        FT_Glyph_Metrics  metrics;
        FT_UInt glyph_index;

        FT_Pos  width;         /* glyph width  */
        FT_Pos  height;        /* glyph height */

        FT_Pos  horiBearingX;  /* left side bearing in horizontal layouts */
        FT_Pos  horiBearingY;  /* top side bearing in horizontal layouts  */
        FT_Pos  horiAdvance;   /* advance width for horizontal layout     */

        FT_Pos  vertBearingX;  /* left side bearing in vertical layouts */
        FT_Pos  vertBearingY;  /* top side bearing in vertical layouts  */
        FT_Pos  vertAdvance;   /* advance height for vertical layout    */
    int units_per_EM;

        face = XftLockFace(font);

        units_per_EM = face->units_per_EM;
            glyph_index = FT_Get_Char_Index( face, chr );

        FT_Load_Glyph(face, glyph_index, FT_LOAD_RENDER | FT_LOAD_TARGET_MONO);
                glyphslot = face->glyph;
            metrics = glyphslot->metrics;          
                
            width      = metrics.width;         /* glyph width  */
            height     = metrics.height;        /* glyph height */
                          
            horiBearingX = metrics.horiBearingX;  /* left side bearing in horizontal layouts */
            horiBearingY = metrics.horiBearingY;  /* top side bearing in horizontal layouts  */
            horiAdvance  = metrics.horiAdvance;   /* advance width for horizontal layout     */
                          
            vertBearingX = metrics.vertBearingX;  /* left side bearing in vertical layouts */
            vertBearingY = metrics.vertBearingY;  /* top side bearing in vertical layouts  */
            vertAdvance  = metrics.vertAdvance;   /* advance height for vertical layout    */

        /* Multyplier to obtain proper values in pixels of the metrics */
            mltpl = CONST_96_DIV_76 / units_per_EM;

#ifdef DEBUG

        printf("\n   glyph metrics char = %d: : \n", chr);      
        printf("width = %f : height = %f \n", width, height);       
        printf("ghoriBearingX = %f : ghoriBearingY = %f \n", (float)fontHeight * horiBearingX *mltpl, (float)fontHeight * horiBearingY *mltpl);     
        printf("gvertBearingX = %f : gvertBearingY = %f \n", (float)fontHeight * vertBearingX *mltpl, (float)fontHeight * vertBearingY  *mltpl);        
        printf("ghoriAdvance = %f : gvertAdvance = %f \n", (float)fontHeight * horiAdvance  *mltpl, (float)fontHeight * vertAdvance  *mltpl);       
#endif /* DEBUG*/

        XftUnlockFace(font);

    values[0] = (float)fontHeight * horiBearingX * mltpl; // Glyph Precise Bounds : X
    values[1] = (float)fontHeight * horiBearingY * mltpl; // Glyph Precise Bounds : Y
    values[2] = (float)fontHeight * horiAdvance * mltpl; // Precise AdvanceX
    values[3] = (float)fontHeight * vertAdvance * mltpl; // Precise AdvanceY ?= Ascent+Descent
    values[4] = (float)fontHeight * width * mltpl; // Glyph Precise Bounds : width
    values[5] = (float)fontHeight * height * mltpl; // Glyph Precise Bounds : height

    results = (*env)->NewFloatArray(env, 6);
    (*env)->SetFloatArrayRegion(env, results, 0, 6, values);

    return results;


}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getGlyphPxlInfoNative
 * Signature: (JJC)[I
 * Returns array of glyph metrics values in pixels for the specified character
 * null is returned and NullPointerException is thrown in case of FreeType errors.
 */
JNIEXPORT jintArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getGlyphPxlInfoNative(JNIEnv *env, jclass obj, jlong display, jlong fnt, jchar chr){

    Display * dpy = (Display *)display;
    jintArray metricsArr = 0;
    jint values[6];
    XftFont *font = (XftFont *)fnt;
    XGlyphInfo extents;
        FT_Face face;
    FT_UInt glyph_index;
    FT_BBox  acbox;
        FT_Glyph        glyph;                                         
        FT_Error error;

        face = XftLockFace(font);

        glyph_index = FT_Get_Char_Index (face, chr);

            error = FT_Load_Glyph( face, glyph_index, (FT_LOAD_RENDER | FT_LOAD_TARGET_MONO));

        error = FT_Get_Glyph( face->glyph, &glyph );                   

        FT_Glyph_Get_CBox(glyph,
                      2,   //FT_GLYPH_BBOX_PIXELS
                     &acbox);

    XftTextExtents16 (dpy,
              font,
              &chr, 
              1,
              &extents);
#ifdef DEBUG

    printf("char = %d; y = %d; x = %d; height = %d; width = %d; advX = %d; advY = %d \n", 
            chr, extents.y, extents.x, extents.height, extents.width, extents.xOff, extents.yOff);
#endif /* DEBUG */


    values[0] = - extents.x ; // Glyph Pixels Bounds : X
    values[1] = extents.y ; // Glyph Pixels Bounds : Y
    values[2] = extents.xOff; // Pixels AdvanceX
    values[3] = extents.yOff; // Pixels AdvanceY ?= Ascent+Descent
//  values[4] = extents.width;  // Glyph Pixels Bounds : width
//  values[5] = extents.height; // Glyph Pixels Bounds : height
    values[4] = acbox.xMax-acbox.xMin;
    values[5] = acbox.yMax-acbox.yMin;

    XftUnlockFace(font);

    metricsArr = (*env)->NewIntArray(env, 6);
    (*env)->SetIntArrayRegion(env, metricsArr, 0, 6, values);

    return metricsArr;

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getGlyphCodesNative
 * Signature: (JLjava/lang/String;I)[I
 * Returns glyphs code corresponding to the characters in String specified, null 
 * is returned if failure. NullPointerException is thrown in case of Display 
 * is null.
 */
JNIEXPORT jintArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getGlyphCodesNative(JNIEnv *env, jclass obj, jlong fnt, jstring str, jint len){

    jintArray glyphsCodes = NULL;

    // TODO: implement method
    
    return glyphsCodes; 
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getGlyphCodeNative
 * Signature: (JCJ)I
 * Returns glyph code corresponding to the specified character, null is 
 * returned if failure. NullPointerException is thrown in case of Display is null.
 */
JNIEXPORT jint JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getGlyphCodeNative(JNIEnv *env, jclass obj, jlong fnt, jchar chr, jlong display){
    
    jint code = 0xFFFF;
    Display *dpy = (Display *)display;
    XftFont *font = (XftFont *)fnt;

    if (dpy == NULL){
        throwNPException(env, "Cannot connect to XServer");
    }   


    code = XftCharIndex (dpy, font, (XftChar32)chr);

    return (code == 0) ? 0xFFFF : code; 
    

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    RemoveFontResource
 * Signature: (Ljava/lang/String;)I
 * Description: Re-cache ~/.fonts directory, after temporary font-file is deleted.
 */
JNIEXPORT jint JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_RemoveFontResource(JNIEnv *env, jclass obj, jstring fName){

    FcChar8* dirName;
    jboolean iscopy;
    FcConfig *config;
    FcFontSet *set;
        FcStrSet *subdirs;
    FcStrList *list;
    jboolean result = TRUE;
    dirName = (FcChar8*)((*env)->GetStringUTFChars(env, fName, &iscopy));

    /* get current congif */
    config = FcConfigGetCurrent();

    if (!config){
        return FALSE;
    }

    list = FcConfigGetConfigDirs (config);
    set = FcFontSetCreate ();
    subdirs = FcStrSetCreate ();

    /* scan dir for changes */
    result = result && FcDirScan (set, subdirs, 0, FcConfigGetBlanks (config), dirName, FcFalse);

    /* save changes to the config */
        result = result && FcDirSave (set, subdirs, dirName);

    /* rebuild fonts list */
    FcConfigBuildFonts(config);

    FcFontSetDestroy (set);
    FcStrSetDestroy (subdirs);

    FcStrListDone (list);

    (*env)->ReleaseStringUTFChars(env, fName, dirName);

    return result;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    NativeInitGlyphBitmap
 * Signature: (JC)J
 * Returns pointer to FreeType FT_Bitmap that represents bitmap
 * of the character specified or 0 if failures in native code.
 */
JNIEXPORT jlong JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_NativeInitGlyphBitmap(JNIEnv *env, jclass jobj,  jlong fnt, jchar chr){

    XftFont *font = (XftFont *)fnt;
    FT_Glyph glyph;
        FT_BitmapGlyph  glyph_bitmap;                                  
        FT_UInt glyph_index;
    int size;

        FT_Error error;
    FT_Face face;
    FT_Bitmap ft_bitmap;                                                                      
                              
    GlyphBitmap *gbmp = (GlyphBitmap *)malloc(sizeof(GlyphBitmap));
                                         
        face = XftLockFace(font);

    glyph_index = FT_Get_Char_Index( face, chr );

        // load glyph                                                  
    error = FT_Load_Glyph(face, glyph_index, (FT_LOAD_RENDER | FT_LOAD_TARGET_MONO));
        // extract glyph image                                         
        error = FT_Get_Glyph( face->glyph, &glyph );                   
                                                                       
        // convert to a bitmap (default render mode + destroy old)     
        if ( glyph->format != FT_GLYPH_FORMAT_BITMAP )                 
        {                                                              
          error = FT_Glyph_To_Bitmap( &glyph, (FT_LOAD_RENDER | FT_LOAD_TARGET_MONO),  
                                      0, 1 );                          
            if ( error ){
             // glyph unchanged                              
                FT_Done_Glyph( glyph );
                XftUnlockFace(font);
            return 0;
        }
        }                                                              
                                                                           
        glyph_bitmap = (FT_BitmapGlyph)(glyph);                          
    ft_bitmap = (FT_Bitmap)glyph_bitmap->bitmap;
        
    gbmp->left = glyph_bitmap->left;
    gbmp->top = glyph_bitmap->top;
    gbmp->bitmap.rows = ft_bitmap.rows;
    gbmp->bitmap.width = ft_bitmap.width;
    gbmp->bitmap.pitch = ft_bitmap.pitch;
    gbmp->bitmap.buffer = malloc(ft_bitmap.rows * ft_bitmap.pitch);
    size= ft_bitmap.rows * ft_bitmap.pitch;
    memcpy(gbmp->bitmap.buffer, ft_bitmap.buffer, size);

    FT_Done_Glyph(glyph);
    XftUnlockFace(font);
    return (jlong)gbmp;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    NativeInitGlyphImage
 * Signature: (JC)[B
 * Returns byte array that represents bitmap of the character specified. 
 */
JNIEXPORT jbyteArray JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_NativeInitGlyphImage(JNIEnv *env, jclass jobj,  jlong fnt, jchar chr){
    
    jintArray bitmap = NULL;


    XftFont *font = (XftFont *)fnt;
        FT_Glyph        glyph;                                         
        FT_BitmapGlyph  glyph_bitmap;                                  
        FT_UInt glyph_index;
        int rows;
        int pitch;
    int width;
    int size;
        unsigned char*  buffer;
        short           num_grays;
        char            pixel_mode;
        char            palette_mode;

        FT_Error error;
    FT_Face face;
    FT_Bitmap ft_bitmap;                                                                      
                                                                       
    face = XftLockFace(font);

    glyph_index = FT_Get_Char_Index( face, chr );

      // load glyph                                                  
        error = FT_Load_Glyph( face, glyph_index, (FT_LOAD_RENDER | FT_LOAD_TARGET_MONO) );     
        // extract glyph image                                         
        error = FT_Get_Glyph( face->glyph, &glyph );                   
                                                                       
        // convert to a bitmap (default render mode + destroy old)     
        if ( glyph->format != FT_GLYPH_FORMAT_BITMAP )                 
        {                                                              

          error = FT_Glyph_To_Bitmap( &glyph, (FT_LOAD_RENDER | FT_LOAD_TARGET_MONO),  
                                      0, 1 );                          
            if ( error ){
             // glyph unchanged                              
                FT_Done_Glyph( glyph );
                XftUnlockFace(font);

        }
        }                                                              
                                                                       
        glyph_bitmap = (FT_BitmapGlyph)glyph;                          
                                                                       
    ft_bitmap = (FT_Bitmap)glyph_bitmap->bitmap;
        
    rows = ft_bitmap.rows;
    width = ft_bitmap.width;
    pitch = ft_bitmap.pitch;
        buffer = ft_bitmap.buffer;       
        num_grays = ft_bitmap.num_grays;    
        pixel_mode = ft_bitmap.pixel_mode;   
        palette_mode = ft_bitmap.palette_mode; 
        size = pitch * rows;
#ifdef DEBUG
    printf("Bitmap: ");
        printf("    left = %d : top  = %d\n", glyph_bitmap->left, glyph_bitmap->top);                                                              
        printf("    rows = %d : width  = %d\n", rows, width);                                                              
        printf("    pitch (bytes in a row)  = %d\n", pitch);                                                              
        printf("    num grays  = %d\n", num_grays);                                                              
        printf("    pixel_mode  = %d\n", pixel_mode);                                                              
    printf("    size  = %d\n", pitch * rows);                                                              
    printf("    buffer:: \n");                                                              

    k = 0;
    while ( k < rows){
        for (j = 0 ; j < pitch; j++){
            for (i = 7; i >= 0; i--){
                if (buffer[k*pitch + j] & (1 << i)){
                    printf("*");
                } else {
                    printf(".");
                }
            }
        }
        k++;
        printf(" \n");
    }
#endif // DEBUG
    if (size){
        bitmap = (*env)->NewByteArray(env, size); // resulting Int Array ; 
        (*env)->SetByteArrayRegion(env, bitmap, 0, size, (jbyte *)buffer);
    }
        FT_Done_Glyph( glyph );                                        
    XftUnlockFace(font);

    return bitmap;  

}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    drawStringNative
 * Signature: (JJJJII[CIJ)V
 * Draws text on XftDraw with specified parameters.
 */
JNIEXPORT void 
    JNICALL Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_drawStringNative
      (JNIEnv *env, jclass jobj,  jlong xftDraw, jlong display,  
            jlong colormap, jlong font, jint x, jint y, jcharArray str, jint len, jlong color){

    jboolean iscopy;

        XftDraw *draw = (XftDraw *)xftDraw;
    Display *dpy = (Display *)display;
    Colormap  cmap = (Colormap)colormap;
    XftFont *fnt = (XftFont *)font;
    XftColor *xftColor = (XftColor *)calloc(1, sizeof(XftColor));
    XRenderColor renderColor;
        XftChar16 *string;
    XColor *xcolor = (XColor *)color;

    XftDrawSetSubwindowMode(draw, IncludeInferiors/*mode*/); 

    string = (XftChar16 *)(*env)->GetCharArrayElements(env, str, &iscopy);

    /* Creating XRenderColor structure */
    renderColor.red = xcolor->red;
    renderColor.green = xcolor->green;
    renderColor.blue = xcolor->blue;
    renderColor.alpha = 0xFFFF;

    /* Creating XftColor structure */
    if (XAllocColor (dpy, cmap, xcolor)){
        xftColor->pixel = xcolor->pixel;
    }
    xftColor->color.red = renderColor.red;
    xftColor->color.green = renderColor.green;
    xftColor->color.blue = renderColor.blue;
    xftColor->color.alpha = renderColor.alpha;

    XftDrawString16 (draw,
             xftColor,
             fnt,
             x,
             y,
             string,
             len);

    free(xftColor);
    (*env)->ReleaseCharArrayElements(env, str, string, JNI_ABORT);
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    createXftDrawNative
 * Signature: (JJJ)J
 * Returns XftDraw handle created from specified parameters.
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_createXftDrawNative
  (JNIEnv *env, jclass jobj, jlong display, jlong drawable, jlong visual){

        XftDraw *draw;
    Display *dpy = (Display *)display;
    Drawable drwbl = (Drawable)drawable;

    /* Creating xftDraw structure */
    draw = XftDrawCreate (dpy, drwbl, (Visual *)visual, 0);

    return (jlong)draw;
        
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    xftDrawSetSubwindowModeNative
 * Signature: (JI)V
 * Set new subwindow mode to XftDraw object.
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_xftDrawSetSubwindowModeNative
  (JNIEnv *env, jclass jobj, jlong xftDraw, jint mode){

        XftDraw *draw = (XftDraw *)xftDraw;
    
        XftDrawSetSubwindowMode(draw, mode); 
    
}

/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    XftDrawSetClipRectangles
 * Signature: (JIIJI)Z
 * Sets clipping rectangles in Xft drawable to the specified clipping rectangles. 
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_XftDrawSetClipRectangles
  (JNIEnv *env, jclass jobj, jlong xftDraw, jint xOrigin, jint yOrigin, jlong rects, jint n){

    XftDraw *draw = (XftDraw *)xftDraw;
    XRectangle *xrects = (XRectangle *)rects;
    
    return XftDrawSetClipRectangles (draw, xOrigin, yOrigin, xrects, n);

}


/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    freeXftDrawNative
 * Signature: (J)V
 * Destroys XftDraw object.
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_freeXftDrawNative
  (JNIEnv *env, jclass jobj, jlong xftDraw){
    
    XftDraw *draw = (XftDraw *)xftDraw;

    XftDrawDestroy(draw);
}


/*
 * Class:     org_apache_harmony_awt_gl_font_LinuxNativeFont
 * Method:    getGlyphOutline
 * Signature: (JC)J
 * Returns pointer to the FreeType FT_Outline structure. 
 */
JNIEXPORT jlong JNICALL 
    Java_org_apache_harmony_awt_gl_font_LinuxNativeFont_getGlyphOutline(JNIEnv *env, jclass obj, jlong fnt, jchar chr){

    XftFont *font = (XftFont *)fnt;
    FT_Face face;
    FT_GlyphSlot glyphslot;
        FT_UInt glyph_index;

        face = XftLockFace(font);

            glyph_index = FT_Get_Char_Index( face, chr );

        FT_Load_Glyph(face, glyph_index, FT_LOAD_RENDER | FT_LOAD_TARGET_MONO);
                glyphslot = face->glyph;

        XftUnlockFace(font);
        if ((glyphslot->format & FT_GLYPH_FORMAT_OUTLINE) != 0){
        return (jlong)(&glyphslot->outline);                        
    }
    return 0;
}



