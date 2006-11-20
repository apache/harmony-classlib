/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.net.URL;

/**
 * This class must be implemented by the VM vendor.
 * 
 * An instance of class Package contains information about a Java package. This
 * includes implementation and specification versions. Typically this
 * information is retrieved from the manifest.
 * <p>
 * Packages are managed by class loaders. All classes loaded by the same loader
 * from the same package share a Package instance.
 * 
 * 
 * @see ClassLoader
 * @since 1.0
 */
public class Package implements AnnotatedElement {

    /**
     * Prevent this class from being instantiated
     */
    private Package(){
        //do nothing
    }
    
    /**
     * Gets the annotation associated with the given annotation type and this
     * package.
     * 
     * @return An instance of {@link Annotation} or <code>null</code>.
     * @since 1.5
     * @see java.lang.reflect.AnnotatedElement#getAnnotation(java.lang.Class)
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        return null;
    }

    /**
     * Gets all of the annotations associated with this package.
     * 
     * @return An array of {@link Annotation} instances, which may be empty.
     * @since 1.5
     * @see java.lang.reflect.AnnotatedElement#getAnnotations()
     */
    public Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    /**
     * Gets all of the annotations directly declared on this element.
     * 
     * @return An array of {@link Annotation} instances, which may be empty.
     * @since 1.5
     * @see java.lang.reflect.AnnotatedElement#getDeclaredAnnotations()
     */
    public Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    /**
     * Indicates whether or not the given annotation is present.
     * 
     * @return A value of <code>true</code> if the annotation is present,
     *         otherwise <code>false</code>.
     * @since 1.5
     * @see java.lang.reflect.AnnotatedElement#isAnnotationPresent(java.lang.Class)
     */
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        return false;
    }

    /**
     * Return the title of the implementation of this package, or null if this
     * is unknown. The format of this string is unspecified.
     * 
     * @return The implementation title, or null
     */
    public String getImplementationTitle() {
        return null;
    }

    /**
     * Return the name of the vendor or organization that provided this
     * implementation of the package, or null if this is unknown. The format of
     * this string is unspecified.
     * 
     * @return The implementation vendor name, or null
     */
    public String getImplementationVendor() {
        return null;
    }

    /**
     * Return the version of the implementation of this package, or null if this
     * is unknown. The format of this string is unspecified.
     * 
     * @return The implementation version, or null
     */
    public String getImplementationVersion() {
        return null;
    }

    /**
     * Return the name of this package in the standard dot notation; for
     * example: "java.lang".
     * 
     * @return The name of this package
     */
    public String getName() {
        return null;
    }

    /**
     * Attempt to locate the requested package in the caller's class loader. If
     * no package information can be located, null is returned.
     * 
     * @param packageName The name of the package to find
     * @return The package requested, or null
     * 
     * @see ClassLoader#getPackage
     */
    public static Package getPackage(String packageName) {
        return null;
    }

    /**
     * Return all the packages known to the caller's class loader.
     * 
     * @return All the packages known to the caller's class loader
     * 
     * @see ClassLoader#getPackages
     */
    public static Package[] getPackages() {
        return null;
    }

    /**
     * Return the title of the specification this package implements, or null if
     * this is unknown.
     * 
     * @return The specification title, or null
     */
    public String getSpecificationTitle() {
        return null;
    }

    /**
     * Return the name of the vendor or organization that owns and maintains the
     * specification this package implements, or null if this is unknown.
     * 
     * @return The specification vendor name, or null
     */
    public String getSpecificationVendor() {
        return null;
    }

    /**
     * Return the version of the specification this package implements, or null
     * if this is unknown. The version string is a sequence of non-negative
     * integers separated by dots; for example: "1.2.3".
     * 
     * @return The specification version string, or null
     */
    public String getSpecificationVersion() {
        return null;
    }

    /**
     * Answers an integer hash code for the receiver. Any two objects which
     * answer <code>true</code> when passed to <code>equals</code> must
     * answer the same value for this method.
     * 
     * @return the receiver's hash
     */
    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Return true if this package's specification version is compatible with
     * the specified version string. Version strings are compared by comparing
     * each dot separated part of the version as an integer.
     * 
     * @param version The version string to compare against
     * @return true if the package versions are compatible, false otherwise
     * 
     * @throws NumberFormatException if the package's version string or the one
     *         provided is not in the correct format
     */
    public boolean isCompatibleWith(String version) throws NumberFormatException {
        return false;
    }

    /**
     * Return true if this package is sealed, false otherwise.
     * 
     * @return true if this package is sealed, false otherwise
     */
    public boolean isSealed() {
        return false;
    }

    /**
     * Return true if this package is sealed with respect to the specified URL,
     * false otherwise.
     * 
     * @param url the URL to test
     * @return true if this package is sealed, false otherwise
     */
    public boolean isSealed(URL url) {
        return false;
    }

    /**
     * Answers a string containing a concise, human-readable description of the
     * receiver.
     * 
     * @return a printable representation for the receiver.
     */
    @Override
    public String toString() {
        return null;
    }
}
