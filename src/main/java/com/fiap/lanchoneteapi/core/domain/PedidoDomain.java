package com.fiap.lanchoneteapi.core.domain;


import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;

import java.util.Date;
import java.util.Objects;

public class PedidoDomain {

    private Integer id;
    private Date instanteCriacaoPedido;
    private Date instanteStatusPedido;
    private ClienteDomain clienteDomain;
    private String observacao;
    private StatusPedidoEnum status;

    public PedidoDomain(Integer id, Date instanteCriacaoPedido, Date instanteStatusPedido, ClienteDomain clienteDomain, String observacao, StatusPedidoEnum status) {
        this.id = id;
        this.instanteCriacaoPedido = instanteCriacaoPedido;
        this.instanteStatusPedido = instanteStatusPedido;
        this.clienteDomain = clienteDomain;
        this.observacao = observacao;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstanteCriacaoPedido() {
        return instanteCriacaoPedido;
    }

    public void setInstanteCriacaoPedido(Date instanteCriacaoPedido) {
        this.instanteCriacaoPedido = instanteCriacaoPedido;
    }

    public Date getInstanteStatusPedido() {
        return instanteStatusPedido;
    }

    public void setInstanteStatusPedido(Date instanteStatusPedido) {
        this.instanteStatusPedido = instanteStatusPedido;
    }

    public ClienteDomain getCliente() {
        return clienteDomain;
    }

    public void setCliente(ClienteDomain clienteDomain) {
        this.clienteDomain = clienteDomain;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDomain pedidoDomain = (PedidoDomain) o;
        return Objects.equals(id, pedidoDomain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PedidoDomain{" +
                "id=" + id +
                ", instanteCriacaoPedido=" + instanteCriacaoPedido +
                ", instanteStatusPedido=" + instanteStatusPedido +
                ", cliente=" + clienteDomain +
                ", observacao='" + observacao + '\'' +
                ", status=" + status +
                '}';
    }
}
