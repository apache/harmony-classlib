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
package javax.imageio.spi;

/**
 * TODO add all the methods from the spec
 */
public abstract class ImageReaderWriterSpi extends IIOServiceProvider
        implements RegisterableService {

    protected String[] names;
    protected String[] suffixes;
    protected String[] MIMETypes;
    protected String pluginClassName;
    protected boolean supportsStandardStreamMetadataFormat;
    protected String nativeStreamMetadataFormatName;
    protected String nativeStreamMetadataFormatClassName;
    protected String[] extraStreamMetadataFormatNames;
    protected String[] extraStreamMetadataFormatClassNames;
    protected boolean supportsStandardImageMetadataFormat;
    protected String nativeImageMetadataFormatName;
    protected String nativeImageMetadataFormatClassName;
    protected String[] extraImageMetadataFormatNames;
    protected String[] extraImageMetadataFormatClassNames;

    public ImageReaderWriterSpi(String vendorName, String version, String[] names,
                                String[] suffixes, String[] MIMETypes,
                                String pluginClassName,
                                boolean supportsStandardStreamMetadataFormat,
                                String nativeStreamMetadataFormatName,
                                String nativeStreamMetadataFormatClassName,
                                String[] extraStreamMetadataFormatNames,
                                String[] extraStreamMetadataFormatClassNames,
                                boolean supportsStandardImageMetadataFormat,
                                String nativeImageMetadataFormatName,
                                String nativeImageMetadataFormatClassName,
                                String[] extraImageMetadataFormatNames,
                                String[] extraImageMetadataFormatClassNames) {
        super(vendorName, version);

        if (names == null || names.length == 0 || pluginClassName == null) {
            throw new NullPointerException("format names array cannot be NULL or empty");
        }

        if (pluginClassName == null) {
            throw new NullPointerException("Plugin class name cannot bu NULL");
        }


        this.names = names;
        this.suffixes = suffixes;
        this.MIMETypes = MIMETypes;
        this.pluginClassName = pluginClassName;
        this.supportsStandardStreamMetadataFormat = supportsStandardStreamMetadataFormat;
        this.nativeStreamMetadataFormatName = nativeStreamMetadataFormatName;
        this.nativeStreamMetadataFormatClassName = nativeStreamMetadataFormatClassName;
        this.extraStreamMetadataFormatNames = extraStreamMetadataFormatNames;
        this.extraStreamMetadataFormatClassNames = extraStreamMetadataFormatClassNames;
        this.supportsStandardImageMetadataFormat = supportsStandardImageMetadataFormat;
        this.nativeImageMetadataFormatName = nativeImageMetadataFormatName;
        this.nativeImageMetadataFormatClassName = nativeImageMetadataFormatClassName;
        this.extraImageMetadataFormatNames = extraImageMetadataFormatNames;
        this.extraImageMetadataFormatClassNames = extraImageMetadataFormatClassNames;
    }

    public ImageReaderWriterSpi() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String[] getFormatNames() {
        return names;
    }

    public String[] getFileSuffixes() {
        return suffixes;
    }
}
