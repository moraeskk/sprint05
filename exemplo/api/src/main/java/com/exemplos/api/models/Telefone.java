package com.exemplos.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Telefone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private String numero;

    @ManyToOne
    private Pessoa pessoa;


}
