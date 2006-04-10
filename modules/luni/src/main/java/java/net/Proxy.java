/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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
package java.net;

/**
 * <p>
 * This class is about proxy setting. A proxy contains <code>type</code>,
 * proxy host address information. There are three types of <code>Proxy</code>:
 * <li>Direct type proxy</li>
 * <li>HTTP type proxy</li>
 * <li>SOCKS type proxy</li>
 * </p>
 * <p>
 * A <code>Proxy</code> instance is immutable.
 * </p>
 */
public class Proxy {

	/**
	 * Represents <code>Proxy.Type.DIRECT</code> type proxy setting. It tells
	 * protocol handlers not to use any proxy.
	 */
	public static final Proxy NO_PROXY = new Proxy();

	private Proxy.Type type;

	private SocketAddress address;

	/**
	 * New a <code>Proxy</code> instance. SocketAddress must NOT be null when
	 * <code>type</code> is either <code>Proxy.Type.HTTP</code> or
	 * <code>Proxy.Type.SOCKS</code>. For <code>Proxy.Type.DIRECT</code>
	 * type proxy, use <code>Proxy.NO_PROXY</code> directly instead of
	 * constructing it.
	 * 
	 * @param type
	 *            proxy type
	 * @param sa
	 *            proxy address
	 * @throws IllegalArgumentException
	 *             when <code>type</code> is <code>Proxy.Type.DIRECT</code>
	 *             or SocketAddress is null.
	 */
	public Proxy(Proxy.Type type, SocketAddress sa) {
		/*
		 * Don't use DIRECT type to construct a proxy instance directly.
		 * SocketAddress must NOT be null.
		 */
		if (type == Type.DIRECT || null == sa) {
			throw new IllegalArgumentException(
					"Illegal Proxy.Type or SocketAddress argument.");
		}
		this.type = type;
		address = sa;
	}

	/*
	 * Constructs a Proxy instance, which is Proxy.DIRECT type with null
	 * SocketAddress. This constructor is used for NO_PROXY.
	 */
	private Proxy() {
		type = Type.DIRECT;
		address = null;
	}

	/**
	 * Gets the proxy type.
	 * 
	 * @return the proxy type.
	 */
	public Proxy.Type type() {
		return type;
	}

	/**
	 * Gets the proxy address.
	 * 
	 * @return the proxy address for <code>HTTP</code> and <code>SOCKS</code>
	 *         type proxy. Returns null for <code>DIRECT</code> type proxy.
	 */
	public SocketAddress address() {
		return address;
	}

	/**
	 * <p>
	 * Representing string of the proxy. The string consists of
	 * <code>type.toString()</code> and <code>address.toString()</code> if
	 * <code>type</code> and <code>address</code> are not null.
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return representing string of the proxy.
	 */
	public String toString() {
		String proxyString = String.valueOf(type);
		if (null != address) {
			proxyString += "/" + address.toString();
		}
		return proxyString;
	}

	/**
	 * <p>
	 * Compare <code>obj</code> with current proxy. Returns false if the
	 * <code>obj</code> is not a <code>Proxy</code> object. Returns true if
	 * and only if the <code>obj</code> has the same <code>address</code>
	 * and <code>type</code> value as current proxy.
	 * </p>
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @return true if <code>obj</code> represents the same proxy. Otherwise,
	 *         returns false.
	 */
	public final boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if (!(obj instanceof Proxy)) {
			return false;
		}
		Proxy another = (Proxy) obj;
		// address is null when and only when it's NO_PROXY.
		return (type == another.type) && address.equals(another.address);
	}

	/**
	 * gets the hash code of <code>Proxy</code>.
	 * 
	 * @see java.lang.Object#hashCode()
	 * @return the hash code of <code>Proxy</code>.
	 */
	public final int hashCode() {
		int ret = 0;
		ret += type.hashCode();
		if (null != address) {
			ret += address.hashCode();
		}
		return ret;
	}

	/**
	 * The proxy type, includes <code>DIRECT</code>, <code>HTTP</code> and
	 * <code>SOCKS</code>.
	 */
	// FIXME: This class needs java 5 Enum feature support. 
	// The code will be modified when Enum feature is ready. 
	public static final class Type {
		/**
		 * Direct connection. Connect without any proxy.
		 */
		public static final Type DIRECT = new Type("DIRECT");

		/**
		 * HTTP type proxy. It's often used by protocol handlers such as HTTP,
		 * HTTPS and FTP.
		 */
		public static final Type HTTP = new Type("HTTP");

		/**
		 * SOCKS type proxy.
		 */
		public static final Type SOCKS = new Type("SOCKS");

		private String name;

		/*
		 * Build-in proxy list.
		 */
		private static final Type buildInProxyList[] = { DIRECT, HTTP, SOCKS };

		Type(String name) {
			this.name = name;
		}

		/**
		 * Retrieve <code>Proxy</code> by <code>name</code>.
		 * 
		 * @throws NullPointerException
		 *             if <code>name</code> is null.
		 * @throws IllegalArgumentException
		 *             if <code>name</code> is not a valid proxy type name.
		 */
		public static Proxy.Type valueOf(String name) {
			if (null == name) {
				throw new NullPointerException();
			}
			for (int i = 0; i < buildInProxyList.length; ++i) {
				if (buildInProxyList[i].name.equals(name)) {
					return buildInProxyList[i];
				}
			}
			throw new IllegalArgumentException(name);
		}

		/**
		 * Retrieve an array contains all available proxy types. The sequence of
		 * proxy types is the same as their declaration.
		 * 
		 * @return an array contains all available proxy types.
		 */
		public static final Proxy.Type[] values() {
			return buildInProxyList;
		}

		/**
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return name;
		}

	}
}
