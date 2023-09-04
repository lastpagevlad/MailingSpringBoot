package com.example.microservice.controller;

import com.example.microservice.store.entity.User;
import com.example.microservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Управление пользователями (User)")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Добавление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Пользователь добавлен"),
            @ApiResponse(responseCode = "401",
                    description = "В случае если запрос отправлен без токена или с недействительным " +
                            "токеном возвращается ошибка 401 и строка с ошибкой в теле запроса.",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PostMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@RequestBody User user){
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Отображение пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Пользователи отображены"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс." +
                            " Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> read(){
        final List<User> users = userService.readAll();
        return users!=null && !users.isEmpty()
                ? new ResponseEntity<>(users,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(summary = "Отображение пользователя по идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Пользователь отображен"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс." +
                            " Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id){
        final User user = userService.read(id);
        return user!=null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Operation(summary = "Обновление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Пользователь обновлен"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс. " +
                            "Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user){
        final boolean updated = userService.update(user, id);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(summary = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Пользователь удален"),
            @ApiResponse(responseCode = "404",
                    description = "Сервер не может найти запрашиваемый ресурс. " +
                            "Код этого ответа, наверно, самый известный из-за частоты его появления в вебе. ",
                    content = @Content(schema = @Schema(implementation = String.class))
            )
    })
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean deleted = userService.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
