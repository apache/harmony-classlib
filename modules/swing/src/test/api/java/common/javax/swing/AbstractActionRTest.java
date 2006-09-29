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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 * Created on 16.12.2004

 */
package javax.swing;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AbstractActionRTest extends SwingTestCase {

    protected AbstractAction action;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        action = new AbstractAction() {
            public void actionPerformed(final ActionEvent event) {
            }

            private void writeObject(final ObjectOutputStream outStream) throws IOException {
            }
            private void readObject(final ObjectInputStream inStream) throws IOException,
                                                                       ClassNotFoundException  {
            }
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        };
    }

    public void testClone() throws CloneNotSupportedException {
        class MyAbstractAction extends AbstractAction {
            public void actionPerformed(final ActionEvent e) {
            }
            public Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        };
        MyAbstractAction instance = new MyAbstractAction();
        Object value1 = new Object();
        instance.putValue("Value1", value1);
        MyAbstractAction anotherInstance = (MyAbstractAction)instance.clone();
        Object value2 = new Object();
        instance.putValue("Value1", value2);
        assertEquals("cloned object's values list isn't shared", value1, anotherInstance.getValue("Value1"));
    }
}
