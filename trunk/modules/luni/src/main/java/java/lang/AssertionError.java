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

package java.lang;

/**
 * Indicates that an assertion has failed.
 * 
 * @since 1.4
 */
public class AssertionError extends Error {

    private static final long serialVersionUID = -5013299493970297370L;

    /**
     * Constructs an instance without a message.
     */
    public AssertionError() {
        super();
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(Object)} of the object passed. If the object passed
     * is an instance of {@link Throwable}, then it also becomes the cause of
     * this error.
     * 
     * @param detailMessage
     *            The value to be converted into the message and optionally the
     *            cause.
     */
    public AssertionError(Object detailMessage) {
        super(String.valueOf(detailMessage),
                (detailMessage instanceof Throwable ? (Throwable) detailMessage
                        : null));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(boolean)} of the boolean passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(boolean detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(char)} of the char passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(char detailMessage) {
        this(String.valueOf(detailMessage));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(int)} of the int passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(int detailMessage) {
        this(Integer.toString(detailMessage));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(long)} of the long passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(long detailMessage) {
        this(Long.toString(detailMessage));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(float)} of the float passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(float detailMessage) {
        this(Float.toString(detailMessage));
    }

    /**
     * Constructs an instance with a message that is the
     * {@link String#valueOf(double)} of the double passed.
     * 
     * @param detailMessage
     *            The value to be converted into the message.
     */
    public AssertionError(double detailMessage) {
        this(Double.toString(detailMessage));
    }
}
