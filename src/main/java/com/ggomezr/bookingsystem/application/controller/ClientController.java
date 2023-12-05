package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.ClientService;
import com.ggomezr.bookingsystem.domain.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/users")
    public List<Client> getAllUsers() {
        return clientService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Client getUserById(@PathVariable Long id) {
        return clientService.getUserById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody Client client) {
        clientService.createUser(client);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody Client client) {
        clientService.updateUser(client);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        clientService.deleteUser(id);
    }
}
