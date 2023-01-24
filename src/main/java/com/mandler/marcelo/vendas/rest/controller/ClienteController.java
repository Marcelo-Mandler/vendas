package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import com.mandler.marcelo.vendas.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping
    public List<Cliente> filterOrGetAllClient(Cliente filterOrGet) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filterOrGet, matcher);
        return clientesRepository.findAll(example);
    }

    @GetMapping("/{id}")
    public Cliente getClientById(@PathVariable Integer id) {
        return clientesRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado."));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente saveClient(@RequestBody @Valid Cliente client) {
        return clientesRepository.save(client);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateClient(@PathVariable Integer id, @RequestBody @Valid Cliente client) {
        clientesRepository
                .findById(id)
                .map(clientIsPresent -> {
                    client.setId(clientIsPresent.getId());
                    clientesRepository.save(client);
                    return clientIsPresent;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cliente não encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteClient(@PathVariable Integer id) {
        clientesRepository.findById(id)
                .map(client -> {
                    clientesRepository.delete(client);
                    return client;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cliente não encontrado."));
    }
}
