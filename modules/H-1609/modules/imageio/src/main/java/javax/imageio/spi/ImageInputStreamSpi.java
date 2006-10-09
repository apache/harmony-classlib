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
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
package javax.imageio.spi;

import javax.imageio.stream.ImageInputStream;
import java.util.Locale;
import java.io.IOException;
import java.io.File;

public abstract class ImageInputStreamSpi extends IIOServiceProvider
        implements RegisterableService {

    protected Class inputClass;

    protected ImageInputStreamSpi() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public ImageInputStreamSpi(String vendorName, String version, Class inputClass) {
        super(vendorName, version);
        this.inputClass = inputClass;
    }

    public Class getInputClass() {
        return inputClass;
    }

    public boolean canUseCacheFile() {
        return false; //-- def
    }

    public boolean needsCacheFile() {
        return false; // def
    }

    public abstract ImageInputStream createInputStreamInstance(Object input,
                    boolean useCache, File cacheDir)
            throws IOException;

    public ImageInputStream createInputStreamInstance(Object input) throws IOException {
        return createInputStreamInstance(input, true, null);
    }
}
