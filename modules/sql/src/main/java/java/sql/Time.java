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


package java.sql;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Java representation of an SQL TIME value.  Provides functions to aid generation and
 * interpretation of JDBC escape format for time values.
 * 
 */
public class Time extends Date implements Serializable, Cloneable, Comparable {
	
	private static final long serialVersionUID = 8397324403548013681L;

	/**
	 * @deprecated Please use the constructor Time( long )
	 * Constructs a Time object using the supplied values for Hour, Minute and Second.
	 * The Year, Month and Day elements of the Time object are set to 1970, January, 1
	 * reflecting the Epoch (Time in milliseconds = 0).
	 * <p>
	 * Any attempt to access the Year, Month or Day elements of a Time object will result in
	 * an IllegalArgumentException.
	 * <p>
	 * Result is undefined if any argument is out of bounds.
	 * @param theHour a value from 0 - 23
	 * @param theMinute a value from 0 - 59
	 * @param theSecond a value from 0 - 59
	 */
	public Time( int theHour, int theMinute, int theSecond ) {
		super( 70, 0, 1, theHour, theMinute, theSecond );
	} // end method Time(int, int, int)
	
	/**
	 * Constructs a Time object using a supplied time specified in milliseconds
	 * @param theTime a Time specified in milliseconds since the Epoch (January 1st 1970, 00:00:00.000)
	 */
	public Time( long theTime ) {
		super( theTime );
		// System.out.println("Clear version of java.sql.Time");
	} // end method Time( long )
	
	/**
	 * @deprecated
	 * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Date component.	 
	 * @return 0
	 * @throws IllegalArgumentException if this method is called
	 */
	public int getDate() {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
		return 0;
	} // end method getDate()
    
	/**
	 * @deprecated
	 * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Day component.
	 * @return 0
	 * @throws IllegalArgumentException if this method is called
	 */
	public int getDay() {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
		return 0;
	} // end method getDay()
    
	/**
	 * @deprecated
	 * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Month component.	 
	 * @return 0
	 * @throws IllegalArgumentException if this method is called
	 */
	public int getMonth() {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
		return 0;
	} // end method getMonth()
    
	/**
	 * @deprecated	 
	 * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Year component.	 
	 * @return 0
	 * @throws IllegalArgumentException if this method is called
	 */
	public int getYear() {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
		return 0;
	} // end method getYear()
	
    /**
     * @deprecated
     * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Date component.	 
	 * @throws IllegalArgumentException if this method is called
     */
	public void setDate(int i) {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
	} // end method setDate( int )
	
    /**
     * @deprecated     
     * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Month component.	 
	 * @throws IllegalArgumentException if this method is called
     */
	public void setMonth(int i) {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
	} // end method setMonth( int )
	
	/**
	 * @deprecated     
	 * This method is deprecated and must not be used.  An SQL Time object does not have a
	 * Year component.	 
	 * @throws IllegalArgumentException if this method is called
	 */
    public void setYear(int i) {
		if( true ) {
			throw new IllegalArgumentException();
		} // end if
    } // end method setYear( int )
    
	/**
	 * Sets the time for this Time object to the supplied milliseconds value.
	 * @param time A time value expressed as milliseconds since the Epoch.  Negative values are milliseconds 
	 * before the Epoch.  The Epoch is January 1 1970, 00:00:00.000
	 */
    public void setTime(long time) {
    	super.setTime( time );
    } // end method setTime( long )
    
    /**
     * Formats the Time as a String in JDBC escape format: hh:mm:ss
     * @return A String representing the Time value in JDBC escape format: HH:mm:ss
     */
    public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss" );
		
		return dateFormat.format( this );
    } // end method toString()
    
    /**
     * Creates a Time object from a String holding a time represented in JDBC escape
     * format:  hh:mm:ss.
     * <p>
     * An exception occurs if the input string is not in the form of a time in JDBC escape
     * format.
     * @param theTime A String representing the time value in JDBC escape format:  hh:mm:ss
     * @return The Time object set to a time corresponding to the given time
     * @throws IllegalArgumentException is the supplied time string is not in JDBC escape
     * format.
     */
    public static Time valueOf( String theTime ) {
		java.util.Date aDate;
		
		if( theTime == null ) throw new IllegalArgumentException();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat( "HH:mm:ss" );
		try {
			aDate = dateFormat.parse( theTime );
		} catch( ParseException pe ) {
			throw new IllegalArgumentException();
		} // end try
		
		return new Time( aDate.getTime() );
    } // end method valueOf( String )

} // end class Time


