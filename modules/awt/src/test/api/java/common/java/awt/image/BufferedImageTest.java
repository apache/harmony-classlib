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
        bi.getWritableTile(1, 1);
        
        //Regression test for HARMONY-1658
        BufferedImage img = new BufferedImage(10, 16, BufferedImage.TYPE_4BYTE_ABGR);
        try {
            img.isTileWritable(1,1);
            fail("IllegalArgumentException is expected");
        } catch (IllegalArgumentException iae) {
        }
    }

    public void testGetProperty() {
        // Regression test HARMONY-1656
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        assertEquals("Image.UndefinedProperty",
                     Image.UndefinedProperty, img.getProperty("XXX"));
    }

}
