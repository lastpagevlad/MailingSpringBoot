package com.example.microservice.service;

import com.example.microservice.store.entity.Message;
import com.example.microservice.store.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
public class MessageServiceImpl implements MessageSerivce{
    private final MessageRepository messageRepository;

    @Override
    public void create(Message message) {

    }

    @Override
    public List<Message> readAll() {
        return null;
    }

    @Override
    public Message read(int message_id) {
        return null;
    }

    @Override
    public boolean update(Message message, int message_id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
