package com.algaworks.pedidovenda.util.jsf;

import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FacesProducer {
	
	@Produces
	@javax.enterprise.context.RequestScoped
	public FacesContext getFacesContext(){
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@javax.enterprise.context.RequestScoped
	public ExternalContext getExternalContext(){
		return getFacesContext().getExternalContext();
	}
	
	@Produces
	@javax.enterprise.context.RequestScoped
	public HttpServletRequest getHttpServletRequest(){
		return ((HttpServletRequest) getExternalContext().getRequest());
	}
	
	@Produces
	@javax.enterprise.context.RequestScoped
	public HttpServletResponse getHttpServletResponse(){
		return ((HttpServletResponse) getExternalContext().getResponse());
	}
}
