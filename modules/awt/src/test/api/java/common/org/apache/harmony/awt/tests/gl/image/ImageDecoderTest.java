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
package org.apache.harmony.awt.tests.gl.image;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

import junit.framework.TestCase;

public class ImageDecoderTest extends TestCase {

    /**
     * Regression test for HARMONY-3602
     */
    public void testTerminate() {
        Image img = Toolkit.getDefaultToolkit().createImage(
                Thread.currentThread().getContextClassLoader()
                        .getResource("images/test.gif"));

        System.out.println(new ImageIcon(img)); // Loads the image.
        img.flush(); // ERROR: Calls Thread.currentThread().interrupt().
        assertFalse("Current thread is interrupted",
                Thread.currentThread().isInterrupted());
    }
}
