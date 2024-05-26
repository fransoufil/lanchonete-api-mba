package com.fiap.lanchoneteapi.core.domain.enums;

public enum StatusPagamentoEnum {
   PENDENTE (1, "Pendente"),
   QUITADO (2, "Quitado"),
   CANCELADO (3, "Cancelado");

   private final int codigo;
   private final String descricao;

   private StatusPagamentoEnum(int codigo, String descricao) {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   public int getCodigo() {
      return codigo;
   }

   public String getDescricao () {
      return descricao;
   }

   public static StatusPagamentoEnum toEnum(Integer cod) {

      if (cod == null) {
         return null;
      }

      for (StatusPagamentoEnum x : StatusPagamentoEnum.values()) {
         if (cod.equals(x.getCodigo())) {
            return x;
         }
      }

      throw new IllegalArgumentException("Id inválido: " + cod);
   }
}
