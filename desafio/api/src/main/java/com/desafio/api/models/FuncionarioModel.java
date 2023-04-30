package com.desafio.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_funcionario_responsavel")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 40)
    private String nome;
    @Column(length = 50)
    private String cpf;
    @Column(length = 20)
    private String cargo;

}
