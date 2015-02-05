/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 27, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.Map;

import javax.ejb.Remote;

/**
 * @author hennebrueder
 *
 */
@Remote
public interface LibraryCartRemote {
	public void addBook(Book book);
	public void addBook(Integer id);
	public void removeBook(Book book);
	public void removeBook(Integer id);
	public void clearCart();
	public Map getCart();
	// check out and save the borrow of the book
	public void checkOut(Customer customer);
	public void returnBook(Integer id);
}
