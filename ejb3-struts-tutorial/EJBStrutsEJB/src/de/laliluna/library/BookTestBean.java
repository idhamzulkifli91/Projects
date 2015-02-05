/**
 * 
 * @author Sebastian Hennebrueder
 * created Mar 15, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author hennebrueder
 * 
 */
@Stateless
public class BookTestBean implements BookTestBeanLocal, BookTestBeanRemote {

	@PersistenceContext
	EntityManager em;

	public static final String RemoteJNDIName = BookTestBean.class
			.getSimpleName()
			+ "/remote";

	public static final String LocalJNDIName = BookTestBean.class
			.getSimpleName()
			+ "/local";

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.library.BookTestBean#test()
	 */
	public void testBook() {
		Book book = new Book(null, "My first bean book", "Sebastian");
		em.persist(book);
		Book book2 = new Book(null, "another book", "Paul");
		em.persist(book2);
		Book book3 = new Book(null, "EJB 3 developer guide, comes soon",
				"Sebastian");
		em.persist(book3);

		System.out.println("list some books");
		List someBooks = em.createQuery("from Book b where b.author=:name")
				.setParameter("name", "Sebastian").getResultList();
		for (Iterator iter = someBooks.iterator(); iter.hasNext();)
		{
			Book element = (Book) iter.next();
			System.out.println(element);
		}
		System.out.println("List all books");
		List allBooks = em.createQuery("from Book").getResultList();
		for (Iterator iter = allBooks.iterator(); iter.hasNext();)
		{
			Book element = (Book) iter.next();
			System.out.println(element);
		}
		System.out.println("delete a book");
		em.remove(book2);
		System.out.println("List all books");
		allBooks = em.createQuery("from Book").getResultList();
		for (Iterator iter = allBooks.iterator(); iter.hasNext();)
		{
			Book element = (Book) iter.next();
			System.out.println(element);
		}

	}

	public void testCustomer() {
		Customer customer1 = new Customer(null, "Sebastian the reader");
		em.persist(customer1);
		Customer customer2 = new Customer(null, "Sebastian the reader");
		em.persist(customer2);
		Customer customer3 = new Customer(null, "Sebastian the reader");
		em.persist(customer3);

		System.out.println("list some customers");
		List someCustomers = em.createQuery(
				"from Customer c where c.name like :name").setParameter("name",
				"Sebastian%").getResultList();
		for (Iterator iter = someCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
		}
		System.out.println("List all customers");
		List allCustomers = em.createQuery("from Customer").getResultList();
		for (Iterator iter = allCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
		}
		System.out.println("delete a customer");
		em.remove(customer2);
		System.out.println("List all customers");
		allCustomers = em.createQuery("from Customer").getResultList();
		for (Iterator iter = allCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
		}

	}

	public void testRelation() {

		Customer customer1 = new Customer(null, "Sebastian the reader");
		em.persist(customer1);
		Customer customer2 = new Customer(null, "Sebastian the reader");
		em.persist(customer2);
		Book book = new Book(null, "My first bean book", "Sebastian");
		em.persist(book);
		Book book2 = new Book(null, "another book", "Paul");
		em.persist(book2);
		Book book3 = new Book(null, "EJB 3 developer guide, comes soon",
				"Sebastian");
		em.persist(book3);

		// all objects are in peristence state, so any changes will be reflected
		// in the db
		/*
		 * important you must set both side of a relation, EJB3 will not update
		 * the other side automatically. Your objects in your application could
		 * be inconsistent, if you do not do this. This is only a problem when
		 * setting relations not when you load data.
		 */
		customer1.getBooks().add(book);
		book.setCustomer(customer1);
		customer2.getBooks().add(book2);
		customer2.getBooks().add(book3);

		System.out.println("list some customers");
		List someCustomers = em.createQuery(
				"from Customer c where c.name like :name").setParameter("name",
				"Sebastian%").getResultList();
		for (Iterator iter = someCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
			for (Iterator iterator = element.getBooks().iterator(); iterator
					.hasNext();)
			{
				Book aBook = (Book) iterator.next();
				System.out.println("--" + aBook);
			}
		}
		System.out.println("List all customers");
		List allCustomers = em.createQuery("from Customer").getResultList();
		for (Iterator iter = allCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
		}
		System.out.println("delete a customer");
		em.remove(customer2);
		System.out.println("List all customers");
		allCustomers = em.createQuery("from Customer").getResultList();
		for (Iterator iter = allCustomers.iterator(); iter.hasNext();)
		{
			Customer element = (Customer) iter.next();
			System.out.println(element);
		}
		System.out
				.println("let us see what is happening with the books after we deleted the customer");
		System.out.println("List all customers");
		List allBooks = em.createQuery("from Book").getResultList();
		for (Iterator iter = allBooks.iterator(); iter.hasNext();){
			Book aBook = (Book) iter.next();
			System.out.println(aBook);
			System.out.println("--"+aBook.getCustomer());
		}
	}

}
