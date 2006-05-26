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
package org.apache.harmony.rmi.internal.transport.jrmp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.rmi.MarshalException;
import java.rmi.NoSuchObjectException;
import java.rmi.UnmarshalException;
import java.rmi.server.ObjID;
import java.rmi.server.UID;

import org.apache.harmony.rmi.internal.runtime.RemoteReferenceManager;
import org.apache.harmony.rmi.internal.transport.ProtocolException;
import org.apache.harmony.rmi.internal.transport.RMIObjectInputStream;
import org.apache.harmony.rmi.internal.transport.RMIObjectOutputStream;

/**
 * Encapsulates all the messages that a RMI Client can send. It is a Tagged 
 * Union Type.
 * 
 * @author Gustavo Petri
 */
public final class Message {

    /**
     * Serialization ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * This constant is used as a part of the JRMI protocol
     */
    private static final int UNUSED_OPERATION = -1;

    /*
     * Data Structures used for a Call message.
     */
    /**
     * The hash of the method to be invoked
     */
    private long hash;

    /**
     * The {@link ObjID} of the Object to which a method will be invoked
     */
    private ObjID objID;

    /**
     * The operation number of the operation to be invoked (Actually not used)
     */
    private int operation;

    /**
     * Argumenst to execute the method 
     */
    private Object[] values;
    
    /**
     * Type of the message to be sent. Defines the type in the tagged union
     */
    private MessageType type;

    /*
     * UID used to for the DGCAck message.
     */
    /**
     * {@link UID} for DGCAck purposes. 
     */
    private UID uid;

    /**
     * Constructs a Ping Message
     */
    public Message() {
        this.type = MessageType.PING;
        this.operation = UNUSED_OPERATION;
    }

    /**
     * Constructs a Call message
     * 
     * @param objID 
     *          the identifier of the Object which's method will be executed 
     * @param hash 
     *          the hash of the method to be executed
     * @param values 
     *          the arguments to execute the method
     */
    public Message(ObjID objID, long hash, Object... values) {
        this.type = MessageType.CALL;
        this.operation = UNUSED_OPERATION;
        this.objID = objID;
        this.hash = hash;
        this.values = values;
    }

    /**
     * Constructs a DGCAck message
     * @param uid
     *          the {@link UID} to issue the DGCAck
     */
    public Message(UID uid) {
        this.type = MessageType.DGCACK;
        this.uid = uid;
    }

    /**
     * Reads a Call message
     * @param in 
     *          the {@link java.io.InputStream} to be read
     * @throws UnmarshalException 
     *          if not able to read the Call
     * @throws NoSuchObjectException
     *          if the object ID is not exported in this server  
     */
    private final void readCall(DataInputStream in) throws UnmarshalException,
            NoSuchObjectException {
        RMIObjectInputStream objIn;
        RemoteReferenceManager refMgr;
        int argsCount = 0;

        try {
            objIn = new RMIObjectInputStream(in);
            this.objID = ObjID.read(objIn);
            this.operation = objIn.readInt();
            this.hash = objIn.readLong();
        } catch (IOException e) {
            throw new UnmarshalException("I/O Error Unmarshaling Call Header",
                    e);
        }
        refMgr = RemoteReferenceManager.getRemoteReferenceManager();
        argsCount = refMgr.getArgsCount(objID, hash);
        values = new Object[argsCount];
        try {
            for (int i = 0; i < argsCount; i++) {
                values[i] = objIn.readObject();
            }
        } catch (ClassNotFoundException e) {
            throw new UnmarshalException(
                    "I/O Error Unmarshaling the Arguments", e);
        } catch (IOException e) {
            throw new UnmarshalException(
                    "I/O Error Unmarshaling the Arguments", e);
        }
    }

    /**
     * Reads a DGCAck message
     * @param in 
     *          the {@link java.io.InputStream} to be read
     * @throws ProtocolException
     *          if not able to read the DGCAck
     */
    private final void readDGCAck(DataInputStream in) throws ProtocolException {
        try {
            uid = UID.read(in);
        } catch (IOException e) {
            throw new ProtocolException("I/O Error Reading the DGCAck");
        }
    }

