package com.fiap.lanchoneteapi.core.ports;

import com.fiap.lanchoneteapi.core.domain.ClienteDomain;

public interface ClienteRepository {

    ClienteDomain findByCpf(String cpf);

    ClienteDomain findByEmail(String email);
}
