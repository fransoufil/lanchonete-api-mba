package com.fiap.lanchoneteapi.core.ports;

import com.fiap.lanchoneteapi.core.domain.PedidoDomain;

import java.util.List;

public interface PedidoRepository {

    public List<PedidoDomain> findAllByOrderById();
}
