package com.haobi.eventbusstickyeventdemo;

/**
 * Created by 15739 on 2019/3/25.
 */

public class MessageEvent {
    private String message;
    public MessageEvent(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
}
