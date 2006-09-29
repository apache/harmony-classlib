/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
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

import junit.framework.TestCase;

import javax.sound.midi.Patch;

public class PatchTest extends TestCase
{
    /**
     * test constructor of class Patch
     *
     */
    public void test_constructor()
    {
        Patch patch = new Patch( 34, 68 );
        assertTrue( patch.getBank() == 34 );
        assertTrue( patch.getProgram() == 68 );
        
        Patch patch2 = new Patch( -4, 567 );
        assertTrue( patch2.getBank() == -4 );
        assertTrue( patch2.getProgram() == 567 );
    }
    
    /**
     * test method getBank() of class Patch
     *
     */
    public void test_getBank()
    {
        Patch patch = new Patch( 45, 78 );
        assertTrue( patch.getBank() == 45 );
        
        Patch patch1 = new Patch( -78, 78 );
        assertTrue( patch1.getBank() == -78 );
        
        Patch patch2 = new Patch( 16400, 78 );
        assertTrue( patch2.getBank() == 16400 );
        
    }
    
    /**
     * test method getProgram() of class Patch
     *
     */
    public void test_getProgram()
    {
        Patch patch = new Patch( 45, 78 );
        assertTrue( patch.getProgram() == 78 );
        
        Patch patch1 = new Patch( -78, -5 );
        assertTrue( patch1.getProgram() == -5 );
        
        Patch patch2 = new Patch( 16400, 216 );
        assertTrue( patch2.getProgram() == 216 );
        
    }
}
