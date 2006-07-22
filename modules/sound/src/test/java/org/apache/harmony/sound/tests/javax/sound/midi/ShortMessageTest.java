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

/**
 * @author Evgeny S. Sidorenko
 */

package org.apache.harmony.sound.tests.javax.sound.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.ShortMessage;

import junit.framework.TestCase;

public class ShortMessageTest extends TestCase
{
    /**
     * test constants
     *
     */
    public void test_constants()
    {
        assertTrue( ShortMessage.ACTIVE_SENSING == 254 );
        assertTrue( ShortMessage.CHANNEL_PRESSURE == 208 );
        assertTrue( ShortMessage.CONTINUE == 251 );
        assertTrue( ShortMessage.CONTROL_CHANGE == 176 );
        assertTrue( ShortMessage.END_OF_EXCLUSIVE == 247 );
        assertTrue( ShortMessage.MIDI_TIME_CODE == 241 );
        assertTrue( ShortMessage.NOTE_OFF == 128 );
        assertTrue( ShortMessage.NOTE_ON == 144 );
        assertTrue( ShortMessage.PITCH_BEND == 224 );
        assertTrue( ShortMessage.POLY_PRESSURE == 160 );
        assertTrue( ShortMessage.PROGRAM_CHANGE == 192 );
        assertTrue( ShortMessage.SONG_POSITION_POINTER == 242 );
        assertTrue( ShortMessage.SONG_SELECT == 243 );
        assertTrue( ShortMessage.START == 250 );
        assertTrue( ShortMessage.STOP == 252 );
        assertTrue( ShortMessage.SYSTEM_RESET == 255 );
        assertTrue( ShortMessage.TIMING_CLOCK == 248 );
        assertTrue( ShortMessage.TUNE_REQUEST == 246 );
    }
    
