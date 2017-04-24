package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class FuncionarioBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	public FuncionarioBO() {
	}

	
	@Transactional
	public Funcionario salvar(Funcionario funcionario){
		//Funcionario retorno = null;
		
		funcionario = funcionarioDAO.salvar(funcionario);
		
		if(funcionario != null){
			FacesUtil.InfoMessage("Funcionario cadastrado com sucesso!");
		}else{
			FacesUtil.ErrorMessage("Não foi possível salvar");
		}
		return funcionario;
				
	}
	
	
	public List<Funcionario> pesquisarPorNome(String nome){
		List<Funcionario> lista = null;
		
		if(nome.isEmpty()){
			FacesUtil.AvisoMessage("Informe um nome para pesquisar o funcionaio");
		}else{
			lista = this.funcionarioDAO.pesquisarPorNome(nome);
		}
		
		return lista;
	}
	
	
}
