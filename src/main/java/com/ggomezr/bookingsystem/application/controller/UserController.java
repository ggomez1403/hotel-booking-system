package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.UserService;
import com.ggomezr.bookingsystem.domain.dto.UserDto;
import com.ggomezr.bookingsystem.domain.entity.User;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public record UserController(UserService userService) {
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) throws UserNotFoundException {
        return userService.
                getUserById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user) throws UserNotFoundException {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
