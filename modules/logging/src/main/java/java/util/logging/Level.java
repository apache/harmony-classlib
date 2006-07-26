/* Copyright 2004 The Apache Software Foundation or its licensors, as applicable
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package java.util.logging;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * <code>Level</code> objects are used to indicate the level of logging. There
 * are a set of predefined logging levels, each associated with an integer
 * value. Enabling a certain logging level also enables all logging levels with
 * larger values.
 * <p>
 * The predefined levels in ascending order are FINEST, FINER, FINE, CONFIG,
 * INFO, WARNING, SEVERE. There are two additional predefined levels, which are
 * ALL and OFF. ALL indicates logging all messages, and OFF indicates logging no
 * messages.
 * </p>
 * 
 */
public class Level implements Serializable {
    
    
    //for serialization compatibility
    private static final long serialVersionUID = -8176160795706313070L;    

    /*
     * -------------------------------------------------------------------
     * Constants
     * -------------------------------------------------------------------
     */

	/*
     * -------------------------------------------------------------------
     * Class variables
     * -------------------------------------------------------------------
     */
    private static Map<String, Level> levels = new HashMap<String, Level>();
	
    // The following string constants define the name of all predefined levels.
    private static final String SEVERESTR = "SEVERE"; //$NON-NLS-1$

    private static final String WARNINGSTR = "WARNING"; //$NON-NLS-1$

    private static final String INFOSTR = "INFO"; //$NON-NLS-1$

    private static final String CONFIGSTR = "CONFIG"; //$NON-NLS-1$

    private static final String FINESTR = "FINE"; //$NON-NLS-1$

    private static final String FINERSTR = "FINER"; //$NON-NLS-1$

    private static final String FINESTSTR = "FINEST"; //$NON-NLS-1$

    private static final String OFFSTR = "OFF"; //$NON-NLS-1$

    private static final String ALLSTR = "ALL"; //$NON-NLS-1$

    /**
     * The SEVERE level indicates a severe failure.
     */
    public static final Level SEVERE = new Level(SEVERESTR, 1000);

    /**
     * The WARNING level indicates a warning.
     */
    public static final Level WARNING = new Level(WARNINGSTR, 900);

    /**
     * The INFO level indicates an informative message.
     */
    public static final Level INFO = new Level(INFOSTR, 800);

    /**
     * The CONFIG level indicates a static configuration message.
     */
    public static final Level CONFIG = new Level(CONFIGSTR, 700);

    /**
     * The FINE level provides tracing messages.
     */
    public static final Level FINE = new Level(FINESTR, 500);

    /**
     * The FINER level provides more detailed tracing messages.
     */
    public static final Level FINER = new Level(FINERSTR, 400);

    /**
     * The FINEST level provides highly detailed tracing messages.
     */
    public static final Level FINEST = new Level(FINESTSTR, 300);

    /**
     * The OFF level provides no logging messages.
     */
    public static final Level OFF = new Level(OFFSTR, Integer.MAX_VALUE);

    /**
     * The ALL level provides all logging messages.
     */
    public static final Level ALL = new Level(ALLSTR, Integer.MIN_VALUE);


    /*
     * -------------------------------------------------------------------
     * Global initialization
     * -------------------------------------------------------------------
     */

    static {
		levels.remove(null);
    }

    /*
     * -------------------------------------------------------------------
     * Instance variables
     * -------------------------------------------------------------------
     */

    /**
     * The name of this Level.
     * 
     * @serial
     */
    private final String name;

    /**
     * The integer value indicating the level.
     * 
     * @serial
     */
    private final int value;

    /**
     * The name of the resource bundle used to localize the level name.
     * 
     * @serial
     */
    private final String resourceBundleName;

    /*
     * The resource bundle associated with this level, used to localize the
     * level name.
     */
    private transient ResourceBundle rb;

    /*
     * -------------------------------------------------------------------
     * Constructors
     * -------------------------------------------------------------------
     */

    /**
     * Constructs an instance of <code>Level</code> taking the supplied name
     * and level value.
     * 
     * @param name
     *            name of the level
     * @param level
     *            an integer value indicating the level
     */
    protected Level(String name, int level) {
        this(name, level, null);
    }

