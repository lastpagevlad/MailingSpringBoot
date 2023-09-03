package com.example.microservice.store.repository;

import com.example.microservice.store.entity.Message;
import com.example.microservice.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByName(Integer id);
}
