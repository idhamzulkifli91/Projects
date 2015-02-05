
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Manage customer</title>
  </head>

  <body>
<jsp:include page="/jsp/navigation.jsp"></jsp:include>
  <c:if test="${sessionScope.customer_key == null}">
      <h3>Warning! No customer selected.</h3>
    </c:if>
    <c:if test="${sessionScope.customer_key != null}">
      Selected customer: <c:out value="${sessionScope.customer_key.name}"></c:out>
    </c:if>
    <br>
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
          <c:url value="/manageCustomer.do" var="manageCustomerUrl">
          <c:param name="action">returnBook</c:param>
          <c:param name="id">${book.id}</c:param>
          </c:url>
          <A href="${manageCustomerUrl}">Return book</A>
          </td>
        </tr>
        </c:forEach>
      </TABLE>
   
  </body>
</html>
