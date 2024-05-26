package com.fiap.lanchoneteapi.application.adapters.services;

import com.fiap.lanchoneteapi.application.entities.ItemPedido;
import com.fiap.lanchoneteapi.application.entities.Pedido;
import com.fiap.lanchoneteapi.application.entities.dtos.PedidoRecordNewEntityDTO;
import com.fiap.lanchoneteapi.application.ports.repositories.ItemPedidoRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.repositories.PedidoRepositoryPort;
import com.fiap.lanchoneteapi.application.ports.services.IPedidoServicePort;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import com.fiap.lanchoneteapi.infrastructure.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImplAdapterPort implements IPedidoServicePort {

    @Autowired
    private PedidoRepositoryPort pedidoRepositoryPort;

    @Autowired
    private ItemPedidoRepositoryPort itemPedidoRepositoryPort;

    @Autowired
    private ClienteServiceImplAdapterPort clienteService;

    @Autowired
    private ProdutoServiceImplAdapterPort produtoService;

    @Override
    public Optional<Pedido> findById(Integer id) {
        return pedidoRepositoryPort.findById(id);
    }

    @Override
    @Transactional
    public Pedido insert(PedidoRecordNewEntityDTO newObj) {

        Pedido obj = new Pedido();

        obj.setId(null);
        obj.setInstanteCriacaoPedido(new Date());
        obj.setCliente(clienteService.find(newObj.cliente().getId() != null ? newObj.cliente().getId() : null));
        obj.setStatus(StatusPedidoEnum.RECEBIDO);
        obj.setObservacao(newObj.observacao());

        obj = pedidoRepositoryPort.save(obj);

        for (ItemPedido ip : obj.getItens()){
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco((ip.getProduto().getPreco()));
            ip.setPedido(obj);
            ip.setObservacao(ip.getObservacao());
        }

        itemPedidoRepositoryPort.saveAll(obj.getItens());

        return obj;
    }

    @Override
    public Pedido update(Pedido obj) {

        Pedido newObj = find(obj.getId());
        updateData(newObj, obj);
        return pedidoRepositoryPort.save(newObj);

    }

    @Override
    public List<Pedido> findAllByOrderById(Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return pedidoRepositoryPort.findAllByOrderById(pageRequest);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepositoryPort.findAll();
    }

    @Override
    public List<Pedido> findByStatus(StatusPedidoEnum status) {
        return pedidoRepositoryPort.findByStatusOrderByIdAsc(status);
    }

    @Override
    public void updateStatus(Integer id, StatusPedidoEnum statusPedidoEnum) {

        Optional<Pedido> pedidoOpt = pedidoRepositoryPort.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setStatus(statusPedidoEnum);
            pedidoRepositoryPort.save(pedido);
        }

    }


    public Pedido find(Integer id){

        Optional<Pedido> obj = pedidoRepositoryPort.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Pedido não encontrado! Id: " + id + ", Nome: " + Pedido.class.getName()
        ));

    }

    private void updateData(Pedido newObj, Pedido obj){

        newObj.setItens(obj.getItens());
        newObj.setStatus(obj.getStatus());
        newObj.setObservacao(obj.getObservacao());

    }

}
