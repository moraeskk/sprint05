package com.desafio.api.repositories;

import com.desafio.api.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel,Integer> {
    public List<FuncionarioModel> findByNomeContainingIgnoreCase(String nome);
    public FuncionarioModel findByNomeAndCargo(String nome, String cargo);
}
