package de.laliluna.library;

import java.util.List;

import javax.ejb.Local;

import org.jboss.annotation.ejb.LocalBinding;

@Local
public interface BookDao {
	public void save(Book book);
	public void merge(Book book);
	public List findAll();
	public List findByCustomer(Customer customer);
	public Book findById(Integer id);
	
}
