package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.CategoriaDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class CategoriaBO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Transactional
	public Categoria salvar(Categoria categoria){
		categoria.setData_cadastro(new Date());
		categoria = categoriaDAO.salvar(categoria);
		
		if(categoria != null){
			FacesUtil.InfoMessage("Categoria salva com sucesso!");
		}else{
			FacesUtil.ErrorMessage("Não foi possível salvar");
		}
		
		return categoria;
	}
	
	@Transactional
	public void remover(Categoria categoria){
		categoriaDAO.remover(categoria);
	}
	
	
	@Transactional
	public Categoria alterar(Categoria categoria) {
		FacesUtil.AvisoMessage("alterado");
		categoria.setData_alteracao(new Date());
		categoria = this.categoriaDAO.salvar(categoria);

		return categoria;
	}
	
	
	public List<Categoria> listarTodos(){
		return categoriaDAO.listarTodos();
	}
	
	public List<Categoria> pesquisarPorNome(String nome){
		
		List<Categoria> lista = categoriaDAO.pesquisarPorNome(nome);
		
		if(lista.isEmpty())
			FacesUtil.AvisoMessage("Não foi encontrada nenhuma categoria");
		return lista;
	}

}
