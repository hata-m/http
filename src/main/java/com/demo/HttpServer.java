package com.demo;

import java.io.*;
import java.net.*;

public class HttpServer{

    private int portNumber;

    public HttpServer(int portNumber){
        this.portNumber = portNumber;
    }

    public void start() throws IOException{
        try (ServerSocket socket = new ServerSocket()) {
            socket.bind(new InetSocketAddress("0.0.0.0", portNumber));


            while (true){
                Socket acccepted = socket.accept();

                new Thread() {

                    @Override
                    public void run(){
                        try {
                            try {
                                try (
                                    InputStream in = acccepted.getInputStream();
                                    OutputStream out = acccepted.getOutputStream()
                                    ) {

                                    ResponceData responceData = new ResponceData(in, out);
                                    responceData.responce();
                                } 
                            } finally {
                                acccepted.close();
                            }
                        } catch (IOException e){
                            
                        }
                    }
                }.start();  
            }
        }
    }

}