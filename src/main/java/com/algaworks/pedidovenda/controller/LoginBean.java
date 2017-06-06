package com.algaworks.pedidovenda.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@SessionScoped
//@ManagedBean
//@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	
	private ExternalContext externalContext = getFacesContext()
			.getExternalContext();
	
	private HttpServletRequest request = (HttpServletRequest) getExternalContext().getRequest();
			
	
	private HttpServletResponse response = (HttpServletResponse) getExternalContext()
			.getResponse();
	
	private String usuario;
	
	public LoginBean() {
		
	}

	public void preRender(){
		if("true".equals(request.getParameter("invalid"))){
			FacesUtil.ErrorMessage("Usuario ou senha Invalida");
		}
	}
	
	
	public void login() throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		//facesContext.responseComplete();
	}
	
	
//	public String logar() throws UnsupportedEncodingException{
//		
//		this.usuarioLogado = loginBo.logar(usuario,senha);
//		if(this.usuarioLogado != null){
//			return "/Home";			
//		}else{
//			return "/login";
//		}
//	}
	
//	public String efetuarLogin() throws UnsupportedEncodingException{
//		if(funcionarioDAO.logar(usuario,senha)){
//			usuarioLogado = funcionarioDAO.pesquisarPorUsuario(usuario);
//			this.usuarioLogado = (Funcionario) context.getExternalContext() .getSessionMap().get("usuarioLogado");
//			FacesUtil.InfoMessage("Seja bem vindo " + usuarioLogado.getNome());
//			return "/Home";
//		}else{
//			FacesUtil.ErrorMessage("Usuario ou senha Invalida");
//			return "/login";
//		}
//	}
	
//	public String sair(){
//		
//		this.usuarioLogado = loginBo.sair();
//		return "/login?faces-redirect=true";
//		
//	}
	
//	public String pegarNome(){
//		return this.usuarioLogado.getNome();
//	}
	
	//get and set
//	public Funcionario getFuncionario() {
//		return funcionario;
//	}
//
//	public void setFuncionario(Funcionario funcionario) {
//		this.funcionario = funcionario;
//	}

//	public Funcionario getUsuarioLogado() {
//		return usuarioLogado;
//	}
//
//	public void setUsuarioLogado(Funcionario usuarioLogado) {
//		this.usuarioLogado = usuarioLogado;
//	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public void setExternalContext(ExternalContext externalContext) {
		this.externalContext = externalContext;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


//	public String getSenha() {
//		return senha;
//	}
//
//
//	public void setSenha(String senha) {
//		this.senha = senha;
//	}
	
	
}
