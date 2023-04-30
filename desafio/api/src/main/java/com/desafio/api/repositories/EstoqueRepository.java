package com.desafio.api.repositories;

import com.desafio.api.models.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EstoqueRepository extends JpaRepository<EstoqueModel,Integer> {

}
