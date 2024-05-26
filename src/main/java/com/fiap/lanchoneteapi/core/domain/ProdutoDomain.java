package com.fiap.lanchoneteapi.core.domain;

import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoDomain {

    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProdutoEnum categoria;

    public ProdutoDomain(Integer id, String nome, String descricao, BigDecimal preco, CategoriaProdutoEnum categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public CategoriaProdutoEnum getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProdutoEnum categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDomain produtoDomain = (ProdutoDomain) o;
        return Objects.equals(id, produtoDomain.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProdutoDomain{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }
}
