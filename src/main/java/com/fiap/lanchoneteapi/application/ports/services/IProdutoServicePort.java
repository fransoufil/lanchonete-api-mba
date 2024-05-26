package com.fiap.lanchoneteapi.application.ports.services;


import com.fiap.lanchoneteapi.application.entities.Produto;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;

import java.util.List;
import java.util.Optional;

public interface IProdutoServicePort {

    List<Produto> findAll();

    List<Produto> findByCategoria (CategoriaProdutoEnum categoria);

    Optional<Produto> findById(Integer id);

    Produto insert(Produto obj);

    Produto update(Produto obj);

    void delete(Integer id);

}
