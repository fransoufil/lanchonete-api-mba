package com.fiap.lanchoneteapi.application.entities.dtos;


import com.fiap.lanchoneteapi.application.entities.ItemPedido;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;

import java.util.Set;

public record PedidoRecordEntityDTO(Integer id, Set<ItemPedido> itens, StatusPedidoEnum statusPedido) {
}
