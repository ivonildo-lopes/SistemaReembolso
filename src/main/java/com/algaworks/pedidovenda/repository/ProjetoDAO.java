package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Projeto;

public class ProjetoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public Projeto salvar(Projeto projeto){
		return em.merge(projeto);
	}
	
	public void remover(Projeto projeto){
		em.remove(projeto);
	}
	
	public Projeto pesquisarPorID(Integer id) {
		return em.find(Projeto.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Projeto> listarTodos(){
		String jpql = "from Projeto order by nome";
		return em.createQuery(jpql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Projeto> pesquisarPorNome(String nome){
		String jpql = "from Projeto where upper(nome) like :nome";
		return em.createQuery(jpql).setParameter("nome","%"+ nome.toUpperCase()+"%").getResultList();
	}
	
}