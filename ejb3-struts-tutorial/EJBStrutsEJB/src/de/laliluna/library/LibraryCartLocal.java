package de.laliluna.library;

import java.util.Map;


import javax.ejb.Local;
@Local
public interface LibraryCartLocal {
	/**
	 * ad a book to the cart
	 * @param book
	 */
	public void addBook(Book book);
	/**
	 * find a Book for the give Id and add it to the cart
	 * @param id
	 */
	public void addBook(Integer id);
	/**
	 * remove a book from the cart
	 * @param book
	 */
	public void removeBook(Book book);
	/**
	 *  find a Book for the give Id and remove it from the cart
	 * @param id
	 */
	public void removeBook(Integer id);
	/**
	 * remove all books from the cart
	 *
	 */
	public void clearCart();
	/**
	 * return the cart as java.util.Map containing the id of the book as key and the book itself as value.
	 * @return
	 */
	public Map getCart();
	/** check out and save the borrow of the book
	 * mark all books as borrowed by the specified customer, destroy the stateful session bean afterwards
	 * @param customer
	 */
	public void checkOut(Customer customer);
	/**
	 * mark a borrowed book as returned which is equivalent to removing the relation between customer and book
	 * @param id
	 */
	public void returnBook(Integer id);
}
