package tests.support;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.HttpConnection;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.handler.ResourceHandler;

public class Support_Jetty {
    public static Server DEFAULT_SERVER = null;
    
    public static int DEFAULT_PORT = 0;
    
    public static Server SERVER = null;
    
    public static int PORT = 0;
    
    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    stopDefaultServer();
                    stopServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static class HYDefaultHandler extends AbstractHandler
    {
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException
        {
            Request base_request = (request instanceof Request) ? (Request)request:HttpConnection.getCurrentConnection().getRequest();
            base_request.setHandled(true);   
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello OneHandler</h1>");
        }
    }
    
    public static int startDefaultHttpServer() throws Exception{
        if (DEFAULT_SERVER != null){
            return DEFAULT_PORT;
        }
        DEFAULT_PORT = Support_PortManager.getNextPortForUDP();
        DEFAULT_SERVER = new Server(DEFAULT_PORT);
        DEFAULT_SERVER.setHandler(new HYDefaultHandler());
        DEFAULT_SERVER.start();
        return DEFAULT_PORT;
    }

    public static int startHttpServerWithDocRoot(String root)
            throws Exception {
        if (SERVER != null) {
            SERVER.stop();
            SERVER = null;
        }
        PORT = Support_PortManager.getNextPortForUDP();
        SERVER = new Server(PORT);
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase(root);
        SERVER.setHandler(resource_handler);
        SERVER.start();
        return PORT;
    }

    private static void stopDefaultServer() throws Exception {
        if (DEFAULT_SERVER != null) {
            DEFAULT_SERVER.stop();
            DEFAULT_SERVER = null;
        }
    }
    
    private static void stopServer() throws Exception {
        if (SERVER != null) {
            SERVER.stop();
            SERVER = null;
        }
    }
}
