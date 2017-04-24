package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.entity.filtro.CategoriaParaPesquisa;
import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.security.Seguranca;
import com.algaworks.pedidovenda.service.CategoriaBO;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	
	private static final long serialVersionUID = 1L;


	private Categoria categoria;
	private CategoriaParaPesquisa categoriaParaPesquisa;
	
	private Seguranca usuarioLogado;
	
	@Inject
	private CategoriaBO categoriaBO;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	private List<Categoria> listaCategoria = new ArrayList<>();
	
	
	
	public CategoriaBean() {
		this.usuarioLogado = new Seguranca();
		limpar();
	}
	
	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.listaCategoria = categoriaBO.listarTodos();		
		}
	}

	public void limpar() {
		categoria = new Categoria();
		this.categoriaParaPesquisa = new CategoriaParaPesquisa();
	}
	
	public void salvar(){
		//this.categoria.setFuncionario_cadastro(pegaUsuarioLogado());
		this.categoria.setFuncionario_cadastro(this.usuarioLogado.getFuncionario());
		
		this.categoria = this.categoriaBO.salvar(categoria);
		limpar();
	}
	
	public void alterar(Categoria categoria){
		this.categoria.setFuncionario_alteracao(this.usuarioLogado.getFuncionario());
		this.categoria = categoriaBO.alterar(categoria);
	}
	
	public void pesquisar(){
		this.listaCategoria = categoriaBO.pesquisarPorNome(categoriaParaPesquisa.getNome());
	}
	public boolean verificaEdicao(){
		return this.categoria.getId() != null;
	}

	
	public Funcionario pegaUsuarioLogado(){
		String nome = usuarioLogado.getNomeUsuarioLogado();
		return (Funcionario) this.funcionarioDAO.pesquisarPorUsuario(nome);
	}
	
	//get and set
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public CategoriaParaPesquisa getCategoriaParaPesquisa() {
		return categoriaParaPesquisa;
	}

	public void setCategoriaParaPesquisa(CategoriaParaPesquisa categoriaParaPesquisa) {
		this.categoriaParaPesquisa = categoriaParaPesquisa;
	}

	
	
}
