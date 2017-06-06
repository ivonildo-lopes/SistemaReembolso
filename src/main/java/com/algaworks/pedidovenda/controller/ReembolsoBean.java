package com.algaworks.pedidovenda.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.algaworks.pedidovenda.converter.FuncionarioConverter;
import com.algaworks.pedidovenda.entity.filtro.FuncionarioParaPesquisa;
import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.Projeto;
import com.algaworks.pedidovenda.model.Reembolso;
import com.algaworks.pedidovenda.repository.CategoriaDAO;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.repository.ProjetoDAO;
import com.algaworks.pedidovenda.security.Seguranca;
import com.algaworks.pedidovenda.service.ReembolsoBO;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ReembolsoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reembolso reembolso;
	
	
	private Seguranca usuarioLogado;
	
	@Inject
	private ReembolsoBO reembolsoBO;
	
	@Inject
	private FuncionarioDAO funcionarioDAO;
	
	private List<Reembolso> lista = new ArrayList<Reembolso>();

	private Double somaValores = 0.0;
	
	private UploadedFile file;
	
	private FuncionarioConverter funcionarioConverter;
	private FuncionarioParaPesquisa filtro;
	
	private String estadoParaPagamento;

	@Inject
	private CategoriaDAO categoriaDAO;
	
	@Inject
	private ProjetoDAO projetoDAO;
	
	
	public ReembolsoBean() {
		this.reembolso = new Reembolso();
		this.funcionarioConverter = new FuncionarioConverter();
		this.filtro = new FuncionarioParaPesquisa();
		this.usuarioLogado = new Seguranca();		
	}
	
	// esse metodo é chamado antes de renderizar a pagina
	public void inicializar() { // carrega as categorias
		if (FacesUtil.isNotPostback()) {

			this.lista = funcionarioDAO.listarTodos();
		}
	}
	
	public void salvar(){
		if(this.usuarioLogado.getPermissaoUsuarioLogado().equals("BASICO"))
			this.reembolso.setFuncionario(this.usuarioLogado.getFuncionario());
		
		//this.reembolso.setFuncionario_cadastro(this.usuarioLogado.getFuncionario());
		this.reembolso = reembolsoBO.salvar(reembolso);
		this.lista.add(reembolso);
		
	}
	
	public void alterar(Reembolso reembolso){
		//this.reembolso.setFuncionario_alteracao(this.usuarioLogado.getFuncionario());
		this.reembolso = reembolsoBO.alterar(reembolso);
	}
	
	
	public void aprovar(Reembolso reembolso){
		//reembolso.setFuncionario_aprovador(this.usuarioLogado.getFuncionario());
		this.reembolso = reembolsoBO.aprovar(reembolso);
	}
	
	public void rejeitar(Reembolso reembolso){
		this.reembolso = reembolsoBO.rejeitar(reembolso);
	}
	
	public void esperar(Reembolso reembolso){
		this.reembolso = reembolsoBO.esperar(reembolso);
	}
	
	
	public void pagar(Reembolso reembolso){
		//reembolso.setFuncionario_pagador(this.usuarioLogado.getFuncionario());
		this.reembolso = reembolsoBO.pagamento(reembolso);
	}
	
	public void cancelarPagamento(Reembolso reembolso){
		this.reembolso = reembolsoBO.cancelaPagamento(reembolso);
	}
	
	
	
	public void novo(){
		this.reembolso = new Reembolso();
	}
	
	public void limpar(){
		lista = new ArrayList<Reembolso>();
		this.somaValores = 0.0;
		this.reembolso.setFuncionario(null);
	}
	

	public void pesquisar(){
		
		if(this.usuarioLogado.getPermissaoUsuarioLogado().equals("BASICO"))
			filtro.setNome(this.usuarioLogado.getNomeUsuarioLogado());
		
		lista = reembolsoBO.pesquisar(filtro.getNome());
	}
	
	public Double calculaTotal(){
		
		
		for(Reembolso reembolso: lista){
			this.somaValores += reembolso.getValor();
		}
		
		return this.somaValores;
	}
	
	
	public List<Funcionario> completarFuncionario(String nome){
		return this.funcionarioDAO.pesquisarPorNome(nome);
		
	}
	
	public List<Funcionario> completarAdministrador(String nome){
		return this.funcionarioDAO.pesquisarPorNomeAdministrador(nome);
	}
	
	public List<Categoria> completarCategoria(String nome){
		return this.categoriaDAO.pesquisarPorNome(nome);
	}
	
	public List<Projeto> completarProjeto(String nome){
		return this.projetoDAO.pesquisarPorNome(nome);
	}
	
	public void doUpload(FileUploadEvent event) throws IOException {
		file = event.getFile();

		if (file != null) {
			
			String caminho = "C:/desenvolvimento/projetos/SistemaReembolso/src/main/webapp/resources/imagesUpload/";
			//String caminho = "C:/desenvolvimento/imagens/";
			//String caminho1 = "c:/img/";
			
			File file1 = new File(caminho,
					file.getFileName());
			
//			File file1 = new File("c:/workspace-estudo/ReembolsoManhattan/src/main/webapp/resources/imagesUpload/"+
//			file.getFileName());
			
			try {
				FileOutputStream fos = new FileOutputStream(file1);
				fos.write(event.getFile().getContents());
				fos.close();

				this.reembolso.setNomeDaImagem(file.getFileName());
				
				FacesContext instance = FacesContext.getCurrentInstance();
				instance.addMessage("mensagens", new FacesMessage(
						FacesMessage.SEVERITY_INFO, file.getFileName()
								+ " anexado com sucesso", null));
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//cmd.execCommand("ipconfig");
		
	}
	
//	public Boolean acessoBasico(){
//		
//	}
	
	//get and set
	
	public List<Reembolso> getLista() {
		return lista;
	}

	public void setLista(List<Reembolso> lista) {
		this.lista = lista;
	}
	
	public Reembolso getReembolso() {
		return reembolso;
	}

	public void setReembolso(Reembolso reembolso) {
		this.reembolso = reembolso;
	}

	public FuncionarioConverter getFuncionarioConverter() {
		return funcionarioConverter;
	}

	public FuncionarioParaPesquisa getFiltro() {
		return filtro;
	}

	public void setFiltro(FuncionarioParaPesquisa filtro) {
		this.filtro = filtro;
	}

	public String getEstadoParaPagamento() {
		return estadoParaPagamento;
	}

	public void setEstadoParaPagamento(String estadoParaPagamento) {
		this.estadoParaPagamento = estadoParaPagamento;
	}

	public Seguranca getUsuarioLogado() {
		return usuarioLogado;
	}

}
