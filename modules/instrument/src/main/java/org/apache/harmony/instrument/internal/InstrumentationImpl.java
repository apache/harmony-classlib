/* Copyright 2006 The Apache Software Foundation or its licensors, as applicable
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

package org.apache.harmony.instrument.internal;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

/**
 * Default implementation of Instrumentation
 */
public class InstrumentationImpl implements Instrumentation {
    /*
     * ----------------------------------------------------------------------------
     * Consts
     * ----------------------------------------------------------------------------
     */
    private static final int JVMTI_SUCCEED = 0;

    private static final int JVMTI_ERROR_MUST_POSSESS_CAPABILITY = 99;

    private static final int JVMTI_ERROR_NULL_POINTER = 100;

    private static final int JVMTI_ERROR_UNMODIFIABLE_CLASS = 79;

    private static final int JVMTI_ERROR_INVALID_CLASS = 21;

    private static final int JVMTI_ERROR_UNSUPPORTED_VERSION = 68;

    private static final int JVMTI_ERROR_INVALID_CLASS_FORMAT = 60;

    private static final int JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION = 61;

    private static final int JVMTI_ERROR_FAILS_VERIFICATION = 62;

    private static final int JVMTI_ERROR_NAMES_DONT_MATCH = 69;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED = 63;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED = 64;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED = 66;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED = 67;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED = 70;

    private static final int JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED = 71;

    private static final Class[] PREMAIN_SIGNATURE = new Class[] {
            String.class, Instrumentation.class };

    /*
     * ----------------------------------------------------------------------------
     * Variables
     * ----------------------------------------------------------------------------
     */
    private ClassFileTransformer[] transformers = new ClassFileTransformer[0];

    private boolean isRedefineClassesSupported;

    /*
     * ----------------------------------------------------------------------------
     * Constructor
     * ----------------------------------------------------------------------------
     */
    /**
     * Constructs a new instance.
     * 
     * @param isRedefineClassesSupported
     */
    public InstrumentationImpl(boolean isRedefineClassesSupported) {
        this.isRedefineClassesSupported = isRedefineClassesSupported;
    }

