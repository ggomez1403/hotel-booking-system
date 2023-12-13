package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.UserService;
import com.ggomezr.bookingsystem.domain.dto.UserDto;
import com.ggomezr.bookingsystem.domain.entity.User;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotAuthorizedException;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotFoundException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) throws UserNotFoundException {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
