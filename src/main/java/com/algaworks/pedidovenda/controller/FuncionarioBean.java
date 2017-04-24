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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.algaworks.pedidovenda.converter.FuncionarioConverter;
import com.algaworks.pedidovenda.converter.SetorConverter;
import com.algaworks.pedidovenda.entity.filtro.FuncionarioParaPesquisa;
import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.model.Setor;
import com.algaworks.pedidovenda.repository.PermissaoDAO;
import com.algaworks.pedidovenda.repository.SetorDAO;
import com.algaworks.pedidovenda.service.FuncionarioBO;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
// @ManagedBean
// @SessionScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private FuncionarioParaPesquisa filtro;
	private Funcionario funcionario;

	@Inject
	private FuncionarioBO funcionarioBO;

	private List<Funcionario> lista = new ArrayList<Funcionario>();

	@Inject
	private SetorDAO setorDAO;

	@Inject
	private PermissaoDAO permissaoDAO;

	private List<Setor> listaSetor = new ArrayList<Setor>();
	private List<Permissao> listaPermissao = new ArrayList<Permissao>();
	private SetorConverter setorConverter;

	private UploadedFile file;
	
	
	private FacesContext facesContext = FacesContext.getCurrentInstance();
	private ExternalContext externalContext = getFacesContext()
			.getExternalContext();
	private HttpServletResponse response = (HttpServletResponse) getExternalContext()
			.getResponse();

	private FuncionarioConverter funcionarioConverter;
	
	public FuncionarioBean() {
		this.filtro = new FuncionarioParaPesquisa();
		this.funcionario = new Funcionario();
		this.setorConverter = new SetorConverter();
		this.funcionarioConverter = new FuncionarioConverter();
	}

	
	
	
	// esse metodo é chamado antes de renderizar a pagina
	public void inicializar() { // carrega as categorias
		if (FacesUtil.isNotPostback()) {

			listaSetor = setorDAO.listarTodos();
			listaPermissao = permissaoDAO.listarTodos();
		}
	}

	public String alterar(Funcionario funcionario) {
		this.funcionario = funcionario;
		return "/privado/funcionario/form?faces-redirect=true";

	}

	public void salvar() {
		this.funcionario = this.funcionarioBO.salvar(funcionario);
		limpa();
	}

	public void pesquisar() {
		this.lista = this.funcionarioBO.pesquisarPorNome(this.filtro.getNome());
	}

	private void limpa() {
		this.funcionario = new Funcionario();
	}

//	public void enviaFoto(FileUploadEvent event) {
//		try {
//			byte[] foto = IOUtils.toByteArray(event.getFile().getInputstream());
//			this.funcionario.setFoto(foto);
//			
//			
//		} catch (Exception e) {
//			FacesUtil.ErrorMessage("Erro ao enviar foto");
//		}
//	}

	public List<Setor> completarSetor(String nome) {
		return this.setorDAO.pesquisarPorNome(nome);

	}

	public List<Permissao> completarPermissao(String nome) {
		return this.permissaoDAO.pesquisarPorNome(nome);

	}

	public void doUpload(FileUploadEvent event) {
		file = event.getFile();

		if (file != null) {

//			File file1 = new File("c:/img/",
//					file.getFileName());
			
			File file1 = new File("c:/workspace-estudo/ReembolsoManhattan/src/main/webapp/resources/imagesUpload/" +
			file.getFileName());
			
			try {
				FileOutputStream fos = new FileOutputStream(file1);
				fos.write(event.getFile().getContents());
				fos.close();

				this.funcionario.setNomeDaImagem(file.getFileName());
				
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
	}

	
	

	// get and set
	public FuncionarioParaPesquisa getFiltro() {
		return filtro;
	}

	public void setFiltro(FuncionarioParaPesquisa filtro) {
		this.filtro = filtro;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getLista() {
		return lista;
	}

	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}

	public SetorConverter getSetorConverter() {
		return setorConverter;
	}

	public List<Setor> getListaSetor() {
		return listaSetor;
	}

	public List<Permissao> getListaPermissao() {
		return listaPermissao;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
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




	public FuncionarioConverter getFuncionarioConverter() {
		return funcionarioConverter;
	}




	public void setFuncionarioConverter(FuncionarioConverter funcionarioConverter) {
		this.funcionarioConverter = funcionarioConverter;
	}

}
