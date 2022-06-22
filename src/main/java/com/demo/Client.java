package com.demo;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        HttpClient echoClient = new HttpClient(12345);
        try{
            echoClient.start();
        }catch(IOException e){
            System.err.println("an error occured");
        }
    }
}
