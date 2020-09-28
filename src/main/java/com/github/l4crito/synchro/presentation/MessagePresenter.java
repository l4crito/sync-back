package com.github.l4crito.synchro.presentation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagePresenter {
    private String from;
    private String to;
    private String message;
}
