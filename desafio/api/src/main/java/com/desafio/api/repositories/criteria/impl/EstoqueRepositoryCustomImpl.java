package com.desafio.api.repositories.criteria.impl;

import com.desafio.api.models.EstoqueModel;
import com.desafio.api.repositories.criteria.EstoqueRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EstoqueRepositoryCustomImpl implements EstoqueRepositoryCustom {
    private EntityManager em;
    public EstoqueRepositoryCustomImpl(EntityManager manager) {
        this.em = manager;
    }

  @Override
    public List<EstoqueModel> listarHistorico(Integer id){

      CriteriaBuilder cb = this.em.getCriteriaBuilder();
      CriteriaQuery<EstoqueModel> cq = cb.createQuery(EstoqueModel.class);

      Root<EstoqueModel> estoqueRoot = cq.from(EstoqueModel.class);

      if (id != null){
          cq.select(estoqueRoot).where(cb.equal(estoqueRoot.get("produto"),id));
      }
      TypedQuery<EstoqueModel> typedResult = this.em.createQuery(cq);
      return typedResult.getResultList();
    }
}
