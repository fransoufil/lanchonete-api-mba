package com.fiap.lanchoneteapi.application.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tb_pedidos")
public class Pedido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instanteCriacaoPedido;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instanteStatusPedido;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "id.pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemPedido> itens = new HashSet<>();
    private String observacao;
    private StatusPedidoEnum status;
    @OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")
    private Pagamento pagamento;

    public Pedido() {
    }

    public Pedido(Integer id, Date instanteCriacaoPedido, Date instanteStatusPedido, Cliente cliente, StatusPedidoEnum status, String observacao) {
        this.id = id;
        this.instanteCriacaoPedido = instanteCriacaoPedido;
        this.instanteStatusPedido = instanteStatusPedido;
        this.cliente = cliente;
        this.status = status;
        this.observacao = observacao;
    }

    public BigDecimal getTotal(){
        return itens.stream()
                .map(item -> item.getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public String getObservacao() { return observacao; }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public Date getInstanteStatusPedido() {
        return instanteStatusPedido;
    }

    public void setInstanteStatusPedido(Date instanteStatusPedido) {
        this.instanteStatusPedido = instanteStatusPedido;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", instanteCriacaoPedido=" + instanteCriacaoPedido +
                ", instanteStatusPedido=" + instanteStatusPedido +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", status=" + status +
                ", observacao=" + observacao +
                '}';
    }
}
