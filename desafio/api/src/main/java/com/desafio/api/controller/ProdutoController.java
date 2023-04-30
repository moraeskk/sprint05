package com.desafio.api.controller;

import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.criteria.params.ProdutoModelFIlterParam;
import com.desafio.api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping(value = "/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro de produto", description = "Função da api para cadastro de produtos no banco de dados.")
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity novoProduto(@RequestBody ProdutoModel id) {

        try {
            service.adicionar(id);
            return new ResponseEntity("Produto cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possível cadastrar o produto!Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listagem de produtos", description = "Função da api para listar todos os produtos")
    @ApiResponse(responseCode = "201", description = "Todos os produtos foram listados com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity listarProdutos(ProdutoModel id) {

        try {
            service.listarProduto(id.getId());
            return new ResponseEntity(service.listarProduto(id.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foram encontrados produtos cadastrados para serem listados", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Altera produto", description = "Api para alterar algo no produto.")
    @ApiResponse(responseCode = "201", description = "Alteração concluida com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterarProduto(@PathVariable Integer id, @RequestBody ProdutoModel produto) {
        try {
            service.update(id, produto);
            return new ResponseEntity("Produto alterado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o produto!Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/remover/{id}")
    @Operation(summary = "Remoção de produto", description = "função da api para remover um produto.")
    @ApiResponse(responseCode = "201", description = "Produto removido com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity removerProduto(@RequestBody Integer id) {
        try {
            service.remover(id);
            return new ResponseEntity<>("Produto removido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possivel remover o produto selecionado!Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listarpositivos")
    @Operation(summary = "Lista os produtos positivos", description = "Faz a listagem dos produtos que se encontram com valor positivo")
    @ApiResponse(responseCode = "201", description = "Todos os produtos positivos foram listados com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity listarPositivo() {
        try {
            return new ResponseEntity<>(service.listarPositivo(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel listar todos os produtos positivos!Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/listarcadastrados")
    @Operation(summary = "Listar produtos cadastrados", description = "faz a filtragem dos produtos que se encontram dentro de um estoque")
    @ApiResponse(responseCode = "201", description = "Todos os produtos cadastrados foram listados com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado")
    public ResponseEntity listarCadastrados(@RequestParam(required = false) String descricao, @RequestParam(required = false) BigDecimal precoVenda, @RequestParam(required = false) Integer saldoAtual) {

        ProdutoModelFIlterParam produtoParams = new ProdutoModelFIlterParam();

        produtoParams.setDescricao(produtoParams.getDescricao());
        produtoParams.setSaldoAtual(produtoParams.getSaldoAtual());
        produtoParams.setPrecoVenda(produtoParams.getPrecoVenda());

        return new ResponseEntity<>(service.listarCadastrados(produtoParams), HttpStatus.OK);
    }


}

