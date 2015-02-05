
<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table> <TR>
<TD>
<html:link action="selectCustomer">Select a customer</html:link>
</TD>
<TD>
<html:link action="manageCustomer">Manage customer</html:link>
</TD>
<TD>
<html:link action="listBooks">List Books</html:link>
</TD>
<TD>
<html:link action="createSampleData">Create Sample Data</html:link>
</TD>
<TD>
<html:link action="manageCart">Show Cart</html:link>
</TD>
<TD>
<html:link action="logout">Log out</html:link>
</TD>
</TR>
</table>