    /**
     * test constructors
     *
     */
    public void test_constructor_ShortMessage1()
    {
        ShortMessage1 message = new ShortMessage1();
        assertTrue( message.getChannel() == 0 );
        assertTrue( message.getCommand() == 144 );
        assertTrue( message.getData1() == 64 );
        assertTrue( message.getData2() == 127 );
        assertTrue( message.getLength() == 3 );
        assertTrue( message.getStatus() == 144 );
        assertTrue( message.getMessage().length == 3 );
        assertTrue( message.getMessage()[0] == -112 );
        assertTrue( message.getMessage()[1] == 64 );
        assertTrue( message.getMessage()[2] == 127 );
                
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 95, -5, 9, 56, -18 } );
        assertTrue( message1.getChannel() == 15 );
        assertTrue( message1.getCommand() == 80 );
        assertTrue( message1.getData1() == 251 );
        assertTrue( message1.getData2() == 9 );
        assertTrue( message1.getLength() == 5 );
        assertTrue( message1.getStatus() == 95 );
        assertTrue( message1.getMessage().length == 5 );
        
        ShortMessage1 message2 = new ShortMessage1( new byte[] { 95, 14 } );
        assertTrue( message2.getChannel() == 15 );
        assertTrue( message2.getCommand() == 80 );
        assertTrue( message2.getData1() == 14 );
        assertTrue( message2.getData2() == 0 );
        assertTrue( message2.getLength() == 2 );
        assertTrue( message2.getStatus() == 95 );
        assertTrue( message2.getMessage().length == 2 );
        
        ShortMessage1 message3 = new ShortMessage1( null );
        assertTrue( message3.getChannel() == 0 );
        assertTrue( message3.getCommand() == 0 );
        assertTrue( message3.getData1() == 0 );
        assertTrue( message3.getData2() == 0 );
        assertTrue( message3.getLength() == 0 );
        assertTrue( message3.getStatus() == 0 );
    }
    
    /**
     * test method setMessage( int ) of class ShortMessage
     */
    public void test_setMessage1()
    {
        ShortMessage1 message = new ShortMessage1();
        /**
         * value of variable status is more or equals 246 and
         * less or equals 255
         */
        try {
            message.setMessage( 245 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 256 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 250 );
        } catch( InvalidMidiDataException e ) { }
        /**
         * channel change from 0 up to 15, and
         * channel + command == status, so
         * the value of command divisible by 16
         */
        assertTrue( message.getChannel() == 10 );
        assertTrue( message.getCommand() == 240 );
        assertTrue( message.getData1() == 0 );
        assertTrue( message.getData2() == 0 );
        assertTrue( message.getLength() == 1 );
        assertTrue( message.getStatus() == 250 );
        assertTrue( message.getMessage().length == 1 );
    }
    
    /**
     * test method setMessage( int, int, int ) of 
     * class ShortMessage
     */
    public void test_setMessage2()
    {
        ShortMessage1 message = new ShortMessage1();
        /**
         * value of variable status is more or equals 246 and
         * less or equals 255
         */
        try {
            message.setMessage( 245, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 256, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 250, 34, 56 );
        } catch( InvalidMidiDataException e ) { }
        /**
         * channel change from 0 up to 15, and
         * channel + command == status, so
         * the value of command divisible by 16.
         */
        assertTrue( message.getChannel() == 10 );
        assertTrue( message.getCommand() == 240 );
        assertTrue( message.getData1() == 0 );
        assertTrue( message.getData2() == 0 );
        assertTrue( message.getLength() == 1 );
        assertTrue( message.getStatus() == 250 );
        assertTrue( message.getMessage().length == 1 );
    }
    
    /**
     * test method setMessage( int, int, int ) of 
     * class ShortMessage
     */
    public void test_setMessage3()
    {
        ShortMessage1 message = new ShortMessage1();
        /**
         * value of variable command is more or equals 128 and
         * less or equals 239
         */
        try {
            message.setMessage( 127, 10, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 240, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        
        /**
         * value of variable channel is more or equals 0 and 
         * less or equals 15
         */
        try {
            message.setMessage( 200, -1, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 200, 16, 34, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
  
        /**
         * value of data1 and data2 is more or equals 0 and
         * less or equals 127, but when command more or
         * equals 192 and less or equals 224 the second data,
         * data2, is unused
         */
        try {
            message.setMessage( 200, 12, -1, 56 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 225, 8, 34, 456 );
            fail( "InvalidMidiDataException expected" );
        } catch( InvalidMidiDataException e ) { }
        try {
            message.setMessage( 200, 8, 34, 456 );
        } catch( InvalidMidiDataException e ) { }
                
        try {
            message.setMessage( 200, 9, 34, 56 );
        } catch( InvalidMidiDataException e ) { }
        /**
         * channel change from 0 up to 15;
         * 
         * command must to divisible by 16, and so it less or 
         * equals parameter command;
         * 
         * status is sum of channel and command
         */
        assertTrue( message.getChannel() == 9 );
        assertTrue( message.getCommand() == 192 );
        assertTrue( message.getData1() == 34 );
        assertTrue( message.getData2() == 0 );
        assertTrue( message.getLength() == 2 );
        assertTrue( message.getStatus() == 201 );
        assertTrue( message.getMessage().length == 2 );
        
        try {
            message.setMessage( 148, 9, 34, 56 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getChannel() == 9 );
        assertTrue( message.getCommand() == 144 );
        assertTrue( message.getData1() == 34 );
        assertTrue( message.getData2() == 56 );
        assertTrue( message.getLength() == 3 );
        assertTrue( message.getStatus() == 153 );
        assertTrue( message.getMessage().length == 3 );
    }
    
    /**
     * test method getChannel() of class ShortMessage
     *
     */
    public void test_getChannel()
    {
        ShortMessage message = new ShortMessage();
        assertTrue( message.getChannel() == 0 );
        
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 23, 16, 35 } );
        assertTrue( message1.getChannel() == 7 );
        
        ShortMessage1 message2 = new ShortMessage1( null );
        assertTrue( message2.getChannel() == 0 );
        
        try {
            message.setMessage( 249 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getChannel() == 9 );
        
        try {
            message.setMessage( 250, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getChannel() == 10 );
        
        try {
            message.setMessage( 234, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getChannel() == 15 );
        
    }
    
    /**
     * test method getCommand() of class ShortMessage
     *
     */
    public void test_getCommand()
    {
        ShortMessage message = new ShortMessage();
        assertTrue( message.getCommand() == 144 );
        
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 23, 16, 35 } );
        assertTrue( message1.getCommand() == 16 );
        
        ShortMessage1 message2 = new ShortMessage1( null );
        assertTrue( message2.getCommand() == 0 );
        
        try {
            message.setMessage( 249 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getCommand() == 240 );
        
        try {
            message.setMessage( 250, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getCommand() == 240 );
        
        try {
            message.setMessage( 234, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getCommand() == 224 );
    }
    
    /**
     * test method getLength() of class ShortMessage
     *
     */
    public void test_getLength()
    {
        ShortMessage message = new ShortMessage();
        assertTrue( message.getLength() == 3 );
        
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 23, 16, 35 } );
        assertTrue( message1.getLength() == 3 );
        
        ShortMessage1 message2 = new ShortMessage1( null );
        assertTrue( message2.getLength() == 0 );
        
        try {
            message.setMessage( 249 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getLength() == 1 );
        
        try {
            message.setMessage( 250, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getLength() == 1 );
        
        try {
            message.setMessage( 234, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getLength() == 3 );
        
        try {
            message.setMessage( 214, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getLength() == 2 );
    }
    
    /**
     * test method getStatus() of class ShortMessage
     *
     */
    public void test_getStatus()
    {
        ShortMessage message = new ShortMessage();
        assertTrue( message.getStatus() == 144 );
        
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 23, 16, 35 } );
        assertTrue( message1.getStatus() == 23 );
        
        ShortMessage1 message2 = new ShortMessage1( null );
        assertTrue( message2.getStatus() == 0 );
        
        try {
            message.setMessage( 249 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getStatus() == 249 );
        
        try {
            message.setMessage( 250, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getStatus() == 250 );
        
        try {
            message.setMessage( 234, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getStatus() == 239 );
    }
    
    /**
     * test methods getData1() and getData2() of 
     * class ShortMessage
     *
     */
    public void test_getData1_2()
    {
        ShortMessage message = new ShortMessage();
        assertTrue( message.getData1() == 64 );
        assertTrue( message.getData2() == 127 );
        
        ShortMessage1 message1 = new ShortMessage1( new byte[] { 23, 16, 35 } );
        assertTrue( message1.getData1() == 16 );
        assertTrue( message1.getData2() == 35 );
        
        ShortMessage1 message11 = new ShortMessage1( new byte[] { 23, 16 } );
        assertTrue( message11.getData1() == 16 );
        assertTrue( message11.getData2() == 0 );
        
        ShortMessage1 message12 = new ShortMessage1( new byte[] { 23 } );
        assertTrue( message12.getData1() == 0 );
        assertTrue( message12.getData2() == 0 );
        
        ShortMessage1 message2 = new ShortMessage1( null );
        assertTrue( message2.getData1() == 0 );
        assertTrue( message2.getData2() == 0 );
        
        try {
            message.setMessage( 249 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getData1() == 0 );
        assertTrue( message.getData2() == 0 );
        
        try {
            message.setMessage( 250, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getData1() == 0 );
        assertTrue( message.getData2() == 0 );
        
        try {
            message.setMessage( 234, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getData1() == 14 );
        assertTrue( message.getData2() == 62 );
        
        try {
            message.setMessage( 198, 15, 14, 62 );
        } catch( InvalidMidiDataException e ) { }
        assertTrue( message.getData1() == 14 );
        assertTrue( message.getData2() == 0 );
    }
    
    /**
     * test method getDataLength() of class ShortMessage
     *
     */
    public void test_getDataLentgth()
    {
        //TODO
    }
    
    /**
     * Subsidiary class in order to use constructor
     * and method getDataLength( int ) of class Instrument, 
     * because its declared as protected
     */
    static class ShortMessage1 extends ShortMessage
    {
        ShortMessage1()
        {
            super();
        }
        ShortMessage1( byte[] data )
        {
            super( data );
        }
        
        public int getDataLength1( int status )
                throws InvalidMidiDataException
        {
            return super.getDataLength( status );
        }
    }
}
