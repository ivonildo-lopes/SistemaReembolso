package com.algaworks.pedidovenda.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String usuario)
			throws UsernameNotFoundException {
		
		FuncionarioDAO funcionarioDAO = CDIServiceLocator.getBean(FuncionarioDAO.class);
		Funcionario funcionario = funcionarioDAO.pesquisarPorUsuario(usuario);
		UsuarioSistema user = null; //implementa userDetails
		
		if(funcionario != null){
			user = new UsuarioSistema(funcionario, getPermissao(funcionario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getPermissao(
			Funcionario funcionario) {
		
		List<SimpleGrantedAuthority> permissoes = new ArrayList<SimpleGrantedAuthority>();
		
		//for(Permissao permissao : funcionario.getPermissao())
		Permissao permissao = funcionario.getPermissao();
		
		permissoes.add(new SimpleGrantedAuthority(permissao.getNome().toUpperCase()));
		return permissoes;
	}

}
