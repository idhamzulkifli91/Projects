//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package de.laliluna.library.struts.action;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import de.laliluna.library.*;


public class SelectCustomerAction extends DispatchAction {

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
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerDao customerDao;
		try
		{
			Context context = new InitialContext();
			customerDao = (CustomerDao) context
					.lookup(CustomerDaoImp.LocalJNDIName);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		request.setAttribute("customers", customerDao.findAll());
		return mapping.findForward(Common.SELF);
	}

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward selectCustomer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CustomerDao customerDao;
		ActionMessages messages = new ActionMessages();
		try
		{
			Context context = new InitialContext();
			customerDao = (CustomerDao) context
					.lookup(CustomerDaoImp.LocalJNDIName);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// look for the customer in the db for the given id
		String sId = request.getParameter("id");
		if (sId == null)
		{
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"error.param.noentry"));

		}
		Customer customer = customerDao.findById(Integer.parseInt(sId));
		if (customer == null)
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
			"error.param.noentry"));
		
		// when we found a customer, save it in the session, else go back to the customer select
		if (messages.isEmpty()){
			request.getSession().setAttribute(Common.CUSTOMER_KEY, customer);
			
			return mapping.findForward(Common.SUCCESS);}
		else
		{
			request.setAttribute("customers", customerDao.findAll());
			saveErrors(request, messages);
			return mapping.findForward(Common.SELF);
		}

	}

}
