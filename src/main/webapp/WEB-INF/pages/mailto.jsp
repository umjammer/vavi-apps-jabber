<%@ page contentType="text/html;charset=Windows-31J" session="true" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/struts-logic-el" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean-el" prefix="bean" %>
<%@ taglib uri="/tags/struts-html-el" prefix="html" %>
<%@ taglib uri="/WEB-INF/vavi.tld" prefix="vavi" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * mailto.jsp
 *
 * @author	Naohide Sano
 * @version	0.00	040128	nsano	initial version <br>
--%>

<html:html>

<head>
<title>Jabber</title>
</head>

<body>

<center>
Jabber Web
</center>

<hr>

<li><html:link action="/Menu">Menu</html:link></li>

<hr>

<logic:iterate id="entry" name="jabberClient" property="roster.entries">
  <vavi:imIcon user="${entry.name}" />
  <html:link action="/Chat"
        paramId="jid" paramName="entry" paramProperty="name" anchor="bottom">
    <c:out value="${entry.name}" />
  </html:link>
  <br>
</logic:iterate>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
