package com.fiap.lanchoneteapi.application.ports.services;


import com.fiap.lanchoneteapi.application.entities.Cliente;

import java.util.List;

public interface IClienteServicePort {

    List<Cliente> findAll();

    Cliente findByCpf(String cpf);

    Cliente findByEmail(String email);

    Cliente insert(Cliente obj);

    Cliente update(Cliente obj);

    void delete(Integer id);

}
