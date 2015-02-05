//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

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

/**
 * MyEclipse Struts Creation date: 03-15-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action parameter="action"
 * @struts.action-forward name="success" path="/jsp/cart.jsp"
 */
public class ManageCartAction extends DispatchAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods



	public ActionForward addToCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActionMessages actionMessages = new ActionMessages();
		BookDao bookDao;
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
			cartLocal.addBook(id);
		}
		if (!actionMessages.isEmpty())
			saveErrors(request, actionMessages);
		return mapping.findForward(Common.SELF);
	}
	
	public ActionForward removeFromCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ActionMessages actionMessages = new ActionMessages();
		
		// first try to get an existing cart from the current session
		LibraryCartLocal cartLocal = (LibraryCartLocal) session
				.getAttribute(Common.CART_KEY);

		// when there is no cart there is nothing to remove so no further actions
		if (cartLocal == null)
		{
			return mapping.findForward(Common.SELF); 
		}
		// read the id and try to remove the book for this id from the cart
		String sId = request.getParameter("id");
		if (sId == null)
		{
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE,
					new ActionMessage("error.param.noentry"));
		} else
		{
			Integer id = Integer.parseInt(sId);
			cartLocal.removeBook(id);
		}
		if (!actionMessages.isEmpty())
			saveErrors(request, actionMessages);
		return mapping.findForward(Common.SELF);
	}

	@Override
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward(Common.SELF);
	}
	
	public ActionForward checkOut(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ActionMessages actionMessages = new ActionMessages();
		
		// first try to get an existing cart from the current session
		LibraryCartLocal cartLocal = (LibraryCartLocal) session
				.getAttribute(Common.CART_KEY);
		Customer customer = (Customer) session.getAttribute(Common.CUSTOMER_KEY);
		if (cartLocal == null || customer == null){
			actionMessages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("error.checkout.missing_cart_customer"));
			saveErrors(request,actionMessages);
			return mapping.findForward(Common.SELF);
		}
		else{
			cartLocal.checkOut(customer);
			// checkOut is configured to delete the stateful bean, so me must delete the reference to it as well
			session.removeAttribute(Common.CART_KEY); 
		return mapping.findForward(Common.SUCCESS);
		}
	}
	
	
}
