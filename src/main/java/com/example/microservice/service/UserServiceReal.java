package com.example.microservice.service;

import com.example.microservice.store.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceReal implements UserService{
    //Здесь пока хранятся пользователи
    private static final Map<Integer, User> userRepositoryMap = new HashMap<>();
    //Переменная для увеличения айди
    private static final AtomicInteger clientIdHolder = new AtomicInteger();
    @Override
    public void create(User user) {
        final int userId = clientIdHolder.incrementAndGet();
        user.setId(userId);
        userRepositoryMap.put(userId,user);
    }

    @Override
    public List<User> readAll() {
        return new ArrayList<>(userRepositoryMap.values());
    }

    @Override
    public User read(int user_id) {
        return userRepositoryMap.get(user_id);
    }

    @Override
    public boolean update(User user, int user_id) {
        if (userRepositoryMap.containsKey(user_id)) {
            user.setId(user_id);
            userRepositoryMap.put(user_id,user);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return userRepositoryMap.remove(id)!=null;
    }
}
