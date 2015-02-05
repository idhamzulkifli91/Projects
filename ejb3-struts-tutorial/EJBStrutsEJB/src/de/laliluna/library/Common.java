/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 27, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

/**
 * @author hennebrueder
 *
 */
public class Common {
	private Common(){
		
	}
	
	public static final String JNDI_PREFIX = "LearnEJB3/";
	
	// forward used as success
	public static final String SUCCESS = "success";
//	 forward used for failure
	public static final String FAILURE = "failure";
//	 forward used for self 
	public static final String SELF = "self";
	
	// session key for customer
	public static final String CUSTOMER_KEY = "customer_key";
	
//	 session key for cart
	public static final String CART_KEY = "cart";
}
