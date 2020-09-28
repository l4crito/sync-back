package com.github.l4crito.synchro.controller;

import com.github.l4crito.synchro.presentation.UserPresenter;
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
    private static Set<UserPresenter> users = new HashSet<>();

    @PostMapping(value = "/join")
    Set<UserPresenter> logIn(@RequestBody UserPresenter userPresenter) throws ValidationException {
        UserPresenter exists =
                users.stream()
                        .filter(userPresenter1 -> userPresenter1.getName().equals(userPresenter.getName()))
                        .findFirst().orElse(new UserPresenter());
        if (exists.getName() != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Este usuario ya se encuentra en la sala");        }
        userPresenter.setLastUpdate(new Date());
        users.add(userPresenter);
        return users;
    }

    @PostMapping(value = "/exit")
    Set<UserPresenter> logOut(@RequestBody UserPresenter userPresenter) {
        users.removeIf(userPresenter1 -> userPresenter.getName().equals(userPresenter1.getName()));
        return users;
    }

    @GetMapping(value = "/current")
    Set<UserPresenter> getCurrentUsers() {
        return users;
    }
}
