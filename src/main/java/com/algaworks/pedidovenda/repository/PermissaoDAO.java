package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class PermissaoDAO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	public PermissaoDAO() {
		//em = EntityManagerUtil.getEntityManager();
	}
	
	//consultas
	public Permissao pesquisarPorID(Integer id){
		return em.find(Permissao.class, id);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Permissao> listarTodos(){
		return em.createQuery("from Permissao order by nome").getResultList();
	}

	// crud
	@Transactional
	public Permissao salvar(Permissao permissao) {
//		Permissao retorno = null;
//		try {
//			em.clear();
//			em.getTransaction().begin();
//			
//			em.persist(permissao);
//			em.getTransaction().commit();
//			retorno = permissao;
//		} catch (Exception e) {
//			if (em.getTransaction().isActive()) {
//				em.getTransaction().begin();
//				em.getTransaction().rollback();
//			}
//		}
		
		return em.merge(permissao);
	}
	
	public void remover(Permissao permissao) {
		try {
			em.getTransaction().begin();
				em.remove(permissao);				
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().rollback();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Permissao> pesquisarPorNome(String nome) {
		String jpql = "from Permissao where upper(nome) like :nome";
		Query query = em.createQuery(jpql).setParameter("nome", "%"+nome.toUpperCase()+"%");
		return query.getResultList();
	}

}
