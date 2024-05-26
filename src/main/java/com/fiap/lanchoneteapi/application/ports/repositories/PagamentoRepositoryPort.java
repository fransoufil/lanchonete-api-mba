package com.fiap.lanchoneteapi.application.ports.repositories;

import com.fiap.lanchoneteapi.application.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositoryPort extends JpaRepository<Pagamento, Integer> {
}
