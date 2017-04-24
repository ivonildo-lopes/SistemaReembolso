package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.Reembolso;

public class FuncionarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public FuncionarioDAO() {

	}

	public Funcionario salvar(Funcionario funcionario) {
		return em.merge(funcionario);
	}

	public Funcionario pesquisarPorId(Integer id) {
		return em.find(Funcionario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> pesquisarPorNome(String nome) {
		List<Funcionario> lista = null;

		String jpql = "from Funcionario where upper(nome) like :nome";
		Query query = em.createQuery(jpql).setParameter("nome",
				"%" + nome.toUpperCase() + "%");
		lista = query.getResultList();

		return lista;
	}

	public Funcionario pesquisarPorCPF(String cpf) {
		String jpql = "from Funcionario where cpf := cpf";
		Query query = em.createQuery(jpql).setParameter("cpf", cpf);
		return (Funcionario) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Reembolso> listarTodos() {
		String jpql = "from Funcionario order by nome";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	// informações de Login
	public Boolean logar(String usuario, String senha)
			throws UnsupportedEncodingException {

		Boolean retorno = false;

		Query query = em.createQuery("from Funcionario "
				+ " where usuario =:usuario" + " and senha =:senha "
				+ " and ativo = true");

		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);
		// query.setParameter("senha", Encripta.encripta(senha));

		if (query.getResultList().isEmpty())
			retorno = false;
		else
			retorno = true;

		return retorno;
	}

	public Funcionario pesquisarPorUsuario(String usuario) {
		Funcionario retorno = null;

		try {
			String jpql = "from Funcionario where usuario =:usuario";
			Query query = em.createQuery(jpql).setParameter("usuario", usuario);
			retorno = (Funcionario) query.getSingleResult();

		} catch (NoResultException e) {
			// TODO: handle exception
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> pesquisarPorNomeAdministrador(String nome) {
		List<Funcionario> lista = null;

		String jpql = "from Funcionario where upper(nome) like :nome and adm = true";
		Query query = em.createQuery(jpql).setParameter("nome",
				"%" + nome.toUpperCase() + "%");
		lista = query.getResultList();

		return lista;
	}

}
