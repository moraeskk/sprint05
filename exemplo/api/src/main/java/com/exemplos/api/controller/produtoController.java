package com.exemplos.api.controller;

import com.exemplos.api.models.Produto;
import com.exemplos.api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/produto")
@RestController
public class produtoController {
    @Autowired
    private ProdutoService service;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastro de produtos", description = "Api para cadastragem de produtos.")
                @ApiResponse(responseCode = "201",description = "produto cadastrado")
                @ApiResponse(responseCode = "404",description = "Erro - não foi possivel cadastar o produto")
                @ApiResponse(responseCode = "500",description = "Erro inesperado")
    public ResponseEntity novoProduto(@RequestBody Produto codigo) {
        try {
            service.adicionar(codigo);
            return new ResponseEntity("Produto cadastrado com sucesso! ", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("Produto não cadastrado!tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @Operation(summary = "Lista produtos", description = "Api para listagem de produtos.")
    public ResponseEntity listarProdutos(Produto codigo) {

        try {
            service.listarProduto(codigo.getCodigo());
            return new ResponseEntity(service.listarProduto(codigo.getCodigo()), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity("Não foram encontrados produtos cadastrados", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{codigo}")
    @Operation(summary = "Lista produtos via codigo", description = "Api para listagem de produtos especificos.")
    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            service.buscaPorCodigo(codigo);

            return new ResponseEntity(service.buscaPorCodigo(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi encontrado nenhum produto cadastrado com o codigo enviado anteriormente,tente novamente!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{codigo}")
    @Operation(summary = "Altera produto", description = "Api para alterar algo no produto.")
    public ResponseEntity alterarProduto(@PathVariable Integer codigo, @RequestBody Produto produto) {
        try {
            service.update(codigo, produto);
            return new ResponseEntity("Produto alterado com sucesso! ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o produto!Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigo}")
    @Operation(summary = "Remove produtos", description = "Api para remoção de produtos.")
    public ResponseEntity removerProduto(@PathVariable Integer codigo) {
        try {
            service.remover(codigo);

            return new ResponseEntity("Produto removido com sucesso! ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel remover o produto!Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/nome")
    @Operation(summary = "lista produtos pelo nome")
    public ResponseEntity listarPorNome(@RequestParam(defaultValue = "") String busca){

        return new ResponseEntity(service.listarPornome(busca), HttpStatus.OK);

    }

}
