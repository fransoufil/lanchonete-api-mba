package com.fiap.lanchoneteapi.application.ports.services;

import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.entities.Fila;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;

import java.util.List;
import java.util.Optional;

public interface FilaServicePort {
    Fila save(Fila fila);
    Optional<Fila> findById(Integer id);
    List<Fila> findAll();
    void deleteById(Integer id);
    void adicionarPedido(Fila fila, Pedido pedido);
    void atualizarStatusPedido(Pedido pedido, StatusPedidoEnum status);
    List<Pedido> listarPedidosEmAndamento();
}
