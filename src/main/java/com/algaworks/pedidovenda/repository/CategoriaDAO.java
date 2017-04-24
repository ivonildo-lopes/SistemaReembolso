package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.pedidovenda.model.Categoria;

public class CategoriaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public Categoria salvar(Categoria categoria){
		return em.merge(categoria);
	}
	
	public void remover(Categoria categoria){
		em.remove(categoria);
	}
	
	public Categoria pesquisarPorID(Integer id) {
		return em.find(Categoria.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> listarTodos(){
		String jpql = "from Categoria order by nome";
		return em.createQuery(jpql).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> pesquisarPorNome(String nome){
		String jpql = "from Categoria where upper(nome) like :nome";
		return em.createQuery(jpql).setParameter("nome","%"+ nome.toUpperCase()+"%").getResultList();
	}
	
}