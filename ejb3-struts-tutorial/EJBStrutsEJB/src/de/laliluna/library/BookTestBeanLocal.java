package de.laliluna.library;

import javax.ejb.Local;

@Local
public interface BookTestBeanLocal {
	public void testBook();
	public void testCustomer();
	public void testRelation();
}
