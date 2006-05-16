/* 
*  Copyright 2005 The Apache Software Foundation or its licensors, as applicable. 
* 
*  Licensed under the Apache License, Version 2.0 (the "License"); 
*  you may not use this file except in compliance with the License. 
*  You may obtain a copy of the License at 
* 
*    http://www.apache.org/licenses/LICENSE-2.0 
* 
*  Unless required by applicable law or agreed to in writing, software 
*  distributed under the License is distributed on an "AS IS" BASIS, 
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
*  See the License for the specific language governing permissions and 
*  limitations under the License. 
*/
package ar.org.fitc.rmi.transport.jrmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.MarshalException;
import java.rmi.UnmarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;

import ar.org.fitc.rmi.transport.EndpointID;
import ar.org.fitc.rmi.transport.ProtocolException;

/**
 * Implements the management Client view of the messages of the JRMP protocol.
 * Each of the methods here represent a message or a protocol handshake.
 * 
 * @author Gustavo Petri
 */
public class ClientProtocolHandler {

    /**
     * The local
     * {@link java.io.InputStream}
     * to interact with the server
     */
    private DataInputStream in;

    /**
     * The local
     * {@link java.io.OutputStream}
     * to interact with the server.
     */
    private DataOutputStream out;

    /**
     * Constructor of {@link ClientProtocolHandler}
     * 
     * @param in
     *            the
     *            {@link ar.org.fitc.rmi.transport.RMIObjectInputStream RMIObjectInputStream}
     * @param out
     *            the
     *            {@link ar.org.fitc.rmi.transport.RMIObjectOutputStream RMIObjectOutputStream}
     */
    public ClientProtocolHandler(DataInputStream in, DataOutputStream out) {
        this.out = out;
        this.in = in;
    }

    /**
     * Reads the {@link HeaderResponse#PROTOCOL_ACK PROTOCOL_ACK}.
     * 
     * @throws UnmarshalException
     *             if an I/O exceptions or a read of an unexpected byte ocurrs
     */
    public final void readHandshakeResponse() throws UnmarshalException {

        HeaderResponse rep;
        try {
        	rep = HeaderResponse.createHeaderResponse(in.readByte());
        } catch (IOException e) {
        	throw new UnmarshalException("Exception reading the Header response", e);
        }
        if (!rep.equals(HeaderResponse.PROTOCOL_ACK)) {
        	throw new UnmarshalException("Protocol Not Supported");
        }    }

    /**
     * Reads the {@link MessageResponse#PING_ACK PING_ACK}.
     * 
     * @throws ProtocolException
     *             if an I/O exceptions or a read of an unexpected byte ocurrs
     */
    public final void readPingAck() throws ProtocolException {
        try {
            MessageResponse msgResp = MessageResponse.createMessageResponse(in
                    .readByte());
            if (!msgResp.equals(MessageResponse.PING_ACK)) {
                throw new ProtocolException("I/O Error Reading PingAck");
            }
        } catch (IOException e) {
            throw new ProtocolException("I/O Error PingAck", e);
        }
    }

    /**
     * Constructs a ReturnMessage object and sets its returns value. <br>
     * 
     * Then, calls the
     * {@link ReturnMessage#read(DataInputStream) read(DataInputStream)} method
     * in order to set this object which is finally returned.
     * 
     * @param waitResult
     *            to set the <code>ReturnMessage</code> object
     * @return
     *            the <code>ReturnMessage</code> object
     * @throws Exception
     *             any exception
     */
    public final ReturnMessage readResult(boolean waitResult) throws Exception {
        ReturnMessage result = new ReturnMessage();
        result.setReturnsValue(waitResult);
        result.read(in);
        return result;
    }

    /**
     * Constructs a message with this parameters and writes the Call
     * 
     * @param objId
     *            the object ID
     * @param hash
     *            the hash
     * @param args
     *            the arguments to invoke
     * @throws Exception
     *             any exception
     */
    public final void writeCall(ObjID objId, long hash, Object[] args)
            throws Exception {
        new Message(objId, hash, args).writeExternal(out);
    }

    /**
     * Constructs a message with the <code>uid</code> and then, writes the
     * DGCAck.
     * 
     * @param uid
     *            a unique identifier
     * @throws ProtocolException
     *             if an I/O exceptions ocurrs
     */
    public final void writeDGCAck(UID uid) throws ProtocolException {

        try {
            new Message(uid).writeExternal(out);
        } catch (IOException e) {
            throw new ProtocolException("Error while sending DGCAck", e);
        }
    }

    /**
     * Writes the protocol header, version and type of connection
     * 
     * @param type
     *            the protocol's type
     * @throws MarshalException
     *             if an I/O exceptions ocurrs
     */
    public final void writeHandshake(ProtocolType type) throws MarshalException {

        Header header = new Header(type);
        try {
            header.writeExternal(out);
        } catch (IOException e) {
            throw new MarshalException("Exception marshaling JRMP Header", e);
        }
    }

    /**
     * Writes the host and port
     * 
     * @throws ProtocolException
     *             if an I/O exceptions ocurrs
     */
    public final void writeHandshakeResponse() throws ProtocolException {
        try {
            new EndpointID().write(out);
        } catch (IOException e) {
            throw new ProtocolException(
                    "Error marshaling the protocolHandshake response", e);
        }
    }

    /**
     * Writes the Ping request.
     * 
     * @throws ProtocolException
     *             if an I/O exceptions ocurrs
     */
    public final void writePing() throws ProtocolException {
        try {
            new Message().writeExternal(out);
        } catch (IOException e) {
            throw new ProtocolException("Socket Closed while pinging", e);
        }
    }
}