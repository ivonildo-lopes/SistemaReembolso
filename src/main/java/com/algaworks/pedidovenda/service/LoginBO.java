package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class LoginBO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	public Funcionario logar(String usuario, String senha) throws UnsupportedEncodingException{
//		Funcionario func = null;
//		
//		Funcionario funcionarioLogado = funcionarioDAO.logar(usuario,senha);
//		
//		if( funcionarioLogado == null){
//			FacesUtil.AvisoMessage("Usuario ou senha invalida");
//		}else{
//			func = funcionarioLogado;
//			FacesUtil.InfoMessage("Seja bem vindo");
//		}
//		
//		return func;
		
		Funcionario retorno = null;
		if(funcionarioDAO.logar(usuario, senha)){
			retorno = funcionarioDAO.pesquisarPorUsuario(usuario);
			FacesUtil.InfoMessage("Seja bem vindo");
		}
		
		return retorno;
	}
	
	public Funcionario sair(){
		FacesUtil.AvisoMessage("Ate a proxima");
		return null;
	}

}
