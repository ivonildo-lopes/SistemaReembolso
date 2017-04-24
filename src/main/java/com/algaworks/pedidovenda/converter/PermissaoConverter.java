package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Permissao;
import com.algaworks.pedidovenda.repository.PermissaoDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Permissao.class,value="permissaoConverter")
public class PermissaoConverter implements Converter {

	@Inject
	private PermissaoDAO permissaoDAO;
	
	public PermissaoConverter() {
		permissaoDAO = CDIServiceLocator.getBean(PermissaoDAO.class);
		//permissaoDAO = new PermissaoDAO();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Permissao retorno = null;
		
		if (value != null) {
			//Long id = new Long(value);
			Integer id = Integer.parseInt(value);
			retorno = permissaoDAO.pesquisarPorID(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Permissao permissao = (Permissao) value;
			return  permissao.getId() == null ?  null :  permissao.getId().toString();
		}
		
		return "";
		
	}

}
