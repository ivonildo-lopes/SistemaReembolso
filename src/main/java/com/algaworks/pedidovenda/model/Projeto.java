package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
