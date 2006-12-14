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


package java.awt.image;

import junit.framework.TestCase;

public class RasterTest extends TestCase {
    // A regression test for harmony-2717
    public void test_createPackedRaster()
    {
        try {
            Raster.createPackedRaster(null, -32, Integer.MAX_VALUE, 35, new int[] {}, null);
            fail("Exception expected");
        } catch (NullPointerException expectedException) {
            System.out.println(expectedException +" was thrown");
        } catch (RasterFormatException expectedException) {
            fail(expectedException +" was thrown");
        }
    }
}
