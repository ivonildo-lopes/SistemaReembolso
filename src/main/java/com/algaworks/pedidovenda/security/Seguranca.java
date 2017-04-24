package com.algaworks.pedidovenda.security;

import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.algaworks.pedidovenda.model.Funcionario;

@Named
@RequestScoped
public class Seguranca {

	private FacesContext facesContext = FacesContext.getCurrentInstance();

	private ExternalContext externalContext = getFacesContext()
			.getExternalContext();

	public String getNomeUsuarioLogado() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null)
			nome = usuarioLogado.getFuncionario().getNome();

		return nome;
	}

	public String getPermissaoUsuarioLogado() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getFuncionario().getPermissao().getNome();
		}
		return nome;
	}
	
	
	public String getSetorUsuarioLogado() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getFuncionario().getSetor().getNome();
		}
		return nome;
	}

	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}
	
	
	public Funcionario getFuncionario(){
		Funcionario usuario  = getUsuarioLogado().getFuncionario();
		return usuario;
	}

	public Boolean acessoBasico(){
		return this.externalContext.isUserInRole("BASICO");
	}
	
	public Boolean diferenteAcessoBasico(){
		return !acessoBasico();
	}
	
	public Boolean acessoFinanceiro(){
		return this.externalContext.isUserInRole("FINANCEIRO");
	}
	
	public Boolean acessoAprovador(){
		return this.externalContext.isUserInRole("APROVADOR");
	}

	public Boolean acessoAdministrador(){
		return this.externalContext.isUserInRole("ADMINISTRADOR");
	}
	
	public Boolean acessoAprovacao(){
		return this.externalContext.isUserInRole("APROVADOR") || this.externalContext.isUserInRole("ADMINISTRADOR");
	}
	
	public Boolean acessoVisualPagamento(){
		return this.externalContext.isUserInRole("APROVADOR") 
				|| this.externalContext.isUserInRole("ADMINISTRADOR")
				|| this.externalContext.isUserInRole("FINANCEIRO");
	}
	
	public Boolean acessoPagamento(){
		return     this.externalContext.isUserInRole("ADMINISTRADOR")
				|| this.externalContext.isUserInRole("FINANCEIRO");
	}
	
	public Boolean naoAcessoPagamento(){
		return !acessoPagamento();
	}
	
	//get and set
	public FacesContext getFacesContext() {
		return facesContext;
	}
}
