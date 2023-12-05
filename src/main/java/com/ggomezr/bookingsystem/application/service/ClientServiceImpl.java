package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    private Map<Long, Client> users = new HashMap<>();

    @Override
    public List<Client> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Client getUserById(Long id) {
        return users.get(id);
    }

    @Override
    public void createUser(Client client) {
        users.put(client.getId(), client);
    }

    @Override
    public void updateUser(Client client) {
        users.put(client.getId(), client);
    }

    @Override
    public void deleteUser(Long id) {
        users.remove(id);
    }

}
