package com.desafio.api.models;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;

@Data
@Entity(name = "tb_produto")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 255)
    private String descricao;

    @Column(precision = 12, scale = 2)
    private BigDecimal precoVenda;

   @Column(name = "saldo_atual")
    private Integer saldoAtual;

}
