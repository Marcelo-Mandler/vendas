package com.mandler.marcelo.vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidosDTO {
    private Integer client;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
