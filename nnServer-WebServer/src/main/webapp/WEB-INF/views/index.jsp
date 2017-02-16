<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<layout:extends name="part/base.jsp">
    <layout:put block="body">
        <h1>${command.username}</h1>
    </layout:put>
</layout:extends>