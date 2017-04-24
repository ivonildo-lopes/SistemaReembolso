package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.entity.filtro.ProjetoParaPesquisa;
import com.algaworks.pedidovenda.model.Projeto;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.security.Seguranca;
import com.algaworks.pedidovenda.service.ProjetoBO;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProjetoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;


	private Projeto projeto;
	private ProjetoParaPesquisa projetoParaPesquisa;
	
	private Seguranca usuarioLogado;
	
	@Inject
	private ProjetoBO projetoBO;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	private List<Projeto> listaProjeto = new ArrayList<>();
	
	
	
	public ProjetoBean() {
		this.usuarioLogado = new Seguranca();
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.listaProjeto = projetoBO.listarTodos();		
		}
	}

	public void limpar() {
		projeto = new Projeto();
		this.projetoParaPesquisa = new ProjetoParaPesquisa();
	}
	
	public void salvar(){
		//this.projeto.setFuncionario_cadastro(pegaUsuarioLogado());
		this.projeto.setFuncionario_cadastro(this.usuarioLogado.getFuncionario());
		
		this.projeto = this.projetoBO.salvar(projeto);
		limpar();
	}
	
	public void alterar(Projeto projeto){
		this.projeto.setFuncionario_alteracao(this.usuarioLogado.getFuncionario());
		this.projeto = projetoBO.alterar(projeto);
	}
	
	public void pesquisar(){
		this.listaProjeto = projetoBO.pesquisarPorNome(projetoParaPesquisa.getNome());
	}
	public boolean verificaEdicao(){
		return this.projeto.getId() != null;
	}

	
	public Funcionario pegaUsuarioLogado(){
		String nome = usuarioLogado.getNomeUsuarioLogado();
		return (Funcionario) this.funcionarioDAO.pesquisarPorUsuario(nome);
	}
	
	//get and set
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	public List<Projeto> getListaProjeto() {
		return listaProjeto;
	}

	public void setListaProjeto(List<Projeto> listaProjeto) {
		this.listaProjeto = listaProjeto;
	}

	public ProjetoParaPesquisa getProjetoParaPesquisa() {
		return projetoParaPesquisa;
	}

	public void setProjetoParaPesquisa(ProjetoParaPesquisa projetoParaPesquisa) {
		this.projetoParaPesquisa = projetoParaPesquisa;
	}

	
	
}
