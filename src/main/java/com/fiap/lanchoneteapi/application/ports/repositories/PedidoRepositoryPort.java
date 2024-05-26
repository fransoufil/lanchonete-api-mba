package com.fiap.lanchoneteapi.application.ports.repositories;

import com.fiap.lanchoneteapi.application.entities.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepositoryPort extends JpaRepository<Pedido, Integer> {

    @Transactional(readOnly = true)
    public Page<Pedido> findAllByOrderById(Pageable pageRequest);
}
