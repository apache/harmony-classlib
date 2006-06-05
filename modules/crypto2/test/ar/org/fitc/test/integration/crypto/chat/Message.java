/*
 *  Copyright 2005 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Hugo Beilis
 * @author Osvaldo Demo
 * @author Jorge Rafael
 * @version 1.0
 */

package ar.org.fitc.test.integration.crypto.chat;

import java.io.Serializable;

public class Message implements Serializable{

    private static final long serialVersionUID = 4753454675796908642L;

    private String msg;

    private byte[] hash;

    Message(String m,byte[] h) {
        msg = m;
        hash = h;
    }

    public String getText() {
        return msg;
    }

    public byte[] getHash() {
        return hash;
    }

    public String toString() {
        return msg;
    }

    public void setHash(byte[] data) {
        hash = data;
    }

}
