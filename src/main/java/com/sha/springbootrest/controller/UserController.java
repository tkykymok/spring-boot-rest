package com.sha.springbootrest.controller;

import com.sha.springbootrest.model.UserDto;
import com.sha.springbootrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    // Example: POST http://localhost:8080/api/user
    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid UserDto user) {

        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.saveUser(user.convertToUser());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
