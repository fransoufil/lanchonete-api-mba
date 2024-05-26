package com.fiap.lanchoneteapi.application.entities.dtos;

import com.fiap.lanchoneteapi.application.entities.Cliente;
import com.fiap.lanchoneteapi.application.entities.ItemPedido;
import jakarta.validation.constraints.Max;

import java.util.Set;

public record PedidoRecordNewEntityDTO(Cliente cliente, Set<ItemPedido> itens, @Max(255) String observacao) {
}
