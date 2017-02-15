<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--<spring:url value="/resources/css/test.css" var="crunchifyCSS" />--%>
    <%--<link href="${crunchifyCSS}" rel="stylesheet" />--%>

    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/test.css"/> "/>

</head>
<body>
<h1>hello css h1</h1>
<h2>hello css h2</h2>
Hello


</body>
</html>
