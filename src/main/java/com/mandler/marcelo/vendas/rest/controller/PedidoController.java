package com.mandler.marcelo.vendas.rest.controller;

import com.mandler.marcelo.vendas.domain.entity.ItemPedido;
import com.mandler.marcelo.vendas.domain.entity.Pedido;
import com.mandler.marcelo.vendas.domain.enums.StatusPedido;
import com.mandler.marcelo.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.mandler.marcelo.vendas.rest.dto.InformacoesPedidoDTO;
import com.mandler.marcelo.vendas.rest.dto.PedidoDTO;
import com.mandler.marcelo.vendas.rest.dto.UpdateOrderStatusDTO;
import com.mandler.marcelo.vendas.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer saveOrder(@RequestBody @Valid PedidoDTO dto) {
        Pedido pedido = pedidoService.saveOrder(dto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService
                .getCompletOrder(id)
                .map(pedido -> convert(pedido))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus (@PathVariable Integer id, @RequestBody UpdateOrderStatusDTO statusDTO) {
        String novoStatus = statusDTO.getNovoStatus();
        pedidoService.updateStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO convert (Pedido pedido) {
        return InformacoesPedidoDTO.builder().codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(convertList(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> convertList (List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(item -> InformacaoItemPedidoDTO.builder()
                .descricaProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()).collect(Collectors.toList());

    }
}
