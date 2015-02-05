package de.laliluna.library.struts.action;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import de.laliluna.library.Book;
import de.laliluna.library.BookDao;
import de.laliluna.library.BookDaoImp;
import de.laliluna.library.Common;
import de.laliluna.library.struts.CustomAction;

public class ListBooksAction extends Action {

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
		BookDao bookDao = null;
		List<Book> books = null;
		try
		{
			Context context = new InitialContext();
			bookDao = (BookDao) context.lookup(BookDaoImp.LocalJNDIName);
		} catch (NamingException e)
		{
			e.printStackTrace();
			throw new RuntimeException("error accessing EJB");

		}
		books = bookDao.findAll();
		request.setAttribute("books", books);
		return mapping.findForward(Common.SUCCESS);
	}
}
