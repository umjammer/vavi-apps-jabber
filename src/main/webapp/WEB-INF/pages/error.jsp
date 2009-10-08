<%@ page contentType="text/html;charset=Windows-31J" session="false" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * error.jsp
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
Jabber Web<br>
Error
</center>

<hr>

<html:errors />
<br>
<br>

<html:link forward="jabber.top" >Return to Top</html:link>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
