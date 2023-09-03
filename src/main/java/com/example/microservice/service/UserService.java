package com.example.microservice.service;

import com.example.microservice.store.entity.User;

import java.util.List;

public interface UserService {
    void create(User user);
    List<User> readAll();
    User read(int userId);
    boolean update(User user, int userId);
    boolean delete(int id);
}
