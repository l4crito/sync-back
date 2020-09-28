package com.github.l4crito.synchro.presentation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize
@JsonDeserialize
public class UserPresenter {
    private String name;
    private String photo;
    private Date lastUpdate;

}
