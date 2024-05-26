package com.fiap.lanchoneteapi.application.ports.repositories;

import com.fiap.lanchoneteapi.application.entities.Fila;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilaRepositoryPort extends JpaRepository<Fila, Integer>{

    Fila save(Fila fila);
    Optional<Fila> findById(Integer id);
    void deleteById(Integer id);
}
