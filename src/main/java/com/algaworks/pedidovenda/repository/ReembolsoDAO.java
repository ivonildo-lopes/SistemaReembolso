package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Reembolso;

public class ReembolsoDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public ReembolsoDAO(){
		
		
	}

	public Reembolso pesquisarPorID(Long id) {
		return em.find(Reembolso.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Reembolso> findByNameCliente(String nome) {
		String jpql = "from Reembolso where upper(funcionario.nome) like :funcionario";
		Query query = em.createQuery(jpql);
		query.setParameter("funcionario", "%"+nome.toUpperCase()+"%");
		return query.getResultList();
	}
	
	public Reembolso salvar(Reembolso reembolso) {
		
		return em.merge(reembolso);

	}

}
