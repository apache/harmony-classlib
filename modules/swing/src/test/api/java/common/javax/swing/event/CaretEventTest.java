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
 * @author Evgeniya G. Maenkova
 * @version $Revision$
 * Created on 11.11.2004

 */
package javax.swing.event;

import junit.framework.TestCase;

public class CaretEventTest extends TestCase {
    SimpleCaretEvent sce;
    Object obj;

    class SimpleCaretEvent extends CaretEvent {

        public SimpleCaretEvent(final Object obj) {
            super(obj);
        }

        public int getMark(){
            return 0;
        }

        public int getDot(){
            return 0;
        }

    }


    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        obj = new Object();
        sce = new SimpleCaretEvent(obj);
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCaretEvent() {
        assertNotNull(sce);
        assertEquals(sce.getSource(),obj);
    }

    }
