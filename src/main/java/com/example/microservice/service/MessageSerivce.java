package com.example.microservice.service;

import com.example.microservice.store.entity.Message;

import java.util.List;

public interface MessageSerivce {
    void create(Message message);

    List<Message> readAll();
    Message read(int message_id);
    boolean update(Message message, int message_id);
    boolean delete(int id);
}
