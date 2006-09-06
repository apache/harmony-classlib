/**
 * @author Dennis Ushakov
 * @version $Revision$
 */
package javax.swing;

import java.util.Arrays;


public class SizeSequenceTest extends BasicSwingTestCase {
    private SizeSequence sizeSequence;
    private int[] sizes;
    
    protected void setUp() throws Exception {
        super.setUp();
        sizes = new int[]{1, 2, 3, 4, 5};
        sizeSequence = new SizeSequence(sizes);
    }
    
    protected void tearDown() throws Exception {
        super.tearDown();
        sizeSequence = null;
        sizes = null;
    }
    
    public void testSizeSequence() throws Exception {             
        sizeSequence = new SizeSequence();
        assertEquals(0, sizeSequence.getSizes().length);        
        
        sizeSequence = new SizeSequence(10);
        assertEquals(10, sizeSequence.getSizes().length);
        assertEquals(10, sizeSequence.getIndex(0));
        for (int i = 0; i < 10; i++) {
            assertEquals(0, sizeSequence.getSizes()[i]);
        }
        
        sizeSequence = new SizeSequence(10, 13);
        assertEquals(10, sizeSequence.getSizes().length);
        for (int i = 0; i < 10; i++) {
            assertEquals(13, sizeSequence.getSizes()[i]);
        }
        
        int[] sizes = new int[]{1, 2, 3, 4, 5};
        sizeSequence = new SizeSequence(sizes);
        assertEquals(5, sizeSequence.getSizes().length);
        assertNotSame(sizes, sizeSequence.getSizes());        
    }
    
    public void testGetIndex() {
        assertEquals(2, sizeSequence.getIndex(3));
        assertEquals(3, sizeSequence.getIndex(6));
        assertEquals(4, sizeSequence.getIndex(11));
        assertEquals(5, sizeSequence.getIndex(100));
        assertEquals(0, sizeSequence.getIndex(-100));
    }
    
    public void testGetPosition() {
        assertEquals(0, sizeSequence.getPosition(0));
        assertEquals(1, sizeSequence.getPosition(1));
        assertEquals(3, sizeSequence.getPosition(2));
        assertEquals(6, sizeSequence.getPosition(3));
        assertEquals(10, sizeSequence.getPosition(4));        
        assertEquals(15, sizeSequence.getPosition(100));
        assertEquals(0, sizeSequence.getPosition(-100));
        
        sizeSequence = new SizeSequence();
        assertEquals(0, sizeSequence.getPosition(5));
    }
    
    public void testGetSetSize() {
        assertEquals(1, sizeSequence.getSize(0));
        
        sizeSequence.setSize(0, 10);
        assertEquals(10, sizeSequence.getSize(0));
        assertEquals(10, sizeSequence.getPosition(1));
        assertEquals(12, sizeSequence.getPosition(2));
        assertEquals(24, sizeSequence.getPosition(5));
        assertEquals(1, sizeSequence.getIndex(11));
        assertEquals(3, sizeSequence.getIndex(15));
        
        sizeSequence.setSize(0, -10);
        assertEquals(-10, sizeSequence.getSize(0));
        assertEquals(-10, sizeSequence.getPosition(1));
        
        sizeSequence.setSize(10, 1);
        assertEquals(0, sizeSequence.getSize(10));
    }
    
    public void testGetSetSizes() {
        int[] first = sizeSequence.getSizes();
        assertNotSame(first, sizeSequence.getSizes());
        
        sizes[0] = 5;
        assertEquals(1, sizeSequence.getSizes()[0]);
        
        sizes = new int[]{1, 2, 3, 4, 5, 6};
        sizeSequence.setSizes(sizes);
        assertEquals(sizes.length, sizeSequence.getSizes().length);
        assertNotSame(sizes, sizeSequence.getSizes());
        for (int i = 0; i < sizes.length; i++) {
            assertEquals(sizes[i], sizeSequence.getSizes()[i]);
            assertEquals(sizes[i], sizeSequence.getSize(i));
        }
    }
    
    public void testInsertRemoveEntries() {
        sizeSequence.removeEntries(0, 3);
        assertEquals(2, sizeSequence.getSizes().length);
        assertEquals(5, sizeSequence.getSize(1));
        
        testExceptionalCase(new ExceptionalCase() {
            public void exceptionalAction() throws Exception {
                sizeSequence.removeEntries(10, 11);
            }
            public Class expectedExceptionClass() {
                return NegativeArraySizeException.class;
            }
        });                                
        testExceptionalCase(new ExceptionalCase() {
            public void exceptionalAction() throws Exception {
                sizeSequence.removeEntries(10, -1);
            }
            public Class expectedExceptionClass() {
                return ArrayIndexOutOfBoundsException.class;
            }
        });                
     
        sizeSequence = new SizeSequence(sizes);        
        sizeSequence.insertEntries(2, 3, 2);
        assertEquals(3 + sizes.length, sizeSequence.getSizes().length);        
        assertEquals(2, sizeSequence.getSize(2));
        assertEquals(2, sizeSequence.getIndex(3));
        assertEquals(5, sizeSequence.getSize(7));
        
        testExceptionalCase(new ExceptionalCase() {
            public void exceptionalAction() throws Exception {
                sizeSequence.insertEntries(10, 11, 0);
            }
            public Class expectedExceptionClass() {
                return ArrayIndexOutOfBoundsException.class;
            }
        });        
        testExceptionalCase(new ExceptionalCase() {
            public void exceptionalAction() throws Exception {
                sizeSequence.insertEntries(0, -1, 0);
            }
            public Class expectedExceptionClass() {
                return ArrayIndexOutOfBoundsException.class;
            }
        });        
    }
}
