package com.desafio.api.service;

import com.desafio.api.models.EstoqueModel;
import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.EstoqueRepository;
import com.desafio.api.repositories.ProdutoRepository;
import com.desafio.api.repositories.criteria.EstoqueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    EstoqueRepository estoqueRepository;
    @Autowired
    EstoqueRepositoryCustom estoqueRepositoryCustom;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoService pService;

    public void cadastrarMovimento(EstoqueModel novoMovimento) {

        ProdutoModel produto = pService.listarPorCodigo(novoMovimento.getProduto().getId());

        if (novoMovimento.getTipoMovimento().equals("E")) {

            produto.setSaldoAtual(produto.getSaldoAtual() + novoMovimento.getQuantidade());
            pService.update(produto.getId(), produto);
        } else {
            produto.setSaldoAtual(produto.getSaldoAtual() - novoMovimento.getQuantidade());
            pService.update(produto.getId(), produto);
        }

    }


    public Object buscaPorCodigo(Integer id) {

        Optional<EstoqueModel> optEstoque = estoqueRepository.findById(id);
        if (optEstoque.isEmpty()) {

            throw new RuntimeException("Estoque Não encontrado");

        }
        return optEstoque.get();
    }


    public void update(Integer id, EstoqueModel movimento) {

        if (estoqueRepository.existsById(id)) {

            estoqueRepository.save(movimento);
        }

    }

    public void remover(Integer id) {
        if (estoqueRepository.existsById(id)) {
            estoqueRepository.deleteById(id);
        }
    }

    public List<ProdutoModel> consultorQuantidade(Integer saldoAtual) {

        return produtoRepository.findBySaldoAtual(saldoAtual);
    }

    public List<EstoqueModel> listarMovimentoPorHistorico(Integer Id) {

        return estoqueRepositoryCustom.listarHistorico(Id);

    }
}
