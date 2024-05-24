package com.fiap.lanchoneteapi.application.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fila {
    private Integer id;
    private List<Pedido> pedidos;

    public Fila(Integer id) {
        this.id = id;
        this.pedidos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void removerPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fila fila = (Fila) o;
        return Objects.equals(id, fila.id) &&
                Objects.equals(pedidos, fila.pedidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedidos);
    }

    @Override
    public String toString() {
        return "Fila{" +
                "id=" + id +
                ", pedidos=" + pedidos +
                '}';
    }
}