package com.fiap.lanchoneteapi.application.entities.dtos;

import java.math.BigDecimal;

public record ProdutoRecordEntityDTO(String nome, String descricao, BigDecimal preco) {
}
