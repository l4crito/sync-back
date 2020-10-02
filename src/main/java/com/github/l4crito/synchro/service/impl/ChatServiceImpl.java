package com.github.l4crito.synchro.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.l4crito.synchro.controller.WebSocketController;
import com.github.l4crito.synchro.presentation.MessagePresenter;
import com.github.l4crito.synchro.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    WebSocketController webSocketController;

    @Override
    public void sendMessage(MessagePresenter messagePresenter) throws JsonProcessingException {
        webSocketController.sendMessage(messagePresenter);
    }
}