    /**
     * Constructs an instance of <code>Level</code> taking the supplied name
     * and level value.
     * 
     * @param name
     *            name of the level
     * @param level
     *            an integer value indicating the level
     * @param resourceBundleName
     *            the name of the resource bundle to use
     */
    protected Level(String name, int level, String resourceBundleName) {
        if (null == name) {
            throw new NullPointerException("null"); //$NON-NLS-1$
        }
        this.name = name;
        this.value = level;
        this.resourceBundleName = resourceBundleName;
		//put value into known values list in Constructor
		if(null==levels.get(name)){
			levels.put(name,this);
		}
		if(null==levels.get(String.valueOf(level))){
			levels.put(String.valueOf(this.intValue()), this);
		}
    }

    /*
     * -------------------------------------------------------------------
     * Methods
     * -------------------------------------------------------------------
     */

    /**
     * Gets the name of this <code>Level</code>.
     * 
     * @return the name of this <code>Level</code>
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the resource bundle associated with this
     * <code>Level</code>.
     * 
     * @return the name of the resource bundle associated with this
     *         <code>Level</code>
     */
    public String getResourceBundleName() {
        return this.resourceBundleName;
    }

    /**
     * Gets the integer value indicating this <code>Level</code>.
     * 
     * @return the integer value indicating this <code>Level</code>
     */
    public final int intValue() {
        return this.value;
    }

    /**
     * Parses a level name into a <code>Level</code> object.
     * 
     * @param name
     *            the name of the desired level, which cannot be null
     * @return a <code>Level</code> object with the specified name
     * @throws NullPointerException
     *             If <code>name</code> is null.
     * @throws IllegalArgumentException
     *             When <code>name</code> cannot be parsed.
     */
    public static final Level parse(String name) {
        if (null == name) {
            throw new NullPointerException("null"); //$NON-NLS-1$
        }
        // Check if the name is a predefined one
        Level result = levels.get(name);
        if (null != result) {
            return result;
        }
        // Try to parse the name as an integer
        try {
            int v = Integer.parseInt(name);
            result = new Level(name, v);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Cannot parse this name: " //$NON-NLS-1$
                    + name);
        }
    }

    /**
     * Gets the localized name of this level. The default locale is used. If no
     * resource bundle is associated with this <code>Level</code>, the
     * original level name is returned.
     * 
     * @return the localized name of this level
     */
    public String getLocalizedName() {
        String result = name;
        if (null != resourceBundleName && null == rb) {
            try {
                rb = ResourceBundle.getBundle(resourceBundleName);
            } catch (Exception e) {
                rb = null;
            }
        }
        if (null != rb) {
            try {
                result = rb.getString(name);
            } catch (Exception e) {
                result = name;
            }
        }
        return result;
    }

    /*
     * Maintains the Object uniqueness property across serialization.
     */
    private Object readResolve() {
        String levelName = this.getName();
        Level result = levels.get(levelName);

        if (null != result) {
            boolean sameResourceBundle = (this.resourceBundleName == null ? result
                    .getResourceBundleName() == null
                    : this.resourceBundleName.equals(result
                            .getResourceBundleName()));
            if (result.intValue() == this.value && sameResourceBundle) {
                return result;
            }
        }
        return this;
    }

    /*
     * -------------------------------------------------------------------
     * Methods overriding parent class Object
     * -------------------------------------------------------------------
     */

    /**
     * Compares two <code>Level</code> objects for equality. They are
     * considered to be equal if they have the same value.
     * 
     * @param o
     *            the other object to be compared with
     * @return <code>true</code> if this object equals to the supplied object,
     *         otherwise <code>false</code>
     */
    public boolean equals(Object o) {
        if (!(o instanceof Level)) {
            return false;
        }
        return ((Level) o).intValue() == this.value;
    }

    /**
     * Returns the hash code of this <code>Level</code> object.
     * 
     * @return the hash code of this <code>Level</code> object
     */
    public int hashCode() {
        return this.value;
    }

    /**
     * Returns the string representation of this <code>Level</code> object.
     * Usually this will include its name.
     * 
     * @return the string representation of this <code>Level</code> object
     */
    public final String toString() {
        return this.name;
    }

}

