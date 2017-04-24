package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Projeto;
import com.algaworks.pedidovenda.repository.ProjetoDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Projeto.class,value="projetoConverter")
public class ProjetoConverter implements Converter {

	@Inject
	private ProjetoDAO projetoDAO;
	
	public ProjetoConverter() {
		projetoDAO = CDIServiceLocator.getBean(ProjetoDAO.class);
		//projetoDAO = new ProjetoDAO();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Projeto retorno = null;
		
		if (value != null) {
			//Long id = new Long(value);
			Integer id = Integer.parseInt(value);
			retorno = projetoDAO.pesquisarPorID(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Projeto projeto = (Projeto) value;
			return  projeto.getId() == null ?  null :  projeto.getId().toString();
		}
		
		return "";
		
	}

}
