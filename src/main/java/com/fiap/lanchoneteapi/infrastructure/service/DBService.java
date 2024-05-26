package com.fiap.lanchoneteapi.infrastructure.service;

import com.fiap.lanchoneteapi.application.entities.*;
import com.fiap.lanchoneteapi.application.ports.repositories.*;
import com.fiap.lanchoneteapi.core.domain.enums.CategoriaProdutoEnum;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPagamentoEnum;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private ClienteRepositoryPort clienteRepositoryPort;

    @Autowired
    private ProdutoRepositoryPort produtoRepositoryPort;

    @Autowired
    private PedidoRepositoryPort pedidoRepositoryPort;

    @Autowired
    private ItemPedidoRepositoryPort itemPedidoRepositoryPort;

    @Autowired
    private PagamentoRepositoryPort pagamentoRepositoryPort;

    public void instantiateTestDatabase() throws ParseException {

        Cliente cli1 = new Cliente(null, "Luciana Green", "luciana@green.com.br", "43712717741");
        Cliente cli2 = new Cliente(null, "Vinicius White", "vinicius@white.com.br", "27633413140");
        Cliente cli3 = new Cliente(null, "Gabriel Grey", "gabriel@grey.com.br", "63216708551");
        Cliente cli4 = new Cliente(null, "Francisco Brown", "francisco@brown.com.br", "48123717164");
        Cliente cli5 = new Cliente(null, "Angela Yellow", "angela@yellow.com.br", "58312472100");

        clienteRepositoryPort.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));

        // Produtos de Lanche
        Produto prod1 = new Produto(null, "X-Salada", "Pão com Hamburger de carne bovina, queijo, maionese, alface e tomate", new BigDecimal("25.99"), CategoriaProdutoEnum.LANCHE);
        Produto prod2 = new Produto(null, "X-Bacon", "Pão com Hamburger de carne bovina, bacon, queijo e molho barbecue", new BigDecimal("28.99"), CategoriaProdutoEnum.LANCHE);
        Produto prod3 = new Produto(null, "Cheeseburger", "Pão com Hamburger de carne bovina e queijo cheddar", new BigDecimal("22.50"), CategoriaProdutoEnum.LANCHE);
        Produto prod4 = new Produto(null, "Veggie Burger", "Pão com burger vegetariano, alface, tomate e maionese vegana", new BigDecimal("27.00"), CategoriaProdutoEnum.LANCHE);
        Produto prod5 = new Produto(null, "Chicken Sandwich", "Pão com filé de frango grelhado, alface e maionese", new BigDecimal("23.50"), CategoriaProdutoEnum.LANCHE);
        Produto prod16 = new Produto(null, "Fish Burger", "Pão com filé de peixe empanado, alface e maionese tartar", new BigDecimal("25.00"), CategoriaProdutoEnum.LANCHE);
        Produto prod17 = new Produto(null, "Club Sandwich", "Sanduíche com três camadas de pão, frango, bacon, alface, tomate e maionese", new BigDecimal("28.00"), CategoriaProdutoEnum.LANCHE);
        Produto prod18 = new Produto(null, "Wrap de Frango", "Wrap com frango desfiado, alface, tomate, cebola e molho especial", new BigDecimal("20.99"), CategoriaProdutoEnum.LANCHE);
        Produto prod19 = new Produto(null, "Bauru", "Pão francês com rosbife, queijo, tomate e picles", new BigDecimal("18.50"), CategoriaProdutoEnum.LANCHE);
        Produto prod20 = new Produto(null, "Hot Dog Especial", "Pão de hot dog com salsicha, molho de tomate, milho, ervilha, batata palha e queijo", new BigDecimal("15.99"), CategoriaProdutoEnum.LANCHE);

        // Produtos de Bebida
        Produto prod6 = new Produto(null, "Coca-Cola 600ml", "Refrigerante de cola em garrafa de plástico com 600ml", new BigDecimal("8.99"), CategoriaProdutoEnum.BEBIDA);
        Produto prod7 = new Produto(null, "Pepsi 600ml", "Refrigerante de cola em garrafa de plástico com 600ml", new BigDecimal("8.50"), CategoriaProdutoEnum.BEBIDA);
        Produto prod8 = new Produto(null, "Suco de Laranja", "Suco natural de laranja em copo de 300ml", new BigDecimal("10.00"), CategoriaProdutoEnum.BEBIDA);
        Produto prod9 = new Produto(null, "Água Mineral 500ml", "Água mineral sem gás", new BigDecimal("5.00"), CategoriaProdutoEnum.BEBIDA);
        Produto prod10 = new Produto(null, "Cerveja Artesanal 350ml", "Cerveja artesanal tipo IPA em lata", new BigDecimal("12.99"), CategoriaProdutoEnum.BEBIDA);

        // Produtos de Sobremesa
        Produto prod11 = new Produto(null, "Sorvete de Morango", "Sorvete de morango em copo plástico de 100g", new BigDecimal("9.50"), CategoriaProdutoEnum.SOBREMESA);
        Produto prod12 = new Produto(null, "Sorvete de Chocolate", "Sorvete de chocolate em copo plástico de 100g", new BigDecimal("9.50"), CategoriaProdutoEnum.SOBREMESA);
        Produto prod13 = new Produto(null, "Torta de Limão", "Fatia de torta de limão", new BigDecimal("11.00"), CategoriaProdutoEnum.SOBREMESA);
        Produto prod14 = new Produto(null, "Cheesecake de Frutas Vermelhas", "Fatia de cheesecake coberta com frutas vermelhas", new BigDecimal("13.00"), CategoriaProdutoEnum.SOBREMESA);
        Produto prod15 = new Produto(null, "Pudim", "Pudim de leite em porção individual", new BigDecimal("8.00"), CategoriaProdutoEnum.SOBREMESA);

        // Produtos de Acompanhamento
        Produto prod21 = new Produto(null, "Batata Frita", "Porção de batatas fritas crocantes", new BigDecimal("14.99"), CategoriaProdutoEnum.ACOMPANHAMENTO);
        Produto prod22 = new Produto(null, "Nuggets de Frango", "Nuggets de frango empanados e fritos, 10 unidades", new BigDecimal("12.99"), CategoriaProdutoEnum.ACOMPANHAMENTO);
        Produto prod23 = new Produto(null, "Saladinha", "Salada fresca com alface, tomate, cenoura e molho", new BigDecimal("9.99"), CategoriaProdutoEnum.ACOMPANHAMENTO);
        Produto prod24 = new Produto(null, "Palitos de Queijo", "Palitos de queijo mozzarella empanados e fritos", new BigDecimal("16.50"), CategoriaProdutoEnum.ACOMPANHAMENTO);
        Produto prod25 = new Produto(null, "Batata Rústica", "Batatas rústicas assadas com ervas finas", new BigDecimal("15.50"), CategoriaProdutoEnum.ACOMPANHAMENTO);

        produtoRepositoryPort.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12, prod13, prod14, prod15, prod16, prod17, prod18, prod19, prod20, prod21, prod22, prod23, prod24, prod25));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("27/04/2024 16:05"), sdf.parse("27/04/2024 16:20"), cli1, StatusPedidoEnum.FINALIZADO, "");
        Pedido ped2 = new Pedido(null, sdf.parse("27/04/2024 16:07"), sdf.parse("27/04/2024 16:12"),cli2, StatusPedidoEnum.EM_PREPARACAO, "");
        Pedido ped3 = new Pedido(null, sdf.parse("27/04/2024 16:10"), sdf.parse("27/04/2024 16:13"), cli3, StatusPedidoEnum.EM_PREPARACAO, "");
        Pedido ped4 = new Pedido(null, sdf.parse("27/04/2024 16:12"), sdf.parse("27/04/2024 16:12"), cli4, StatusPedidoEnum.RECEBIDO, "");
        Pedido ped5 = new Pedido(null, sdf.parse("27/04/2024 16:15"), sdf.parse("27/04/2024 16:15"), cli5, StatusPedidoEnum.RECEBIDO, "");
        Pedido ped6 = new Pedido(null, sdf.parse("27/04/2024 16:20"), sdf.parse("27/04/2024 16:20"), null, StatusPedidoEnum.RECEBIDO, "Pedido sem identificacao do Cliente");

        Pagamento pagto1 = new Pagamento(null, StatusPagamentoEnum.QUITADO, ped1);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new Pagamento(null, StatusPagamentoEnum.QUITADO, ped2);
        ped2.setPagamento(pagto2);
        Pagamento pagto3 = new Pagamento(null, StatusPagamentoEnum.QUITADO, ped3);
        ped3.setPagamento(pagto3);
        Pagamento pagto4 = new Pagamento(null, StatusPagamentoEnum.QUITADO, ped4);
        ped4.setPagamento(pagto4);
        Pagamento pagto5 = new Pagamento(null, StatusPagamentoEnum.QUITADO, ped5);
        ped5.setPagamento(pagto5);
        Pagamento pagto6 = new Pagamento(null, StatusPagamentoEnum.PENDENTE, ped6);
        ped6.setPagamento(pagto6);

        pedidoRepositoryPort.saveAll(Arrays.asList(ped1, ped2, ped3, ped4, ped5, ped6));
        pagamentoRepositoryPort.saveAll(Arrays.asList(pagto1, pagto2, pagto3, pagto4, pagto5, pagto6));

        List<ItemPedido> itensPedido = new ArrayList<>();

        ItemPedido ip1 = new ItemPedido(prod1.getPreco(), 1, ped1, prod1, "");
        ItemPedido ip2 = new ItemPedido(prod3.getPreco(), 2, ped1, prod3, "No chessburguer usar mussarela no lugar do cheddar");
        ItemPedido ip3 = new ItemPedido(prod2.getPreco(), 1, ped2, prod2, "No chessbacon colocar folhas de alface");
        ItemPedido ip4 = new ItemPedido(prod6.getPreco(), 3, ped2, prod6, "");
        ItemPedido ip5 = new ItemPedido(prod1.getPreco(), 1, ped3, prod1, "");
        ItemPedido ip6 = new ItemPedido(prod11.getPreco(), 3, ped3, prod11, "");
        ItemPedido ip7 = new ItemPedido(prod20.getPreco(), 1, ped3, prod20, "Nao colocar ervilha no HotDog");
        ItemPedido ip8 = new ItemPedido(prod5.getPreco(), 2, ped4, prod5, "Fazer o file bem passado");
        ItemPedido ip9 = new ItemPedido(prod12.getPreco(), 1, ped5, prod12, "");
        ItemPedido ip10 = new ItemPedido(prod25.getPreco(), 2, ped5, prod25, "");
        ItemPedido ip11 = new ItemPedido(prod22.getPreco(), 1, ped6, prod22, "");

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3, ip4));
        ped3.getItens().addAll(Arrays.asList(ip5, ip6, ip7));
        ped4.getItens().addAll(Arrays.asList(ip8));
        ped5.getItens().addAll(Arrays.asList(ip9, ip10));
        ped6.getItens().addAll(Arrays.asList(ip11));

        prod1.getItens().addAll(Arrays.asList(ip1, ip5));
        prod2.getItens().addAll(Arrays.asList(ip3));
        prod3.getItens().addAll(Arrays.asList(ip2));
        prod6.getItens().addAll(Arrays.asList(ip4));
        prod11.getItens().addAll(Arrays.asList(ip6));
        prod20.getItens().addAll(Arrays.asList(ip7));
        prod5.getItens().addAll(Arrays.asList(ip8));
        prod12.getItens().addAll(Arrays.asList(ip9));
        prod25.getItens().addAll(Arrays.asList(ip10));
        prod22.getItens().addAll(Arrays.asList(ip11));

        itemPedidoRepositoryPort.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5, ip6, ip7, ip8, ip9, ip10, ip11));

    }
}
