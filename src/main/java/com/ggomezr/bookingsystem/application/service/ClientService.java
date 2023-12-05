package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllUsers();

    Client getUserById(Long id);

    void createUser(Client client);

    void updateUser(Client client);

    void deleteUser(Long id);
}
