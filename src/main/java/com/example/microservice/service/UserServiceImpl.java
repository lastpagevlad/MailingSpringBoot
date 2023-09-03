package com.example.microservice.service;

import com.example.microservice.store.entity.User;
import com.example.microservice.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User user) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User read(int userId) {
        return null;
    }

    @Override
    public boolean update(User user, int userId) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
