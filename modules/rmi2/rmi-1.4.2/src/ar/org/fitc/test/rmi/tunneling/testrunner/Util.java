package ar.org.fitc.test.rmi.tunneling.testrunner;
import java.rmi.server.RMISocketFactory;

import sun.rmi.transport.proxy.RMIDirectSocketFactory;
import sun.rmi.transport.proxy.RMIHttpToCGISocketFactory;
import sun.rmi.transport.proxy.RMIHttpToPortSocketFactory;

public class Util {

    private Util() {
        
    }
    
    public static RMISocketFactory getSunSocketFactory(String type) {
        if (type.equalsIgnoreCase("default")) {
            System.out.println("No forced SocketFactory, enabled DefaultSocketFactory supporting Fallback mode");
            return new RMIDirectSocketFactory();
        } else if (type.equalsIgnoreCase("cgi")) {
            System.out.println("RMIToCGISocketFactory enabled...");
            return new RMIHttpToCGISocketFactory();    
        } else if (type.equalsIgnoreCase("http")) {
            System.out.println("RMIHttpToPortSocketFactory enabled...");
            return new RMIHttpToPortSocketFactory();
        } else if (type.equalsIgnoreCase("direct")) {
            System.out.println("RMIDirectSocketFactory enabled...");
            return new RMIDirectSocketFactory();
        }
        return new RMIDirectSocketFactory(); // if null returns default socket factory
    }

    public static void setITCSocketFactoryMode(String string) {
        if (string.equalsIgnoreCase("cgi")) {
            System.setProperty("ar.org.fitc.rmi.transport.proxy.httpToCgiOnly","true");
        } else if (string.equalsIgnoreCase("http")) {
            System.setProperty("ar.org.fitc.rmi.transport.proxy.httpToPortOnly","true");
        } // anything else should use the default socket factory
        
    }
    
}
