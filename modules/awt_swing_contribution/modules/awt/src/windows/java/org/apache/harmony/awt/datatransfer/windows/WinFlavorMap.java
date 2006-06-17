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
 * @author Michael Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer.windows;

import java.awt.datatransfer.DataFlavor;
import java.util.*;

import org.apache.harmony.awt.datatransfer.*;

public final class WinFlavorMap extends ListResourceBundle {

    public static final String UNICODE = "UNICODE TEXT";
    public static final String TEXT = "TEXT";
    public static final String HTML = "HTML Format";
    public static final String RTF = "Rich Text Format";
    public static final String HDROP = "HDROP";
    public static final String JPEG = "JFIF";
    public static final String PNG = "PNG";
    public static final String BITMAP = "DIB";
    public static final String ENHANCED_METAFILE = "ENHMETAFILE";
    public static final String METAFILE = "METAFILEPICT";
    public static final String LOCALE = "LOCALE";
    public static final String UNIFORM_RESOURCE_LOCATOR = "UniformResourceLocator";

    private static final String multiByteSysParams = ";eoln=\"\r\n\";terminators=1";
    private static final String wideCharSysParams = ";eoln=\"\r\n\";terminators=2";
    private static final DataFlavor mainTextFlavor =
            TextTranslator.getFlavorsIterator().getMainFlavor();
    private static final String mainTextMIME = mainTextFlavor.getMimeType();
    private static final String mainTextName = mainTextFlavor.getHumanPresentableName();

    // UNICODE
    private static final String unicodeAndStringFlavor = mainTextMIME + wideCharSysParams;
    public static final TranslationPoint unicodeAndString = new TranslationPoint(
            new DataFlavor(unicodeAndStringFlavor, mainTextName), UNICODE);
    // TEXT
    private static final String textAndStringFlavor = mainTextMIME + multiByteSysParams;
    public static final TranslationPoint textAndString = new TranslationPoint(
            new DataFlavor(textAndStringFlavor, mainTextName), TEXT);
    // HTML
    private static final String htmlAndUTFStreamFlavor =
            "text/html;charset=utf-8" + multiByteSysParams;
    public static final TranslationPoint htmlAndUTFStream = new TranslationPoint(
            new DataFlavor(htmlAndUTFStreamFlavor, "HTML Fragment"), HTML);
    // URL
    private static final String urlAndURLObjectFlavor =
            "application/x-java-url;class=java.net.URL" + multiByteSysParams;
    public static final TranslationPoint urlAndURLObject = new TranslationPoint(
            new DataFlavor(urlAndURLObjectFlavor, "URL"), UNIFORM_RESOURCE_LOCATOR);
    private static final String urlAndURIListFlavor =
            "text/uri-list" + multiByteSysParams;
    public static final TranslationPoint urlAndURIList = new TranslationPoint(
            new DataFlavor(urlAndURIListFlavor,"URI List"), UNIFORM_RESOURCE_LOCATOR);
    private static final String urlAndStringFlavor =  mainTextMIME + multiByteSysParams;
    public static final TranslationPoint urlAndString = new TranslationPoint(
            new DataFlavor(urlAndStringFlavor, mainTextName), UNIFORM_RESOURCE_LOCATOR);
    // HDROP
    public static final TranslationPoint hdropAndFileList = new TranslationPoint(
            DataFlavor.javaFileListFlavor, HDROP);
    // Images
    public static final TranslationPoint jpegAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, JPEG);
    public static final TranslationPoint pngAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, PNG);
    public static final TranslationPoint bitmapAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, BITMAP);
    public static final TranslationPoint enhmetaAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, ENHANCED_METAFILE);
    public static final TranslationPoint metafileAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, METAFILE);
    // Binary
    public static final TranslationPoint binaryAndRTF = new TranslationPoint(
            new DataFlavor("text/rtf", "Rich Text Format"), RTF);
    public static final TranslationPoint binaryAndLocale = new TranslationPoint(
            new DataFlavor("application/x-java-text-encoding;class=\"[B\"", "Locale"), LOCALE);

    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {
            {unicodeAndString.natife,       unicodeAndStringFlavor},
            {textAndString.natife,          textAndStringFlavor},
            {htmlAndUTFStream.natife,       htmlAndUTFStreamFlavor},
            {hdropAndFileList.natife,       hdropAndFileList.flavor.getMimeType()},
            {binaryAndRTF.natife,           binaryAndRTF.flavor.getMimeType()},
            {binaryAndLocale.natife,        binaryAndLocale.flavor.getMimeType()},
            {urlAndURLObject.natife,    Arrays.asList(new String[] {
                                            urlAndURLObjectFlavor,
                                            urlAndURIListFlavor,
                                            urlAndStringFlavor})
            },
            {jpegAndImage.natife,           jpegAndImage.flavor.getMimeType()},
//          TODO: Uncomment following 4 lines when additional formats are ready
//            {pngAndImage.natife,            pngAndImage.flavor.getMimeType()},
//            {bitmapAndImage.natife,         bitmapAndImage.flavor.getMimeType()},
//            {enhmetaAndImage.natife,        enhmetaAndImage.flavor.getMimeType()},
//            {metafileAndImage.natife,       metafileAndImage.flavor.getMimeType()},
    };

}
