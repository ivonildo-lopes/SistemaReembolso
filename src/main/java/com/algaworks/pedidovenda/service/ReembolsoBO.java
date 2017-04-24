package com.algaworks.pedidovenda.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.EstadoParaPagamento;
import com.algaworks.pedidovenda.model.Reembolso;
import com.algaworks.pedidovenda.repository.ReembolsoDAO;
import com.algaworks.pedidovenda.security.Seguranca;
import com.algaworks.pedidovenda.util.Datas;
import com.algaworks.pedidovenda.util.jpa.Transactional;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

public class ReembolsoBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReembolsoDAO reembolsoDAO;
	
	private Seguranca usuarioLogado;

	public ReembolsoBO() {
		usuarioLogado = new Seguranca();
	}

	@Transactional
	public Reembolso salvar(Reembolso reembolso) {
		reembolso.setFuncionario_cadastro(this.usuarioLogado.getFuncionario());
		reembolso.setData_cadastro(new Date());
		reembolso = reembolsoDAO.salvar(reembolso);

		if (reembolso != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Reembolso salvo com sucesso!", "teste");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erro ao tentar salvar o reembolso!", "teste");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		return reembolso;
	}

	@Transactional
	public Reembolso alterar(Reembolso reembolso) {
		FacesUtil.AvisoMessage("alterado");
		reembolso.setFuncionario_alteracao(this.usuarioLogado.getFuncionario());
		reembolso.setData_alteracao(new Date());
		reembolso = reembolsoDAO.salvar(reembolso);

		return reembolso;
	}

	@Transactional
	public Reembolso aprovar(Reembolso reembolso) {
		
//		if(!reembolso.getFuncionario().getSetor().getNome().equals(usuarioLogado.getSetorUsuarioLogado())){
//			FacesUtil.AvisoMessage("Só pode aprovar do mesmo setor");
//			System.out.println("nao pode "+usuarioLogado.getSetorUsuarioLogado());
//		}else{
//			System.out.println(usuarioLogado.getSetorUsuarioLogado());
			reembolso.setFuncionario_aprovador(this.usuarioLogado.getFuncionario());
			reembolso.setData_aprovacao(new Date());
			reembolso.setEstadoParaPagamento(EstadoParaPagamento.APROVADO);

			reembolso = reembolsoDAO.salvar(reembolso);
			FacesUtil.InfoMessage("Aprovado para pagamento");
		//}
		

		return reembolso;
	}

	@Transactional
	public Reembolso rejeitar(Reembolso reembolso) {

		reembolso.setEstadoParaPagamento(EstadoParaPagamento.REJEITADO);
		reembolso.setData_aprovacao(null);
		reembolso.setFuncionario_aprovador(null);
		reembolso = reembolsoDAO.salvar(reembolso);
		FacesUtil.ErrorMessage("Rejeitado para pagamento");
		return reembolso;
	}

	@Transactional
	public Reembolso esperar(Reembolso reembolso) {

		reembolso
				.setEstadoParaPagamento(EstadoParaPagamento.AGUARDANDO_APROVACAO);
		reembolso.setData_aprovacao(null);
		reembolso.setFuncionario_aprovador(null);
		reembolso = reembolsoDAO.salvar(reembolso);
		FacesUtil.AvisoMessage("O reembolso esta aguardado aprovação");

		return reembolso;
	}

	@Transactional
	public Reembolso pagamento(Reembolso reembolso) {


		int hora = Datas.pegarHora(new Date());

		if (hora < 15) {
			System.out.println("pode cadastrar");
			reembolso.setFuncionario_pagador(this.usuarioLogado.getFuncionario());
			reembolso.setData_pagamento(new Date());
			reembolso.setEstadoParaPagamento(EstadoParaPagamento.PAGO);
			FacesUtil.InfoMessage("Reembolso pago com sucesso.");
			reembolso = reembolsoDAO.salvar(reembolso);
		} else {
			FacesUtil
					.AvisoMessage("Não é possivel efetuar pagamento após as 15 horas");
			System.out.println("não poed cadastrar");
		}
		return reembolso;
	}

	@Transactional
	public Reembolso cancelaPagamento(Reembolso reembolso) {

		reembolso.setEstadoParaPagamento(EstadoParaPagamento.APROVADO);
		reembolso.setData_pagamento(null);
		reembolso.setFuncionario_pagador(null);
		FacesUtil.ErrorMessage("O pagamento foi cancelado");
		reembolso = reembolsoDAO.salvar(reembolso);
		return reembolso;
	}

	public List<Reembolso> pesquisar(String nome) {
		List<Reembolso> lista = null;


		lista = reembolsoDAO.findByNameCliente(nome);

		return lista;
	}
}
