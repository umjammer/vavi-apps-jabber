<%@ page contentType="text/html;charset=Windows-31J" session="true" %>
<%@ taglib uri="/tags/struts-logic-el" prefix="logic" %>
<%@ taglib uri="/tags/struts-html-el" prefix="html" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * menu.jsp
 *
 * @author	Naohide Sano
 * @version	0.00	040222	nsano	initial version <br>
--%>

<html:html>

<head>
<title>Jabber</title>
</head>

<body>

<center>
Jabber Web<br>
Menu
</center>

<hr>

<li><html:link action="/Logout">Logout</html:link></li>
<li><html:link action="/Top">User List</html:link></li>
<li><html:link action="/Mailto">Mail Log</html:link></li>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
