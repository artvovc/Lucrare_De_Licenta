<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<layout:extends name="base.jsp">
    <layout:put block="header">
        <div class="company">
            <h1>
                Feedly
            </h1>
        </div>
        <div class="search-form">
            <form:form method="POST" action="/search" modelAttribute="requestSearch">
                <table>
                    <tr>
                        <td class="td-padding">
                            <form:input id="searchInput" path="key" placeholder="Input word key..."/>
                        </td>
                        <td class="td-padding">
                            <img src="<c:url value="/resources/images/search-icon.png"/>" width="25px"/>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </layout:put>
</layout:extends>
