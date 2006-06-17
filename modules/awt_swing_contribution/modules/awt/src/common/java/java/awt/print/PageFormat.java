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
 * @author Igor A. Pyankov
 * @version $Revision$
 */
package java.awt.print;

public class PageFormat implements Cloneable {
    public static final int LANDSCAPE = 0;
    public static final int PORTRAIT = 1;
    public static final int REVERSE_LANDSCAPE = 2;

    private Paper pagePaper;
    private int   pageOrientation;

    public PageFormat() {
        super();
        pageOrientation = PORTRAIT;
        pagePaper= new Paper();
    }

    public Object clone(){
        PageFormat clonedPage;
        try {
            clonedPage = (PageFormat) super.clone();
            clonedPage.pagePaper = (Paper) pagePaper.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace();
            clonedPage = null;
        }
        return clonedPage;
    }

    public double getHeight(){
        if (pageOrientation == PORTRAIT) {
            return pagePaper.getHeight();
        }
        return pagePaper.getWidth();
    }

    public double getImageableHeight() {
        if (pageOrientation == PORTRAIT) {
            return pagePaper.getImageableHeight();
        }
        return pagePaper.getImageableWidth();
    }

    public double getImageableWidth() {
        if (pageOrientation == PORTRAIT) {
            return pagePaper.getImageableWidth();
        }
        return pagePaper.getImageableHeight();
    }

    public double getImageableX(){
        double x = 0;
        switch(getOrientation()){
            case PORTRAIT:
                x = pagePaper.getImageableX();
                break;

            case LANDSCAPE:
                x = pagePaper.getHeight()
                    - (pagePaper.getImageableY()
                    +  pagePaper.getImageableHeight());
                break;

            case REVERSE_LANDSCAPE:
                x = pagePaper.getImageableY();
                break;
        }
        return x;
    }

    public double getImageableY(){
        double y = 0;
        switch(getOrientation()){
            case PORTRAIT:
                y = pagePaper.getImageableY();
                break;

            case LANDSCAPE:
                y = pagePaper.getImageableX();
                break;

            case REVERSE_LANDSCAPE:
                y = pagePaper.getWidth()
                    - (pagePaper.getImageableX()
                    +  pagePaper.getImageableWidth());
                break;
        }
        return y;
    }

    public double[] getMatrix(){
        double matrix[] = {0d,0d,0d,0d,0d,0d};

        switch(pageOrientation){

            case PORTRAIT:
                 matrix[0] = 1.0d;
                 matrix[3] = 1.0d;
                 break;

            case LANDSCAPE:
                 matrix[1] = -1.0d;
                 matrix[2] = 1.0d;
                 matrix[5] = pagePaper.getHeight();
                 break;

            case REVERSE_LANDSCAPE:
                  matrix[1] = 1.0d;
                  matrix[2] = -1.0d;
                  matrix[4] = pagePaper.getWidth();
                  break;
        }
        return matrix;
    }

    public int getOrientation(){
        return pageOrientation;
    }

    public Paper getPaper(){
        return pagePaper;
    }

    public double getWidth(){
        if (pageOrientation == PORTRAIT) {
            return pagePaper.getWidth();
        }
        return pagePaper.getHeight();
    }

    public void setOrientation(int orientation)
                throws IllegalArgumentException {
        if (orientation == PORTRAIT
                || orientation == LANDSCAPE
                || orientation == REVERSE_LANDSCAPE) {
            this.pageOrientation = orientation;
        } else {
            throw new IllegalArgumentException("wrong orientation");
        }
    }

    public void setPaper(Paper paper){
        this.pagePaper = paper;
    }
}
