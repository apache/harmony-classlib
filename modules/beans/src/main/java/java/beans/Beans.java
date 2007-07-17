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

package java.beans;

import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.applet.AudioClip;
import java.awt.Image;
import java.beans.beancontext.BeanContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import org.apache.harmony.beans.internal.nls.Messages;

public class Beans {

    private static boolean designTime = false;

    private static boolean guiAvailable = true;

    public Beans() {
    }

    public static Object instantiate(ClassLoader loader, String name)
            throws IOException, ClassNotFoundException {
        return instantiate(loader, name, null, null);
    }

    public static Object instantiate(ClassLoader cls, String beanName,
			BeanContext beanContext) throws IOException, ClassNotFoundException {
		return instantiate(cls, beanName, beanContext, null);

	}

    @SuppressWarnings("unchecked")
	public static Object instantiate(ClassLoader cls, String beanName,
			BeanContext context, AppletInitializer initializer)
			throws IOException, ClassNotFoundException {
		Object result = null;

		boolean deserialized = true;

		ClassLoader loader = null;

		String beanResourceName = getBeanResourceName(beanName);

		InputStream is = (cls == null) ? ClassLoader
				.getSystemResourceAsStream(beanResourceName) : cls
				.getResourceAsStream(beanResourceName);

		if (is != null) {
			ObjectInputStream ois = (cls == null) ? new ObjectInputStream(is)
					: new CustomizedObjectInputStream(is, cls);
			result = ois.readObject();
		}

		if (result == null) {
			deserialized = false;
			try {
				loader = cls == null ? ClassLoader.getSystemClassLoader() : cls;
				Class<?> c = Class.forName(beanName, true, loader);

				try {
					result = c.newInstance();

				} catch (IllegalAccessException iae) {
					throw new ClassNotFoundException(iae.getClass() + ": " //$NON-NLS-1$
							+ iae.getMessage());
				}
			} catch (InstantiationException ie) {
				throw new ClassNotFoundException(ie.getClass() + ": " //$NON-NLS-1$
						+ ie.getMessage());
			}
		}

		if (result != null) {
			// Applet specific initialization
			if (result instanceof Applet) {
				appletLoaded((Applet) result, loader, beanName, context,
						initializer, deserialized);
			}
			if (null != context) {
				context.add(result);
			}
		}
		return result;
	}

    public static Object getInstanceOf(Object bean, Class<?> targetType) {
        return bean;
    }

    public static boolean isInstanceOf(Object bean, Class<?> targetType) {
        if (bean == null) {
            throw new NullPointerException(Messages.getString("beans.1D")); //$NON-NLS-1$
        }

        return targetType == null ? false : targetType.isInstance(bean);
    }

    public static synchronized void setGuiAvailable(boolean isGuiAvailable)
            throws SecurityException {
        checkPropertiesAccess();
        guiAvailable = isGuiAvailable;
    }

    public static void setDesignTime(boolean isDesignTime)
            throws SecurityException {
        checkPropertiesAccess();
        synchronized(Beans.class){
            designTime = isDesignTime;
        }
    }

    public static synchronized boolean isGuiAvailable() {
        return guiAvailable;
    }

    public static synchronized boolean isDesignTime() {
        return designTime;
    }

    private static void checkPropertiesAccess() throws SecurityException {
        SecurityManager sm = System.getSecurityManager();

        if (sm != null) {
            sm.checkPropertiesAccess();
        }
    }

    private static String getBeanResourceName(String beanName) {
        return beanName.replace('.', '/') + ".ser"; //$NON-NLS-1$
    }
    
    
    private static void appletLoaded(Applet applet, ClassLoader loader,
            String name, BeanContext context, AppletInitializer initializer,
            boolean deserialized) throws ClassNotFoundException {

        // If there is an initializer
        if (initializer != null) {
            initializer.initialize(applet, context);
        } else {
            setStub(applet, loader, deserialized, name);
        }

        if (!deserialized) {
            applet.init();
        }

        if (initializer != null) {
            initializer.activate(applet);
        }
    }

    private static void setStub(Applet applet, final ClassLoader loader,
            boolean serialized, String beanName) throws ClassNotFoundException {
        // Get path to the resource representing the applet.
        String pathName = beanName.replace('.', '/');
        final String resourceName = serialized ? pathName.concat(".ser") : pathName.concat(".class"); //$NON-NLS-1$ //$NON-NLS-2$
        URL objectUrl = AccessController
                .doPrivileged(new PrivilegedAction<URL>() {
                    public URL run() {
                        if (loader == null)
                            return ClassLoader.getSystemResource(resourceName);
                        return loader.getResource(resourceName);
                    }
                });

        // If we can't get to the applet itself, the codebase and doc base are
        // left as null.
        if (objectUrl == null) {
            applet.setStub(getAppletStub(applet, getStubAppletContext(applet),
                    null, null));
            return;
        }

        // Try to decompose the resource URL to get to the doc/code URL
        String urlString = objectUrl.toExternalForm();

        // This is the URL of the directory that contains the applet.
        int codeURLlength = urlString.length() - resourceName.length();
        URL codeBase = safeURL(urlString.substring(0, codeURLlength));

        // URL of the document containing the applet.
        int docURLlength = urlString.lastIndexOf('/');
        URL docBase = safeURL(urlString.substring(0, docURLlength + 1));

        applet.setStub(getAppletStub(applet, getStubAppletContext(applet),
                codeBase, docBase));
    }
    
    
    private static AppletStub getAppletStub(final Applet target,
            final AppletContext context, final URL codeBase, final URL docBase) {

        return new AppletStub() {
            public boolean isActive() {
                return true;
            }

            public URL getDocumentBase() {
                return docBase;
            }

            public URL getCodeBase() {
                return codeBase;
            }

            public String getParameter(String name) {
                // Applet beans have no params.
                return null;
            }

            public AppletContext getAppletContext() {
                return context;
            }

            public void appletResize(int width, int height) {
                // Do nothing.
            }
        };
    }

    private static AppletContext getStubAppletContext(final Applet target) {
        return new AppletContext() {
            public AudioClip getAudioClip(URL url) {
                return null;
            }

            public synchronized Image getImage(URL url) {
                return null;
            }

            public Applet getApplet(String name) {
                return null;
            }

            public Enumeration<Applet> getApplets() {
                Vector<Applet> applets = new Vector<Applet>();
                applets.addElement(target);
                return applets.elements();
            }

            public void showDocument(URL url) {
                // Do nothing.
            }

            public void showDocument(URL url, String aTarget) {
                // Do nothing.
            }

            public void showStatus(String status) {
                // Do nothing.
            }

            public void setStream(String key, InputStream stream)
                    throws IOException {
                // Do nothing.
            }

            public InputStream getStream(String key) {
                return null;
            }

            public Iterator<String> getStreamKeys() {
                return null;
            }
        };
    }
    

	//  Maps malformed URL exception to ClassNotFoundException
	private static URL safeURL(String urlString) throws ClassNotFoundException {
		try {
			return new URL(urlString);
		} catch (MalformedURLException exception) {
			throw new ClassNotFoundException(exception.getMessage());
		}
	}

}
