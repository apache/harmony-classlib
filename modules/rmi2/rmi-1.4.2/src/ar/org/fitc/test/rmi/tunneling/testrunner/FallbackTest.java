package ar.org.fitc.test.rmi.tunneling.testrunner;

import java.io.IOException;
import java.net.*;

public class FallbackTest {
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("http://10.100.2.246:1099");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        Proxy prox = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("90.0.0.1", 3128));

        try {
            url.openConnection(prox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

