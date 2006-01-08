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
* @author Alexander V. Esin
* @version $Revision$
*/
package org.ietf.jgss;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @com.intel.drl.spec_ref
 */
public interface GSSContext {
    /**
     * @com.intel.drl.spec_ref
     */
    public static final int DEFAULT_LIFETIME = 0;

    /**
     * @com.intel.drl.spec_ref
     */
    public static final int INDEFINITE_LIFETIME = Integer.MAX_VALUE;//2147483647;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] initSecContext(byte[] inputBuf, int offset, int len)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int initSecContext(InputStream inStream, OutputStream outStream)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] acceptSecContext(byte[] inToken, int offset, int len)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void acceptSecContext(InputStream inStream, OutputStream outStream)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isEstablished();

    /**
     * @com.intel.drl.spec_ref
     */
    public void dispose() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public int getWrapSizeLimit(int qop, boolean confReq, int maxTokenSize)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] wrap(byte[] inBuf, int offset, int len, MessageProp msgProp)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void wrap(InputStream inStream, OutputStream outStream,
            MessageProp msgProp) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] unwrap(byte[] inBuf, int offset, int len, MessageProp msgProp)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void unwrap(InputStream inStream, OutputStream outStream,
            MessageProp msgProp) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] getMIC(byte[] inMsg, int offset, int len, MessageProp msgProp)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void getMIC(InputStream inStream, OutputStream outStream,
            MessageProp msgProp) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void verifyMIC(byte[] inToken, int tokOffset, int tokLen,
            byte[] inMsg, int msgOffset, int msgLen, MessageProp msgProp)
            throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void verifyMIC(InputStream tokStream, InputStream msgStream,
            MessageProp msgProp) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public byte[] export() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestMutualAuth(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestReplayDet(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestSequenceDet(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestCredDeleg(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestAnonymity(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestConf(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestInteg(boolean state) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void requestLifetime(int lifetime) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public void setChannelBinding(ChannelBinding cb) throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getCredDelegState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getMutualAuthState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getReplayDetState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getSequenceDetState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getAnonymityState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isTransferable() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isProtReady();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getConfState();

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean getIntegState();

    /**
     * @com.intel.drl.spec_ref
     */
    public int getLifetime();

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSName getSrcName() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSName getTargName() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public Oid getMech() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public GSSCredential getDelegCred() throws GSSException;

    /**
     * @com.intel.drl.spec_ref
     */
    public boolean isInitiator() throws GSSException;

}