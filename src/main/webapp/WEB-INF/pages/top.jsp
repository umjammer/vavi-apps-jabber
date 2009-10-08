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
 * top.jsp
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
  <vavi:imIcon user="${entry.user}" />
  <html:link action="/Chat"
        paramId="jid" paramName="entry" paramProperty="user" anchor="bottom">
    <c:out value="${entry.name}" />
  </html:link>
  <br>
</logic:iterate>


<%--
<logic:equal name="jabberClient" property="roster.groupCount" value="0">
</logic:equal>

<logic:notEqual name="jabberClient" property="roster.groupCount" value="0">
  <logic:iterate id="group" name="jabberClient" property="roster.groups">
    <logic:iterate id="entry" name="jabberClient" property="roster.entries">
    </logic:iterate>
  </logic:iterate>
</logic:notEqual>
--%>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
