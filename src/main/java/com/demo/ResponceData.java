package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResponceData {

    private final InputStream in;
    private final OutputStream out;
    public ResponceData(InputStream in, OutputStream out){
        this.in = in;
        this.out = out; 
    }

    public void responce() throws IOException{
        RequestData req = RequestParser.parseData(in);
        System.out.println(req.method);
        byte[] buf = new byte[4096];
        int len = in.read(buf);
        
        out.write(buf, 0, len);
    }
}
