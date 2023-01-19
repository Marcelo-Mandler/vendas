package com.mandler.marcelo.vendas.domain.repository;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import com.mandler.marcelo.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository <Pedido, Integer> {
    List<Pedido> findByCliente (Cliente cliente);
}
