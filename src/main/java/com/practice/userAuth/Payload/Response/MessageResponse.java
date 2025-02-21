package com.practice.userAuth.Payload.Response;

public class MessageResponse {

    public String messsage;

    public MessageResponse( String messsage){
        this.messsage = messsage;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
