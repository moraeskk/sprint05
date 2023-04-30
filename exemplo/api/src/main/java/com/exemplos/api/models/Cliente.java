package com.exemplos.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long id;

    @Column(length = 150)
    private String razaoSocial;

    @Column(name ="nom_fantasia",length = 200)
    private String nomeFantasia;

    @Column(length = 20)
    private String numeroDocumento;

    @Column(name = "responsavel",length = 100)
    private String nomeResponsavel;
}