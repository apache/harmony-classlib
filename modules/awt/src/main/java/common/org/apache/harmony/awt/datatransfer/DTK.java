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
 * @author Michael Danilov
 * @version $Revision$
 */
package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.FlavorMap;

import org.apache.harmony.awt.ContextStorage;

/**
 * Data transfer ToolKit.
 * Unites context-dependent and platform-dependent information of data transferring subsystem.
 */
public abstract class DTK {

    private static final String osName = System.getProperty("os.name").toLowerCase();

    /**
     * Descriptor of text representation in native platform (common for all application contexts).
     */
    public static final NativeTextDescriptor textDescriptor =
            getContextInstance().newTextDescriptor();

    private NativeTranslationManager translationManager = null;
    private NativeClipboard nativeClipboard = null;
    private NativeClipboard nativeSelection = null;

    private FlavorMap flavorMap = null;

    /**
     * Returns data transfer toolkit for current application context.
     */
    public static DTK getContextInstance() {
        synchronized(ContextStorage.getContextLock()) {
            if (ContextStorage.shutdownPending()) {
                return null;
            }

            DTK instance = ContextStorage.getDTK();

            if (instance == null) {
                String name;

                if (osName.startsWith("linux")) {
                    name = "org.apache.harmony.awt.datatransfer.linux.LinuxDTK";
                } else if (osName.startsWith("windows")) {
                    name = "org.apache.harmony.awt.datatransfer.windows.WinDTK";
                } else {
                    throw new RuntimeException("Unknown native platform.");
                }

                try {
                    instance = (DTK) Class.forName(name).newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                ContextStorage.setDTK(instance);
            }

            return instance;
        }
    }

    /**
     * Returns system flavor map for current application context.
     * For use from SystemFlavorMap.getDefaultFlavorMap() only
     */
    public FlavorMap getFlavorMap() {
        return flavorMap;
    }

    /**
     * Sets system flavor map for current application context.
     * For use from SystemFlavorMap.getDefaultFlavorMap() only.
     */
    public void setFlavorMap(FlavorMap flavorMap) {
        this.flavorMap = flavorMap;
    }

    /**
     * Returns clipboard translation manager for current application context.
     */
    public NativeTranslationManager getTranslationManager() {
        if (translationManager == null) {
            translationManager = newTranslationManager();
        }

        return translationManager;
    }

    /**
     * Returns native clipboard for current application context.
     */
    public NativeClipboard getNativeClipboard() {
        if (nativeClipboard == null) {
            nativeClipboard = newNativeClipboard();
        }

        return nativeClipboard;
    }

    /**
     * Returns native selection for current application context.
     */
    public NativeClipboard getNativeSelection() {
        if (nativeSelection == null) {
            nativeSelection = newNativeSelection();
        }

        return nativeSelection;
    }

    /**
     * Creates native clipboard for current native platform.
     */
    protected abstract NativeClipboard newNativeClipboard();

    /**
     * Creates native selection for current native platform.
     */
    protected abstract NativeClipboard newNativeSelection();

    /**
     * Creates text descriptor for current native platform.
     */
    protected abstract NativeTextDescriptor newTextDescriptor();

    /**
     * Creates translation manager for current native platform.
     */
    protected abstract NativeTranslationManager newTranslationManager();

}
