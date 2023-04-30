package com.desafio.api.repositories.criteria.params;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoModelFIlterParam {
    private String descricao;
    private BigDecimal precoVenda;
    private Integer saldoAtual;
}
