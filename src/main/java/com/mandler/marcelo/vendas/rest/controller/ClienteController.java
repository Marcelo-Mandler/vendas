package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import com.mandler.marcelo.vendas.domain.repository.ClientesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Cliente> ListAllClient(@RequestBody Cliente listCliente) {
        return clientesRepository.findAll();
    }
    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClientById(@PathVariable Integer id) {
        Optional <Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity saveClient(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity updateClient(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clientesRepository
                .findById(id)
                .map(clientIsPresent -> {
                    cliente.setId(clientIsPresent.getId());
                    clientesRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping ("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity deleteClient (@PathVariable Integer id) {
        Optional <Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/clientes")
    public ResponseEntity find (Cliente filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filter, matcher);
        List<Cliente> list = clientesRepository.findAll(example);
        return ResponseEntity.ok(list);

    }
}
