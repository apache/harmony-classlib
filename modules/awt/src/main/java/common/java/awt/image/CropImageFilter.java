/*
 *  Copyright 2005 - 2006 The Apache Software Software Foundation or its licensors, as applicable.
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
 * @author Igor V. Stolyarov
 * @version $Revision$
 */
package java.awt.image;

import java.util.Hashtable;

public class CropImageFilter extends ImageFilter {

    private int X, Y, WIDTH, HEIGHT;

    public CropImageFilter(int x, int y, int w, int h) {
        X = x;
        Y = y;
        WIDTH = w;
        HEIGHT = h;
    }

    public void setProperties(Hashtable props) {
        Hashtable fprops;
        if(props == null) fprops = new Hashtable();
        else fprops = (Hashtable) props.clone();
        String propName = "Crop Filters";
        String prop = "x=" + X + "; y=" + Y + "; width=" +
        WIDTH + "; height=" + HEIGHT;
        Object o = fprops.get(propName);
        if(o != null){
            if(o instanceof String){
                prop = (String)o + "; " + prop;
            }else{
                prop =  o.toString() + "; " + prop;
            }
        }
        fprops.put(propName, prop);
        consumer.setProperties(fprops);
    }

    public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {

        if(x + w < X || X + WIDTH < x ||
                y + h < Y || Y + HEIGHT < y) return;

        int destX, destY, destWidth, destHeight, endX, endY,
        srcEndX, srcEndY;

        int newOffset = off;

        endX = X + WIDTH;
        endY = Y + HEIGHT;

        srcEndX = x + w;
        srcEndY = y + h;

        if(x <= X){
            destX = 0;
            newOffset += X;
            if(endX >= srcEndX){
                destWidth = srcEndX - X;
            }else{
                destWidth = WIDTH;
            }
        }else{
            destX = x - X;
            if(endX >= srcEndX){
                destWidth = w;
            }else{
                destWidth = endX - x;
            }
        }


        if(y <= Y){
            newOffset += scansize * (Y - y);
            destY = 0;
            if(endY >= srcEndY){
                destHeight = srcEndY - Y;
            }else{
                destHeight = HEIGHT;
            }
        }else{
            destY = y - Y;
            if(endY >= srcEndY){
                destHeight = h;
            }else{
                destHeight = endY - y;
            }
        }
        consumer.setPixels(destX, destY, destWidth, destHeight, model, pixels, newOffset, scansize);
    }

    public void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {

        if(x + w < X || X + WIDTH < x ||
                y + h < Y || Y + HEIGHT < y) return;

        int destX, destY, destWidth, destHeight, endX, endY,
        srcEndX, srcEndY;

        int newOffset = off;

        endX = X + WIDTH;
        endY = Y + HEIGHT;

        srcEndX = x + w;
        srcEndY = y + h;

        if(x <= X){
            destX = 0;
            newOffset += X;
            if(endX >= srcEndX){
                destWidth = srcEndX - X;
            }else{
                destWidth = WIDTH;
            }
        }else{
            destX = x - X;
            if(endX >= srcEndX){
                destWidth = w;
            }else{
                destWidth = endX - x;
            }
        }


        if(y <= Y){
            newOffset += scansize * (Y - y);
            destY = 0;
            if(endY >= srcEndY){
                destHeight = srcEndY - Y;
            }else{
                destHeight = HEIGHT;
            }
        }else{
            destY = y - Y;
            if(endY >= srcEndY){
                destHeight = h;
            }else{
                destHeight = endY - y;
            }
        }
        consumer.setPixels(destX, destY, destWidth, destHeight, model, pixels, newOffset, scansize);
    }

    public void setDimensions(int w, int h) {
        consumer.setDimensions(WIDTH, HEIGHT);
    }

}

