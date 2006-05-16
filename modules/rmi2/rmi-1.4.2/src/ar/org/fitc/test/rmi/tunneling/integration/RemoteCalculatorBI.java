package ar.org.fitc.test.rmi.tunneling.integration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

public class RemoteCalculatorBI extends UnicastRemoteObject implements CalculatorBI {

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
            System.out.println("Vector A entered from "+getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        this.v1 = data;
        System.out.println(data.toString());
        System.out.println();
    }
    
    public BigDecimal[] getDevProm() {
        try {
            System.out.println("get Standard Deviation and Average called from "+getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        BigDecimal[] result = new BigDecimal[2];
        if ((v1 == null) || (v1.size() <= 1)) {
            result[0] = BigDecimal.ZERO;
            result[1] = BigDecimal.ZERO;
            return result;
        }
        
        BigDecimal sumatoria = BigDecimal.ZERO;
        Enumeration e = v1.elements();
        while (e.hasMoreElements()) {
          BigDecimal dValue =  (BigDecimal) e.nextElement();
          
          sumatoria.add(dValue);
        }
        
        BigDecimal promedio = sumatoria.divide(BigDecimal.valueOf(v1.size()));
        BigDecimal temp = BigDecimal.ZERO;
        

        e = v1.elements();
        
        while (e.hasMoreElements()) {
          BigDecimal value =  (BigDecimal) e.nextElement();
          temp = temp.add(value.add(promedio.negate()).pow(2));
        }

        BigDecimal devstandard = temp.divide(BigDecimal.valueOf(v1.size()-1));

        System.out.println("Standard Deviation: "+devstandard);
        System.out.println("Average: "+promedio);
        result[0] = devstandard;
        result[1] = promedio;
        return result;
    }

    public void setMatrixA(BigInteger[][] data) {
        if (data.length != data[0].length) {
            throw new RuntimeException("Matrix should be square!");
        }
        try {
            System.out.println("Matrix A entered from "+getClientHost());
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
            System.out.println("Matrix B entered from "+getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data[0]));
        System.out.println(Arrays.toString(data[1]));
        System.out.println();
        this.m2 = data;
    }

    public BigInteger[][] sumMatrixAB() {
        if ((m1 == null)|| (m2 == null)) {
            throw new RuntimeException("Matrix not loaded");
        }

        try {
            System.out.println("add Matrix called from "+getClientHost());
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        BigInteger[][] result = new BigInteger[m1.length][m2.length];

        int i, j;
        for (i = 0; i < m1.length; i++) {
            for (j = 0; j < m2.length; j++) {
                result [i][j] = m1 [i][j].add(m2 [i][j]);
            }
        }
        System.out.println("Result Matrix (add)");
        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        System.out.println();
        return result;
    }
    
    public BigInteger[][] multiplyMatrixAB() {
        if ((m1 == null)|| (m2 == null)) {
            throw new RuntimeException("Matrix not loaded");
        }

        try {
            System.out.println("multiply Matrix called from "+getClientHost());
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
