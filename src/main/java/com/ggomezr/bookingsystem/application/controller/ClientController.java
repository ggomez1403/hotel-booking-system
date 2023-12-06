package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.ClientService;
import com.ggomezr.bookingsystem.domain.entity.Client;
import com.ggomezr.bookingsystem.domain.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/users")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/users/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) throws ClientNotFoundException {
        return clientService.
                getClientById(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }

    @PutMapping("/users/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody Client client) throws ClientNotFoundException {
        client.setId(id);
        clientService.updateClient(client);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws ClientNotFoundException{
        clientService.deleteClient(id);
    }
}
