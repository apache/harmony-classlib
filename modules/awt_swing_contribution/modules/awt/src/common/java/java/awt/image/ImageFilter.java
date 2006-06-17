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

public class ImageFilter implements ImageConsumer, Cloneable {

    protected ImageConsumer consumer;

    public ImageFilter() {}

    public ImageFilter getFilterInstance(ImageConsumer ic) {
        ImageFilter filter = (ImageFilter) clone();
        filter.consumer = ic;
        return filter;
    }

    public void setProperties(Hashtable props) {
        Hashtable fprops;
        if(props == null) fprops = new Hashtable();
        else fprops = (Hashtable) props.clone();
        String propName = "Filters";
        String prop = "Null filter" ;
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

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void resendTopDownLeftRight(ImageProducer ip) {
        ip.requestTopDownLeftRightResend(this);
    }

    public void setColorModel(ColorModel model) {
        consumer.setColorModel(model);
    }

    public void setPixels(int x, int y, int w, int h, ColorModel model, 
            int[] pixels, int off, int scansize) {
        
        consumer.setPixels(x, y, w, h, model, pixels, off, scansize);
    }

    public void setPixels(int x, int y, int w, int h, ColorModel model, 
            byte[] pixels, int off, int scansize) {
        
        consumer.setPixels(x, y, w, h, model, pixels, off, scansize);
    }

    public void setDimensions(int width, int height) {
        consumer.setDimensions(width, height);
    }

    public void setHints(int hints) {
        consumer.setHints(hints);
    }

    public void imageComplete(int status) {
        consumer.imageComplete(status);
    }

}

