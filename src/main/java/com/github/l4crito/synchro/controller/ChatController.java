package com.github.l4crito.synchro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.l4crito.synchro.presentation.ChatPresenter;
import com.github.l4crito.synchro.presentation.MessagePresenter;
import com.github.l4crito.synchro.presentation.MessageType;
import com.github.l4crito.synchro.presentation.UserPresenter;
import com.github.l4crito.synchro.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController()
@RequestMapping("/chat")
@CrossOrigin()
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping(value = "/send")
    void sendMessage(@RequestBody ChatPresenter chatPresenter) throws JsonProcessingException {
        MessagePresenter messagePresenter=MessagePresenter.builder()
                .message(chatPresenter)
                .type(MessageType.CHAT)
                .build();
        chatService.sendMessage(messagePresenter);
    }

}
