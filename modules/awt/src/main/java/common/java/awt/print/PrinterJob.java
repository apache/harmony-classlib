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

package java.awt.print;

import java.awt.AWTError;
import java.awt.HeadlessException;
import java.security.AccessController;
import java.security.PrivilegedAction;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.StreamPrintServiceFactory;
import javax.print.attribute.PrintRequestAttributeSet;

public abstract class PrinterJob {

    /* abstract section */
    public abstract void cancel();

    public abstract void setPrintable(Printable painter);

    public abstract void setPrintable(Printable painter, PageFormat format);

    public abstract void setPageable(Pageable document)
            throws NullPointerException;

    public abstract void print() throws PrinterException;

    public abstract void setJobName(String jobName);

    public abstract void setCopies(int copies);

    public abstract int getCopies();

    public abstract boolean printDialog() throws HeadlessException;

    public abstract boolean isCancelled();

    public abstract String getUserName();

    public abstract String getJobName();

    public abstract PageFormat pageDialog(PageFormat page)
            throws HeadlessException;

    public abstract PageFormat defaultPage(PageFormat page);

    public abstract PageFormat validatePage(PageFormat page);

    /* static section */
    public static PrinterJob getPrinterJob(){

        SecurityManager securitymanager = System.getSecurityManager();
        if(securitymanager != null) {
            securitymanager.checkPrintJobAccess();
        }
        /* This code has been developed according to API documentation
         * for Priviledged Blocks. 
         */
        return AccessController.doPrivileged(
                new PrivilegedAction<PrinterJob>() {
            public PrinterJob run() {
                String s = System.getProperty("java.awt.printerjob");

                if (s == null || s.equals("")){
                    s = "org.apache.harmony.x.print.awt.PSPrinterJob";
                }
                try {
                    return (PrinterJob) Class.forName(s).newInstance();
                } catch (ClassNotFoundException cnfe) {
                    throw new AWTError(
                            "Default class for PrinterJob is not found");
                } catch (IllegalAccessException iae) {
                    throw new AWTError(
                            "No access to default class for PrinterJob");
                } catch (InstantiationException ie) {
                    throw new AWTError(
                            "Instantiation exception for PrinterJob");
                }
            }
        });
    }


    public static PrintService[] lookupPrintServices(){
       return PrintServiceLookup.lookupPrintServices(
           javax.print.DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
    }

    public static StreamPrintServiceFactory[] lookupStreamPrintServices(
            String mimeType) {
        return StreamPrintServiceFactory.lookupStreamPrintServiceFactories(
                javax.print.DocFlavor.SERVICE_FORMATTED.PAGEABLE, mimeType);
    }

    /* public section*/
    public PrinterJob() {
        super();
     }

     public PageFormat defaultPage(){
        return defaultPage(new PageFormat());
     }

    public PrintService getPrintService(){
        return null;
    }

    public void print(PrintRequestAttributeSet attributes)
            throws PrinterException {
        // This implementation ignores the attribute set.
        print();
    }

    public boolean printDialog(PrintRequestAttributeSet attributes)
            throws HeadlessException {
        if (attributes == null) {
            throw new NullPointerException(
                    "The parameter 'attributes' is null");
        }
        //This implementation ignores the attribute set.
        return printDialog();
    }

    public void setPrintService(PrintService printservice)
            throws PrinterException {
        throw new PrinterException(printservice.toString()
                    + "is not supported");
    }

    public PageFormat pageDialog(PrintRequestAttributeSet attributes)
        throws HeadlessException {
        //This implementation ignores the attribute set.
        if(attributes == null) {
            throw new NullPointerException(
                    "The parameter 'attributes' is null");
        }
        return pageDialog(defaultPage());
    }

}
