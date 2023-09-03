package com.example.microservice.service;

import com.example.microservice.store.entity.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@Service
public class MessageSerivceReal implements MessageSerivce{

    private static final Map<Integer, Message> messageRepositoryMap = new HashMap<>();
    //Переменная для увеличения айди
    private static final AtomicInteger clientIdHolder = new AtomicInteger();
    @Override
    public void create(Message message) {
        final int messageId = clientIdHolder.incrementAndGet();
        message.setMessage_id(messageId);
        messageRepositoryMap.put(messageId,message);
    }

    @Override
    public List<Message> readAll() {
        return new ArrayList<>(messageRepositoryMap.values());
    }

    @Override
    public Message read(int message_id) {
        return messageRepositoryMap.get(message_id);
    }

    @Override
    public boolean update(Message message, int message_id) {
        if (messageRepositoryMap.containsKey(message_id)) {
            message.setMessage_id(message_id);
            messageRepositoryMap.put(message_id,message);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return messageRepositoryMap.remove(id)!=null;
    }
}
