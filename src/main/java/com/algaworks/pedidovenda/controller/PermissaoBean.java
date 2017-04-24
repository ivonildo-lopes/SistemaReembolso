package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.entity.filtro.PermissaoParaPesquisa;
import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.service.PermissaoBO;

@Named
@ViewScoped
public class PermissaoBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PermissaoParaPesquisa permissaoParaPesquisa;
	
	@Inject
	private PermissaoBO PermissaoBO;
	
	private Permissao permissao;
	private List<Permissao> listaPermissao = new ArrayList<Permissao>();

	public PermissaoBean() {
		permissao = new Permissao();
		//listaPermissao = PermissaoBO.listarTodos();
		
		permissaoParaPesquisa = new PermissaoParaPesquisa();
	}

	public void listarTodos() {
		this.listaPermissao = PermissaoBO.listarTodos();
	}


	public void salvar() {
		this.permissao = this.PermissaoBO.salvar(permissao);
		//return LIST_SETOR;
		limpar();
	}

	public void limpar(){
		this.permissao = new Permissao();
	}
	
	public void pesquisar(){
		this.listaPermissao = this.PermissaoBO.pesquisar(this.permissaoParaPesquisa.getNome());
	}
	
	// get and set
	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public PermissaoBO getPermissaoBO() {
		return PermissaoBO;
	}

	public void setPermissaoBO(PermissaoBO PermissaoBO) {
		this.PermissaoBO = PermissaoBO;
	}

	public List<Permissao> getListaPermissao() {
		return listaPermissao;
	}

	public void setListaPermissao(List<Permissao> listaPermissao) {
		this.listaPermissao = listaPermissao;
	}

	public PermissaoParaPesquisa getPermissaoParaPesquisa() {
		return permissaoParaPesquisa;
	}

	public void setPermissaoParaPesquisa(PermissaoParaPesquisa permissaoParaPesquisa) {
		this.permissaoParaPesquisa = permissaoParaPesquisa;
	}

}
