package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Client;
import com.ggomezr.bookingsystem.domain.exceptions.ClientNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record ClientService(ClientRepository clientRepository){

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) throws ClientNotFoundException {
        return Optional.ofNullable(clientRepository.findById(id).orElseThrow(ClientNotFoundException::new));
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }

    public void updateClient(Client client) throws ClientNotFoundException {
        Client existingClient = clientRepository.findById(client.getId()).orElseThrow(ClientNotFoundException::new);

        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setEmail(client.getEmail());

        clientRepository.save(existingClient);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }

}
