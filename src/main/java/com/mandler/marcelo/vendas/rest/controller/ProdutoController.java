package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.domain.entity.Produto;
import com.mandler.marcelo.vendas.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> filterOrGetAllProducts(Produto filterOrGet) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filterOrGet, matcher);
        return produtoRepository.findAll(example);
    }
    @GetMapping("/{id}")
    public Produto getProductById (@PathVariable Integer id) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto saveProduct(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateProduct(@PathVariable Integer id, @RequestBody Produto produto) {
        produtoRepository
                .findById(id)
                .map(productIsPresent -> {
                    produto.setId(productIsPresent.getId());
                    produtoRepository.save(produto);
                    return productIsPresent;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteProduct (@PathVariable Integer id) {
        produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND,
                                "Cliente não encontrado."));
    }
}
