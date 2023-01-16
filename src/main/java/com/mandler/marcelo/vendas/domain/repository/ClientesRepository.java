package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);

    Boolean existsByNome (String nome);
}
