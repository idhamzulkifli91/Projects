
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'selectBooks.jsp' starting page</title>
  </head>
  <body>
<jsp:include page="/jsp/navigation.jsp"></jsp:include>
      <TABLE border="1">
        <tr>
          <td>
            id
          </td>
          <td>
            author
          </td>
          <td>
            title
          </td>
          <td></td>
          <td>
           Borrowed to
           </td>
        </tr>
        <c:forEach items="${requestScope.books}" var="book">
        <tr>
          <td>
            ${book.id}
          </td>
          <td>
            ${book.author}
          </td>
          <td>
            ${book.title}
          </td>
          <td>
          <c:if test="${book.customer == null}">
          <c:url value="/manageCart.do" var="addToCartUrl">
          <c:param name="action">addToCart</c:param>
          <c:param name="id">${book.id}</c:param>
          </c:url>
          <A href="${addToCartUrl}">Add to Cart</A>
          </c:if>
          </td>
          <TD>
           <c:if test="${book.customer != null}"> ${book.customer.name}
           </c:if>
          </TD>
        </tr>
        </c:forEach>
      </TABLE>
  </body>
</html>
