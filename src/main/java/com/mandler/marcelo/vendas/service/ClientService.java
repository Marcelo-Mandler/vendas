package com.mandler.marcelo.vendas.service;

import com.mandler.marcelo.vendas.model.Client;
import com.mandler.marcelo.vendas.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    ClientRepository repository;
    @Autowired
    public ClientService (ClientRepository repository) {
        this.repository = repository;
    }
    public void saveClient(Client client) {
        validateClient(client);
        this.repository.persistir(client);
    }

    public void validateClient(Client client) {
        //aplica validações

    }
}
