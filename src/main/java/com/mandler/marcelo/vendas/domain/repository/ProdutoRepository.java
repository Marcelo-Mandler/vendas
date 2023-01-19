package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
