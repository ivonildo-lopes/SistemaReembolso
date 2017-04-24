package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.repository.PermissaoDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;


public class PermissaoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PermissaoDAO permissaoDAO;
	
	public PermissaoBO() {
	}
	
	@Transactional
	public Permissao salvar(Permissao permissao){
		
		permissao = permissaoDAO.salvar(permissao);
		
		if(permissao!=null){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Permissao salva com sucesso!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao tentar salvar o Permissao!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}
		
		return permissao;
	}
	
	
	public List<Permissao> pesquisar(String nome){
		List<Permissao> lista = null;
		
		if(nome.isEmpty()){
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "informe um nome para pesquisar!", "teste");
			FacesContext.getCurrentInstance().addMessage(null,msg);
		}else{
			lista = permissaoDAO.pesquisarPorNome(nome) ;
		}
		
		return lista;
	}

	public List<Permissao> listarTodos() {
		return permissaoDAO.listarTodos();
	}
}
