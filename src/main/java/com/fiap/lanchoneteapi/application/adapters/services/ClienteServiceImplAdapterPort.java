package com.fiap.lanchoneteapi.application.adapters.services;

import com.fiap.lanchoneteapi.application.entities.Cliente;
import com.fiap.lanchoneteapi.application.entities.dtos.ClienteRecordEntityDTO;
import com.fiap.lanchoneteapi.application.ports.repositories.ClienteRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.services.IClienteServicePort;
import com.fiap.lanchoneteapi.infrastructure.exceptions.DataIntegrityException;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImplAdapterPort implements IClienteServicePort {

    @Autowired
    private ClienteRepositoryPort clienteRepositoryPort;
    @Override
    public List<Cliente> findAll() {
        return clienteRepositoryPort.findAll();
    }

    @Override
    public Cliente findByCpf(String cpf) {
        return clienteRepositoryPort.findByCpf(cpf);
    }

    @Override
    public Cliente findByEmail(String email) {
        return clienteRepositoryPort.findByEmail(email);
    }

    @Override
    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepositoryPort.save(obj);
        return obj;
    }

    @Override
    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return clienteRepositoryPort.save(newObj);
    }

    @Override
    public void delete(Integer id) {
        find(id);
        try {
            clienteRepositoryPort.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }

    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setCpf(obj.getCpf());
    }

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepositoryPort.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Nome: " + Cliente.class.getName()
        ));

    }

    public Cliente fromDTO(ClienteRecordEntityDTO objDTO){
        return new Cliente(null, objDTO.nome(), objDTO.email(), objDTO.cpf());
    }

}
