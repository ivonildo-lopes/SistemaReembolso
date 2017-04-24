package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.entity.filtro.SetorParaPesquisa;
import com.algaworks.pedidovenda.model.Setor;
import com.algaworks.pedidovenda.service.SetorBO;

@Named
@ViewScoped
public class SetorBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String FORM_SETOR = "/privado/setor/formSetor?faces-redirect=true";
	private static String LIST_SETOR = "/privado/setor/listSetor?faces-redirect=true";

	private SetorParaPesquisa setorParaPesquisa;
	
	@Inject
	private SetorBO SetorBO;
	private Setor setor;
	private List<Setor> listaSetor = new ArrayList<Setor>();

	public SetorBean() {
		setor = new Setor();
		//listaSetor = SetorBO.listarTodos();
		
		setorParaPesquisa = new SetorParaPesquisa();
	}

	public void listarTodos() {
		this.listaSetor = SetorBO.listarTodos();
	}

	public String novoSetor() {
		setor = new Setor();
		return FORM_SETOR;
	}

	public String cancelar() {
		return LIST_SETOR;
	}

	public String listarSetor() {
		return LIST_SETOR;
	}

	// CRUD
	public String remover(Setor setor) {
		//this.SetorBO.remover(setor);
		return LIST_SETOR;
	}

	public String alterar(Setor setor) {
		this.setor = setor;
		return FORM_SETOR;
	}

	public void salvar() {
		this.setor = this.SetorBO.salvar(setor);
		//return LIST_SETOR;
		limpar();
	}

	public void limpar(){
		this.setor = new Setor();
	}
	
	public void pesquisar(){
		this.listaSetor = this.SetorBO.pesquisar(this.setorParaPesquisa.getNome());
	}
	
	// get and set
	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public SetorBO getSetorBO() {
		return SetorBO;
	}

	public void setSetorBO(SetorBO SetorBO) {
		this.SetorBO = SetorBO;
	}

	public List<Setor> getListaSetor() {
		return listaSetor;
	}

	public void setListaSetor(List<Setor> listaSetor) {
		this.listaSetor = listaSetor;
	}

	public SetorParaPesquisa getSetorParaPesquisa() {
		return setorParaPesquisa;
	}

	public void setSetorParaPesquisa(SetorParaPesquisa setorParaPesquisa) {
		this.setorParaPesquisa = setorParaPesquisa;
	}

}
