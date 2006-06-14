/* Copyright 2005, 2005 The Apache Software Foundation or its licensors, as applicable
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


package java.util.prefs;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * An exception to indicate that the input XML file is not well-formed or 
 * validating to the appropriate document type, which is specified by 
 * <code>Preferences</code>. 
 * <p>
 * Please note that this class cannot be serialized actually, so relevant 
 * serialization methods only throw <code>NotSerializableException</code>.</p>
 * 
 * @see Preferences
 *
 * @since 1.4
 */
public class InvalidPreferencesFormatException extends Exception {
    
	/**
	 * Constructs a new <code>InvalidPreferencesFormatException</code> instance using an 
	 * exception message.
	 * 
	 * @param s 	the exception message.
	 */
	public InvalidPreferencesFormatException (String s) {
		super(s);
	}

	/**
	 * Constructs a new <code>InvalidPreferencesFormatException</code> instance using a 
	 * exception message and a nested <code>Throwable</code> instance.
	 * 
	 * @param s 	the exception message.
	 * @param t		the nested <code>Throwable</code> instance.
	 */
	public InvalidPreferencesFormatException (String s, Throwable t) {
		super(s,t);
	}

	/**
	 * Constructs a new <code>InvalidPreferencesFormatException</code> instance using a
	 * nested <code>Throwable</code> instance.
	 *	
	 * @param t		the nested <code>Throwable</code> instance.
	 */
	public InvalidPreferencesFormatException (Throwable t) {
		super(t);
	}
	
    /*
     * This method always throws a <code>NotSerializableException</code>, because 
     * this object cannot be serialized,  
     */
	private void writeObject(ObjectOutputStream out) throws NotSerializableException{
	    throw new NotSerializableException();	    
	}
	
    /*
     * This method always throws a <code>NotSerializableException</code>, because 
     * this object cannot be serialized,  
     */
	private void readObject(ObjectInputStream in) throws NotSerializableException{
	    throw new NotSerializableException();	    
	}	
}



