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
package org.apache.harmony.awt.datatransfer.linux;

import java.awt.datatransfer.*;
import java.util.ListResourceBundle;

import org.apache.harmony.awt.datatransfer.*;


public final class LinuxFlavorMap extends ListResourceBundle {

    public static final String UTF8_STRING = "UTF8_STRING";
    public static final String STRING = "STRING";
    public static final String FILE_NAME = "FILE_NAME";
    public static final String JPEG = "JFIF";
    public static final String PNG = "PNG";

    private static final String sysParams = ";eoln=\"\n\";terminators=0";

    // UTF8_STRING
    private static final String utfAndUTFStreamFlavor = "text/plain;charset=utf-8" + sysParams;
    public static final TranslationPoint utfAndUTFStream = new TranslationPoint(
            new DataFlavor(utfAndUTFStreamFlavor, "UTF-8 Stream"), UTF8_STRING);
    // STRING
    private static final String stringAndISOStreamFlavor =
            "text/plain;charset=iso8859-1" + sysParams;
    public static final TranslationPoint stringAndISOStream = new TranslationPoint(
            new DataFlavor(stringAndISOStreamFlavor, "ISO Stream"), STRING);
    // FILE_NAME
    public static final TranslationPoint fileAndFileList = new TranslationPoint(
            DataFlavor.javaFileListFlavor, FILE_NAME);
    // Images
    public static final TranslationPoint jpegAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, JPEG);
    public static final TranslationPoint pngAndImage = new TranslationPoint(
            DataFlavor.imageFlavor, PNG);

    public Object[][] getContents() {
        return contents;
    }

    /**
     * Do not add natives TARGETS and TEXT.
     * They are polymorphous and supported by AWT automatically.
     */
    static final Object[][] contents = {
            {utfAndUTFStream.natife,    utfAndUTFStreamFlavor},
            {stringAndISOStream.natife, stringAndISOStreamFlavor},
            {fileAndFileList.natife,    fileAndFileList.flavor.getMimeType()},
            {jpegAndImage.natife,       jpegAndImage.flavor.getMimeType()},
//          TODO: Uncomment following line when additional formats are ready
//            {pngAndImage.natife,        pngAndImage.flavor.getMimeType()},
    };

}
