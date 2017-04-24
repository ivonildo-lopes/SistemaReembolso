package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.pedidovenda.model.Categoria;
import com.algaworks.pedidovenda.repository.CategoriaDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class,value="categoriaConverter")
public class CategoriaConverter implements Converter {

	@Inject
	private CategoriaDAO categoriaDAO;
	
	public CategoriaConverter() {
		categoriaDAO = CDIServiceLocator.getBean(CategoriaDAO.class);
		//categoriaDAO = new CategoriaDAO();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if (value != null) {
			//Long id = new Long(value);
			Integer id = Integer.parseInt(value);
			retorno = categoriaDAO.pesquisarPorID(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Categoria categoria = (Categoria) value;
			return  categoria.getId() == null ?  null :  categoria.getId().toString();
		}
		
		return "";
		
	}

}
