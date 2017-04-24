package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Setor;
import com.algaworks.pedidovenda.repository.SetorDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;


public class SetorBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SetorDAO SetorDAO;
	
	public SetorBO() {
	}
	
	@Transactional
	public Setor salvar(Setor Setor){
		
		Setor = SetorDAO.salvar(Setor);
		
		if(Setor!=null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Setor salvo com sucesso!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao tentar salvar o Setor!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}
		
		return Setor;
	}
	
	
	public List<Setor> pesquisar(String nome){
		List<Setor> lista = null;
		
		if(nome.isEmpty()){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informe um nome para pesquisar!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}else{
			lista = SetorDAO.pesquisarPorNome(nome) ;
		}
		
		return lista;
	}

	public List<Setor> listarTodos() {
		return SetorDAO.listarTodos();
	}
}
