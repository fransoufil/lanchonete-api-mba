package com.fiap.lanchoneteapi.application.ports.services;

public interface IFilaServicePort {

    Integer novaPosicaoFila(Integer posicaoAtual);

    Integer verificaPosicaoFila();

}
