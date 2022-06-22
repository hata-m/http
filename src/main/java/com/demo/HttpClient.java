package com.demo;

import java.io.*;
import java.net.*;


public class HttpClient{
    private int portNumber;

    public HttpClient(int portNumber){
        this.portNumber = portNumber;
    }

    public void start() throws IOException{
        Socket socket = new Socket("0.0.0.0", portNumber);
        byte[] buf = new byte[4096];
        OutputStream out = socket.getOutputStream();
        // InputStream in = socket.getInputStream();
        InputStream in = socket.getInputStream();
        byte[] data = "GET /test.txt HTTP/1.1\r\n Host".getBytes();
        out.write(data);

        int len = in.read(buf);
        // String test = new String(buf, "UTF-8");

        System.out.write(buf,0, len);

        socket.close();
    }

}

