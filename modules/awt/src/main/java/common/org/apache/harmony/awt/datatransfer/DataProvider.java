/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.DataFlavor;


/**
 * Unified representation of transferable data,
 * either obtained from or prepared for native clipboard and/or drag&drop
 */
public interface DataProvider {
    /**
     * More information about MIME types and Drag&Drop can be found at  
     * http://java.sun.com/j2se/1.5.0/docs/api/java/awt/datatransfer/DataFlavor.html
     * http://java.sun.com/j2se/1.5.0/docs/guide/dragndrop/spec/dnd1.html
     */
    
    public static final String FORMAT_TEXT = "text/plain";
    public static final String FORMAT_FILE_LIST = "application/x-java-file-list";
    public static final String FORMAT_URL = "application/x-java-url";
    public static final String FORMAT_HTML = "text/html";
    public static final String FORMAT_IMAGE = "image/x-java-image";

    public static final String TYPE_IMAGE = "image/x-java-image";
    public static final String TYPE_SERIALIZED = 
                                    "application/x-java-serialized-object";
    public static final String TYPE_PLAINTEXT = "text/plain";
    public static final String TYPE_HTML = "text/html";
    public static final String TYPE_URL = "application/x-java-url";
    public static final String TYPE_TEXTENCODING = 
                                    "application/x-java-text-encoding";
    public static final String TYPE_FILELIST = "application/x-java-file-list";
    public static final String TYPE_URILIST = "text/uri-list";

    public static final DataFlavor urlFlavor = 
        new DataFlavor("application/x-java-url;class=java.net.URL", "URL");
    
    public static final DataFlavor uriFlavor = 
        new DataFlavor("text/uri-list", "URI");

    
    public String[] getNativeFormats();
    
    public boolean isNativeFormatAtailable(String nativeFormat);
    
    public String getText();
    
    public String[] getFileList();
    
    public String getURL();

    public String getHTML();
    
    public RawBitmap getRawBitmap();
    
    public byte[] getSerializedObject(Class<?> clazz);
}
