package com.mandler.marcelo.vendas.service.implement;

import com.mandler.marcelo.vendas.domain.repository.PedidosRepository;
import com.mandler.marcelo.vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImplement implements PedidoService {
    private PedidosRepository pedidosRepository;

    public PedidoServiceImplement(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }
}
