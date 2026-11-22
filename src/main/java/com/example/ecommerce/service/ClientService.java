package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.ecommerce.models.Client;
import com.example.ecommerce.repository.CilentRpository;


@Service 
public class ClientService {

    @Autowired 
    private CilentRpository clientRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(String id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }







}
