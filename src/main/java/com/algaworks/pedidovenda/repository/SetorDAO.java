package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Setor;

public class SetorDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public SetorDAO() {
	}
	
	//consultas
	public Setor pesquisarPorID(Integer id){
		return em.find(Setor.class, id);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> listarTodos(){
		return em.createQuery("from Setor order by nome").getResultList();
	}

	// crud
	public Setor salvar(Setor setor) {
//		Setor retorno = null;
//		try {
//			em.getTransaction().begin();
//			
//			if (setor.getId() == null) {
//				em.persist(setor);
//			} else
//				em.merge(setor);
//
//			retorno = setor;
//			em.getTransaction().commit();
//		} catch (Exception e) {
//			if (em.getTransaction().isActive()) {
//				em.getTransaction().begin();
//				em.getTransaction().rollback();
//			}
//		}
		
		return em.merge(setor);
	}
	
	public void remover(Setor setor) {
		try {
			em.getTransaction().begin();
				em.remove(setor);				
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Setor> pesquisarPorNome(String nome) {
		List<Setor> lista = null;
			String jpql = "from Setor where upper(nome) like :nome";
				Query query = em.createQuery(jpql).setParameter("nome", "%"+nome.toUpperCase()+"%");
				lista =  query.getResultList();
		return lista;
	}

}
