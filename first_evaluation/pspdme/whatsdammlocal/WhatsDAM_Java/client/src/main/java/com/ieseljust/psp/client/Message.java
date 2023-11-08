package com.ieseljust.psp.client;

import org.json.JSONObject;

public class Message {

    private String user;
    private String content;
    

    public Message(String user, String content){
        this.user = user;
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user=user;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content=content;
    }

    @Override
    public String toString() {
        return user + " " + content;
    }

    public JSONObject toJSON(){
        JSONObject jso=new JSONObject();
        jso.put("user", user);
        jso.put("content", content);
        return jso;

    }
    
    public JSONObject toJSONCommand(){
        JSONObject jso=new JSONObject();
        jso.put("command", "newMessage");
        jso.put("user", user);
        jso.put("content", content);
        return jso;

    }

}
