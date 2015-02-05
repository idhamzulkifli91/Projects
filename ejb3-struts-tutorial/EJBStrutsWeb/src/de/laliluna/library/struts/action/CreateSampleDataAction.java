//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package de.laliluna.library.struts.action;

import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.laliluna.library.*;
import de.laliluna.library.struts.CustomAction;

public class CreateSampleDataAction extends Action {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		BookDao bookDao;
		CustomerDao customerDao;
		Random r = new Random();
		try
		{
			Context context = new InitialContext();
			bookDao = (BookDao) context.lookup(BookDaoImp.LocalJNDIName);
			customerDao = (CustomerDao) context.lookup(CustomerDaoImp.LocalJNDIName);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Book book = new Book(null, "EJB 3 Developer "
				+ r.nextInt(100), "Sebastian");
		bookDao.save(book);
		Customer customer = new Customer(null, "Sebastian "+r.nextInt(100));
		customerDao.save(customer);
		return mapping.findForward(Common.SUCCESS);
	}

}
