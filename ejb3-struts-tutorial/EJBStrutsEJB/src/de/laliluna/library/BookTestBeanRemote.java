package de.laliluna.library;

import javax.ejb.Remote;

@Remote
public interface BookTestBeanRemote {
	public void testBook();
	public void testCustomer();
	public void testRelation();
}
