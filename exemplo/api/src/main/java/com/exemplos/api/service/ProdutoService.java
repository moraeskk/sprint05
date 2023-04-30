package com.exemplos.api.service;

import com.exemplos.api.models.Produto;
import com.exemplos.api.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void remover(Integer codigo) {
        if (produtoRepository.existsById(codigo)) {
            produtoRepository.deleteById(codigo);
        }
    }

    public void adicionar(Produto novoProduto) {
            produtoRepository.save(novoProduto);

    }

    public List<Produto> listarProduto(Integer codigo) {
        return produtoRepository.findAll();
    }

    public void update(Integer codigo, Produto produto) {

        if (produtoRepository.existsById(codigo)) {

            produtoRepository.save(produto);
        }
    }

    public Produto buscaPorCodigo(Integer codigo) {

        Optional<Produto> optProduto = produtoRepository.findById(codigo);
        if (optProduto.isEmpty()) {
            throw  new RuntimeException("produto não encontrado");
        }
        return optProduto.get();
    }
    public List<Produto> listarPornome(String descricaoDaBusca) {

        return produtoRepository.findByDescricaoContainingIgnoreCase(descricaoDaBusca);
    }
}
