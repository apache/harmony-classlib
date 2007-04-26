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

package javax.sql.rowset.serial;

import java.io.Serializable;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * 
 * A array that can be serialized
 * 
 */
public class SerialArray implements Array, Serializable, Cloneable {

    private static final long serialVersionUID = -8466174297270688520L;

    Object[] elements;

    int baseType;

    String baseTypeName;

    int len;

    /**
     * The constructor
     * 
     * @param array
     *            array to be serializated
     * @param map
     *            the UDT map
     * @throws SerialException
     *             when any error occurs during serializing
     * @throws SQLException
     *             if array is null
     */
    public SerialArray(Array array, Map<String, Class<?>> map)
            throws SerialException, SQLException {
        // TODO add check for map
        if (null == array || null == array.getArray()) {
            throw new SQLException(
                    "Cannot instantiate a SerialArray object with a null Array object.");
        }
        baseType = array.getBaseType();
        baseTypeName = array.getBaseTypeName();
        elements = (Object[]) array.getArray();
    }

    /**
     * The constructor
     * 
     * @param array
     *            array to be serializated
     * @throws SerialException
     *             when any error occurs during serializing
     * @throws SQLException
     *             if array is null
     */
    public SerialArray(Array array) throws SerialException, SQLException {
        if (null == array || null == array.getArray()) {
            throw new SQLException(
                    "Cannot instantiate a SerialArray object with a null Array object.");
        }
        baseType = array.getBaseType();
        baseTypeName = array.getBaseTypeName();
        elements = (Object[]) array.getArray();
    }

    /**
     * Answers the array that copies the SerialArray object.
     * 
     * @return the array that copies the SerialArray object.
     * @throws SerialException
     *             if any error occurs when copy the array
     */
    public Object getArray() throws SerialException {
        Object[] ret = new Object[elements.length];
        System.arraycopy(elements, 0, ret, 0, elements.length);
        return elements;
    }

    /**
     * Answers the array that copies the certain elements of the SerialArray
     * object
     * 
     * @param index
     *            the start offset
     * @param count
     *            the length of element to be copied
     * @return the array that copies the SerialArray object.
     * @throws SerialException
     *             if any error occurs when copy the array
     */
    public Object getArray(long index, int count) throws SerialException {
        if (index < 0 || count >= index + elements.length) {
            throw new SerialException("Illegal Argument");
        }
        Object[] ret = new Object[count];
        // TODO check if casting long to int is legal
        System.arraycopy(elements, (int) index, ret, 0, count);
        return ret;
    }

    /**
     * Answers the array that copies the certain elements of the SerialArray
     * object according to UDT map
     * 
     * @param index
     *            the start offset
     * @param count
     *            the length of element to be copied
     * @param map
     *            the UDT map
     * @return the array that copies the SerialArray object.
     * @throws SerialException
     *             if any error occurs when copy the array
     */
    public Object getArray(long index, int count, Map<String, Class<?>> map)
            throws SerialException {
        // TODO replace raw string to Msg.getString 
        if (index < 0 || count >= index + elements.length) {
            throw new SerialException("Illegal Argument");
        }
        Object[] ret = new Object[count];
        // TODO check if casting long to int is legal
        System.arraycopy(elements, (int) index, ret, 0, count);
        return ret;
    }

    /**
     * Answers the array that copies the SerialArray object according to UDT map
     * 
     * @param map
     *            the UDT map
     * @return the array that copies the SerialArray object.
     * @throws SerialException
     *             if any error occurs when copy the array
     */
    public Object getArray(Map<String, Class<?>> map) throws SerialException {
        // TODO add check for map
        Object[] ret = new Object[elements.length];
        System.arraycopy(elements, 0, ret, 0, elements.length);
        return elements;
    }

    /**
     * Answers the base type of this array
     * 
     * @return the base type of this array
     * @throws SerialException
     *             if any errors occur
     */
    public int getBaseType() throws SerialException {
        return baseType;
    }

    /**
     * Answers the base type name of this array
     * 
     * @return the base type name of this array
     * @throws SerialException
     *             if any errors occur
     */
    public String getBaseTypeName() throws SerialException {
        return baseTypeName;
    }

    /**
     * Answers the base type of this array, but throw always
     * UnsupportedOperationException here
     * 
     * @return the base type of this array
     * @throws SerialException
     *             if any errors occur
     */
    public ResultSet getResultSet() throws SerialException {
        throw new UnsupportedOperationException();
    }

    /**
     * Answers the base type of this array, but throw always
     * UnsupportedOperationException here
     * 
     * @param index
     *            the start offset
     * @param count
     *            the length of element to be copied
     * @return the base type of this array
     * @throws SerialException
     *             if any errors occur
     */
    public ResultSet getResultSet(long index, int count) throws SerialException {
        throw new UnsupportedOperationException();
    }

    /**
     * Answers the base type of this array, but throw always
     * UnsupportedOperationException here
     * 
     * @param index
     *            the start offset
     * @param count
     *            the length of element to be copied
     * @param map
     *            the UDT map
     * @return the base type of this array
     * @throws SerialException
     *             if any errors occur
     */
    public ResultSet getResultSet(long index, int count,
            Map<String, Class<?>> map) throws SerialException {
        throw new UnsupportedOperationException();
    }

    /**
     * Answers the base type of this array, but throw always
     * UnsupportedOperationException here
     * 
     * @param map
     *            the UDT map
     * @return the base type of this array
     * @throws SerialException
     *             if any errors occur
     */
    public ResultSet getResultSet(Map<String, Class<?>> map)
            throws SerialException {
        throw new UnsupportedOperationException();
    }

}
