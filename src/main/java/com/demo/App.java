package com.demo;

import java.io.IOException;
/**
 * Hello world!
 *
 */
public class App 
{
    
    public static void main( String[] args )
    {
        //TODO 引数でポート番号を与える
        HttpServer echoServer   = new HttpServer(12345);
        try{
            echoServer.start();
        }catch(IOException e){
            System.err.println("an error occured");
        }

    }


}
