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
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
package javax.imageio;

import java.util.Locale;
import java.awt.*;

/**
 * TODO: add the methods from the spec
 */
public class ImageWriteParam extends IIOParam {

    public static final int MODE_DISABLED = 0;
    public static final int MODE_DEFAULT = 1;
    public static final int MODE_EXPLICIT = 2;
    public static final int MODE_COPY_FROM_METADATA = 3;
    protected boolean canWriteTiles = false;
    protected int tilingMode = MODE_COPY_FROM_METADATA;
    protected Dimension[] preferredTileSizes = null;
    protected boolean tilingSet = false;
    protected int tileWidth = 0;
    protected int tileHeight = 0;
    protected boolean canOffsetTiles = false;
    protected int tileGridXOffset = 0;
    protected int tileGridYOffset = 0;
    protected boolean canWriteProgressive = false;
    protected int progressiveMode = MODE_COPY_FROM_METADATA;
    protected boolean canWriteCompressed = false;
    protected int compressionMode = MODE_COPY_FROM_METADATA;
    protected String[] compressionTypes = null;
    protected String compressionType = null;
    protected float compressionQuality = 1.0f;
    protected Locale locale = null;

    protected ImageWriteParam() {}

    public ImageWriteParam(Locale locale) {
        this.locale = locale;

    }

    public int getProgressiveMode() {
        if (canWriteProgressive()) {
            return progressiveMode;
        }
        throw new UnsupportedOperationException("progressive mode is not supported");
    }

    public boolean canWriteProgressive() {
        return canWriteProgressive;
    }

    public void setProgressiveMode(int mode) {
        if (canWriteProgressive()) {
            if (mode < MODE_DISABLED || mode > MODE_COPY_FROM_METADATA || mode == MODE_EXPLICIT) {
                throw new IllegalArgumentException("mode is not supported");
            }
            this.progressiveMode = mode;
        }
        throw new UnsupportedOperationException("progressive mode is not supported");
    }
}

