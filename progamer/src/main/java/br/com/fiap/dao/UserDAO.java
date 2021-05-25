package br.com.fiap.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFacade;

public class UserDAO {
	
	private EntityManager manager = EntityManagerFacade.get();

	public void save(User user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.close();
		
	}

	public List<User> getAll() {
		String jpql = "SELECT u from User u";
		TypedQuery<User> createQuery = manager.createQuery(jpql, User.class);
		return createQuery.getResultList();
	}

	public boolean exist(User user) {
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE "
				+ "email=:email AND "
				+ "password=:password", User.class);
		
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		
		User result;
		try {
			result = query.getSingleResult();
			if (result != null) return true;
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public User findById(Long id) {
		return manager.find(User.class, id);
	}

	public void update(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
		
	}

	public void deleteById(Long id) {
		manager.remove(id);
		
	}

}