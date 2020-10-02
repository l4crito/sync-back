package com.github.l4crito.synchro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.l4crito.synchro.presentation.MessagePresenter;

public interface ChatService {
    void sendMessage(MessagePresenter messagePresenter) throws JsonProcessingException;
}
