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
 * @author Aleksei V. Ivaschenko 
 * @version $Revision: 1.3 $ 
 */ 

package javax.print;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.print.attribute.AttributeSet;

public abstract class PrintServiceLookup {

    private static ArrayList services = null;
    private static ArrayList providers = null;

    static {
        services = new ArrayList();
        providers = new ArrayList();

        Enumeration providersEnum = null;
        ClassLoader classLoader = null;
        InputStream is = null;

        classLoader = (ClassLoader) AccessController
                .doPrivileged(new PrivilegedAction() {
                    public Object run() {
                        ClassLoader classLoader = Thread.currentThread()
                                .getContextClassLoader();
                        return (classLoader == null) ? ClassLoader
                                .getSystemClassLoader() : classLoader;
                    }
                });

        if (classLoader != null) {
            try {
                providersEnum = classLoader
                        .getResources("META-INF/services/javax.print.PrintServiceLookup");
            } catch (IOException ioe1) {
                ioe1.printStackTrace();
            }

            while (providersEnum.hasMoreElements()) {
                URL url = (URL) providersEnum.nextElement();
                try {
                    is = url.openStream();

                    BufferedReader providerNameReader;
                    String name = null;

                    try {
                        providerNameReader = new BufferedReader(
                                new InputStreamReader(is, "UTF-8"));
                    } catch (UnsupportedEncodingException uee) {
                        providerNameReader = new BufferedReader(
                                new InputStreamReader(is));
                    }

                    if (providerNameReader != null) {
                        try {
                            name = providerNameReader.readLine();
                            while (name != null) {
                                if (name.length() > 0 && name.charAt(0) != '#') {
                                    final Class providerClass = Class
                                            .forName(name);

                                    Object provider = AccessController
                                            .doPrivileged(new PrivilegedAction() {
                                                public Object run() {
                                                    try {
                                                        Object inst = providerClass
                                                                .newInstance();
                                                        return inst;
                                                    } catch (InstantiationException ie) {
                                                        System.err
                                                                .println("Can't instantiate class "
                                                                        + providerClass
                                                                                .getName()
                                                                        + ": "
                                                                        + ie);
                                                    } catch (IllegalAccessException iae) {
                                                        System.out
                                                                .println("Illegal access for class "
                                                                        + providerClass
                                                                                .getName()
                                                                        + ": "
                                                                        + iae);
                                                    }
                                                    return null;
                                                }
                                            });

                                    if (provider != null) {
                                        registerServiceProvider((PrintServiceLookup) provider);
                                    }
                                }
                                name = providerNameReader.readLine();
                            }
                        } catch (IOException ioe) {
                            System.out
                                    .println("IOException during reading file:"
                                            + ioe);
                        } catch (ClassNotFoundException cnfe) {
                            System.out.println("Class" + name
                                    + " is not found:" + cnfe);
                        }
                    }
                } catch (IOException ioe2) {
                    ioe2.printStackTrace();
                }
            }
        }
    }

    public PrintServiceLookup() {
    }

    public abstract PrintService getDefaultPrintService();

    public abstract PrintService[] getPrintServices();

    public abstract PrintService[] getPrintServices(DocFlavor flavor,
            AttributeSet attributes);

    public abstract MultiDocPrintService[] getMultiDocPrintServices(
            DocFlavor[] flavors, AttributeSet attributes);

    public static final PrintService lookupDefaultPrintService() {
        if (providers.size() > 0) {
            PrintServiceLookup provider = (PrintServiceLookup) providers.get(0);
            return provider.getDefaultPrintService();
        }
        return null;
    }

    public static final PrintService[] lookupPrintServices(DocFlavor flavor,
            AttributeSet attributes) {
        ArrayList printServices = new ArrayList();
        for (int i = 0; i < providers.size(); i++) {
            PrintServiceLookup provider = (PrintServiceLookup) providers.get(i);
            PrintService[] providerServices = provider.getPrintServices(flavor,
                    attributes);
            for (int j = 0; j < providerServices.length; j++) {
                printServices.add(providerServices[j]);
            }
        }
        return (printServices.size() == 0 ? new PrintService[0]
                : (PrintService[]) printServices.toArray(new PrintService[0]));
    }

    public static final MultiDocPrintService[] lookupMultiDocPrintServices(
            DocFlavor[] flavors, AttributeSet attributes) {
        ArrayList printServices = new ArrayList();
        for (int i = 0; i < providers.size(); i++) {
            PrintServiceLookup provider = (PrintServiceLookup) providers.get(i);
            MultiDocPrintService[] providerServices = provider
                    .getMultiDocPrintServices(flavors, attributes);
            if (providerServices != null) {
                for (int j = 0; j < providerServices.length; j++) {
                    printServices.add(providerServices[j]);
                }
            }
        }
        return (printServices.size() == 0 ? new MultiDocPrintService[0]
                : (MultiDocPrintService[]) printServices
                        .toArray(new MultiDocPrintService[0]));
    }

    public static boolean registerService(PrintService service) {
        return services.add(service);
    }

    public static boolean registerServiceProvider(PrintServiceLookup provider) {
        return providers.add(provider);
    }
}