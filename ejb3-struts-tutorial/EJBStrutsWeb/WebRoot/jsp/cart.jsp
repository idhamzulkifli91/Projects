<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Cart</title>
  </head>
  <body>
    <logic:messagesPresent>
      <html:messages id="message">
        <bean:write name="message" />
        <br>
      </html:messages>
      <br>
    </logic:messagesPresent>
    <jsp:include page="/jsp/navigation.jsp"></jsp:include>
    <br>
    <c:url var="checkOutUrl" value="manageCart.do">
      <c:param name="action">checkOut</c:param>
    </c:url>
    <a href="${checkOutUrl}">Checkout this cart</a>
    <br>
    <c:if test="${sessionScope.customer_key == null}">
      <h3>Warning! No customer selected you cannot checkout</h3>
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

      </tr>
      <c:forEach items="${sessionScope.cart.cart}" var="book">
        <tr>
          <td>
            ${book.value.id}
          </td>
          <td>
            ${book.value.author}
          </td>
          <td>
            ${book.value.title}
          </td>
          <td>
            <c:url value="/manageCart.do" var="removeFromCartUrl">
              <c:param name="action">removeFromCart</c:param>
              <c:param name="id">${book.value.id}</c:param>
            </c:url>
            <A href="${removeFromCartUrl}">Remove from Cart</A>
          </td>
        </tr>
      </c:forEach>
    </TABLE>
  </body>
</html>
