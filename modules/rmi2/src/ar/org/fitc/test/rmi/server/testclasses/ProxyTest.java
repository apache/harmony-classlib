package ar.org.fitc.test.rmi.server.testclasses;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class ProxyTest {
    public static void main(String[] args) {
        String host = "10.100.2.246";
        String port = "1099";
        int proxyPort = 3128;
        InetAddress proxyHost = null;
        URL conn = null;
        URLConnection uconn = null;

        try {

            proxyHost = InetAddress.getByName("saturno.fitc.org.ar");
            conn = new URL("http://+" + host + ":" + port);
            Proxy p = new Proxy(Proxy.Type.valueOf("HTTP"),
                    new InetSocketAddress(proxyHost, proxyPort));
            
            uconn = conn.openConnection(p);
            uconn.connect();
            
            System.out.println(uconn.getHeaderField("head"));
            System.out.println();
            uconn.setDoOutput(true);
                    
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
