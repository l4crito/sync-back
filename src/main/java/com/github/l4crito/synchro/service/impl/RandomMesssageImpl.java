package com.github.l4crito.synchro.service.impl;

import com.github.l4crito.synchro.controller.WebSocketController;
import com.github.l4crito.synchro.service.RandomMesssage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomMesssageImpl implements RandomMesssage {
    @Autowired
    WebSocketController webSocketController;

    @Override
    public void sendRandomMessage() {
        webSocketController.sendMessage("holiii");
    }
}
