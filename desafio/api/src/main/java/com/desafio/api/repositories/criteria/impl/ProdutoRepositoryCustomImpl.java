package com.desafio.api.repositories.criteria.impl;

import com.desafio.api.models.ProdutoModel;
import com.desafio.api.repositories.criteria.ProdutoRepositoryCustom;
import com.desafio.api.repositories.criteria.params.ProdutoModelFIlterParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepositoryCustomImpl implements ProdutoRepositoryCustom {

    private EntityManager entityManager;

    public ProdutoRepositoryCustomImpl (EntityManager manager){
        this.entityManager = manager;
    }

    @Override
    public List<ProdutoModel> listarPositivos(){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoModel> cq = cb.createQuery(ProdutoModel.class);

        Root<ProdutoModel> produtoRoot = cq.from(ProdutoModel.class);

        cq.select(produtoRoot).where(cb.gt(produtoRoot.get("SaldoAtual"),0));
        TypedQuery<ProdutoModel> typedResult = this.entityManager.createQuery(cq);

      return typedResult.getResultList();
    }

    @Override
    public List<ProdutoModel> listarCadastrados(ProdutoModelFIlterParam params) {
       CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
       CriteriaQuery<ProdutoModel> cq = cb.createQuery(ProdutoModel.class);

       Root<ProdutoModel> produtoRoot = cq.from(ProdutoModel.class);
       List<Predicate> predicates = new ArrayList<>();

       if (params.getDescricao()!= null){
           predicates.add(cb.like(produtoRoot.get("descricao"),"%" + params.getDescricao()+ "%"));
       }
       if (params.getPrecoVenda()!= null){
           predicates.add(cb.equal(produtoRoot.get("precoVenda"),params.getPrecoVenda()));
       }
       if(params.getSaldoAtual()!= null){
           predicates.add(cb.equal(produtoRoot.get("saldoAtual"),params.getSaldoAtual()));
       }
       if (predicates.isEmpty()){
           cq.where(predicates.stream().toArray(Predicate[]::new));
       }

        TypedQuery<ProdutoModel> typedResult = this.entityManager.createQuery(cq);
       return typedResult.getResultList();
    }


}
