/*
 *  Copyright 2005 - 2006 The Apache Software Foundation or its licensors, as applicable.
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
 * @author Alexander T. Simbirtsev
 * @version $Revision$
 */
package javax.swing;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.IllegalComponentStateException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.Serializable;
import java.net.URL;
import java.util.Locale;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleIcon;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleStateSet;

public class ImageIcon implements Icon, Serializable, Accessible {

    //TODO: implement
    protected class AccessibleImageIcon extends AccessibleContext
        implements AccessibleIcon, Serializable {

        public Accessible getAccessibleChild(final int i) {
            return null;
        }

        public int getAccessibleChildrenCount() {
            return 0;
        }

        public int getAccessibleIndexInParent() {
            return 0;
        }

        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.ICON;
        }

        public AccessibleStateSet getAccessibleStateSet() {
            return null;
        }

        public Locale getLocale() throws IllegalComponentStateException {
            return null;
        }

        public void setAccessibleIconDescription(final String description) {
        }

        public String getAccessibleIconDescription() {
            return getDescription();
        }

        public int getAccessibleIconWidth() {
            return 0;
        }

        public int getAccessibleIconHeight() {
            return 0;
        }

    }

    protected static final Component component;
    protected static final MediaTracker tracker;

    private ImageObserver observer;
    private AccessibleContext accessibleContext;
    private Image image;
    private String description;

    /**
     * unique id is to enable image loading tracking via MediaTracker
     */
    private final int id = getUniqueID();

    /**
     * is used for generating unique ids for icons
     */
    private static int lastAssignedID;

    static {
        component = new Panel();
        tracker = new MediaTracker(component);
        lastAssignedID = 0;
    }

    /**
     * 'generates' unique ids for icons
     */
    private final static int getUniqueID() {
        return lastAssignedID++;
    }

    public ImageIcon(final URL url, final String description) {
        this(url);
        this.description = description;
    }

    public ImageIcon(final String fileName, final String description) {
        this(fileName);
        this.description = description;
    }

    public ImageIcon(final Image image, final String description) {
        this(image);
        this.description = description;
    }

    public ImageIcon(final byte[] imageData, final String description) {
        this(imageData);
        this.description = description;
    }

    public ImageIcon(final URL url) {
        image = Toolkit.getDefaultToolkit().createImage(url);
        loadImage(image);
        description = url.toString();
    }

    public ImageIcon(final String fileName) {
        image = Toolkit.getDefaultToolkit().createImage(fileName);
        loadImage(image);
        description = fileName;
    }

    public ImageIcon(final Image image) {
        this.image = image;
        loadImage(image);
        Object comment = image.getProperty("comment", observer);
        if (comment instanceof String) {
            description = (String)comment;
        }
    }

    public ImageIcon(final byte[] imageData) {
        image = Toolkit.getDefaultToolkit().createImage(imageData);
        loadImage(image);
        Object comment = image.getProperty("comment", observer);
        if (comment instanceof String) {
            description = (String)comment;
        }
    }

    public ImageIcon() {
    }

    public synchronized void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        g.drawImage(image, x, y, (observer != null) ? observer : c);
    }

    public AccessibleContext getAccessibleContext() {
        return (accessibleContext == null) ? (accessibleContext = new AccessibleImageIcon())
                : accessibleContext;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String toString() {
        return description != null ? description : super.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setImageObserver(final ImageObserver observer) {
        this.observer = observer;
    }

    public ImageObserver getImageObserver() {
        return observer;
    }

    public void setImage(final Image newImage) {
        tracker.removeImage(image);
        image = newImage;
        tracker.addImage(image, id);
    }

    protected void loadImage(final Image image) {
        tracker.addImage(image, id);
        try {
            tracker.waitForID(id);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public Image getImage() {
        return image;
    }

    public int getImageLoadStatus() {
        return tracker.statusID(id, false);
    }

    public int getIconWidth() {
        return (image != null) ? image.getWidth(observer) : -1;
    }

    public int getIconHeight() {
        return (image != null) ? image.getHeight(observer) : -1;
    }
}

