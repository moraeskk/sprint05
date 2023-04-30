package com.desafio.api.repositories;

import com.desafio.api.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoModel,Integer> {

    public List<ProdutoModel> findBySaldoAtual(Integer SaldoAtual);

}
