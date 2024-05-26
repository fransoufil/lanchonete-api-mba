package com.fiap.lanchoneteapi.application.ports.repositories;
import com.fiap.lanchoneteapi.application.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepositoryPort extends JpaRepository<ItemPedido, Integer> {
}
