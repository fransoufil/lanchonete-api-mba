package com.fiap.lanchoneteapi.core.domain;

import com.fiap.lanchoneteapi.application.entities.Pedido;

import java.util.List;
import java.util.Objects;

public class ClienteDomain {

    private Integer id;
    private String cpf;
    private String nome;
    private String email;

    private List<Pedido> pedidos;

    public ClienteDomain(Integer id, String cpf, String nome, String email) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteDomain clienteDomain = (ClienteDomain) o;
        return Objects.equals(id, clienteDomain.id) && Objects.equals(cpf, clienteDomain.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }

    @Override
    public String toString() {
        return "ClienteDomain{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
