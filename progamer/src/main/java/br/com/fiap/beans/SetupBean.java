package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;

@Named
@RequestScoped
public class SetupBean {
	
	private Setup setup = new Setup();

	public void save() {
		new SetupDAO().save(this.setup);
		this.setup = new Setup();
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Setup cadastrado com sucesso"));
	}
	
	public List<Setup> getSetups(){
		return new SetupDAO().getAll();
	}
	
	public void executar() {
		System.out.println("acionando o bean");
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}
	
	


}