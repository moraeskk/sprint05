package com.desafio.api.service;

import com.desafio.api.models.FuncionarioModel;
import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void adicionar(FuncionarioModel novoFuncionario) {
        funcionarioRepository.save(novoFuncionario);
    }

    public List<FuncionarioModel> listarFuncionario(Integer id) {
        return funcionarioRepository.findAll();
    }

    public void update(Integer id, FuncionarioModel funcionario) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.save(funcionario);
        }

    }

    public void remover(Integer id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
        }
    }

    public FuncionarioModel pesquisarFuncionarioPorId(Integer codigo) {

        Optional<FuncionarioModel> optFuncionarioModel = funcionarioRepository.findById(codigo);
        if (optFuncionarioModel.isEmpty()) {

            return null;
        }
        return optFuncionarioModel.get();
    }


}
