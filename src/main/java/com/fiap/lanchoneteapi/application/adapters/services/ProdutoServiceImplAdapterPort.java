package com.fiap.lanchoneteapi.application.adapters.services;

import com.fiap.lanchoneteapi.application.entities.Produto;
import com.fiap.lanchoneteapi.application.entities.dtos.ProdutoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.application.ports.repositories.ProdutoRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.services.IProdutoServicePort;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;
import com.fiap.lanchoneteapi.infrastructure.exceptions.DataIntegrityException;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImplAdapterPort implements IProdutoServicePort {

    @Autowired
    private ProdutoRepositoryPort produtoRepositoryPort;
    @Override
    public List<Produto> findAll() {
        return produtoRepositoryPort.findAll();
    }

    @Override
    public List<Produto> findByCategoria(CategoriaProdutoEnum categoria) {
        return produtoRepositoryPort.findByCategoria(categoria);
    }

    @Override
    public Optional<Produto> findById(Integer id) {
        return produtoRepositoryPort.findById(id);

    }

    @Override
    @Transactional
    public Produto insert(Produto obj) {
        obj.setId(null);
        obj = produtoRepositoryPort.save(obj);
        return obj;

    }

    @Override
    public Produto update(Produto obj) {
        Produto newObj = find(obj.getId());
        updateData(newObj, obj);
        return produtoRepositoryPort.save(newObj);

    }

    @Override
    public void delete(Integer id) {
        find(id);
        try {
            produtoRepositoryPort.deleteById(id);
        }
        catch (DataIntegrityException e){
            throw new DataIntegrityException("Não foi possível excluir o produto.");
        }

    }

    private void updateData(Produto newObj, Produto obj){
        newObj.setNome(obj.getNome());
        newObj.setDescricao(obj.getDescricao());
        newObj.setPreco(obj.getPreco());
        newObj.setCategoria(obj.getCategoria());
    }

    public Produto find(Integer id) {
        Optional<Produto> obj = produtoRepositoryPort.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Produto não encontrado! Id: " + id + ", Nome: " + Produto.class.getName()
        ));
    }

    public Produto fromDTO(ProdutoRecordNewEntityDTO objDTO){
        return new Produto(null, objDTO.nome(), objDTO.descricao(), objDTO.preco(), objDTO.categoria());
    }

}
