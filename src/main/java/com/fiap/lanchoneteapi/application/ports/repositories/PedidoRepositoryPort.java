package com.fiap.lanchoneteapi.application.ports.repositories;

import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PedidoRepositoryPort extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    Page<Pedido> findAllByOrderById(Pageable pageRequest);

    List<Pedido> findByStatusOrderByIdAsc(StatusPedidoEnum status);
}
