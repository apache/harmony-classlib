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
 * @author Sergey Burlak
 * @version $Revision$
 */

package javax.swing.plaf.basic;

import javax.swing.Action;
import javax.swing.LookAndFeel;
import javax.swing.SwingTestCase;
import javax.swing.UIDefaults;

public class BasicLookAndFeelTest extends SwingTestCase {
    private BasicLookAndFeel lf;

    public void setUp() {
        lf = basicLookAndFeelInstance();
    }

    public void tearDown() {
        lf = null;
    }

    public void testAudioActionMap() {
        assertEquals(13, lf.getAudioActionMap().size());
    }

    public void testDefaultsTable() {
        UIDefaults defaults = lf.getDefaults();
        if (isHarmony()) {
            assertEquals(474, defaults.size());
        }
        assertNull(lookAndFeelInstance().getDefaults());
    }

    public void testCreateAudioAction() {
        Action createAudioAction = lf.createAudioAction("-");
        assertTrue(createAudioAction instanceof BasicLookAndFeel.AudioAction);
        assertTrue(lf.createAudioAction("CheckBoxMenuItem.commandSound") instanceof BasicLookAndFeel.AudioAction);
    }

    private LookAndFeel lookAndFeelInstance() {
        return new LookAndFeel() {
            public String getDescription() {
                return null;
            }
            public String getID() {
                return null;
            }
            public String getName() {
                return null;
            }
            public boolean isNativeLookAndFeel() {
                return false;
            }
            public boolean isSupportedLookAndFeel() {
                return false;
            }
        };
    }

    private BasicLookAndFeel basicLookAndFeelInstance() {
        return new BasicLookAndFeel() {
            public String getDescription() {
                return "basic description";
            }
            public String getID() {
                return "basic id";
            }
            public String getName() {
                return "basic name";
            }
            public boolean isNativeLookAndFeel() {
                return false;
            }
            public boolean isSupportedLookAndFeel() {
                return false;
            }
        };
    }
}