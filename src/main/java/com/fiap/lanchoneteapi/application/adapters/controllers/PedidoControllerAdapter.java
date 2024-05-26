package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.adapters.services.PedidoServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordEntityDTO;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
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
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> list = pedidoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id){

        Pedido obj = pedidoService.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Pedido: " + Pedido.class.getName()
        ));
        return ResponseEntity.ok().body(obj);

    }

    @PostMapping
    public ResponseEntity<Void> insert (@Valid @RequestBody PedidoRecordNewEntityDTO newObj) {

        Pedido obj = pedidoService.insert(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @GetMapping(value = "/page")
    public ResponseEntity<List<Pedido>> findAllByOrderById(@RequestParam(value="page", defaultValue="0") Integer page,
                                                           @RequestParam(value="linesPerPage", defaultValue="2") Integer linesPerPage,
                                                           @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
                                                           @RequestParam(value="direction", defaultValue="DESC") String direction) {

        List<Pedido> list = pedidoService.findAllByOrderById(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);

    }

    @PutMapping(value = "/{id}")
    public  ResponseEntity<Pedido> update (@RequestBody Pedido obj, @PathVariable Integer id) {

        Pedido updatedObj = pedidoService.update(obj);
        return ResponseEntity.ok().body(obj);

    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoRecordEntityDTO>> listarPedidosPorStatus(@PathVariable StatusPedidoEnum status) {
        List<Pedido> pedidos = pedidoService.findByStatus(status);
        List<PedidoRecordEntityDTO> pedidoRecordDTOs = pedidos.stream().map(pedido -> {
            return new PedidoRecordEntityDTO(pedido.getId(), pedido.getItens(), pedido.getStatus());
        }).collect(Collectors.toList());
        return ResponseEntity.ok(pedidoRecordDTOs);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> atualizarStatus(@PathVariable Integer id, @RequestParam StatusPedidoEnum status) {
        pedidoService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
