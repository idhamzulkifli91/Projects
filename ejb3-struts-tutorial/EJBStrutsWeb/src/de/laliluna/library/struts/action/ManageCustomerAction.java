
package de.laliluna.library.struts.action;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import de.laliluna.library.*;


public class ManageCustomerAction extends DispatchAction {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward unspecified(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

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
		Customer customer = (Customer) request.getSession().getAttribute(Common.CUSTOMER_KEY);
		if (customer != null)
			request.setAttribute("books", bookDao.findByCustomer(customer));
		return mapping.findForward(Common.SUCCESS);
	}
	
	public ActionForward returnBook(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		ActionMessages actionMessages = new ActionMessages();
		HttpSession session = request.getSession();
		// first try to get an existing cart from the current session
		LibraryCartLocal cartLocal = (LibraryCartLocal) session
				.getAttribute("cart");

		// when there is no cart create a new one and save it in the session
		if (cartLocal == null)
		{
			try
			{
				Context context = new InitialContext();
				cartLocal = (LibraryCartLocal) context
						.lookup(LibraryCart.LocalJNDIName);

				session.setAttribute("cart", cartLocal);
			} catch (NamingException e)
			{
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		String sId = request.getParameter("id");
		if (sId == null)
		{
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("error.param.noentry"));
		} else
		{
			Integer id = Integer.parseInt(sId);
			cartLocal.returnBook(id);
		}
		if (!actionMessages.isEmpty())
			saveErrors(request, actionMessages);
		return mapping.findForward(Common.SUCCESS);
			
		
		}
	

}

