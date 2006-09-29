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
 * @author Pavel Dolgov
 * @version $Revision$
 */
package org.apache.harmony.awt;

import java.io.*;
import java.util.EventListener;

import junit.framework.TestCase;

/**
 * ListenerListTest
 */
public class ListenerListTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(ListenerListTest.class);
    }

    public void testAddSystemListener() {
    }

    public void testAddUserListener() {
    }

    public void testRemoveUserListener() {
    }

    public void testGetUserListeners() {
    }

    public void testGetUserIterator() {
    }

    public void testGetSystemIterator() {
    }

    static class SerializableListener implements EventListener, Serializable {
    };

    public void testSerialize() {

        ListenerList list = new ListenerList();

        list.addUserListener(new EventListener() {});
        assertTrue(list.getUserIterator().hasNext());

        list.addSystemListener(new SerializableListener());
        assertTrue(list.getSystemIterator().hasNext());

        ListenerList restored = writeAndRead(list);
        assertFalse(restored.getUserIterator().hasNext());
        assertTrue(restored.getSystemIterator().hasNext());

    }

    ListenerList writeAndRead(ListenerList original) {
        try {
            File tempFile = File.createTempFile("save", ".object");

            FileOutputStream fos = new FileOutputStream(tempFile);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(original);
            oos.close();


            FileInputStream fis = new FileInputStream(tempFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ListenerList restored = (ListenerList)ois.readObject();
            tempFile.delete();
            return restored;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
