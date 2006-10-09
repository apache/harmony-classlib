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
package javax.imageio.metadata;

/**
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 * 
 * TODO add all the methods from the spec
 */
public abstract class IIOMetadata {

    protected boolean standardFormatSupported;
    protected String nativeMetadataFormatName;
    protected String nativeMetadataFormatClassName;
    protected String[] extraMetadataFormatNames;
    protected String[] extraMetadataFormatClassNames;
    protected IIOMetadataController defaultController;
    protected IIOMetadataController controller;

    protected IIOMetadata() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    protected IIOMetadata(boolean standardMetadataFormatSupported,
                          String nativeMetadataFormatName,
                          String nativeMetadataFormatClassName,
                          String[] extraMetadataFormatNames,
                          String[] extraMetadataFormatClassNames) {
        // TODO implement for SpecJBB
        throw new UnsupportedOperationException("Not supported yet");
    }

    //-- TODO add all the methods from the spec
}
