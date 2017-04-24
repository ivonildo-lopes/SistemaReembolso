package com.algaworks.pedidovenda.model;

public enum EstadoParaPagamento {

	
	AGUARDANDO_APROVACAO("AGUARDANDO APROVACAO"),APROVADO("APROVADO"),REJEITADO("REJEITADO"),PAGO("PAGO");
	
	private String descricao;
	
	private EstadoParaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	
}
