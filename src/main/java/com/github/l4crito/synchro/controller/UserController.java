package com.github.l4crito.synchro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.l4crito.synchro.presentation.MessagePresenter;
import com.github.l4crito.synchro.presentation.MessageType;
import com.github.l4crito.synchro.presentation.UserPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/user")
@CrossOrigin()
public class UserController {
    @Autowired
    WebSocketController webSocketController;

    private static Set<UserPresenter> users = new HashSet<>();

    @PostMapping(value = "/join")
    Set<UserPresenter> logIn(@RequestBody UserPresenter userPresenter) throws  JsonProcessingException {
        UserPresenter exists =
                users.stream()
                        .filter(userPresenter1 -> userPresenter1.getName().equals(userPresenter.getName()))
                        .findFirst().orElse(new UserPresenter());
        if (exists.getName() != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Este usuario ya se encuentra en la sala");
        }
        userPresenter.setLastUpdate(new Date());
        users.add(userPresenter);
        sendCurrentUsers();
        return users;
    }

    @PostMapping(value = "/exit")
    Set<UserPresenter> logOut(@RequestBody UserPresenter userPresenter) throws JsonProcessingException {
        users.removeIf(userPresenter1 -> userPresenter.getName().equals(userPresenter1.getName()));
        sendCurrentUsers();
        return users;
    }

    @GetMapping(value = "/current")
    Set<UserPresenter> getCurrentUsers() {
        return users;
    }

    void sendCurrentUsers() throws JsonProcessingException {
        MessagePresenter messagePresenter = MessagePresenter
                .builder()
                .type(MessageType.USER)
                .message(users)
                .build();
        webSocketController.sendMessage(messagePresenter);
    }
}
