package com.exemplos.api.repositories;

import com.exemplos.api.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

        public List<Produto> findByDescricaoContainingIgnoreCase(String descricao);

        public Produto findByDescricaoAndValidade(String descricao, LocalDate validade);

}
