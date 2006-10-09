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

import org.apache.harmony.x.imageio.plugins.jpeg.JPEGImageWriterSpi;
import org.apache.harmony.x.imageio.plugins.jpeg.JPEGImageReaderSpi;
import org.apache.harmony.x.imageio.spi.FileIOSSpi;
import org.apache.harmony.x.imageio.spi.RAFIOSSpi;
import org.apache.harmony.x.imageio.spi.FileIISSpi;
import org.apache.harmony.x.imageio.spi.RAFIISSpi;

import java.util.Arrays;

import org.apache.harmony.awt.datatransfer.*;

/**
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
public final class IIORegistry extends ServiceRegistry {

    private static volatile IIORegistry instance;

    private static final Class[] CATEGORIES = new Class[] {
        javax.imageio.spi.ImageWriterSpi.class,
        javax.imageio.spi.ImageReaderSpi.class,
        javax.imageio.spi.ImageInputStreamSpi.class,
        //javax.imageio.spi.ImageTranscoderSpi.class,
        javax.imageio.spi.ImageOutputStreamSpi.class
    };

    private IIORegistry() {
        super(Arrays.asList(CATEGORIES).iterator());
        registerBuiltinSpis();
        registerApplicationClasspathSpis();
    }

    private void registerBuiltinSpis() {
        registerServiceProvider(new JPEGImageWriterSpi());
        registerServiceProvider(new JPEGImageReaderSpi());
        registerServiceProvider(new FileIOSSpi());
        registerServiceProvider(new FileIISSpi());
        registerServiceProvider(new RAFIOSSpi());
        registerServiceProvider(new RAFIISSpi());
        //-- TODO implement
    }

    public static IIORegistry getDefaultInstance() {
        // TODO implement own instance for each ThreadGroup (see also ThreadLocal)
        if (instance == null) {
            synchronized(IIORegistry.class) {
                if (instance == null) {
                    instance = new IIORegistry();
                }
            }
        }
        return instance;
    }

    public void registerApplicationClasspathSpis() {
        //-- TODO implement for non-builtin plugins
    }
}
