package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.algaworks.pedidovenda.util.Datas;

@Entity
public class Reembolso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name="id_funcionario", referencedColumnName="id")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name="id_administrador", referencedColumnName="id")
	private Funcionario administrador;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", referencedColumnName="id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name="id_projeto", referencedColumnName="id")
	private Projeto Projeto;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column(nullable=false)
	private Double valor;
	
	@Column(columnDefinition = "text")
	private String observacao;
	
	
	@Enumerated(EnumType.STRING)
	private EstadoParaPagamento estadoParaPagamento = EstadoParaPagamento.AGUARDANDO_APROVACAO;
	
	private String nomeDaImagem;
	
	
	@ManyToOne
	@JoinColumn(name="id_funcionario_aprovador",referencedColumnName="id")
	private Funcionario funcionario_aprovador;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_aprovacao;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario_pagador",referencedColumnName="id")
	private Funcionario funcionario_pagador;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_pagamento;
	
	
	@ManyToOne
	@JoinColumn(name="id_funcionario_cadastro",referencedColumnName="id")
	private Funcionario funcionario_cadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_cadastro;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario_alteracao",referencedColumnName="id")
	private Funcionario funcionario_alteracao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_alteracao;
	
	
	@Transient
	public Boolean isAprovadoOuRejeitado(){
		return this.estadoParaPagamento == EstadoParaPagamento.APROVADO || this.estadoParaPagamento == EstadoParaPagamento.REJEITADO;
	}
	
	@Transient
	public Boolean isAguardandoAprovacao(){
		return this.estadoParaPagamento == EstadoParaPagamento.AGUARDANDO_APROVACAO;
	}
	
	@Transient
	public Boolean isNaoAprovado(){
		return this.estadoParaPagamento != EstadoParaPagamento.APROVADO;
	}
	
	@Transient
	public Boolean isAprovado(){
		return this.estadoParaPagamento == EstadoParaPagamento.APROVADO;
	}
	
	@Transient
	public Boolean isPago(){
		return this.estadoParaPagamento == EstadoParaPagamento.PAGO;
	}
	
	@Transient
	public Boolean isNaoPago(){
		return !isPago();
	}
	
	@Transient
	public Boolean cancelaPagamento(){
			
			int dia_pagamento = Datas.pegarDia(getData_pagamento());
			int dia_prazo = Datas.pegarDia(new Date());
			
			if(dia_prazo - dia_pagamento == 0){
				return false;
			}else{
				return true;
			}
			
	}
	
	//get and set
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeDaImagem() {
		return nomeDaImagem;
	}

	public void setNomeDaImagem(String nomeDaImagem) {
		this.nomeDaImagem = nomeDaImagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reembolso other = (Reembolso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Funcionario getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Funcionario administrador) {
		this.administrador = administrador;
	}


	public EstadoParaPagamento getEstadoParaPagamento() {
		return estadoParaPagamento;
	}

	public void setEstadoParaPagamento(EstadoParaPagamento estadoParaPagamento) {
		this.estadoParaPagamento = estadoParaPagamento;
	}

	public Projeto getProjeto() {
		return Projeto;
	}

	public void setProjeto(Projeto projeto) {
		Projeto = projeto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Funcionario getFuncionario_aprovador() {
		return funcionario_aprovador;
	}

	public void setFuncionario_aprovador(Funcionario funcionario_aprovador) {
		this.funcionario_aprovador = funcionario_aprovador;
	}

	public Date getData_aprovacao() {
		return data_aprovacao;
	}

	public void setData_aprovacao(Date data_aprovacao) {
		this.data_aprovacao = data_aprovacao;
	}

	public Funcionario getFuncionario_pagador() {
		return funcionario_pagador;
	}

	public void setFuncionario_pagador(Funcionario funcionario_pagador) {
		this.funcionario_pagador = funcionario_pagador;
	}

	public Date getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Date data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Funcionario getFuncionario_cadastro() {
		return funcionario_cadastro;
	}

	public void setFuncionario_cadastro(Funcionario funcionario_cadastro) {
		this.funcionario_cadastro = funcionario_cadastro;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Funcionario getFuncionario_alteracao() {
		return funcionario_alteracao;
	}

	public void setFuncionario_alteracao(Funcionario funcionario_alteracao) {
		this.funcionario_alteracao = funcionario_alteracao;
	}

	public Date getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Date data_alteracao) {
		this.data_alteracao = data_alteracao;
	}

	
}
