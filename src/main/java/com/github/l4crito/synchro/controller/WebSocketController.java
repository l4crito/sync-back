package com.github.l4crito.synchro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.l4crito.synchro.presentation.MessagePresenter;
import com.github.l4crito.synchro.presentation.UserPresenter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebSocketController {

    private final SimpMessagingTemplate template;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    WebSocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send/messages")
    public void sendMessage(String message) {
        this.template.convertAndSend("/message", message);
    }

    @MessageMapping("/send/message")
    public void sendMessage(MessagePresenter messagePresenter) throws JsonProcessingException {
        this.template.convertAndSend("/message", messagePresenter);
    }
}