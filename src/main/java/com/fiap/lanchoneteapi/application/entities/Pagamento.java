package com.fiap.lanchoneteapi.application.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPagamentoEnum;
import jakarta.persistence.*;

@Entity(name = "tb_pagamentos")
public class Pagamento {
    @Id
    private Integer id;

    private Integer statusPagamento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, StatusPagamentoEnum statusPagamento, Pedido pedido) {
        super();
        this.id = id;
        this.statusPagamento = (statusPagamento==null) ? null : statusPagamento.getCodigo();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusPagamentoEnum getStatusPagamento() {
        return StatusPagamentoEnum.toEnum(statusPagamento);
    }

    public void setStatusPagamento(StatusPagamentoEnum statusPagamento) {
        this.statusPagamento = statusPagamento.getCodigo();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagamento other = (Pagamento) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
