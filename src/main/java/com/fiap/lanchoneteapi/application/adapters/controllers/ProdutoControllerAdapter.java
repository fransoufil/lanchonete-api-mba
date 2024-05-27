package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.adapters.services.ProdutoServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.entities.Produto;
import com.fiap.lanchoneteapi.application.entities.dtos.ProdutoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoControllerAdapter {

    @Autowired
    private ProdutoServiceImplAdapterPort produtoService;

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os Produtos cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de Produtos")
    })
    public ResponseEntity<List<Produto>> findAll(){

        List<Produto> list = produtoService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Realiza a busca de Produto por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de Produto")
    })
    public ResponseEntity<Produto> findById(@PathVariable Integer id){

        Produto obj = produtoService.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Produto não encontrado! Id: " + id + ", Nome: " + Produto.class.getName()
        ));
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping("/categoria/{codigo}")
    @Operation(summary = "Realiza a busca de Produtos pelo Codigo de Categoria", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de Produtos pelo Codigo da Categoria")
    })
    public ResponseEntity<List<Produto>> findByCategoria(@PathVariable("codigo")Integer codigo) {

        CategoriaProdutoEnum categoria = CategoriaProdutoEnum.toEnum(codigo);
        List<Produto> list = produtoService.findByCategoria(categoria);
        return ResponseEntity.ok().body(list);

    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de novo Produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso")
    })
    public ResponseEntity<Void> insert (@RequestBody ProdutoRecordNewEntityDTO objDTO) {

        Produto obj = produtoService.fromDTO(objDTO);
        obj = produtoService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Realiza a atualização de dados de Produto", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Produto atualizado com sucesso")
    })
    public ResponseEntity<Void> update (@RequestBody ProdutoRecordNewEntityDTO objDTO, @PathVariable Integer id) {

        Produto obj = produtoService.fromDTO(objDTO);
        obj.setId(id);
        obj = produtoService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Realiza a deleção de Produto cadastrado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        produtoService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
