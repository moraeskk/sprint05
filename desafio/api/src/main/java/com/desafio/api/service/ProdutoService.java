package com.desafio.api.service;

import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.ProdutoRepository;
import com.desafio.api.repositories.criteria.ProdutoRepositoryCustom;
import com.desafio.api.repositories.criteria.impl.ProdutoRepositoryCustomImpl;
import com.desafio.api.repositories.criteria.params.ProdutoModelFIlterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoRepositoryCustomImpl produtoRepositoryCustomImpl;
    @Autowired
    private ProdutoRepositoryCustom produtoRepositoryCustom;

    public void adicionar(ProdutoModel novoProduto) {

        produtoRepository.save(novoProduto);
    }

    public List<ProdutoModel> listarProduto(Integer id) {
        return produtoRepository.findAll();
    }

    public void update(Integer id, ProdutoModel produto) {

        if (produtoRepository.existsById(id)) {
            produtoRepository.save(produto);
        }
    }

    public void remover(Integer id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        }
    }

    public List<ProdutoModel> listarPositivo() {
        return produtoRepositoryCustomImpl.listarPositivos();
    }

    public List<ProdutoModel> listarCadastrados(ProdutoModelFIlterParam param) {
        return produtoRepositoryCustom.listarCadastrados(param);
    }


    public ProdutoModel listarPorCodigo(Integer codigo) {

        Optional<ProdutoModel> optProduto = produtoRepository.findById(codigo);
        if (optProduto.isEmpty()) {

            return null;
        }
        return optProduto.get();
    }
}
