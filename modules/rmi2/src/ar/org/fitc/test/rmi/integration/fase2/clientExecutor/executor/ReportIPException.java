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
package ar.org.fitc.test.rmi.integration.fase2.clientExecutor.executor;

import java.rmi.RemoteException;

/**
 * Defines the constructors for the <code>ReportIPException</code>
 * 
 * @author Jorge Rafael
 * @author Marcelo Arcidiacono
 * 
 * @version 1.0
 */
public class ReportIPException extends RemoteException {

	/**
	 * Version number unique identificator.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ReportIPException() {
		super();
	}

	/**
	 * Sets the message of the exception.
	 * 
	 * @param s the exception message
	 */
	public ReportIPException(String s) {
		super(s);
	}

	/**
	 * Sets the message and the cause of the exception.
	 * 
	 * @param s the exception message
	 * @param cause the exception cause
	 */
	public ReportIPException(String s, Throwable cause) {
		super(s, cause);
	}
}
