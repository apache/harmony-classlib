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
package ar.org.fitc.test.rmi.integration;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface CalculatorBI extends Remote {

    public static final String SERVICENAME = "RemoteCalculator";

    public void setVectorA(Vector data) throws RemoteException;

    public void setMatrixA(BigInteger[][] data) throws RemoteException;

    public void setMatrixB(BigInteger[][] data) throws RemoteException;

    public BigInteger[][] sumMatrixAB() throws RemoteException;

    public BigInteger[][] multiplyMatrixAB() throws RemoteException;

    public double[] getDevProm() throws RemoteException;

}