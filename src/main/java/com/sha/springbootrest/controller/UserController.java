package com.sha.springbootrest.controller;

import com.sha.springbootrest.model.Role;
import com.sha.springbootrest.model.User;
import com.sha.springbootrest.model.UserDto;
import com.sha.springbootrest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

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

    // GET http://localhost:8080/api/user/login -> It should be same with security login path like described before.
    // This is used for logout path also. After logout -> Spring will redirect it to login path.
    @GetMapping("login")
    public ResponseEntity<?> login(HttpServletRequest request) {

        // Authentication info will be stored on request by Spring Security
        Principal principal = request.getUserPrincipal();
        if (principal == null || principal.getName() == null) {
            // Here is will be logout redirection also so consider it as OK. httpStatus -> no Error.
            return new ResponseEntity<>(HttpStatus.OK);
        }
        User user = userService.findByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // PUT http://localhost:8080/api/user/{username}/change/{role} -> It should be same with security login path like described before.
    @PutMapping("{username}/change/{role}") // This can be also POST, PATCH
    public ResponseEntity<?> changeRole(@PathVariable String username, @PathVariable Role role) {
        User user = userService.changeRole(role, username);
        return ResponseEntity.ok(user);
    }

}
