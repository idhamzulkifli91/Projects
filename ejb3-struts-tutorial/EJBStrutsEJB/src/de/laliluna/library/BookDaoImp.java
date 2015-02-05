/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 27, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import sun.security.krb5.internal.crypto.bo;

/**
 * @author hennebrueder
 * 
 */
@Stateless
public class BookDaoImp implements BookDao {

	@PersistenceContext
	private EntityManager em;

	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookDao#save(de.laliluna.example.ejb.Book)
	 */
	public void save(Book book) {
		log.debug("Persist book: " + book);
		em.persist(book);

	}

	public void merge(Book book) {
		em.merge(book);

	}

	public List findAll() {
		log.debug("find All books");
		return em.createQuery("from Book").getResultList();
	}

	public static final String RemoteJNDIName = BookDaoImp.class
			.getSimpleName()
			+ "/remote";

	public static final String LocalJNDIName = BookDaoImp.class.getSimpleName()
			+ "/local";

	public Book findById(Integer id) {
		return em.find(Book.class, id);
	}

	public List findByCustomer(Customer customer) {
		log.debug("find by customer");
		return em.createQuery("from Book b where b.customer = :customer")
				.setParameter("customer", customer).getResultList();
	}
}
