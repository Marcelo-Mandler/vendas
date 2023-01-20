package com.mandler.marcelo.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosDTO {
    private Integer client;
    private BigDecimal total;
    private List<ItemPedidoDTO> items;
}
