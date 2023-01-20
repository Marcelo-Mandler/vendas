package com.mandler.marcelo.vendas.service;

import com.mandler.marcelo.vendas.domain.entity.Pedido;
import com.mandler.marcelo.vendas.rest.dto.InformacoesPedidoDTO;
import com.mandler.marcelo.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido saveOrder(PedidoDTO dto);

    Optional<Pedido> getCompletOrder(Integer id);
}
