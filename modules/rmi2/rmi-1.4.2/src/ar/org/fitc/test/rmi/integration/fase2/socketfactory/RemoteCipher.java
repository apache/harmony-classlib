package ar.org.fitc.test.rmi.integration.fase2.socketfactory;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCipher<T extends Serializable> extends Remote {

	public byte[] encipher(T s) throws RemoteException;
	public T decipher(byte[] b) throws RemoteException;
	public byte[][] encipher(T[] s) throws RemoteException;
	public T[] decipher(byte[][] b) throws RemoteException;
	
	public Class getTClass() throws RemoteException;
}
