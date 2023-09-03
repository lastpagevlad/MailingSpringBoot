package com.example.microservice.model;

public class Message {
    private Integer message_id;
    private String messageSend;

    public Message(){}
    public Message(Integer message_id, String messageSend) {
        this.message_id = message_id;
        this.messageSend = messageSend;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public String getMessageSend() {
        return messageSend;
    }

    public void setMessageSend(String messageSend) {
        this.messageSend = messageSend;
    }
}
