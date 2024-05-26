package com.fiap.lanchoneteapi.application.ports.repositories;
import com.fiap.lanchoneteapi.application.entities.Cliente;
import com.fiap.lanchoneteapi.core.domain.ClienteDomain;
import com.fiap.lanchoneteapi.core.ports.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClienteRepositoryPort extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM tb_clientes obj WHERE obj.cpf = :cpf")
    public Cliente findByCpf(@Param("cpf") String cpf);

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM tb_clientes obj WHERE obj.email = :email")
    public Cliente findByEmail(@Param("email") String email);

}
