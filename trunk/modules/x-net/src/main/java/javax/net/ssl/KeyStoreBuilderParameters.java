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

package javax.net.ssl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.security.KeyStore;

/**
 * A parameters object for {@link X509KeyManager}
 * 
 * @since 1.5
 * @see X509KeyManager
 * @see KeyStore.Builder
 */
public class KeyStoreBuilderParameters implements ManagerFactoryParameters {

    private final List<KeyStore.Builder> ksbuilders;

    /**
     * Constructs an instance for the builder passed.
     * 
     * @param builder a builder
     */
    public KeyStoreBuilderParameters(KeyStore.Builder builder) {
        super();
        ksbuilders = Collections.singletonList(builder);
    }

    /**
     * Constructs an instance for the builder list passed.
     * 
     * @param parameters the builder list
     * @throws NullPointerException if {@code parameters} is {@code null}
     * @throws IllegalArgumentException if {@code parameters} is empty
     */
    @SuppressWarnings("unchecked")
    public KeyStoreBuilderParameters(List parameters) {
        super();
        if (parameters == null) {
            throw new NullPointerException("Builders list is null");
        }
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("Builders list is empty");
        }
        ksbuilders = Collections.unmodifiableList(new ArrayList<KeyStore.Builder>(parameters));
    }

    /**
     * The list of builder parameters.
     * 
     * @return an unmodifiable list of parameters
     */
    @SuppressWarnings("unchecked")
    public List getParameters() {
        return ksbuilders;
    }
}
