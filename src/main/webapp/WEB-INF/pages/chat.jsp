<%@ page contentType="text/html;charset=Windows-31J" session="true" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/struts-logic-el" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean-el" prefix="bean" %>
<%@ taglib uri="/tags/struts-html-el" prefix="html" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * chat.jsp
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
Jabber<br>
Chat<br>
<font color="green"><c:out value="${param['jid']}" /></font>
</center>

<hr>

<c:out value="${chat}" escapeXml="false" /><br>

<html:form action="/Chat?#bottom" method="POST" scope="request">
 <html:textarea property="message" /><br>
 <html:hidden property="jid" /><br>
 <html:submit property="function" value="Send" />
 <html:submit property="function" value="Reload" />
</html:form>

[ <font color="green"><c:out value="${param['jid']}" /></font> ]<br>

<html:link linkName="bottom" />
<li><html:link action="/Menu">Menu</html:link></li>
<li><html:link action="/Top">User List</html:link></li>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
