package com.nisum.examen.controller;

import com.nisum.examen.model.dto.UserDto;
import com.nisum.examen.model.request.UserRequest;
import com.nisum.examen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("Users")
    public ResponseEntity<UserDto> saveUser(UserRequest user){
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
}
