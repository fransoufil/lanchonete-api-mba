package com.fiap.lanchoneteapi.application.ports.repositories;
import com.fiap.lanchoneteapi.application.entities.Produto;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositoryPort extends JpaRepository<Produto, Integer> {

    List<Produto> findByCategoria(CategoriaProdutoEnum categoria);

}
