package com.desafio.api.controller;

import com.desafio.api.models.FuncionarioModel;
import com.desafio.api.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/funcionario")
@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar funcionario", description = "Função da api para cadastro de um novo funcionário")
    @ApiResponse(responseCode = "201",description = "Funcionario cadastrado com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500",description = "Erro inesperado")
    private ResponseEntity novoFuncionario(@RequestBody FuncionarioModel id) {
        try {
            service.adicionar(id);
            return new ResponseEntity("Funcionário cadastrado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel adicionar o funcionario ao banco de dados!Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/listar")
    @Operation(summary = "LIstar funcionarios", description = "função da api para listar todos os funcionário.")
    @ApiResponse(responseCode = "201",description = "Funcionarios listados com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500",description = "Erro inesperado")
    private ResponseEntity listarFuncionarios(FuncionarioModel id) {
        try {
            service.listarFuncionario(id.getId());
            return new ResponseEntity(service.listarFuncionario(id.getId()), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity("Não foram encontrados funcionários cadastrados para serem listados", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/aterar")
    @Operation(summary = "alterar funcionario", description = "Função da apí para alterar algo dentro de um funcionario")
    @ApiResponse(responseCode = "201",description = "Funcionario alterado com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500",description = "Erro inesperado")
    private ResponseEntity alterarFuncionario(@PathVariable Integer id, @RequestBody FuncionarioModel funcionario) {
        try {
            service.update(id, funcionario);
            return new ResponseEntity("funcionario alterado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel alterar o funcionario!Tente novamente", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(value = "/remover")
    @Operation(summary = "Remover funcionario",description = "Função da api para remoção de funcionario de uma empresa")
    @ApiResponse(responseCode = "201",description = "Funcionario deletado com sucesso!")
    @ApiResponse(responseCode = "404",description = "Erro!Tente novamente.")
    @ApiResponse(responseCode = "500",description = "Erro inesperado")
    private ResponseEntity removerFuncionario(@RequestBody Integer id) {
        try {
            service.remover(id);
            return new ResponseEntity("Funcionario removido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Não foi possivel remover o funcionario selecionado!Tente novamente", HttpStatus.BAD_REQUEST);
        }
    }
}

