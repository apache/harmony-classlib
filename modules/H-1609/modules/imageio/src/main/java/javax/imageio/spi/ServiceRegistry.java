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
 * @author Rustem V. Rafikov
 * @version $Revision: 1.3 $
 */
package javax.imageio.spi;

import java.util.*;

/**
 * TODO: add all the methods from the spec
 */
public class ServiceRegistry {

    CategoriesMap categories = new CategoriesMap(this);

    public ServiceRegistry(Iterator categoriesIterator) {
        if (null == categoriesIterator) {
            throw new IllegalArgumentException("categories iterator should not be NULL");
        }
        while(categoriesIterator.hasNext()) {
            Class c =  (Class) categoriesIterator.next();
            categories.addCategory(c);
        }
    }

    public static Iterator lookupProviders(Class providerClass, ClassLoader loader) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public static Iterator lookupProviders(Class providerClass) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public boolean registerServiceProvider(Object provider, Class category) {
        return categories.addProvider(provider, category);
    }

    public void registerServiceProviders(Iterator providers) {
        for (Iterator iterator = providers; iterator.hasNext();) {
            categories.addProvider(iterator.next(), null);
        }
    }

    public void registerServiceProvider(Object provider) {
        categories.addProvider(provider, null);
    }

    public boolean deregisterServiceProvider(Object provider, Class category) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void deregisterServiceProvider(Object provider) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public Iterator getServiceProviders(Class category, Filter filter, boolean useOrdering) {
        return new FilteredIterator(filter, categories.getProviders(category, useOrdering));
    }

    public Iterator getServiceProviders(Class category, boolean useOrdering) {
        return categories.getProviders(category, useOrdering);
    }

    public Object getServiceProviderByClass(Class providerClass) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public boolean setOrdering(Class category, Object firstProvider, Object secondProvider) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public boolean unsetOrdering(Class category, Object firstProvider, Object secondProvider) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void deregisterAll(Class category) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void deregisterAll() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public void finalize() throws Throwable {
        //-- TODO
    }

    public boolean contains(Object provider) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public Iterator getCategories() {
        return categories.list();
    }

    public static interface Filter {
        boolean filter(Object provider);
    }

    private static class CategoriesMap {
        //-- <category class> -> ProvidersMap (Map)
        Map categories = new HashMap();

        ServiceRegistry registry;

        public CategoriesMap(ServiceRegistry registry) {
            this.registry = registry;
        }

        //-- TODO: useOrdering
        Iterator getProviders(Class category, boolean useOrdering) {
            ProvidersMap providers = (ProvidersMap) categories.get(category);
            if (null == providers) {
                throw new IllegalArgumentException("Unknown category: " + category);
            }
            return providers.getProviders(useOrdering);
        }

        Iterator list() {
            return categories.keySet().iterator();
        }

        void addCategory(Class category) {
            categories.put(category, new ProvidersMap());
        }

        /**
         * Adds a provider to the category. If <code>category</code> is
         * <code>null</code> then the provider will be added to all categories
         * which the provider is assignable from.
         * @param provider provider to add
         * @param category category to add provider to
         * @return if there were such provider in some category
         */
        boolean addProvider(Object provider, Class category) {
            if (provider == null) {
                throw new IllegalArgumentException("provider should be != NULL");
            }

            boolean rt;
            if (category == null) {
                rt = findAndAdd(provider);
            } else {
                rt  = addToNamed(provider, category);
            }

            if (provider instanceof RegisterableService) {
                ((RegisterableService) provider).onRegistration(registry, category);
            }

            return rt;
        }

        private boolean addToNamed(Object provider, Class category) {
            Object obj = categories.get(category);

            if (null == obj) {
                throw new IllegalArgumentException("Unknown category: " + category);
            }

            return ((ProvidersMap) obj).addProvider(provider);
        }

        private boolean findAndAdd(Object provider) {
            boolean rt = false;
            for (Iterator iterator = categories.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry e = (Map.Entry) iterator.next();
                if (((Class)e.getKey()).isAssignableFrom(provider.getClass())) {
                    rt |= ((ProvidersMap) e.getValue()).addProvider(provider);
                }
            }
            return rt;
        }
    }

    private static class ProvidersMap {
        //-- TODO: providers ordering support

        Map providers = new HashMap();

        boolean addProvider(Object provider) {
            return providers.put(provider.getClass(), provider) != null;
        }

        Iterator getProviderClasses() {
            return providers.keySet().iterator();
        }

        //-- TODO ordering
        Iterator getProviders(boolean userOrdering) {
            return providers.values().iterator();
        }
    }

    private static class FilteredIterator implements Iterator {

        private Filter filter;
        private Iterator backend;
        private Object nextObj;

        public FilteredIterator(Filter filter, Iterator backend) {
            this.filter = filter;
            this.backend = backend;
            findNext();
        }

        public Object next() {
            if (nextObj == null) {
                throw new NoSuchElementException();
            }
            Object tmp = nextObj;
            findNext();
            return tmp;
        }

        public boolean hasNext() {
            return nextObj != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("???");
        }

        /**
         * Sets nextObj to a next provider matching the criterion given by the filter
         */
        private void findNext() {
            nextObj = null;
            while (backend.hasNext()) {
                Object o = backend.next();
                if (filter.filter(o)) {
                    nextObj = o;
                    return;
                }
            }
        }
    }
}
