<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Gradle plugin summary</title>
    </head>
    <body>
        <table style="width:100%">
            <tr align="center">
                <td>
                    <img src="<c:url value="/images/gradle_logo.png"/>">
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr align="center">
                <td>
                    <c:choose>
                        <c:when test="${not empty error}">
                            <h1><c:out value="${error}"/></h1>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${not empty summary.exceptionMessage}">
                                    <h1>We are experiencing an issue</h1>
                                    <br/>
                                    <code><c:out value="${summary.exceptionMessage}"/></code>
                                </c:when>
                                <c:otherwise>
                                    <h1>Number of plugins: ${summary.count}</h1>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:if test="${not empty buildInfo}">
            <tr align="center">
                <td>
                    Version <c:out value="${buildInfo.version}"/> (Built on: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${buildInfo.timestamp}" />)
                </td>
            </tr>
            </c:if>
        </table>
    </body>
</html>