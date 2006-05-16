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
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class RemoteCalculatorBI extends UnicastRemoteObject implements
        CalculatorBI {

    public RemoteCalculatorBI() throws RemoteException {
        super();
    }

    private static final long serialVersionUID = 1L;

    private Vector v1;

    private BigInteger[][] m1;

    private BigInteger[][] m2;

    public void setVectorA(Vector data) {
        if (data.size() < 1) {
            throw new RuntimeException("Vector should not be empty!");
        }
        try {
            System.out.println("Vector A entered from " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        this.v1 = data;
        System.out.println(data.toString());
        System.out.println();
    }

    public double[] getDevProm() {
        try {
            System.out
                    .println("get Standard Deviation and Average called from "
                            + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        double[] result = new double[2];
        if ((v1 == null) || (v1.size() <= 1)) {
            result[0] = 0;
            result[1] = 0;
            return result;
        }

        double sumatoria = 0;
        Enumeration e = v1.elements();
        while (e.hasMoreElements()) {
            Double oneValue = (Double) e.nextElement();
            double dValue = oneValue.doubleValue();
            sumatoria += dValue;
        }

        double promedio = sumatoria / v1.size();
        double temp = 0;

        e = v1.elements();

        while (e.hasMoreElements()) {
            Double value = (Double) e.nextElement();
            double doubleValue = value.doubleValue();
            temp += Math.pow(doubleValue - promedio, 2.0);
        }

        double devstandard = Math.sqrt(temp / (v1.size() - 1));

        System.out.println("Standard Deviation: " + devstandard);
        System.out.println("Average: " + promedio);
        result[0] = devstandard;
        result[1] = promedio;
        return result;
    }

    public void setMatrixA(BigInteger[][] data) {
        if (data.length != data[0].length) {
            throw new RuntimeException("Matrix should be square!");
        }
        try {
            System.out.println("Matrix A entered from " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data[0]));
        System.out.println(Arrays.toString(data[1]));
        System.out.println();
        this.m1 = data;
    }

    public void setMatrixB(BigInteger[][] data) {
        if (data.length != data[0].length) {
            throw new RuntimeException("Matrix should be square!");
        }
        try {
            System.out.println("Matrix B entered from " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data[0]));
        System.out.println(Arrays.toString(data[1]));
        System.out.println();
        this.m2 = data;
    }

    public BigInteger[][] sumMatrixAB() {
        if ((m1 == null) || (m2 == null)) {
            throw new RuntimeException("Matrix not loaded");
        }

        try {
            System.out.println("add Matrix called from " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        BigInteger[][] result = new BigInteger[m1.length][m2.length];

        int i, j;
        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m2.length; j++) {
                result[i][j] = m1[i][j].add(m2[i][j]);
            }
        }
        System.out.println("Result Matrix (add)");
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        System.out.println();
        return result;
    }

    public BigInteger[][] multiplyMatrixAB() {
        if ((m1 == null) || (m2 == null)) {
            throw new RuntimeException("Matrix not loaded");
        }

        try {
            System.out
                    .println("multiply Matrix called from " + getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        BigInteger[][] result = new BigInteger[m1.length][m2.length];

        int i, j, k;
        BigInteger sum;

        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m2.length; j++) {
                sum = BigInteger.ZERO;
                for (k = 0; k < m1.length; k++) {
                    sum = m1[i][k].multiply(m2[k][j]).add(sum);
                }
                result[i][j] = sum;
            }
        }
        System.out.println("Result Matrix (multiply)");
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        System.out.println();
        return result;
    }

}
