package com.desafio.api.controller;

import com.desafio.api.models.EstoqueModel;
import com.desafio.api.models.FuncionarioModel;
import com.desafio.api.models.ProdutoModel;
import com.desafio.api.service.EstoqueService;
import com.desafio.api.service.FuncionarioService;
import com.desafio.api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/estoque")
@RestController
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @Autowired
    private ProdutoService pService;

    @Autowired
    private FuncionarioService fService;


    @PostMapping(value = "/novo")
    @Operation(summary = "cadastro de movimento", description = "Metodo da api para cadastro de movimento nos estoques")
    @ApiResponse(responseCode = "201", description = "Estoque cadastrado com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro - não foi possivel adicionar o estoque!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado")
    public ResponseEntity cadastrar(@RequestBody EstoqueModel movimento, @RequestParam Integer produtoId, @RequestParam Integer funcionarioResponsavel) {

        ProdutoModel produto = pService.listarPorCodigo(produtoId);
        movimento.setProduto(produto);

        FuncionarioModel funcionario = fService.pesquisarFuncionarioPorId(funcionarioResponsavel);
        movimento.setFuncionarioResponsavel(funcionario);

        try {
            service.cadastrarMovimento(movimento);
            return new ResponseEntity("Movimento cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel realizar o movimento!Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/listar")
    @Operation(summary = "Listar Estoques", description = "Metodo da api para listagem de estoques")
    @ApiResponse(responseCode = "201", description = "Listagem foi concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro - não foi possivel listar os produtos!Tente novamente. ")
    @ApiResponse(responseCode = "500", description = "Erro inesperado")
    public ResponseEntity listarEstoque(@PathVariable Integer id) {
        return new ResponseEntity(service.buscaPorCodigo(id), HttpStatus.OK);
    }

    @PutMapping(value = "/alterar/{id}")
    @Operation(summary = "Alterar estoque", description = "metodo da api para alterar algo no estoque")
    @ApiResponse(responseCode = "201", description = "A alteração do estoque foi concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "ERRO - não foi possivel alterar o estoque selecionado!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity alterarEstoque(@PathVariable Integer id, @RequestBody EstoqueModel estoque) {
        try {
            service.update(id, estoque);
            return new ResponseEntity("Estoque alterado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o estoque!Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/remover/{id}")
    @Operation(summary = "Remover estoque", description = "função da api para remoção de um estoque")
    @ApiResponse(responseCode = "201", description = " estoque removido com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity removerEstoque(@RequestParam Integer id) {
        try {
            service.remover(id);
            return new ResponseEntity("Estoque removido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel remover este produto!Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/consultaquantidade")
    @Operation(summary = "Consultor de quantidade", description = "função da api para consultar quantidade de um produto no estoque.")
    @ApiResponse(responseCode = "201", description = "Estoques consultados com sucesso!!")
    @ApiResponse(responseCode = "404", description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")
    public ResponseEntity consultorQuantidade(@RequestParam(defaultValue = "") Integer busca) {

        return new ResponseEntity(service.consultorQuantidade(busca), HttpStatus.OK);

    }

    @GetMapping("/historico")
    @Operation(summary = "Lista historico de movimentos do produto", description = "Faz a listagem de movimento de um produto que se encontra no estoque")
    @ApiResponse(responseCode = "201",description = "Listado com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500",description = "Erro inesperado")
    public ResponseEntity listarHistoricoDeMovimentos(Integer pid) {

        return new ResponseEntity(service.listarMovimentoPorHistorico(pid), HttpStatus.OK);

    }
}