    /*
     * ----------------------------------------------------------------------------
     * Methods implemented from Instrumentation
     * ----------------------------------------------------------------------------
     */
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#addTransformer(java.lang.instrument.ClassFileTransformer)
     */
    public void addTransformer(ClassFileTransformer transformer) {
        if (null == transformer) {
            throw new NullPointerException();
        }
        int length = transformers.length;
        ClassFileTransformer[] temp = new ClassFileTransformer[length + 1];
        System.arraycopy(transformers, 0, temp, 0, length);
        temp[length] = transformer;
        transformers = temp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#redefineClasses(java.lang.instrument.ClassDefinition[])
     */
    public void redefineClasses(ClassDefinition[] definitions)
            throws ClassNotFoundException, UnmodifiableClassException {
        if (!isRedefineClassesSupported) {
            throw new UnsupportedOperationException(
                    "Redefinition operation is not supported!"); //$NON-NLS-1$
        }
        for (int i = 0; i < definitions.length; i++) {
            if (null == definitions[i]) {
                throw new NullPointerException();
            }
        }
        int result = redefineClasses_native(definitions);

        switch (result) {
        case JVMTI_SUCCEED:
            return;
        case JVMTI_ERROR_MUST_POSSESS_CAPABILITY:
            throw new UnsupportedOperationException(
                    "The environment does not possess the capability can_redefine_classes."); //$NON-NLS-1$
        case JVMTI_ERROR_NULL_POINTER:
            throw new NullPointerException("One of class_bytes is NULL."); //$NON-NLS-1$
        case JVMTI_ERROR_UNMODIFIABLE_CLASS:
            throw new UnmodifiableClassException(
                    "An element of class_definitions cannot be modified."); //$NON-NLS-1$
        case JVMTI_ERROR_INVALID_CLASS:
            throw new ClassNotFoundException(
                    "An element of class_definitions is not a valid class."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_VERSION:
            throw new UnsupportedClassVersionError(
                    "A new class file has a version number not supported by this VM."); //$NON-NLS-1$
        case JVMTI_ERROR_INVALID_CLASS_FORMAT:
            throw new ClassFormatError("A new class file is malformed."); //$NON-NLS-1$
        case JVMTI_ERROR_CIRCULAR_CLASS_DEFINITION:
            throw new ClassCircularityError(
                    "The new class file definitions would lead to a circular definition."); //$NON-NLS-1$
        case JVMTI_ERROR_FAILS_VERIFICATION:
            throw new ClassFormatError("The class bytes fail verification."); //$NON-NLS-1$
        case JVMTI_ERROR_NAMES_DONT_MATCH:
            throw new NoClassDefFoundError(
                    "The class name defined in a new class file is different from the name in the old class object."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_ADDED:
            throw new UnsupportedOperationException(
                    "A new class file requires adding a method."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_SCHEMA_CHANGED:
            throw new UnsupportedOperationException(
                    "A new class version changes a field."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_HIERARCHY_CHANGED:
            throw new UnsupportedOperationException(
                    "A direct superclass is different for a new class version, or the set of directly implemented interfaces is different."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_DELETED:
            throw new UnsupportedOperationException(
                    "A new class version does not declare a method declared in the old class version."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_CLASS_MODIFIERS_CHANGED:
            throw new UnsupportedOperationException(
                    "A new class version has different modifiers."); //$NON-NLS-1$
        case JVMTI_ERROR_UNSUPPORTED_REDEFINITION_METHOD_MODIFIERS_CHANGED:
            throw new UnsupportedOperationException(
                    "A method in the new class version has different modifiers than its counterpart in the old class version."); //$NON-NLS-1$
        default:
            throw new InternalError("Unknown error during redefinition."); //$NON-NLS-1$
        }
    }

    private native int redefineClasses_native(ClassDefinition[] definitions);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#removeTransformer(java.lang.instrument.ClassFileTransformer)
     */
    public boolean removeTransformer(ClassFileTransformer transformer) {
        if (null == transformer) {
            throw new NullPointerException();
        }
        int i = 0;
        int length = transformers.length;
        for (; i < length && transformers[i] != transformer; i++)
            ;
        if (i == length) {
            return false;
        }
        ClassFileTransformer[] temp = new ClassFileTransformer[length - 1];
        if (i > 0) {
            System.arraycopy(transformers, 0, temp, 0, i);
        }
        if (i < length - 1) {
            System.arraycopy(transformers, i + 1, temp, i, length - i - 1);
        }
        transformers = temp;
        return true;
    }

    public void clear() {
        transformers = new ClassFileTransformer[0];
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#getAllLoadedClasses()
     */
    public native Class[] getAllLoadedClasses();

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#getInitiatedClasses(java.lang.ClassLoader)
     */
    public native Class[] getInitiatedClasses(ClassLoader loader);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#getObjectSize(java.lang.Object)
     */
    public long getObjectSize(Object objectToSize) {
        if (null == objectToSize) {
            throw new NullPointerException();
        }
        return getObjectSize_native(objectToSize);
    }

    private native long getObjectSize_native(Object objectToSize);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.instrument.Instrumentation#isRedefineClassesSupported()
     */
    public boolean isRedefineClassesSupported() {
        return isRedefineClassesSupported;
    }

    /*
     * ----------------------------------------------------------------------------
     * Callback methods for native JVMTI agent
     * ----------------------------------------------------------------------------
     */
    /*
     * ClassFileLoadHook event handler method
     */
    private byte[] transform(ClassLoader loader, byte[] classNameBytes,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) {
        byte[] source = classfileBuffer;
        byte[] result = null;
        byte[] trans = null;
        String className = new String(classNameBytes);
        for (ClassFileTransformer t : transformers) {
            try {
                trans = t.transform(loader, className, classBeingRedefined,
                        protectionDomain, source);
                if (null != trans && 0 != trans.length) {
                    result = trans;
                    source = trans;
                }
            } catch (Exception e) {
                // nothing to do, just continue~
            }
        }
        return result;
    }

    /*
     * callback method to execute javaagents' premain method
     */
    private void executePremain(byte[] className, byte[] options) {
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            Class c = loader.loadClass(new String(className));
            Method method = c.getMethod("premain", PREMAIN_SIGNATURE);
            method.invoke(null, new Object[] {
                    null == options ? null : new String(options), this });
        } catch (Exception e) {
            e.printStackTrace();
            System.err
                    .println("Fatal error: failed to execute premain class of java agent.");
            System.exit(1);
        }
    }
}
