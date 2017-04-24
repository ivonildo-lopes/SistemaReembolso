package com.algaworks.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.pedidovenda.model.Funcionario;
import com.algaworks.pedidovenda.repository.FuncionarioDAO;
import com.algaworks.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Funcionario.class, value = "funcionarioConverter")
public class FuncionarioConverter implements Converter {

	//@Inject
	private FuncionarioDAO funcionarioDAO;
	
	public FuncionarioConverter() {
		this.funcionarioDAO = (FuncionarioDAO) CDIServiceLocator.getBean(FuncionarioDAO.class);
	}
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Funcionario retorno = null;
		
		if (value != null) {
			//Long id = new Long(value);
			Integer id = Integer.parseInt(value);
			retorno = funcionarioDAO.pesquisarPorId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Funcionario funcionario = (Funcionario) value;
			return  funcionario.getId() == null ?  null :  funcionario.getId().toString();
		}
		
		return "";
		
	}

}
