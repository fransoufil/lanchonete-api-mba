package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.adapters.services.ClienteServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.entities.Cliente;
import com.fiap.lanchoneteapi.application.entities.dtos.ClienteRecordEntityDTO;
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
@RequestMapping("/clientes")
public class ClienteControllerAdapter {

    @Autowired
    private ClienteServiceImplAdapterPort clienteService;

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os Clientes cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<List<Cliente>> findAll() {

        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/cpf")
    @Operation(summary = "Realiza a busca de Cliente por CPF", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<Cliente> findByCpf (@RequestParam(value = "value") String cpf){

        Cliente obj = clienteService.findByCpf(cpf);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/email")
    @Operation(summary = "Realiza a busca de Cliente por email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<Cliente> findByEmail (@RequestParam(value = "value") String email){

        Cliente obj = clienteService.findByEmail(email);
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de novo Cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso")
    })
    public ResponseEntity<Void> insert (@RequestBody ClienteRecordEntityDTO objDTO) {

        Cliente obj = clienteService.fromDTO(objDTO);
        obj = clienteService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Realiza a atualização de dados de Cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    })
    public ResponseEntity<Void> update (@RequestBody ClienteRecordEntityDTO objDTO, @PathVariable Integer id) {

        Cliente obj = clienteService.fromDTO(objDTO);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Realiza a deleção de Cliente", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso")
    })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        clienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
