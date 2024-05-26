package com.fiap.lanchoneteapi.application.adapters.services;

import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.ports.repositories.FilaRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.repositories.PedidoRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.services.FilaServicePort;
import com.fiap.lanchoneteapi.application.entities.Fila;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilaServiceImplAdapterPort implements FilaServicePort {

    private final FilaRepositoryPort filaRepository;
    private final PedidoRepositoryPort pedidoRepository;

    public FilaServiceImplAdapterPort(FilaRepositoryPort filaRepository, PedidoRepositoryPort pedidoRepository) {
        this.filaRepository = filaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Fila save(Fila fila) {
        return filaRepository.save(fila);
    }

    @Override
    public Optional<Fila> findById(Integer id) {
        return filaRepository.findById(id);
    }

    @Override
    public List<Fila> findAll() {
        return filaRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        filaRepository.deleteById(id);
    }

    @Override
    public void adicionarPedido(Fila fila, Pedido pedido) {
        fila.adicionarPedido(pedido);
        filaRepository.save(fila);
    }

    @Override
    public void atualizarStatusPedido(Pedido pedido, StatusPedidoEnum status) {
        pedido.setStatus(status);
        pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidosEmAndamento() {
        return pedidoRepository.findAll()
                .stream()
                .filter(pedido -> !"Finalizado".equals(pedido.getStatus()))
                .collect(Collectors.toList());
    }
}
