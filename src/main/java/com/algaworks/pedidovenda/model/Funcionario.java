package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, length = 100)
	@NotEmpty(message = "O campo nome é obrigatório")
	@Length(max = 100, min = 3, message = "o nome do funcionario deve conter entre {min} e {max} caracteres ")
	private String nome;

	@Column(nullable = false, length = 14, unique = true)
	@NotEmpty(message = "o cpf é obrigatório")
	@Length(max = 14, message = "o campo CPF só pode conter {max} caracteres")
	private String cpf;

	@Column(nullable = false, length = 50, unique = true)
	@NotEmpty(message = "o email é obrigatório")
	@Length(max = 150, message = "o campo CPF só pode conter {max} caracteres")
	@Email(message = "digite um email válido")
	private String email;

//	@Column(columnDefinition = "numeric(10,2)")
//	private Double salario;

	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	private Boolean ativo = true;

//	@Lob
//	private byte[] foto;
//
//	@Transient
//	private StreamedContent imagem;
	
	
	private String nomeDaImagem;
	private String caminhoFoto;

	@Column(nullable = false, length = 12, unique = true)
	@NotEmpty(message = "o nome do usuario é obrigatório")
	@Length(max = 12, message = "o campo usuario só pode conter {max} caracteres")
	private String usuario;

	@Column(nullable = false, length = 12)
	@NotEmpty(message = "a senha é obrigatória")
	@Length(min = 4, message = "o campo senha deve conter pelo menos {min} caracteres")
	private String senha;

	
	// muitos funcionarios para um grupo
	@ManyToOne
	@JoinColumn(name = "permisao", referencedColumnName = "id")
	@NotNull(message = "A permisao deve ser informado")
	private Permissao permissao;

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	// muitos funcionarios para um grupo
	@JoinColumn(name = "setor", referencedColumnName = "id")
	@NotNull(message = "o setor deve ser informado")
	private Setor setor;
	
	private Boolean adm = false;
	
	
	//get and set
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Double getSalario() {
//		return salario;
//	}
//
//	public void setSalario(Double salario) {
//		this.salario = salario;
//	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

//	public byte[] getFoto() {
//		return foto;
//	}
//
//	public void setFoto(byte[] foto) {
//		this.foto = foto;
//	}
//	
//	public StreamedContent getImagem() {
//
//		if (this.getFoto() != null) {
//			return new DefaultStreamedContent(new ByteArrayInputStream(
//					this.getFoto()));
//		}
//		return new DefaultStreamedContent();
//	}
//
//	public void setImagem(StreamedContent imagem) {
//		this.imagem = imagem;
//	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getGrupo() {
		return permissao;
	}

	public void setGrupo(Permissao permissao) {
		this.permissao = permissao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
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
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
	}


	
}
