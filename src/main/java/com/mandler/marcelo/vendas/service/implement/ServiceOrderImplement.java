package com.mandler.marcelo.vendas.service.implement;

import com.mandler.marcelo.vendas.domain.entity.Cliente;
import com.mandler.marcelo.vendas.domain.entity.Pedido;
import com.mandler.marcelo.vendas.domain.entity.ItemPedido;
import com.mandler.marcelo.vendas.domain.entity.Produto;
import com.mandler.marcelo.vendas.domain.repository.ClientesRepository;
import com.mandler.marcelo.vendas.domain.repository.ItemsPedidosRepository;
import com.mandler.marcelo.vendas.domain.repository.PedidoRepository;
import com.mandler.marcelo.vendas.domain.repository.ProdutoRepository;
import com.mandler.marcelo.vendas.exception.BusinessRulesException;
import com.mandler.marcelo.vendas.rest.dto.PedidoDTO;
import com.mandler.marcelo.vendas.rest.dto.ItemPedidoDTO;
import com.mandler.marcelo.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOrderImplement implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClientesRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemsPedidosRepository itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido saveOrder(PedidoDTO dto) {
        Integer idClient = dto.getCliente();
        Cliente clientDTO = clienteRepository.findById(idClient)
                .orElseThrow(() ->
                        new BusinessRulesException("Código de cliente inválido."));

        Pedido order = new Pedido();
        order.setTotal(dto.getTotal());
        order.setDataPedido(LocalDate.now());
        order.setCliente(clientDTO);

        List<ItemPedido> orderItemList =  convertItemsInOrderItems(order, dto.getItens());
        pedidoRepository.save(order);
        itemsPedidosRepository.saveAll(orderItemList);
        order.setItens(orderItemList);
        return order;
    }

    @Override
    public Optional<Pedido> getCompletOrder(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

    private List<ItemPedido> convertItemsInOrderItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new BusinessRulesException("Não é possível realizar um pedido sem itens.");
        }
        return items
                .stream()
                .map(dto -> {
                    Integer idProduct = dto.getProduto();
                    Produto productDTO = produtoRepository
                            .findById(idProduct)
                            .orElseThrow(() ->
                            new BusinessRulesException("Código de produto inválido: "
                                    + idProduct));
                    ItemPedido orderItem = new ItemPedido();
                    orderItem.setQuantidade(dto.getQuantidade());
                    orderItem.setPedido(pedido);
                    orderItem.setProduto(productDTO);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}
