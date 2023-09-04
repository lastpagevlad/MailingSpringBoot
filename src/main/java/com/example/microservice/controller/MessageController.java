package com.example.microservice.controller;

import com.example.microservice.store.entity.Message;
import com.example.microservice.service.MessageSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Добавление сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Сообщение добавлено"),
            @ApiResponse(responseCode = "401",
                    description = "В случае если запрос отправлен без токена или с недействительным " +
                            "токеном возвращается ошибка 401 и строка с ошибкой в теле запроса.",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(value = "/messages")
    public ResponseEntity<?> create(@RequestBody Message message){
        messageSerivce.create(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Отображение сообщений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Сообщения отображены"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс." +
                            " Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(value = "/messages")
    public ResponseEntity<List<Message>> read(){
        final List<Message> messages = messageSerivce.readAll();
        return messages!=null && !messages.isEmpty()
                ? new ResponseEntity<>(messages,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(summary = "Отображение сообщений по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Сообщение отображено отображен"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс." +
                            " Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<Message> read(@PathVariable(name = "id") int id){
        final Message message = messageSerivce.read(id);
        return message!=null
                ? new ResponseEntity<>(message, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Обновление сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Сообщение обновлено"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс. " +
                            "Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Message message){
        final boolean updated = messageSerivce.update(message, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Сообщение удалено"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс. " +
                            "Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean deleted = messageSerivce.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
