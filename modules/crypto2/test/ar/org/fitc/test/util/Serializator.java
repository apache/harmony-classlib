package ar.org.fitc.test.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializator<T> {

	static public final Object deserialization(byte[] b) throws IOException,
	ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object result = ois.readObject();
		ois.close();
		return result;
	}
	
	static public final byte[] serialization(Object object) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		oos.close();
		return baos.toByteArray();
	}

	 
	
	
	
}
