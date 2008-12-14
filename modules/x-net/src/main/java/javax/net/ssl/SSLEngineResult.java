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
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package javax.net.ssl;

public class SSLEngineResult {

    public enum HandshakeStatus {
        NOT_HANDSHAKING, FINISHED, NEED_TASK, NEED_WRAP, NEED_UNWRAP
    }

    public static enum Status {
        BUFFER_OVERFLOW, BUFFER_UNDERFLOW, CLOSED, OK
    }

    // Store Status object
    private final SSLEngineResult.Status status;

    // Store HandshakeStatus object
    private final SSLEngineResult.HandshakeStatus handshakeStatus;

    // Store bytesConsumed
    private final int bytesConsumed;

    // Store bytesProduced
    private final int bytesProduced;

    public SSLEngineResult(SSLEngineResult.Status status,
            SSLEngineResult.HandshakeStatus handshakeStatus, int bytesConsumed, int bytesProduced) {
        if (status == null) {
            throw new IllegalArgumentException("status is null");
        }
        if (handshakeStatus == null) {
            throw new IllegalArgumentException("handshakeStatus is null");
        }
        if (bytesConsumed < 0) {
            throw new IllegalArgumentException("bytesConsumed is negative");
        }
        if (bytesProduced < 0) {
            throw new IllegalArgumentException("bytesProduced is negative");
        }
        this.status = status;
        this.handshakeStatus = handshakeStatus;
        this.bytesConsumed = bytesConsumed;
        this.bytesProduced = bytesProduced;
    }

    public final Status getStatus() {
        return status;
    }

    public final HandshakeStatus getHandshakeStatus() {
        return handshakeStatus;
    }

    public final int bytesConsumed() {
        return bytesConsumed;
    }

    public final int bytesProduced() {
        return bytesProduced;
    }

    @Override
    public String toString() {
        return "SSLEngineReport: Status = " + status + "  HandshakeStatus = " + handshakeStatus
                + "\n                 bytesConsumed = " + bytesConsumed + " bytesProduced = "
                + bytesProduced;
    }
}
