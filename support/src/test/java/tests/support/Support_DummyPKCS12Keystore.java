/* Copyright 2005 The Apache Software Foundation or its licensors, as applicable
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

package tests.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Enumeration;

/**
 * Empty implementation used to enable unit tests to run.
 */
public class Support_DummyPKCS12Keystore extends KeyStoreSpi {

	/**
	 * 
	 */
	public Support_DummyPKCS12Keystore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineGetKey(java.lang.String, char[])
	 */
	public Key engineGetKey(String arg0, char[] arg1)
			throws NoSuchAlgorithmException, UnrecoverableKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineGetCertificateChain(java.lang.String)
	 */
	public Certificate[] engineGetCertificateChain(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineGetCertificate(java.lang.String)
	 */
	public Certificate engineGetCertificate(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineGetCreationDate(java.lang.String)
	 */
	public Date engineGetCreationDate(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineSetKeyEntry(java.lang.String,
	 *      java.security.Key, char[], java.security.cert.Certificate[])
	 */
	public void engineSetKeyEntry(String arg0, Key arg1, char[] arg2,
			Certificate[] arg3) throws KeyStoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineSetKeyEntry(java.lang.String,
	 *      byte[], java.security.cert.Certificate[])
	 */
	public void engineSetKeyEntry(String arg0, byte[] arg1, Certificate[] arg2)
			throws KeyStoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineSetCertificateEntry(java.lang.String,
	 *      java.security.cert.Certificate)
	 */
	public void engineSetCertificateEntry(String arg0, Certificate arg1)
			throws KeyStoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineDeleteEntry(java.lang.String)
	 */
	public void engineDeleteEntry(String arg0) throws KeyStoreException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineAliases()
	 */
	public Enumeration engineAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineContainsAlias(java.lang.String)
	 */
	public boolean engineContainsAlias(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineSize()
	 */
	public int engineSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineIsKeyEntry(java.lang.String)
	 */
	public boolean engineIsKeyEntry(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineIsCertificateEntry(java.lang.String)
	 */
	public boolean engineIsCertificateEntry(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineGetCertificateAlias(java.security.cert.Certificate)
	 */
	public String engineGetCertificateAlias(Certificate arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineStore(java.io.OutputStream, char[])
	 */
	public void engineStore(OutputStream arg0, char[] arg1) throws IOException,
			NoSuchAlgorithmException, CertificateException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.security.KeyStoreSpi#engineLoad(java.io.InputStream, char[])
	 */
	public void engineLoad(InputStream arg0, char[] arg1) throws IOException,
			NoSuchAlgorithmException, CertificateException {
		// TODO Auto-generated method stub
	}
}
