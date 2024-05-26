package com.fiap.lanchoneteapi.application.entities.dtos;


import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;

import java.math.BigDecimal;

public record ProdutoRecordNewEntityDTO(String nome, String descricao, BigDecimal preco, CategoriaProdutoEnum categoria){
}
