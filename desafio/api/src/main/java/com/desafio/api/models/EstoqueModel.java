package com.desafio.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity(name = "tb_estoque_movimento")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @ManyToOne
    @JoinColumn(name = "funcionario_responsavel")
    private FuncionarioModel funcionarioResponsavel;

    @Column(name = "data_e_hora")
    private Timestamp dataHora;

    @Column(length = 1)
    private String tipoMovimento;

    @Column(length = 300)
    private Integer quantidade;

}
