package java.awt.image;

import java.awt.Image;
import java.awt.image.BufferedImage;

import junit.framework.TestCase;

public class BufferedImageTest extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(BufferedImageTest.class);
    }
    /*
     * @see TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Constructor for BufferedImageTest.
     * @param name
     */
    public BufferedImageTest(String name) {
        super(name);
    }
    
    public final void testGetWritableTile(){
        BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        WritableRaster tile = null;
        try{
            tile = bi.getWritableTile(1, 1);
            assertTrue(true);
        }catch(ArrayIndexOutOfBoundsException e){
            fail("Unexpected ArrayIndexOutOfBoundsException was thrown");
        }
    }

    public void testGetProperty() {
        // Regression test HARMONY-1656
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        assertEquals("Image.UndefinedProperty",
                     Image.UndefinedProperty, img.getProperty("XXX"));
    }

}
