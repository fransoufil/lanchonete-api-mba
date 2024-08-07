package com.fiap.lanchoneteapi.application.ports.services;

import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IPedidoServicePort {

    Optional<Pedido> findById(Integer id);

    Pedido insert(PedidoRecordNewEntityDTO obj);

    Pedido update(Pedido obj);

    Page<Pedido> findAllByOrderById(Integer page, Integer linesPerPage, String orderBy, String direction);

    List<Pedido> findAll();

    List<Pedido> findByStatus(StatusPedidoEnum status);

    void updateStatus(Integer id, StatusPedidoEnum statusPedidoEnum);
}
