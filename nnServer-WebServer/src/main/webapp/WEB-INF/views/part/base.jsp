<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Feedly</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/> "/>
    <link rel="icon" type="image/png" href="<c:url value="/resources/icon/web.png"/>"/>
    <script src="<c:url value="/resources/javascript/jquery/jquery-3.1.1.min.js"/>"></script>
    <script src="<c:url value="/resources/javascript/onReady.js"/>"></script>
</head>
    <body>
        <header>
            <layout:block name="header"/>
        </header>
        <main>
            <layout:block name="body"/>
        </main>
        <footer>
            <layout:block name="footer"/>
        </footer>
    </body>
</html>
