package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Setor;
import com.algaworks.pedidovenda.repository.SetorDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Setor.class,value="setorConverter")
public class SetorConverter implements Converter {

	@Inject
	private SetorDAO setorDAO;
	
	public SetorConverter() {
		setorDAO = CDIServiceLocator.getBean(SetorDAO.class);
		//setorDAO = new SetorDAO();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Setor retorno = null;
		
		if (value != null) {
			//Long id = new Long(value);
			Integer id = Integer.parseInt(value);
			retorno = setorDAO.pesquisarPorID(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Setor setor = (Setor) value;
			return  setor.getId() == null ?  null :  setor.getId().toString();
		}
		
		return "";
		
	}

}
