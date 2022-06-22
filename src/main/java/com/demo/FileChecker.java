package com.demo;

public class FileChecker {
    public static String getFileName(String path){
        String ret = "";
        if(!path.contains("..")){
            if(path.startsWith("/")){
                ret = path.substring(1);
            }else{
                ret = path;
            }
        }
        return ret;
    }
}
