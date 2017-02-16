<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="
      <c:url value="/resources/css/test.css"/> "/>
</head>
<body>

<layout:block name="body">
    <form:form method="POST" action="/nn/pet">
        <table>
            <tr>
                <td><form:label id="mycolor" path="username">username</form:label></td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td><form:label path="password">password</form:label></td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit"/>
                </td>
            </tr>
        </table>
    </form:form>
</layout:block>

</body>
</html>
