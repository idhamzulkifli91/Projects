/**
 * 
 * @author Sebastian Hennebrueder
 * created Mar 15, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author hennebrueder
 *
 */
@Stateless
public class CustomerDaoImp implements CustomerDao {

	@PersistenceContext
	private EntityManager em;
	
	public static final String RemoteJNDIName =  CustomerDaoImp.class.getSimpleName() + "/remote";

	public static final String LocalJNDIName =  CustomerDaoImp.class.getSimpleName() + "/local";
	
	/* (non-Javadoc)
	 * @see de.laliluna.example.ejb.CustomerDao#save(de.laliluna.example.ejb.Book)
	 */
	public void save(Customer customer) {
		em.persist(customer);

	}

	/* (non-Javadoc)
	 * @see de.laliluna.example.ejb.CustomerDao#reattach(de.laliluna.example.ejb.Book)
	 */
	public void merge(Customer customer) {
	em.merge(customer);

	}

	/* (non-Javadoc)
	 * @see de.laliluna.example.ejb.CustomerDao#findAll()
	 */
	public List findAll() {
		
		return em.createQuery("from Customer").getResultList();
	}

	/* (non-Javadoc)
	 * @see de.laliluna.example.ejb.CustomerDao#findById(java.lang.Integer)
	 */
	public Customer findById(Integer id) {
		Customer customer = em.find(Customer.class, id);
		return customer;
	}

}
