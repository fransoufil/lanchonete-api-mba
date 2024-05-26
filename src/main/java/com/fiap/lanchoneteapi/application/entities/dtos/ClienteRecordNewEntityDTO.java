package com.fiap.lanchoneteapi.application.entities.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRecordNewEntityDTO(
        @NotEmpty(message="Preenchimento obrigatório")
        @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
        String nome,
        @NotEmpty(message="Preenchimento obrigatório")
        @Email(message="Email inválido")
        String email,
        @NotEmpty(message="Preenchimento obrigatório")
        @CPF
        String cpf)

{
}
