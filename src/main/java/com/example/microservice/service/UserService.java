package com.example.microservice.service;

import com.example.microservice.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    List<User> readAll();
    User read(int user_id);
    boolean update(User user, int user_id);
    boolean delete(int id);
}
