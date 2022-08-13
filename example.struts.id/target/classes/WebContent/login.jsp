<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html>
<html:form action="/Login">
<head>
<meta charset="ISO-8859-1">
<title>Login FORM</title>
</head>
<body>
	<html:text name="LoginForm" property="username"/>
	<html:password name="LoginForm" property="password"/>
</body>
</<html:form>