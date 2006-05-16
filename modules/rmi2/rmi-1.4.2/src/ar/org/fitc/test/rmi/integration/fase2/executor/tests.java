package ar.org.fitc.test.rmi.integration.fase2.executor;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class tests {

	public tests() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] arg) throws RemoteException {
		
	
		try {
			ServerExecutor o = ((ServerExecutor)Naming.lookup("hh"));
			System.out.println(o.toString());
			System.out.println(o.getString());
			Object t=  o.execute();;
			System.out.println(t.getClass());
		} catch (NoSuchObjectException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
