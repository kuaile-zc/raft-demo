package com.demo.raft.communication.http;

import com.demo.raft.communication.http.model.Request;
import com.demo.raft.communication.http.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author CoreyChen Zhang
 * @date 2021/8/5 19:06
 * @modified
 */
public class HttpRaftService {

    // shutdown command
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    // the shutdown command received
    private boolean shutdown = false;

    public void await() {
        ServerSocket serverSocket = null;
        int port = 9110;
        try {
            serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request object and parse
                Request request = new Request(input);
                request.parse();
                System.out.println(request.toString());

                // create Response object
                Response response = new Response(output);
                response.setRequest(request);

                // Close the socket
                socket.close();
                //check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

}
