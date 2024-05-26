package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.adapters.services.FilaServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.adapters.services.PedidoServiceImplAdapterPort;
import com.fiap.lanchoneteapi.application.entities.Fila;
import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fila")
public class FilaController {

    private final FilaServiceImplAdapterPort filaService;

    private final PedidoServiceImplAdapterPort pedidoService;

    public FilaController(FilaServiceImplAdapterPort filaService, PedidoServiceImplAdapterPort pedidoService){
        this.filaService = filaService;
        this.pedidoService = pedidoService;
    }

    @PostMapping("/{filaId}/pedidos")
    public ResponseEntity<Fila> adicionarPedido(@PathVariable Integer filaId, @RequestBody Pedido pedido) {
        Optional<Fila> filaOpt = filaService.findById(filaId);
        if (filaOpt.isPresent()) {
            Fila fila = filaOpt.get();
            filaService.adicionarPedido(fila, pedido);
            return ResponseEntity.ok(fila);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pedidos/{pedidoId}/status")
    public ResponseEntity<Pedido> atualizarStatusPedido(@PathVariable Integer pedidoId, @RequestParam StatusPedidoEnum status) {
        Optional<Pedido> pedidoOpt = pedidoService.findById(pedidoId);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            filaService.atualizarStatusPedido(pedido, status);
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pedidos/em-andamento")
    public ResponseEntity<List<Pedido>> listarPedidosEmAndamento() {
        List<Pedido> pedidos = filaService.listarPedidosEmAndamento();
        return ResponseEntity.ok(pedidos);
    }
}
