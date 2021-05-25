package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;

@Named
@RequestScoped
public class UserBean {
	
	private User user = new User();

	public void save() {
		
		new UserDAO().save(this.getUser());
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Usuário cadastrado com sucesso"));
	}
	
	public List<User> getUsers(){
		return new UserDAO().getAll();
	}
	
	public String login() {
		boolean exist =  new UserDAO().exist(this.user);
		if (exist) {
			FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().put("user", user);
			return "index?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance()
			.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR ,"Login inválido", "Erro"));
			return "login?faces-redirect=true";
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance()
		.getExternalContext().getSessionMap().remove("user");
		return "login?faces-redirect=true";
	}
	
	public void executar() {
		System.out.println("acionando o bean");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	


}