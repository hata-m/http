package com.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
 
public class RequestParser {
    private static String CRLF = "\r\n";

    public static RequestData parseData(InputStream in) throws IOException{
        RequestData req = new RequestData();
        byte[] buf = new byte[4096];
        in.read(buf);

        String data = new String(buf, "UTF-8");
        String[] arrays = data.split(CRLF);
        List<String> lists = Arrays.asList(arrays);
        getHead(req, lists.get(0));

        for (String list: lists){
            if(list.contains("keep-alive")){
                req.keep_alive = list;
            }else if(list.contains("HOST")){
                req.host = list;
            }else{
                //残りのメソッド
            }   
        }
        
        return req;
    }

    private static void getHead(RequestData req, String head){
        String[] array = head.split(" ");
        if(array.length == 3){
            req.method = array[0];
            req.path = array[1];
            req.http_ver = array[2];  
        }else{
            req = null;
        }
    }
}
