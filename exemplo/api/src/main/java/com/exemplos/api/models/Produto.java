package com.exemplos.api.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity(name = "tb_produto")
public class Produto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Integer codigo;

@Column(length = 100)
private String descricao;

private BigDecimal valor;
private LocalDate validade;

}