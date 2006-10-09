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
 * @author Igor A. Pyankov 
 * @version $Revision: 1.2 $ 
 */ 

package org.apache.harmony.x.print;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.PrintStream;
import java.text.AttributedCharacterIterator;
import java.text.CharacterIterator;
import java.util.Date;
import java.util.Map;

/*
 * Graphics2D2PS
 *  
 */
public class Graphics2D2PS extends Graphics2D {
    
    private Color color;
    private Color bgcolor;
    private Color XORcolor; 
    private Paint paint; 
    private Font font;    
    private PrintStream out_stream;
    
    static double const1 = 842; // 11.7"
    static double yscale = 1;
    
    private static int convY (int y){      
        return (int)(const1*yscale)- y ;        
    }
   
    public void setY(int y){
        const1 = (double)y;
    }
           
    private static String threebytes2Hex (int b){
        
        char [] hex = {'0', '1', '2', '3', '4', '5', '6', '7',
                       '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        
        char [] ret = new char[6];
        
            for (int i = 0; i < 6; i++){
                ret[5-i] = hex[b & 0x0F];
                b = b >> 4;
        }
        return new  String(ret);
    }   
            
    public Graphics2D2PS(PrintStream stream, double height) {              
        super();
        if (stream == null) {
            throw new IllegalArgumentException("stream is null");
        }          
        out_stream = stream;
        font = new Font("Courier", Font.PLAIN, 12);
        color = Color.BLACK;
        bgcolor = Color.WHITE;
        yscale = 1;
        const1 = height;
        out_stream.println("%!PS-Adobe");
        out_stream.println("%%Title: Java printJob");
        out_stream.println("%%Creator: Intel(tm) java printing ");
        out_stream.println("%%CreationDate: " + new Date());
        out_stream.println("%%EndComments"); 
    }
    
    public Graphics2D2PS(PrintStream stream) {    
        this(stream, 842D);   
    }   

    public void finish(){       
        out_stream.println("%%EOF");   
        out_stream.close();
    }

    public void startPage(int number){
        out_stream.println("%%Page: " + number + " " + number);
    }
    
    public void endOfPage(int number){
        out_stream.println("showpage");
        out_stream.println("%%EndPage: " + number + " " + number);
    }
         
    /* drawImage-s */    
    public boolean drawImage(Image image, int x, int y,
            ImageObserver imageObserver) {
        drawImage(image, x, convY(y));
        return true;
    }   
  
    public boolean drawImage(Image image, int x, int y, int width, int height,
            ImageObserver imageObserver) {
        float w;
        float h;         
        BufferedImage imageGIF = (BufferedImage) image;
        w = (float)imageGIF.getWidth();
        h = (float)imageGIF.getHeight();       
        drawImage(image, x, convY(y), true, ((float)width)/w , ((float)height)/h);
        return true;
    }
        
    public boolean drawImage(Image image, int x, int y, Color bbgcolor,
            ImageObserver imageObserver) {
        int iw;
        int ih;
        Color cur_color;
        
        BufferedImage imageGIF = (BufferedImage) image;
        cur_color = getColor();
        setColor(bbgcolor);
        
        iw = imageGIF.getWidth();
        ih = imageGIF.getHeight();               
        fillRect(x, y, iw, ih);
        setColor(cur_color);
        
        drawImage(image, x, convY(y));
        return true;
    }
    
    
    public boolean drawImage(Image image, int x, int y, int width, int height,
            Color bbgcolor, ImageObserver imageObserver) {
        float w;
        float h;
        Color cur_color;

        cur_color = getColor();
        setColor(bbgcolor);        
        fillRect(x, y, width, height);
        setColor(cur_color);
        
        BufferedImage imageGIF = (BufferedImage) image;
        w = (float)imageGIF.getWidth();
        h = (float)imageGIF.getHeight();
        
        drawImage(image, x, convY(y), true, ((float)width)/w , ((float)height)/h);
        return true;
    }
     
    
    public boolean drawImage(Image image, 
            int dx1, int dy1, int dx2, int dy2,
            int sx1, int sy1, int sx2, int sy2, 
            ImageObserver imageObserver) {
        
        int sx;
        int sy;
        int width;
        int height;
        int dx;
        int dy;
        int d;
        int comp;
        BufferedImage newImage;
        BufferedImage imageGIF;
        
        /* this method have to be improved to flip image 
         * if dx2 < dx1 or dy2 <dy1
         */     
        if (dx2 < dx1) {
            d = dx2;
            dx2 = dx1;
            dx1 = d;
        }
        if (dy2 < dy1) {
            d = dy2;
            dy2 = dy1;
            dy1 = d;
        }
        dx = dx2 - dx1 + 1;
        dy = dy2 - dy1 + 1;       
        
        imageGIF = (BufferedImage) image;
        width = imageGIF.getWidth();
        height = imageGIF.getHeight();       
        if (dx2 > width || dy2 > height){
            return false;
        }
        newImage = new BufferedImage(dx, dy, BufferedImage.TYPE_INT_ARGB);  
        
        sy = 0;
        for (int iy = dy1; iy <= dy2; iy++) {
            sx = 0;
            for (int ix = dx1; ix <= dx2; ix++) {   
                comp = imageGIF.getRGB(ix, iy);
                newImage.setRGB(sx++, sy, comp);              
            } 
            sy++;                
        }        
        drawImage(newImage, sx1, sy1, sx2 - sx1 + 1, sy2 - sy1 + 1, null);
        
        return true;         
    }
    
    public boolean drawImage(Image image, 
            int dx1, int dy1, int dx2, int dy2,
            int sx1, int sy1, int sx2, int sy2, 
            Color bbgcolor, ImageObserver imageObserver) {
        
        Color cur_color;
        cur_color = getColor();
        setColor(bbgcolor);        
        fillRect( sx1, sy1, sx2 - sx1 + 1, sy2 - sy1 + 1);
        setColor(cur_color);
        
        return drawImage(image, 
                dx1, dy1, dx2, dy2,
                sx1, sy1, sx2, sy2, 
                imageObserver); 
        
    }

    /* method have to be implemented*/
    public boolean drawImage(Image image, AffineTransform transform,
            ImageObserver imageObserver) {
        return true;        
    }    
    
    /* method have to be implemented*/     
    public void drawImage(BufferedImage image, BufferedImageOp arg1, 
            int arg2, int arg3) {       
    }
    
    
    /*
     * common private methods for drawImage methods.  
     */    
    private void drawImage(Image image, int x, int y) {
         drawImage(image, x, y, false, 0f, 0f);     
    }       

    private void drawImage(Image image, int x, int y, boolean scale,
            float sx, float sy) {
        int line = 0;
        int comp;
        int imageHeight;
        int imageWidth;
        BufferedImage imageGIF;
        
        if (image != null) {
            imageHeight = image.getHeight(null);
            imageWidth = image.getWidth(null);           
            imageGIF = (BufferedImage) image;          
           
            out_stream.println("");
            out_stream.println(x + " " + y + " translate");  
            if (scale){
                out_stream.println(sx + " " + sy + " scale");               
            }
            out_stream.print(imageWidth + " ");
            out_stream.println(imageHeight + " 8");
            
            out_stream.println(" [1 0 0 -1 0 1]");   
            out_stream.println("{ currentfile");
            
            out_stream.println(" 32 string readhexstring pop");
            out_stream.println("}");
            out_stream.println("false 3"); 
            out_stream.println("colorimage");             
            
            for (int iy = 0; iy < imageHeight; iy++) {
                for (int ix = 0; ix < imageWidth; ix++) {   
                    comp = imageGIF.getRGB(ix, iy);
                    out_stream.print(threebytes2Hex(comp));                                         
                    if (line++ == 30) {
                        out_stream.println();
                        line = 0;
                    }                      
                }
                if (line != 0){
                    line = 0;
                    out_stream.println();
                }
            }
              
            if (scale){
                out_stream.println(1/sx + " " + 1/sy + " scale");                   
            }
            out_stream.println((-x) + " " + (-y) + " translate");
            out_stream.println("stroke");
        }
        return;
    }
   
    
    
    public void drawString(String text, float x, float y) {
        drawString(text, (int) x, (int) y);
    }
    
    public void drawString(String text, int x, int y) {
        Font cur_font = getFont(); 
        y = convY(y);
        out_stream.println("/" + cur_font.getName() + " findfont");
        out_stream.println(cur_font.getSize() + " scalefont setfont");
        out_stream.println("  " + x + " " + y + " moveto");
        out_stream.println("(" + text + ") show");       
        out_stream.println("stroke");
    }  

    public void drawString(AttributedCharacterIterator iterator, 
                           float x, float y) {
        drawString(iterator, (int) x, (int) y);
    }
    
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {

        int i = 0;
        int n = iterator.getEndIndex();
        char [] cc = new char[n];
        
        for (char c = iterator.first(); c != CharacterIterator.DONE; 
                c = iterator.next()) {
            cc[i++] = c;
        }
        drawChars(cc, 0, n, x, y);        
    }

    
    public void drawLine(int x1, int y1, int x2, int y2)  {
        out_stream.println("newpath");  
        out_stream.println("  " + x1 + " " + convY(y1) + " moveto");
        out_stream.println("  " + x2 + " " + convY(y2) + " lineto");
        out_stream.println("stroke");         
    }
    
    
    
    public void drawOval(int x, int y, int width, int height) {    
        drawArc(x, y, width, height, 0, 360, false);       
    }

    
    public void fillOval(int x, int y, int width, int height) {
        drawArc(x, y, width, height, 0, 360, true);
    }

    
    public void drawArc(int x, int y, int width, int height, int startAngle,
            int arcAngle) {          
        drawArc(x, y, width, height, startAngle, arcAngle, false);  
    }
    
    public void fillArc(int x, int y, int width, int height, 
            int startAngle, int arcAngle) {      
        drawArc(x, y, width, height, startAngle, arcAngle, true);      
    }
      
    /*
     * common private method for drawOval, fillOval, 
     * drawArc and fillArc methods.  
     */
    private void drawArc(int x, int y, int width, int height, int startAngle,
            int arcAngle, boolean fill) {   
        
        int cx = x + width /2;
        int cy = convY(y + height /2);
        y = convY(y);
        
        float scale1 = (float)width/(float)height;
        float scale2 = (float)height/(float)width;       
        
        out_stream.println("newpath");
        out_stream.println(scale1 + " 1 scale");
        out_stream.print("  " + (cx * scale2));
        out_stream.print(" " + cy + " " + height /2); 
        out_stream.println(" " + startAngle + " " + arcAngle + " arc");
        if (fill) {      
            out_stream.print("  " + (cx * scale2));
            out_stream.println(" " + cy + " lineto");                
            out_stream.println("fill");       
        }
        out_stream.println(scale2 + " 1 scale");
        out_stream.println("stroke");       
    } 
 
    public void drawRoundRect(int x, int y, int width, int height,
            int arcWidth, int arcHeight) {
        drawRoundRect(x, y, width, height, arcWidth, arcHeight, false);
    }
    
    public void fillRoundRect(int x, int y, int width, int height,
            int arcWidth, int arcHeight) {
        drawRoundRect(x, y, width, height, arcWidth, arcHeight, true);
    }
    
    /*
     * common private method for drawRoundRect 
     * and fillRoundRect methods.  
     */        
    private void drawRoundRect(int x, int y, int width, int height,
            int arcWidth, int arcHeight, boolean fill) { 
             
        int x1 = x + arcWidth;
        int x2 = x + width - arcWidth;
       
        int y1 = convY(y + arcHeight);
        int y2 = convY(y + height - arcHeight);        
        y = convY(y);   
        
        float scale1 = (float)arcWidth/(float)arcHeight;
        float scale2 = (float)arcHeight/(float)arcWidth;  
       
        out_stream.println("newpath");
        out_stream.println("  " + x + " " + y1 + " moveto");
        out_stream.println(scale1 + " 1 scale");        
        out_stream.print("  " + (x1 * scale2) + " " + y2 + " ");
        out_stream.println(arcHeight + " 180 270 arc");
        out_stream.print("  " + (x2 * scale2) + " " + y2 + " "); 
        out_stream.println(arcHeight + " 270 0 arc");
        out_stream.print("  " + (x2 * scale2) + " " + y1 + " ");
        out_stream.println(arcHeight + " 0 90 arc");
        out_stream.print("  " + (x1 * scale2) + " " + y1 + " ");
        out_stream.println(arcHeight + " 90 180 arc");
        out_stream.println(scale2 + " 1 scale");
        if (fill) { 
            out_stream.println("fill");       
        }        
        out_stream.println("stroke");        
    }
    
    public void drawRect(int x, int y, int width, int height) {        
        int x2 = x + width;
        int y1 = convY(y);
        int y2 = convY(y + height);        
        int [] xPoints = {x, x2, x2, x};
        int [] yPoints = {y1, y1, y2, y2};
        drawPolyline (xPoints, yPoints, 4, true, false);
    }

    public void fillRect(int x, int y, int width, int height) {
        int x2 = x + width;
        int y1 = convY(y);
        int y2 = convY(y + height);        
        int [] xPoints = {x, x2, x2, x};
        int [] yPoints = {y1, y1, y2, y2};
        drawPolyline (xPoints, yPoints, 4, true, true);        
    }       
    
    public void clearRect(int x, int y, int width, int height) {
        Color savecolor = getColor();
        setColor(bgcolor);      
        fillRect(x, y, width, height);
        setColor(savecolor);
    }   
    
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            yPoints[i]=convY(yPoints[i]);
        } 
        drawPolyline (xPoints, yPoints, nPoints, true, false); 
    }

    
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {          
        for (int i = 0; i < nPoints; i++) {
            yPoints[i]=convY(yPoints[i]);
        }        
        drawPolyline (xPoints, yPoints, nPoints, false, false);       
    }

    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            yPoints[i]=convY(yPoints[i]);
        } 
        drawPolyline (xPoints, yPoints, nPoints, true, true);      
    }
    
    /*
     * common private method for drawPolyline, drawPolygon, drawRect,
     * clearRect, fillPolyline, fillPolygon and  fillRect methods.  
     */
    private void drawPolyline(int[] xPoints, int[] yPoints, int nPoints,
            boolean close, boolean fill) {

        out_stream.println("newpath");
        out_stream.print("  " + xPoints[0]); 
        out_stream.println(" " + yPoints[0] + " moveto");
        
        for (int i = 1; i < nPoints; i++) {
            out_stream.print("  " + xPoints[i]); 
            out_stream.println(" " + yPoints[i] + " lineto");
        }
        if (close) {
            out_stream.println("closepath");            
        }
        if (fill) {
            out_stream.println("fill");
        }
        out_stream.println("stroke");
    }      
   
    
    public void draw(Shape shape) {
        drawShape(shape, false);
    }

    public void fill(Shape shape) {
        drawShape(shape, true);
    }

    private void drawShape(Shape shape, boolean fill) {
        float[] coords = new float[6];
        int i;
        PathIterator  pathIterator;    
        pathIterator = shape.getPathIterator((AffineTransform)null);
        
        out_stream.println("newpath 3");
        i = 0;
        int j=0;
        
        while (!pathIterator.isDone()){
            i=pathIterator.currentSegment(coords);
            switch (i) {
                case PathIterator.SEG_MOVETO: {
                    out_stream.print("  " + (int) coords[0] + " ");
                    out_stream.println(convY((int) coords[1]) + " moveto");
                    break;
                }
                case PathIterator.SEG_LINETO: {
                    out_stream.println("  " + (int) coords[0] + " ");
                    out_stream.println(convY((int) coords[1]) + " lineto");
                    break;
                }
                case PathIterator.SEG_QUADTO: {
                    //fake - need to improved
                    out_stream.print("  " + (int) coords[0] + " ");
                    out_stream.println(convY((int) coords[1]) + " lineto");                     
                    out_stream.print("  " + (int) coords[2] + " ");
                    out_stream.println(convY((int) coords[3]) + " lineto");
                    break;  
                }
                case PathIterator.SEG_CUBICTO: {
                    out_stream.print("  " + (int) coords[0] + " ");
                    out_stream.print(convY((int) coords[1]));
                    out_stream.print("  " + (int) coords[2] + " ");
                    out_stream.print(convY((int) coords[3]));
                    out_stream.print("  " + (int) coords[4] + " ");
                    out_stream.print(convY((int) coords[5]));
                    out_stream.println(" curveto");
                    break;
                }
                case PathIterator.SEG_CLOSE: {
                    out_stream.println("closepath");
                    break;
                } 
            }
            pathIterator.next();
        }
        
        if (fill) {
            out_stream.println("fill");
        }
        out_stream.println("stroke");
    }      
           
    public Color getColor() { 
        return color;
    }

    public void setColor(Color arg_color) {
        color = arg_color;        
        float[] rgb = color.getRGBColorComponents((float[])null);
        out_stream.print("  " + rgb[0] + " " + rgb[1]);
        out_stream.println(" " + rgb[2] + " setrgbcolor");  
    }

    public Color getBackground() {
        return bgcolor;
    }

    public void setBackground(Color arg_color) {
        bgcolor = arg_color;
    }
        
    public void setXORMode(Color arg_color) {        
        XORcolor = arg_color;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font arg_font) {
        font=arg_font;
    }

    public FontMetrics getFontMetrics(Font arg_font) {
        Font cur_font = getFont();
        setFont(arg_font);
        FontMetrics fontMetrics=getFontMetrics(); 
        setFont(cur_font);
        return fontMetrics;
    }

    public void translate(int x, int y) {
          out_stream.println(x + " " + (-y) + " translate");
    }

    public void translate(double x, double y) {
        translate((int)x, (int)y);
    }
    
    public void rotate(double theta) {
        rotate(theta, 0d, 0d);
    }

    public void rotate(double theta, double x, double y) {
        double alfa; //angle in degrees 
        int y0;
        int x0;
        alfa = - theta * 180/java.lang.Math.PI;
        x0 =(int)x;
        y0 = convY((int)y);
        out_stream.println(x0 + " " + y0 + " translate");       
        out_stream.println(alfa + " rotate");
        out_stream.println((-x0) + " " + (-y0) + " translate");
    }
       
    public void scale(double sx, double sy) {
        out_stream.println(sx + " " + sy + " scale");
        yscale = yscale/sy;
    }

    public Paint getPaint() {      
        return  paint; 
    }

    public void setPaint(Paint arg0) {
        paint = arg0;
    }
    
    public void setClip(int x, int y, int width, int height) {
        int x2 = x + width;
        int y1 = convY(y);
        int y2 = convY(y + height);
        
        out_stream.println("newpath");
        out_stream.println("  " + x + " " + y1 + " moveto");
        out_stream.println("  " + x2 + " " + y1 + " lineto");
        out_stream.println("  " + x2 + " " + y2 + " lineto");
        out_stream.println("  " + x + " " + y2 + " lineto");
        out_stream.println("closepath clip");   
    }      
    
    /* methods below this line must be implemented*/    
    public Graphics create() {        
        return null;
    }

    public Rectangle getClipBounds() {       
        return null;
    }

    public Shape getClip() {
        return null;
    }

    public void setClip(Shape arg0) {       
    }

    public void shear(double arg0, double arg1) {
    }
   
    public Composite getComposite() {
        return null;
    }

    public void setComposite(Composite arg0) {
    }

    public GraphicsConfiguration getDeviceConfiguration() {
        return null;
    }

    public RenderingHints getRenderingHints() {
        return null;
    }

    public void clip(Shape arg0) {
    }
  
   
    public Stroke getStroke() {
        return null;
    }

    public void setStroke(Stroke arg0) {
    }

    public FontRenderContext getFontRenderContext() {
        return null;
    }

    public void drawGlyphVector(GlyphVector arg0, float arg1, float arg2) {
    }

    public AffineTransform getTransform() {
        return null;
    }

    public void setTransform(AffineTransform arg0) {
    }

    public void transform(AffineTransform arg0) {
    }

    public void addRenderingHints(Map hints) {
    }

    public void setRenderingHints(Map hints) {
    }

    public boolean hit(Rectangle arg0, Shape arg1, boolean arg2) {
        return false;
    }

    public void drawRenderedImage(RenderedImage arg0, AffineTransform arg1) {
    }

    public void drawRenderableImage(RenderableImage arg0, 
                                            AffineTransform arg1) {
    }

    public Object getRenderingHint(Key hintKey) {
        return null;
    }

    public void setRenderingHint(RenderingHints.Key hintKey, 
                                            Object hintValue) {
    }

    public void dispose() {      
    }

    public void setPaintMode() {        
    }
  
    public void clipRect(int x, int y, int width, int height) {
    }
        
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
    }    
}
