package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.models.Client;

import com.example.ecommerce.service.ClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/clients")
public class ClientController {


    @Autowired 
    private ClientService clientService;

    @PostMapping("add")
    public String addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return "Client added successfully";
    }

    @GetMapping("/all")
    public List<Client> getAllClients() {
        // Logic to retrieve all clients
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable String id) {
        return clientService.getClientById(id);
    }
    
    
    



}
