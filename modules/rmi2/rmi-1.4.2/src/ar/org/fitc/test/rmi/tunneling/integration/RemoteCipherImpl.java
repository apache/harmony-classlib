package ar.org.fitc.test.rmi.tunneling.integration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;



public class RemoteCipherImpl implements RemoteCipher<BigInteger> {

	private Cipher encipher;
	private Cipher decipher;
	
	private final BigInteger deserialization(byte[] b) throws IOException,
	ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		BigInteger result = (BigInteger) ois.readObject();
		ois.close();
		return result;
	}
	
	private final byte[] serialization(BigInteger object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return baos.toByteArray();
	}
	static public Cipher getCipher(int mode) {
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES/CFB8/NoPadding", "SunJCE");
			byte[] password = new byte[] {(byte)1, (byte)24, (byte)234, (byte)345, (byte)23, (byte)432, (byte)3, (byte)43};
			Key key = SecretKeyFactory.getInstance("DES").translateKey(
					new SecretKeySpec(password, "DES"));
			cipher.init(mode, key, new IvParameterSpec(password));
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return cipher;
	}
	
	public RemoteCipherImpl() {
		super();
		encipher = getCipher(Cipher.ENCRYPT_MODE);
		decipher = getCipher(Cipher.DECRYPT_MODE);
	}

	public byte[] encipher(BigInteger s) throws RemoteException {
		try {
			return encipher.update(serialization(s));
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		}
	}

	public byte[][] encipher(BigInteger[] s) throws RemoteException {
		byte[][] result;
		result = new byte[s.length][];
		for (int i=s.length; i-- > 0;) {
			result[i] = encipher(s[i]);
		}	
		return result;
	}

	public BigInteger decipher(byte[] b) throws RemoteException {
		try {
			return deserialization(decipher.update(b));
		} catch (IOException e) {
			throw new RemoteException(e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RemoteException(e.getMessage());
		}	
	}

	public BigInteger[] decipher(byte[][] b) throws RemoteException {
		BigInteger[] result;
		result = new BigInteger[b.length];
		for (int i=b.length; i-- > 0;) {
			result[i] = decipher(b[i]);
		}	
		return result;
	}

	public Class getTClass() throws RemoteException {
		return BigInteger.class;
	}

}
