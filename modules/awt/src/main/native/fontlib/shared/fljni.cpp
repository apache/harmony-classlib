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

#include "fljni.h"

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontManager
 * Method:    initManager
 * Signature: (Ljava/lang/String;Ljava/lang/Class;)V
 */

static jclass fontClass;
static jclass outlineClass;    
static jmethodID setOutline;
static jmethodID fontConstructor;

JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontManager_initManager
(JNIEnv *env, jobject obj) {

//    jboolean iscopy;

    //printf("getting fonts...\n");
    Environment::getAllFonts();
    //printf("fonts added\n");
#ifdef WIN32    
	//char *nativePath = (char *)(env->GetStringUTFChars(path, &iscopy));

    //Environment::addPath(nativePath);

	//env->ReleaseStringUTFChars(path, nativePath);
#endif 


    fontClass = env->FindClass("java/awt/Font");
    outlineClass = env->FindClass("org/apache/harmony/awt/gl/font/fontlib/FLOutline");    
    fontConstructor = env->GetMethodID(fontClass, "<init>", "(Ljava/lang/String;II)V");
    setOutline = env->GetMethodID(outlineClass, "setOutline", "([B[F)V");
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontManager
 * Method:    getAllFontsNative
 * Signature: ()[Ljava/awt/Font;
 */
JNIEXPORT jobjectArray JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontManager_getAllFontsNative
(JNIEnv *env, jobject obj) {

    Environment::getAllFonts();

    if (Environment::_length == 0) return NULL;

    fontClass = env->FindClass("java/awt/Font");

    jobjectArray fonts = env->NewObjectArray(Environment::_length, fontClass, NULL );

    FontHeader *fh = Environment::getAllFonts();

    for (unsigned short i = 0; i < Environment::_length; i ++){
#ifndef WIN32
        unsigned short fName[wcslen((wchar_t *)fh->_familyName)];
	for(unsigned short a = 0; a<wcslen(fh->_familyName); a++){
		fName[a]=(unsigned short)(fh->_familyName[a]);
	}
#endif

        env->SetObjectArrayElement(fonts, i, env->NewObject(
            fontClass, 
            fontConstructor, 
#ifdef WIN32
	    env->NewString((jchar *)fh->_familyName, (jsize) wcslen((wchar_t *)fh->_familyName)),
#else
            env->NewString((jchar*)fName, (jsize) wcslen((wchar_t *)fh->_familyName)),
#endif
            (jint) (char) fh->_style,
            (jint) 1
            ));
        fh = fh->_nextHeader;
    }

    return fonts;
}

JNIEXPORT jobject JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontManager_addFont
(JNIEnv *env, jobject obj, jstring fontPath, jint type) {
    jboolean iscopy;

    fontClass = env->FindClass("java/awt/Font");

	char *nativePath = (char *)(env->GetStringUTFChars(fontPath, &iscopy));
    
    FontHeader *fh = Environment::addFile(nativePath, (FontType)type);

	env->ReleaseStringUTFChars(fontPath, nativePath);

    if (fh == NULL) return NULL;

    return env->NewObject(
            fontClass, 
            fontConstructor, 
            env->NewString((jchar *)fh->_familyName, (jsize) wcslen((wchar_t *)fh->_familyName)),
            (jint) (char) fh->_style,
            (jint) 1
            );
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontManager
 * Method:    dispose
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontManager_dispose
(JNIEnv *env, jobject obj) {
    delete Environment::getAllFonts();
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    initFLFontPeer
 * Signature: (Ljava/lang/String;I)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_initFLFontPeer
(JNIEnv *env, jobject obj, jstring fontName, jint style) {
    jboolean iscopy;

	//char *getenv( const char *name );	

	char *tName = (char *)(env->GetStringUTFChars(fontName, &iscopy));
			
	Font *font = createFont(tName, (StyleName) style);

	env->ReleaseStringUTFChars(fontName, tName);

	//if(!font) {		
	//	printf("File not found !!!\n");
	//}
	
	#ifdef WIN32
    return (jlong) font;
    #else
    return (jlong) (long) font;
    #endif
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    getLineMetrics
 * Signature: (J)[I
 */
JNIEXPORT jfloatArray JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_getLineMetrics
(JNIEnv *env, jobject obj, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    #else
    Font *font = (Font *) (long)ptr;
    #endif

    jfloatArray metrics = env->NewFloatArray((jsize) FONT_METRICS_QUANTITY);
    float *buffer = (float *)env->GetPrimitiveArrayCritical(metrics, NULL);

//printf("getting line metrics...\n");
    float *lineMetrics;
    memcpy(buffer, lineMetrics = font->getLineMetrics(), FONT_METRICS_QUANTITY * sizeof(float));
//printf("line metrics gotten");

    delete[] lineMetrics;

    env->ReleasePrimitiveArrayCritical(metrics, buffer, 0);

    return metrics;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    getPSName
 * Signature: (J)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_getPSName
(JNIEnv *env, jobject obj, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    #else
    Font *font = (Font *) (long)ptr;
    #endif

    wchar_t *psName = font->getPSName();

    return env->NewString((jchar *) psName, (jsize) wcslen(psName));
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    getMissingGlyphCode
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_getMissingGlyphCode
(JNIEnv *env, jobject obj, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    #else
    Font *font = (Font *) (long)ptr;
    #endif    

    return (jint) font->getMissingGlyphCode();
}

JNIEXPORT jchar JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_getUnicodeByIndex
(JNIEnv *env, jobject obj, jint index, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    #else
    Font *font = (Font *) (long)ptr;
    #endif

    return (jchar) font->getUnicodeByIndex((unsigned short) index);
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    dispose
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_dispose
(JNIEnv *env, jobject obj, jlong ptr) {
    #ifdef WIN32
    delete (Font *) ptr;
    #else
    delete (Font *) (long) ptr;
    #endif
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLFontPeer
 * Method:    canDisplay
 * Signature: (CJ)Z
 */
JNIEXPORT jboolean JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLFontPeer_canDisplay
(JNIEnv *env, jobject obj, jchar ch, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    #else
    Font *font = (Font *) (long)ptr;
    #endif

    return font->canDisplay((unsigned short)ch);
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLGlyph
 * Method:    getGlyphMetrics
 * Signature: (J)[I
 */
JNIEXPORT jfloatArray JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLGlyph_getGlyphMetrics
(JNIEnv *env, jobject obj, jlong ptr) {
    #ifdef WIN32
    Glyph *glyph = (Glyph *) ptr;
    #else
    Glyph *glyph = (Glyph *) (long)ptr;
    #endif


    jfloatArray metrics = env->NewFloatArray((jsize) GLYPH_METRICS_QUANTITY);
    float *buffer = (float *)env->GetPrimitiveArrayCritical(metrics, NULL);

    memcpy(buffer, glyph->getGlyphMetrics(), GLYPH_METRICS_QUANTITY * sizeof(float));

    env->ReleasePrimitiveArrayCritical(metrics, buffer, 0);

    return metrics;
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLGlyph
 * Method:    initGlyph
 * Signature: (CIJ)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLGlyph_initGlyph
(JNIEnv *env, jobject obj, jchar ch, jint size, jlong ptr) {
    #ifdef WIN32
    Font *font = (Font *) ptr;
    return (jlong) font->getGlyph((unsigned short) ch, (unsigned short) size);
    #else
    Font *font = (Font *) (long)ptr;
    return (jlong) (long) font->getGlyph((unsigned short) ch, (unsigned short) size);
    #endif
}

/*
 * Class:     org_apache_harmony_awt_gl_font_fontlib_FLPath
 * Method:    getShape
 * Signature: ([B[FJ)J
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_awt_gl_font_fontlib_FLPath_getShape
(JNIEnv * env, jobject obj, jobject outline, jlong ptr) {
    #ifdef WIN32
    Glyph *glyph = (Glyph *) ptr;
    #else
    Glyph *glyph = (Glyph *) (long)ptr;
    #endif    

    Outline* out = glyph->getOutline(); 

    out->trim();

    jbyteArray commands = env->NewByteArray((jsize) out->getCommandLength());
    unsigned char *native_buffer = (unsigned char *)env->GetPrimitiveArrayCritical(commands, NULL);

    memcpy(native_buffer, out->_commands, out->getCommandLength());

    env->ReleasePrimitiveArrayCritical(commands, native_buffer, 0);


    jfloatArray points = env->NewFloatArray((jsize) out->getPointsLength());
    float *buffer = (float *)env->GetPrimitiveArrayCritical(points, NULL);

    memcpy(buffer, out->_points, out->getPointsLength() * sizeof(float));

    env->ReleasePrimitiveArrayCritical(points, buffer, 0);

    env->CallVoidMethod(outline, setOutline, commands, points);

    delete out;
}
