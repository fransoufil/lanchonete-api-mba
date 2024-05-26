package com.fiap.lanchoneteapi.core.domain.enums;

public enum CategoriaProdutoEnum {
    LANCHE (1, "Lanche"),
    ACOMPANHAMENTO(2, "Acompanhamento"),
    BEBIDA (3, "Bebida"),
    SOBREMESA (4, "Sobremesa");

    private final int codigo;
    private final String descricao;

    private CategoriaProdutoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao () {
        return descricao;
    }

    public static CategoriaProdutoEnum toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (CategoriaProdutoEnum x : CategoriaProdutoEnum.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código da Categoria inválido: " + cod);
    }
}
