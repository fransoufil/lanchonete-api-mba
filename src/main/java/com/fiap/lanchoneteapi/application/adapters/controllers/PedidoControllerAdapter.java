package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.adapters.services.PedidoServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordEntityDTO;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoControllerAdapter {

    @Autowired
    private PedidoServiceImplAdapterPort pedidoService;

    @GetMapping
    @Operation(summary = "Realiza a busca de todos os Pedidos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> list = pedidoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Realiza a busca de Pedido por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){

        Pedido obj = pedidoService.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Pedido: " + Pedido.class.getName()
        ));
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de novo Pedido", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido cadastrado com sucesso")
    })
    public ResponseEntity<Void> insert (@Valid @RequestBody PedidoRecordNewEntityDTO newObj) {

        Pedido obj = pedidoService.insert(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping(value = "/page")
    @Operation(summary = "Realiza a busca de Pedidos paginados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<Page<Pedido>> findAllByOrderById(@RequestParam(value="page", defaultValue="0") Integer page,
                                                           @RequestParam(value="linesPerPage", defaultValue="2") Integer linesPerPage,
                                                           @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
                                                           @RequestParam(value="direction", defaultValue="DESC") String direction) {

        Page<Pedido> list = pedidoService.findAllByOrderById(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);

    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Realiza a atualização de dados de Pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso")
    })
    public  ResponseEntity<Pedido> update (@RequestBody Pedido obj, @PathVariable Integer id) {

        Pedido updatedObj = pedidoService.update(obj);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Realiza a busca de Pedido por Status ordenados em fila", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisicao invalidos"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a busca de dados")
    })
    public ResponseEntity<List<PedidoRecordEntityDTO>> listarPedidosPorStatus(@PathVariable StatusPedidoEnum status) {
        List<Pedido> pedidos = pedidoService.findByStatus(status);
        List<PedidoRecordEntityDTO> pedidoRecordDTOs = pedidos.stream().map(pedido -> {
            return new PedidoRecordEntityDTO(pedido.getId(), pedido.getItens(), pedido.getStatus());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(pedidoRecordDTOs);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Realiza a atualização de Status de Pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status do Pedido atualizado com sucesso")
    })
    public ResponseEntity<Void> atualizarStatus(@PathVariable Integer id, @RequestParam StatusPedidoEnum status) {
        pedidoService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
