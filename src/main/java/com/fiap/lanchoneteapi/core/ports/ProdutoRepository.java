package com.fiap.lanchoneteapi.core.ports;

import com.fiap.lanchoneteapi.core.domain.ProdutoDomain;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;

import java.util.List;

public interface ProdutoRepository {

    List<ProdutoDomain> findByCategoria(CategoriaProdutoEnum categoria);
}