    /**
     * Writes a DGCAck 
     * @param out
     *          the {@link java.io.OutputStream} to be written
     * @throws ProtocolException
     *          if not able to write the DGCAck
     */
    private final void writeDgcAck(DataOutputStream out)
            throws ProtocolException {
        try {
            out.writeByte(MessageType.DGCACK.getValue());
            uid.write(out);
        } catch (IOException e) {
            throw new ProtocolException("I/O Error Sending DGCAck", e);
        }
    }

    /**
     * Writes a Ping message
     * @param out
     *          the {@link java.io.OutputStream} to be written
     * @throws ProtocolException
     *          if not able to write the Ping
     */
    private final void writePing(DataOutputStream out) throws ProtocolException {
        try {
            out.writeByte(MessageType.PING.getValue());
        } catch (IOException e) {
            throw new ProtocolException("Socket Closed while pinging", e);
        }
    }

    /**
     * Writes a Call message
     * @param out
     *          the {@link java.io.OutputStream} to be written
     * @throws MarshalException
     *          if not able to write the Call
     */
    private final void writeCall(DataOutputStream out) throws MarshalException {

        RMIObjectOutputStream objOut = null;
        try {
            out.writeByte(MessageType.CALL.getValue());
            objOut = new RMIObjectOutputStream(out);
            objID.write(objOut);
            objOut.writeInt(operation);
            objOut.writeLong(hash);
        } catch (Exception e) {
            throw new MarshalException("I/O Error Marshaling Call Header", e);
        }
        try {
            if (values != null)
                for (Object o : values) {
                    objOut.writeObject(o);
                }
        } catch (Exception e) {
            throw new MarshalException("I/O Error Marshaling Arguments", e);
        } finally {
            try {
                objOut.drain();
            } catch (IOException e) {
                throw new MarshalException("I/O Error Marshaling Arguments", e);
            }
        }
    }
        
    /**
     * Gets the arguments of a Call
     * @return 
     *          the arguments of a Call
     */
    public final Object[] getArguments() {
        return values;
    }

    /**
     * Gets the hash of a mathod Call
     * @return the hash of a mathod Call
     */
    public final long getHash() {
        return hash;
    }

    /**
     * Gets the {@link ObjID} of a mathod Call
     * @return 
     *          the {@link ObjID} of a mathod Call
     */
    public final ObjID getObjID() {
        return objID;
    }

    /**
     * Gets the Operation number of a mathod to be Called
     * @return 
     *          the Operation number of a mathod to be Called
     */
    public final int getOperation() {
        return operation;
    }

    /**
     * Gets the message type of this message
     * @return the message type of this message
     */
    public final MessageType getType() {
        return type;
    }

    /**
     * Gets the UID of a DGCAck
     * @return the UID of a DGCAck
     */
    public final UID getUID() {
        return uid;
    }

    /**
     * Reads a message from the specified {@link DataInputStream}
     * @param in
     *          the {@link DataInputStream} to be read
     * @throws IOException
     *          if unable to read the message
     */
    public final void readExternal(DataInputStream in) throws IOException {

        type = MessageType.createMessageType(in.readByte());
        if (type.equals(MessageType.CALL)) {
            readCall(in);
        } else if (type.equals(MessageType.DGCACK)) {
            readDGCAck(in);
        } else if (type.equals(MessageType.PING)) {
        } else {
            throw new ProtocolException("Message not supported");
        }
    }

    /**
     * Writes the Message to the specified {@link DataOutputStream}
     * @param out 
     *          the {@link DataOutputStream} to be written 
     * @throws IOException
     *          if unable to write the Message
     */
    public final void writeExternal(DataOutputStream out) throws IOException {

        if (type == MessageType.CALL) {
            writeCall(out);
        } else if (type == MessageType.PING) {
            writePing(out);
        } else if (type == MessageType.DGCACK) {
            writeDgcAck(out);
        } else {
            throw new ProtocolException("Message not supported");
        }
    }
}