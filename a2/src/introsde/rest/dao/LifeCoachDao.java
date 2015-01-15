package introsde.rest.dao;
import introsde.rest.model.Person;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public enum LifeCoachDao {
	instance;
	private EntityManagerFactory emf;
	
	private LifeCoachDao() {
		if (emf!=null) {
			emf.close();
		}
		
		try{
			System.out.println("--> EntityManagerFactory creating..");
			emf = Persistence.createEntityManagerFactory("a2");
			System.out.println("--> EntityManagerFactory created..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public EntityManager createEntityManager() {
		try {
			EntityManager em = emf.createEntityManager();
			System.out.println("--> creating EntityManager...");			
			return em;
		} catch (Exception e) {
			System.out.println("--> createEntityManager failed !!!");
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	// Person related operations could also directly go into the "Person" Model
	// Check Methods in LifeStaus as example
	public static Person getPersonById(Long personId) {
		EntityManager em = instance.createEntityManager();
		Person p = em.find(Person.class, personId);
		instance.closeConnections(em);
		return p;
	}
	
	public static List<Person> getAll() {
		EntityManager em = instance.createEntityManager();
	    List<Person> list = em.createNamedQuery("Person.findAll", Person.class).getResultList();
	    instance.closeConnections(em);
	    return list;
	}
	
	// add other database global access operations

}
