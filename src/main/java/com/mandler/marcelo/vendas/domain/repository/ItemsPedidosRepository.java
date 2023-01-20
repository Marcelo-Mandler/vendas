package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidosRepository extends JpaRepository <ItemPedido, Integer> {
}
