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

package org.apache.harmony.jndi.tests.javax.naming.event;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.event.EventDirContext;
import javax.naming.event.NamingListener;

public class MockEventDirContext implements EventDirContext {

	/*
	 * -------------------------------------------------------------------
	 * Methods
	 * -------------------------------------------------------------------
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventDirContext#addNamingListener(javax.naming.Name,
	 *      java.lang.String, java.lang.Object[],
	 *      javax.naming.directory.SearchControls,
	 *      javax.naming.event.NamingListener)
	 */
	public void addNamingListener(Name name, String s, Object[] aobj,
			SearchControls searchcontrols, NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventDirContext#addNamingListener(javax.naming.Name,
	 *      java.lang.String, javax.naming.directory.SearchControls,
	 *      javax.naming.event.NamingListener)
	 */
	public void addNamingListener(Name name, String s,
			SearchControls searchcontrols, NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventDirContext#addNamingListener(java.lang.String,
	 *      java.lang.String, java.lang.Object[],
	 *      javax.naming.directory.SearchControls,
	 *      javax.naming.event.NamingListener)
	 */
	public void addNamingListener(String s, String s1, Object[] aobj,
			SearchControls searchcontrols, NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventDirContext#addNamingListener(java.lang.String,
	 *      java.lang.String, javax.naming.directory.SearchControls,
	 *      javax.naming.event.NamingListener)
	 */
	public void addNamingListener(String s, String s1,
			SearchControls searchcontrols, NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventContext#addNamingListener(javax.naming.Name,
	 *      int, javax.naming.event.NamingListener)
	 */
	public void addNamingListener(Name name, int i,
			NamingListener naminglistener) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventContext#addNamingListener(java.lang.String,
	 *      int, javax.naming.event.NamingListener)
	 */
	public void addNamingListener(String s, int i, NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventContext#removeNamingListener(javax.naming.event.NamingListener)
	 */
	public void removeNamingListener(NamingListener naminglistener)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.event.EventContext#targetMustExist()
	 */
	public boolean targetMustExist() throws NamingException {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#addToEnvironment(java.lang.String,
	 *      java.lang.Object)
	 */
	public Object addToEnvironment(String s, Object o) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#bind(javax.naming.Name, java.lang.Object)
	 */
	public void bind(Name n, Object o) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#bind(java.lang.String, java.lang.Object)
	 */
	public void bind(String s, Object o) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#close()
	 */
	public void close() throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#composeName(javax.naming.Name,
	 *      javax.naming.Name)
	 */
	public Name composeName(Name n, Name pfx) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#composeName(java.lang.String, java.lang.String)
	 */
	public String composeName(String s, String pfx) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#createSubcontext(javax.naming.Name)
	 */
	public Context createSubcontext(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#createSubcontext(java.lang.String)
	 */
	public Context createSubcontext(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#destroySubcontext(javax.naming.Name)
	 */
	public void destroySubcontext(Name n) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#destroySubcontext(java.lang.String)
	 */
	public void destroySubcontext(String s) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#getEnvironment()
	 */
	public Hashtable getEnvironment() throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#getNameInNamespace()
	 */
	public String getNameInNamespace() throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#getNameParser(javax.naming.Name)
	 */
	public NameParser getNameParser(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#getNameParser(java.lang.String)
	 */
	public NameParser getNameParser(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#list(javax.naming.Name)
	 */
	public NamingEnumeration list(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#list(java.lang.String)
	 */
	public NamingEnumeration list(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#listBindings(javax.naming.Name)
	 */
	public NamingEnumeration listBindings(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#listBindings(java.lang.String)
	 */
	public NamingEnumeration listBindings(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#lookup(javax.naming.Name)
	 */
	public Object lookup(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#lookup(java.lang.String)
	 */
	public Object lookup(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#lookupLink(javax.naming.Name)
	 */
	public Object lookupLink(Name n) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#lookupLink(java.lang.String)
	 */
	public Object lookupLink(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#rebind(javax.naming.Name, java.lang.Object)
	 */
	public void rebind(Name n, Object o) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#rebind(java.lang.String, java.lang.Object)
	 */
	public void rebind(String s, Object o) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#removeFromEnvironment(java.lang.String)
	 */
	public Object removeFromEnvironment(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#rename(javax.naming.Name, javax.naming.Name)
	 */
	public void rename(Name nOld, Name nNew) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#rename(java.lang.String, java.lang.String)
	 */
	public void rename(String sOld, String sNew) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#unbind(javax.naming.Name)
	 */
	public void unbind(Name n) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.Context#unbind(java.lang.String)
	 */
	public void unbind(String s) throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#bind(javax.naming.Name,
	 *      java.lang.Object, javax.naming.directory.Attributes)
	 */
	public void bind(Name name, Object obj, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#bind(java.lang.String,
	 *      java.lang.Object, javax.naming.directory.Attributes)
	 */
	public void bind(String s, Object obj, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#createSubcontext(javax.naming.Name,
	 *      javax.naming.directory.Attributes)
	 */
	public DirContext createSubcontext(Name name, Attributes attributes)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#createSubcontext(java.lang.String,
	 *      javax.naming.directory.Attributes)
	 */
	public DirContext createSubcontext(String s, Attributes attributes)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getAttributes(javax.naming.Name)
	 */
	public Attributes getAttributes(Name name) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getAttributes(javax.naming.Name,
	 *      java.lang.String[])
	 */
	public Attributes getAttributes(Name name, String[] as)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getAttributes(java.lang.String)
	 */
	public Attributes getAttributes(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getAttributes(java.lang.String,
	 *      java.lang.String[])
	 */
	public Attributes getAttributes(String s, String[] as)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getSchema(javax.naming.Name)
	 */
	public DirContext getSchema(Name name) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getSchema(java.lang.String)
	 */
	public DirContext getSchema(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getSchemaClassDefinition(javax.naming.Name)
	 */
	public DirContext getSchemaClassDefinition(Name name)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#getSchemaClassDefinition(java.lang.String)
	 */
	public DirContext getSchemaClassDefinition(String s) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#modifyAttributes(javax.naming.Name,
	 *      int, javax.naming.directory.Attributes)
	 */
	public void modifyAttributes(Name name, int i, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#modifyAttributes(javax.naming.Name,
	 *      javax.naming.directory.ModificationItem[])
	 */
	public void modifyAttributes(Name name, ModificationItem[] amodificationitem)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#modifyAttributes(java.lang.String,
	 *      int, javax.naming.directory.Attributes)
	 */
	public void modifyAttributes(String s, int i, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#modifyAttributes(java.lang.String,
	 *      javax.naming.directory.ModificationItem[])
	 */
	public void modifyAttributes(String s, ModificationItem[] amodificationitem)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#rebind(javax.naming.Name,
	 *      java.lang.Object, javax.naming.directory.Attributes)
	 */
	public void rebind(Name name, Object obj, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#rebind(java.lang.String,
	 *      java.lang.Object, javax.naming.directory.Attributes)
	 */
	public void rebind(String s, Object obj, Attributes attributes)
			throws NamingException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(javax.naming.Name,
	 *      javax.naming.directory.Attributes)
	 */
	public NamingEnumeration search(Name name, Attributes attributes)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(javax.naming.Name,
	 *      javax.naming.directory.Attributes, java.lang.String[])
	 */
	public NamingEnumeration search(Name name, Attributes attributes,
			String[] as) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(javax.naming.Name,
	 *      java.lang.String, java.lang.Object[],
	 *      javax.naming.directory.SearchControls)
	 */
	public NamingEnumeration search(Name name, String s, Object[] aobj,
			SearchControls searchcontrols) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(javax.naming.Name,
	 *      java.lang.String, javax.naming.directory.SearchControls)
	 */
	public NamingEnumeration search(Name name, String s,
			SearchControls searchcontrols) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(java.lang.String,
	 *      javax.naming.directory.Attributes)
	 */
	public NamingEnumeration search(String s, Attributes attributes)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(java.lang.String,
	 *      javax.naming.directory.Attributes, java.lang.String[])
	 */
	public NamingEnumeration search(String s, Attributes attributes, String[] as)
			throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(java.lang.String,
	 *      java.lang.String, java.lang.Object[],
	 *      javax.naming.directory.SearchControls)
	 */
	public NamingEnumeration search(String s, String s1, Object[] aobj,
			SearchControls searchcontrols) throws NamingException {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.naming.directory.DirContext#search(java.lang.String,
	 *      java.lang.String, javax.naming.directory.SearchControls)
	 */
	public NamingEnumeration search(String s, String s1,
			SearchControls searchcontrols) throws NamingException {

		return null;
	}

}
