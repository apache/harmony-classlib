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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
/*
 * Created on 10.02.2005
 *
 */
package org.apache.harmony.awt.gl.image;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayDecodingImageSource extends DecodingImageSource {

    byte imagedata[];
    int imageoffset;
    int imagelength;

    public ByteArrayDecodingImageSource(byte imagedata[], int imageoffset,
            int imagelength){
        this.imagedata = imagedata;
        this.imageoffset = imageoffset;
        this.imagelength = imagelength;
    }

    public ByteArrayDecodingImageSource(byte imagedata[]){
        this(imagedata, 0, imagedata.length);
    }

    protected boolean checkConnection() {
        return true;
    }

    protected InputStream getInputStream() {
        return new BufferedInputStream(new ByteArrayInputStream(imagedata,
                imageoffset, imagelength));
    }

}
