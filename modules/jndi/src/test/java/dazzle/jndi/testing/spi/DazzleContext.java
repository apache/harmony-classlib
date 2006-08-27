/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dazzle.jndi.testing.spi;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Binding;
import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameClassPair;
import javax.naming.NameNotFoundException;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.NotContextException;
import javax.naming.OperationNotSupportedException;

public class DazzleContext implements Context {

	class BindingsEnum extends NamesEnum {

		BindingsEnum(Enumeration keysEnum) {
			super(keysEnum);
		}

		public Object nextElement() {
			String key = (String) super.keysEnum.nextElement();
			return new Binding(key, namespace.get(key));
		}
	}

	class NamesEnum implements NamingEnumeration {

		Enumeration keysEnum;

		NamesEnum(Enumeration keysEnum) {
			this.keysEnum = keysEnum;
		}

		public void close() throws NamingException {
		}

		public boolean hasMore() throws NamingException {
			return hasMoreElements();
		}

		public boolean hasMoreElements() {
			return keysEnum.hasMoreElements();
		}

		public Object next() throws NamingException {
			return nextElement();
		}

		public Object nextElement() {
			String key = (String) keysEnum.nextElement();
			return new NameClassPair(key, namespace.get(key).getClass()
					.getName());
		}
	}

	private static NameParser nameParser = new DazzleParser();

	Hashtable environment;

	Hashtable namespace;

	DazzleContext(Hashtable environment) {
		this.environment = new Hashtable();
		namespace = new Hashtable();
		this.environment = (Hashtable) environment.clone();
	}

	public Object addToEnvironment(String propertyName, Object propertyValue)
			throws NamingException {
		return environment.put(propertyName, propertyValue);
	}

	public void bind(Name name, Object target) throws NamingException {
		bind(name.toString(), target);
	}

	public void bind(String strName, Object target) throws NamingException {
		if (strName.length() == 0) {
			throw new InvalidNameException("Name cannot be empty!");
		}
		if (namespace.get(strName) != null) {
			throw new NameAlreadyBoundException();
		} else {
			namespace.put(strName, target);
			return;
		}
	}

	public void close() throws NamingException {
		environment = namespace = null;
	}

	public Name composeName(Name name, Name prefix) throws NamingException {
		Name composite = (Name) prefix.clone();
		composite.addAll(name);
		return composite;
	}

	public String composeName(String strName, String strPrefix)
			throws NamingException {
		return composeName(((Name) (new CompositeName(strName))),
				((Name) (new CompositeName(strPrefix)))).toString();
	}

	public Context createSubcontext(Name name) throws NamingException {
		return createSubcontext(name.toString());
	}

	public Context createSubcontext(String arg0) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public void destroySubcontext(Name name) throws NamingException {
		destroySubcontext(name.toString());
	}

	public void destroySubcontext(String strName) throws NamingException {
		throw new OperationNotSupportedException();
	}

	public Hashtable getEnvironment() throws NamingException {
		return (Hashtable) environment.clone();
	}

	public String getNameInNamespace() throws NamingException {
		return "";
	}

	public NameParser getNameParser(Name name) throws NamingException {
		return getNameParser(name.toString());
	}

	public NameParser getNameParser(String arg0) throws NamingException {
		return nameParser;
	}

	public NamingEnumeration list(Name name) throws NamingException {
		return list(name.toString());
	}

	public NamingEnumeration list(String strName) throws NamingException {
		if (strName.length() == 0) {
			return new NamesEnum(namespace.keys());
		}
		Object target = namespace.get(strName);
		if (target instanceof Context) {
			return ((Context) target).list("");
		} else {
			throw new NotContextException();
		}
	}

	public NamingEnumeration listBindings(Name name) throws NamingException {
		return listBindings(name.toString());
	}

	public NamingEnumeration listBindings(String strName)
			throws NamingException {
		if (strName.length() == 0) {
			return new BindingsEnum(namespace.keys());
		}
		Object target = namespace.get(strName);
		if (target instanceof Context) {
			return ((Context) target).listBindings("");
		} else {
			throw new NotContextException();
		}
	}

	public Object lookup(Name name) throws NamingException {
		return lookup(name.toString());
	}

	public Object lookup(String strName) throws NamingException {
		if (strName.length() == 0) {
			return new DazzleContext(environment);
		}
		Object result = namespace.get(strName);
		if (result == null) {
			throw new NameNotFoundException();
		} else {
			return result;
		}
	}

	public Object lookupLink(Name name) throws NamingException {
		return lookupLink(name.toString());
	}

	public Object lookupLink(String strName) throws NamingException {
		return lookup(strName);
	}

	public void rebind(Name name, Object target) throws NamingException {
		rebind(name.toString(), target);
	}

	public void rebind(String strName, Object target) throws NamingException {
		if (strName.length() == 0) {
			throw new InvalidNameException("Name cannot be empty!");
		} else {
			namespace.put(strName, target);
			return;
		}
	}

	public Object removeFromEnvironment(String propertyName)
			throws NamingException {
		return environment.remove(propertyName);
	}

	public void rename(Name fromName, Name toName) throws NamingException {
		rename(fromName.toString(), toName.toString());
	}

	public void rename(String fromStrName, String toStrName)
			throws NamingException {
		if (fromStrName.length() == 0 || toStrName.length() == 0) {
			throw new InvalidNameException("Name cannot be empty!");
		}
		if (namespace.containsKey(toStrName)) {
			throw new NameAlreadyBoundException();
		}
		Object target = namespace.remove(fromStrName);
		if (target == null) {
			throw new NameNotFoundException();
		} else {
			namespace.put(toStrName, target);
			return;
		}
	}

	public void unbind(Name name) throws NamingException {
		unbind(name.toString());
	}

	public void unbind(String strName) throws NamingException {
		if (strName.length() == 0) {
			throw new InvalidNameException("Name cannot be empty!");
		} else {
			namespace.remove(strName);
			return;
		}
	}
}
