/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 28, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import de.laliluna.library.Common;

/**
 * @author hennebrueder
 * 
 */
@Stateful
public class LibraryCart implements LibraryCartLocal {
	@PersistenceContext
	private EntityManager em;

	private Logger log = Logger.getLogger(this.getClass());

	private Map<Integer, Book> books = new HashMap<Integer, Book>();

	public static final String RemoteJNDIName = LibraryCart.class
			.getSimpleName()
			+ "/remote";

	public static final String LocalJNDIName = LibraryCart.class
			.getSimpleName()
			+ "/local";

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookCartLocal#addBook(de.laliluna.example.ejb.Book)
	 */
	public void addBook(Book book) {
		if (books.get(book.getId()) == null)
			books.put(book.getId(), book);

	}

	public void addBook(Integer id) {
		if (books.get(id) == null)
		{
			BookDao bookDao;
			try
			{
				Context context = new InitialContext();
				bookDao = (BookDao) context.lookup(BookDaoImp.LocalJNDIName);
			} catch (NamingException e)
			{
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			Book book = bookDao.findById(id);
			if (book != null)
				books.put(id, book);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookCartLocal#removeBook(de.laliluna.example.ejb.Book)
	 */
	public void removeBook(Book book) {
		books.remove(book.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookCartLocal#removeBook(de.laliluna.example.ejb.Book)
	 */
	public void removeBook(Integer id) {
		if (id != null)
			books.remove(id);

	}

	@Remove
	// remove the statefull bean after this method is called
	public void checkOut(Customer customer) {
		BookDao bookDao;
		CustomerDao customerDao;
		try
		{
			Context context = new InitialContext();
			bookDao = (BookDao) context.lookup(BookDaoImp.LocalJNDIName);
			customerDao = (CustomerDao) context
					.lookup(CustomerDaoImp.LocalJNDIName);

		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		log.debug("checkout for: " + customer);
		for (Iterator iter = books.keySet().iterator(); iter.hasNext();)
		{
			Book book = books.get(iter.next());
			log.debug("checkout: " + book);
			book.setCustomer(customer);
			customer.getBooks().add(book);
			bookDao.merge(book);
		}
	}

	public void returnBook(Integer id) {
		BookDao bookDao;
		CustomerDao customerDao;
		try
		{
			Context context = new InitialContext();
			bookDao = (BookDao) context.lookup(BookDaoImp.LocalJNDIName);
			customerDao = (CustomerDao) context
					.lookup(CustomerDaoImp.LocalJNDIName);

		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Book book = bookDao.findById(id);
		if (book != null)
		{
			Customer customer = book.getCustomer();
			customer.getBooks().remove(book);
			book.setCustomer(null);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookCartLocal#clearCart()
	 */
	public void clearCart() {
		books.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.laliluna.example.ejb.BookCartLocal#getCart()
	 */
	public Map getCart() {
		return books;
	}

}
