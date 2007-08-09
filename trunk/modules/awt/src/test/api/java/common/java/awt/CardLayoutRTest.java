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
 * @author Dmitry A. Durnev
 * @version $Revision$
 */
package java.awt;

import junit.framework.TestCase;

public class CardLayoutRTest extends TestCase {
    Container emptyContainer;
    Dimension defSize;
    CardLayout layout;
    private Dimension maxSize;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emptyContainer = new Container();
        defSize = new Dimension();
        maxSize = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        layout = new CardLayout();
    }
    
    public void testFirst() {        
        try {
            layout.first(emptyContainer);
        } catch (IllegalArgumentException iae) {
            return;            
        }
        fail();
    }

    
    public final void testGetLayoutAlignmentX1() {
        try {
            layout.getLayoutAlignmentX(null);
        } catch (Throwable t) {
            fail();
        }        
    }
    
    public final void testGetLayoutAlignmentX2() {        
        try {
            layout.getLayoutAlignmentX(emptyContainer);
        } catch (Throwable t) {
            fail();
        }        
    }
    
    public final void testGetLayoutAlignmentY1() {
        try {
            layout.getLayoutAlignmentY(null);
        } catch (Throwable t) {
            fail();
        }        
    }
    
    public final void testGetLayoutAlignmentY2() {        
        try {
            layout.getLayoutAlignmentY(emptyContainer);
        } catch (Throwable t) {
            fail();
        }        
    }
    
    public void testLast() {        
        try {
            layout.last(emptyContainer);
        } catch (IllegalArgumentException iae) {
            return;            
        }
        fail();
    }
    
    public void testLayoutContainer() {       
        try {
            layout.layoutContainer(emptyContainer);
        } catch (Throwable t) {
            fail();
        }
        assertTrue(true);
    }
    
    public final void testMaximumLayoutSize1() {
        try {
            assertEquals(maxSize, layout.maximumLayoutSize(null));
        } catch (Throwable t) {
            fail();
        }        
    }
    
    public final void testMaximumLayoutSize2() {        
        try {
            assertEquals(maxSize, layout.maximumLayoutSize(emptyContainer));
        } catch (Throwable t) {
            fail();
        }        
    }


    public final void testMinimumLayoutSize() {
        assertEquals(defSize, layout.minimumLayoutSize(emptyContainer));
    }
    
    public void testNext() {        
        try {
            layout.next(emptyContainer);
        } catch (IllegalArgumentException iae) {
            return;            
        }
        fail();
    }
    
    public final void testPreferredLayoutSize() {
        assertEquals(defSize, layout.preferredLayoutSize(emptyContainer));
    }   
    
    public void testPrevious() {        
        try {
            layout.previous(emptyContainer);
        } catch (IllegalArgumentException iae) {
            return;            
        }
        fail();
    }
    
    public void testRemoveLayoutComponent() {       
        try {
            layout.removeLayoutComponent(emptyContainer);
        } catch (Throwable t) {
            fail();
        }
    }
    
}
