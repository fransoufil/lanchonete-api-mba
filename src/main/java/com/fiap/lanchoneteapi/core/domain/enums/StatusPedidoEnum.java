package com.fiap.lanchoneteapi.core.domain.enums;

public enum StatusPedidoEnum {
    RECEBIDO (1, "Recebido"),
    EM_PREPARACAO (2, "Em Preparação"),
    PRONTO (3, "Pronto"),
    FINALIZADO (4, "Finalizado"),
    CANCELADO (5, "Cancelado");

    private final int codigo;
    private final String descricao;

    private StatusPedidoEnum(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao () {
        return descricao;
    }

    public static StatusPedidoEnum toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (StatusPedidoEnum x : StatusPedidoEnum.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
