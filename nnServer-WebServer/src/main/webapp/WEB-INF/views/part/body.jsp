<%@ taglib prefix="layout" uri="http://kwonnam.pe.kr/jsp/template-inheritance" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<layout:extends name="header.jsp">
    <layout:put block="body">
        <div>
            <c:choose>
                <c:when test="${container!=null}">
                    <a class="back" href="/">
                        <img id="back-icon" src="<c:url value="/resources/images/back.png"/>" width="50%">
                    </a>
                    <div class="searchContent">
                        <c:forEach var="item" items="${container.feeds}">
                            <div id="${item}" class="check-box-feed" style="height: 50px" onclick="checkFunction(this)">
                                <img src="${container.iconUrl}" width="20px" style="margin: 10px 15px 0 20px;">
                                    ${item}
                            </div>
                        </c:forEach>
                    </div>
                    <button id="addFeeds">Add</button>
                </c:when>
                <c:otherwise>
                    <table>
                        <tr>
                            <td>
                                <div class="frame">
                                    <iframe src="@" width="100%" height="100%"></iframe>
                                </div>
                            </td>
                            <td>
                                <div class="content">

                                </div>
                            </td>
                        </tr>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </layout:put>
</layout:extends>
