package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Projeto;
import com.algaworks.pedidovenda.repository.ProjetoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ProjetoBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProjetoDAO projetoDAO;
	
	@Transactional
	public Projeto salvar(Projeto projeto){
		projeto.setData_cadastro(new Date());
		projeto = projetoDAO.salvar(projeto);
		
		if(projeto != null){
			FacesUtil.InfoMessage("Projeto salva com sucesso!");
		}else{
			FacesUtil.ErrorMessage("Não foi possível salvar");
		}
		
		return projeto;
	}
	
	@Transactional
	public void remover(Projeto projeto){
		projetoDAO.remover(projeto);
	}
	
	
	@Transactional
	public Projeto alterar(Projeto projeto) {
		FacesUtil.AvisoMessage("Alterado com sucesso");
		projeto.setData_alteracao(new Date());
		projeto = this.projetoDAO.salvar(projeto);

		return projeto;
	}
	
	
	public List<Projeto> listarTodos(){
		return projetoDAO.listarTodos();
	}
	
	public List<Projeto> pesquisarPorNome(String nome){
		
		List<Projeto> lista = projetoDAO.pesquisarPorNome(nome);
		
		if(lista.isEmpty())
			FacesUtil.AvisoMessage("Não foi encontrada nenhuma projeto");
		return lista;
	}

}
