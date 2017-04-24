package com.algaworks.pedidovenda.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.pedidovenda.model.Funcionario;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	
	public UsuarioSistema(Funcionario funcionario,
			Collection<? extends GrantedAuthority> authorities) {
		super(funcionario.getUsuario(), funcionario.getSenha(), authorities);
			this.funcionario = funcionario;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	

}
