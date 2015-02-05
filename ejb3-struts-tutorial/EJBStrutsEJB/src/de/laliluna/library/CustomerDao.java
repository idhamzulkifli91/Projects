/**
 * 
 * @author Sebastian Hennebrueder
 * created Mar 15, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.List;

import javax.ejb.Local;

/**
 * @author hennebrueder
 *
 */
@Local
public interface CustomerDao {
	public void save(Customer customer);
	public void merge(Customer customer);
	public List findAll();
	public Customer findById(Integer id);
}
