/*
 *  Copyright 2006 The Apache Software Foundation or its licensors, as applicable.
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

package org.apache.harmony.sound.tests.javax.sound.midi;

import junit.framework.TestCase;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;


public class MidiMessageTest extends TestCase
{   
    /**
     * test constructor of class MidiMessage
     *
     */
    public void test_constructor_MidiMessage()
    {
        MidiMessage1 midi = new MidiMessage1( new byte[] { 1, 2, 3, -5 } );
        assertTrue( midi.getLength() == 4 );
        assertTrue( midi.getStatus() == 1 );
        assertTrue( midi.getMessage().length == 4 );
        assertTrue( midi.getMessage()[0] == 1 ); 
        assertTrue( midi.getMessage()[1] == 2 );
        assertTrue( midi.getMessage()[2] == 3 );
        assertTrue( midi.getMessage()[3] == -5 );
        
        MidiMessage1 midi1 = new MidiMessage1( new byte[] { 0, -2 } );
        assertTrue( midi1.getLength() == 2 );
        assertTrue( midi1.getStatus() == 0 );
        assertTrue( midi1.getMessage().length == 2 );
        assertTrue( midi1.getMessage()[0] == 0 ); 
        assertTrue( midi1.getMessage()[1] == -2 );
        
        MidiMessage1 midi2 = new MidiMessage1( new byte[] { -1, 87, 19 } );
        assertTrue( midi2.getLength() == 3 );
        assertTrue( midi2.getStatus() == 255 );
        assertTrue( midi2.getMessage().length == 3 );
        assertTrue( midi2.getMessage()[0] == -1 );
        assertTrue( midi2.getMessage()[1] == 87 );
        assertTrue( midi2.getMessage()[2] == 19 );
        
        MidiMessage1 midi3 = new MidiMessage1( null );
        assertTrue( midi3.getLength() == 0 );
        assertTrue( midi3.getStatus() == 0 );
        try {
            midi3.getMessage();
            fail( "NullPointerExcepiton expect" );
        } catch( NullPointerException e ) { }
        
    }
    
    /**
     * test method getMessage() of class MidiMessage
     *
     */
    public void test_getMessage()
    {
        MidiMessage1 midi = new MidiMessage1( new byte[] { 1, 2, 3 } );
        assertTrue( midi.getMessage()[0] == 1 );
        assertTrue( midi.getMessage()[1] == 2 );
        assertTrue( midi.getMessage()[2] == 3 );
        
        MidiMessage1 midi1 = new MidiMessage1( new byte[] { 1 } );
        assertTrue( midi1.getMessage()[0] == 1 );
        try {
            int i = midi1.getMessage()[1];
            fail( "IndexOutOfBoundsException expected" );
        } catch( IndexOutOfBoundsException e ) { };
        
        MidiMessage1 midi2 = new MidiMessage1( null );
        try {
            int i = midi2.getMessage()[0];
            fail( "NullPointerException expected" );
        } catch( NullPointerException e ) { }
        
        try {
            midi2.setMessage( new byte[] { 90, 84 } , 2 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi2.getMessage().length == 2 );
        assertTrue( midi2.getMessage()[0] == 90 );
        assertTrue( midi2.getMessage()[1] == 84 );
        
        try {
            midi1.setMessage( new byte[] { -54, 84, -9 } , 1 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi1.getMessage().length == 1 );
        assertTrue( midi1.getMessage()[0] == -54 );
        
        try {
            midi.setMessage( new byte[] { -90, -7, 4 } , 0 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi.getMessage().length == 0 );
        try {
            int i = midi.getMessage()[0];
            fail( "ArrayIndexOutOfBoundsException expected" );
        } catch( ArrayIndexOutOfBoundsException e ) { };
        
    }
    
    /**
     * test method getStatus() of class MidiMessage
     *
     */
    public void test_getStatus()
    {
        MidiMessage1 midi = new MidiMessage1( new byte[] { 1, 2, 3 } );
        assertTrue( midi.getStatus() == 1 );
        
        MidiMessage1 midi1 = new MidiMessage1( new byte[] { -91, 2, 3 } );
        assertTrue( midi1.getStatus() == 165 );
        
        MidiMessage1 midi2 = new MidiMessage1( new byte[] { 1 } );
        assertTrue( midi2.getStatus() == 1 );
        
        MidiMessage1 midi3 = new MidiMessage1( null );
        assertTrue( midi3.getStatus() == 0 );
        
        try {
            midi2.setMessage( new byte[] { 90, 84 } , 2 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi2.getStatus() == 90 );
        
        try {
            midi1.setMessage( new byte[] { -54, 84, -9 } , 1 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi1.getStatus() == 202 );
        
        try {
            midi.setMessage( new byte[] { -90, -7, 4 } , 0 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi.getStatus() == 0 );
    }
    
    /**
     * test method getLength() of class MidiMessage
     *
     */
    public void test_getLength()
    {
        MidiMessage1 midi = new MidiMessage1( new byte[] { 1, 2, 3 } );
        assertTrue( midi.getLength() == 3 );
        
        MidiMessage1 midi1 = new MidiMessage1( null );
        assertTrue( midi1.getLength() == 0 );
        
        MidiMessage1 midi2 = new MidiMessage1( new byte[] { -71, 2, 3, -90 } );
        assertTrue( midi2.getLength() == 4 );
        
        try {
            midi2.setMessage( new byte[] { 90, 84 } , 2 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi2.getLength() == 2 );
        
        try {
            midi1.setMessage( new byte[] { 90, 84, -9 } , 2 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi1.getLength() == 2 );
        
        try {
            midi.setMessage( new byte[] { -90, -7, 4 } , 0 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi.getLength() == 0 );
    }
    
    /**
     * test method setMessage( byte[], int ) of class MidiMessage
     *
     */
    public void test_setMessage()
    {
        MidiMessage1 midi = new MidiMessage1( new byte[] { 18, 34, 48, -56, 12 } );
        MidiMessage1 midi1 = new MidiMessage1( new byte[] { 18 } );
        MidiMessage1 midi2 = new MidiMessage1( null );
        try {
            midi2.setMessage( new byte[] { 90, 84 } , 2 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi2.getMessage().length == 2 );
        assertTrue( midi2.getMessage()[0] == 90 );
        assertTrue( midi2.getMessage()[1] == 84 );
        
        try {
            midi1.setMessage( new byte[] { -54, 84, -9 } , 1 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi1.getMessage().length == 1 );
        assertTrue( midi1.getMessage()[0] == -54 );
        
        try {
            midi.setMessage( new byte[] { -90, -7, 4 } , 0 );
        } catch( InvalidMidiDataException e ) { };
        assertTrue( midi.getMessage().length == 0 );
        try {
            int i = midi.getMessage()[0];
            fail( "ArrayIndexOutOfBoundsException expected" );
        } catch( ArrayIndexOutOfBoundsException e ) { };
    }
    /**
     * Subsidiary class in order to testing constructor
     * and method setMessage( byte[], int ) of class Instrument, 
     * because its declared as protected
     */
    static class MidiMessage1 extends MidiMessage
    {
        MidiMessage1( byte[] data )
        {
            super( data );
        }
        public Object clone()
        {
            return null;
        }
        
        public void setMessage( byte[] data, int length ) 
                throws InvalidMidiDataException
        {   
            super.setMessage( data, length );
        }
    }
}
