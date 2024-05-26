package com.fiap.lanchoneteapi.application.ports.services;

import com.fiap.lanchoneteapi.core.domain.enums.StatusPagamentoEnum;
import com.fiap.lanchoneteapi.core.domain.enums.StatusPedidoEnum;

public interface FakeCheckoutServicePort {

    StatusPedidoEnum atualizaStatusPedido (StatusPedidoEnum statusPedidoAtual);

    StatusPagamentoEnum atualizaStatusPagamento (StatusPagamentoEnum statusPagamentoAtual);
}
