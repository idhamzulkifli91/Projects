
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
    <title>Select customer</title>

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
        
        </tr>
        <c:forEach items="${requestScope.customers}" var="customer">
        <tr>
          <td>
            ${customer.id}
          </td>
          <td>
            ${customer.name}
          </td>
          <td>
          <c:url value="/selectCustomer.do" var="selectCustomerUrl">
          <c:param name="action">selectCustomer</c:param>
          <c:param name="id">${customer.id}</c:param>
          </c:url>
          <A href="${selectCustomerUrl}">Select Customer</A>
          </td>
        </tr>
        </c:forEach>
      </TABLE>
   
  </body>
</html>
