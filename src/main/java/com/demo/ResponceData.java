package com.demo;

import java.io.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ResponceData {

    private final InputStream in;
    private final OutputStream out;
    private final String CRLF = "\r\n";
    private final String URF8 = "UTF-8";
    
    private String sendError(){
        String header = "HTTP/1.1 ";
        header = header + "404 Not Found";
        header = header + CRLF;
        header = header + CRLF;
        System.out.println(header);
        return header;
    }
    private String sendHeader(){
        String header = "HTTP/1.1 ";
        header = header + "200 OK";
        header = header + CRLF;
        header = header + CRLF;
        return header;
    }

    public ResponceData(InputStream in, OutputStream out){
        this.in = in;
        this.out = out; 
    }

    public void responce() throws IOException{
        RequestData req = RequestParser.parseData(in);
        System.out.println(req.method);

        if(req.method.equals("GET")){    
            byte[] buf = new byte[1280];
            File file = new File(FileChecker.getFileName(req.path));
            // System.out.println("aaaaaa");
            System.out.println(file.getPath());
            if (file == null || !file.exists() || !file.isFile()) {
                String header = sendError();
                out.write(header.toString().getBytes(StandardCharsets.UTF_8));
                return;
            }else{
                String header = sendHeader();
                out.write(header.getBytes(URF8));
                try (FileInputStream fileIn = new FileInputStream(file)) {
                    while (true) {
                        int len = fileIn.read(buf);
                        if (len < 0) {
                            break;
                        } else if (len > 0) {
                            out.write(buf, 0, len);
                        }
                    }
                }
            }
        }else{
            // System.out.println("bbbbbb");
            String header = sendError();
            out.write(header.toString().getBytes(StandardCharsets.UTF_8));
            return;
        }
    }

}
