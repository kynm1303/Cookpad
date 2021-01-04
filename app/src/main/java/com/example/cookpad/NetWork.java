package com.example.cookpad;

public class NetWork {
    private static NetWork obj;
    private String SERVER ;

    private NetWork(){
        SERVER="192.168.1.9:8000";
    }
    public static NetWork getNetworkInfoHolder(){
        if(obj==null){
            synchronized (AccountInfo.class){
                if(obj==null){
                    obj=new NetWork();
                }
            }
        }
        return obj;
    }
    public String getSERVER(){
        return SERVER;
    }
}