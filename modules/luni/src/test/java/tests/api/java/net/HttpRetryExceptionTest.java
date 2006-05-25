/*
 * Copyright 2006 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package tests.api.java.net;

import java.net.HttpRetryException;
import tests.util.SerializationTester;
import junit.framework.TestCase;

public class HttpRetryExceptionTest extends TestCase {

    private static final String SERIALIZATION_FILE_NAME = "serialization/java/net/HttpRetryExceptionTest.golden.ser"; //$NON-NLS-1$

    private static final String LOCATION = "Http test"; //$NON-NLS-1$

    private static final String DETAIL = "detail"; //$NON-NLS-1$

    /**
     * @tests serialization/deserilazation.
     */
    public void testSerialization() throws Exception {
        HttpRetryException ia = new HttpRetryException(DETAIL, 100, LOCATION);

        HttpRetryException deIA = (HttpRetryException) SerializationTester
                .getDeserilizedObject(ia);

        assertEquals(LOCATION, deIA.getLocation());
        assertEquals(100, deIA.responseCode());
        assertEquals(DETAIL, deIA.getReason());
        assertEquals(DETAIL, deIA.getMessage());
    }

    /**
     * @tests serialization/deserilazation compatibility with RI.
     */
    public void testSerializationCompatibility() throws Exception {
        HttpRetryException ia = new HttpRetryException(DETAIL, 100, LOCATION);
        HttpRetryException deIA = (HttpRetryException) SerializationTester
                .readObject(ia, SERIALIZATION_FILE_NAME);
        assertEquals(LOCATION, deIA.getLocation());
        assertEquals(100, deIA.responseCode());
        assertEquals(DETAIL, deIA.getReason());
        assertEquals(DETAIL, deIA.getMessage());
    }
}
