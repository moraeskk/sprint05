package com.desafio.api.repositories.criteria;

import com.desafio.api.models.EstoqueModel;
import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.criteria.params.ProdutoModelFIlterParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepositoryCustom {
    List<EstoqueModel> listarHistorico(Integer id);
}
