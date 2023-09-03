package com.example.microservice.controller;

import com.example.microservice.store.entity.Message;
import com.example.microservice.service.MessageSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageSerivce messageSerivce;

    @Autowired
    public MessageController(MessageSerivce messageSerivce){
        this.messageSerivce = messageSerivce;
    }

    //Создание пользователя
    //ResponseEnity<?> класс для возврата ответа, с помощью которого пользователю возвращается статус код
    //@RequesBody подставляется из тела запроса
    @PostMapping(value = "/messages")
    public ResponseEntity<?> create(@RequestBody Message message){
        messageSerivce.create(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Получение списка пользователей
    //Возвращается не только статус, но и тело ответа
    @GetMapping(value = "/messages")
    public ResponseEntity<List<Message>> read(){
        final List<Message> messages = messageSerivce.readAll();
        return messages!=null && !messages.isEmpty()
                ? new ResponseEntity<>(messages,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Получение пользователя по айди
    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<Message> read(@PathVariable(name = "id") int id){
        final Message message = messageSerivce.read(id);
        return message!=null
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Обновление пользователя
    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Message message){
        final boolean updated = messageSerivce.update(message, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //Удаление пользователя
    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean deleted = messageSerivce.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
