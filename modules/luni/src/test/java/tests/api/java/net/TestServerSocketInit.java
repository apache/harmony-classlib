package tests.api.java.net;

import java.io.IOException;
import java.net.ServerSocket;

public class TestServerSocketInit {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.close();
    }
}
