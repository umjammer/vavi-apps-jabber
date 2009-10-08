<%@ page contentType="text/html;charset=Windows-31J" session="false" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * login.jsp
 *
 * @author	Naohide Sano
 * @version	0.00	040208	nsano	initial version <br>
--%>

<html:html>

<head>
 <title>Jabber</title>
</head>

<body>

<center>
Jabber Web<br>
Login
</center>

<hr>

<html:errors />

<html:form action="/Login.do" method="POST" scope="request">
 User Name<br>
 <html:text property="username" maxlength="16" /><br>
 Password<br>
 <html:password property="password" maxlength="16" /><br>
 Resource<br>
 <html:text property="resource" maxlength="16" /><br>
 Jabber Server<br>
 <html:text property="server" maxlength="16" /><br>
 Use SSL
 <html:checkbox property="usessl" /><br>
 <html:submit property="function" value="Login" />
 <c:if test="${param['function'] != 'Easy'}">
  <html:submit property="function" value="Easy" /><br>
 </c:if>

</html:form>

<c:if test="${param['function'] != 'Easy'}">
 <li><html:link action="/Help">Help</html:link></li>
 </c:if>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
