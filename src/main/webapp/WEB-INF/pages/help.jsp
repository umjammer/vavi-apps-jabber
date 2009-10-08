<%@ page contentType="text/html;charset=Windows-31J" session="false" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>

<%--
 * Copyright (c) 2004 by Naohide Sano, All Rights Reserved.
 *
 * Programmed by Naohide Sano
 *
 * help.jsp
 *
 * @author	Naohide Sano
 * @version	0.00	040301	nsano	initial version <br>
--%>

<html:html>

<head>
<title>Jabber</title>
</head>

<body>

<center>
Jabber Web<br>
Help
</center>

<hr>

<li>Login ... ログインします</li>
<li>Easy ... ログイン画面で入力した値を保持した画面を表示します、画面保存すれば次回ログイン時に楽できます</li>

<br>
<br>

<li><html:link action="/Auth.do" >戻る</html:link></li>

<%@ include file="footer.jsp" %>

</body>

</html:html>

<%-- --%>
