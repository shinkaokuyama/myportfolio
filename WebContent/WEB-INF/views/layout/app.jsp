<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>家つくりナビ</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                <c:choose>
                    <c:when test="${sessionScope.login_user.admin_flag == 1}">
                    <h1><a href="<c:url value='/' />">家つくりナビ</a></h1>&nbsp;&nbsp;&nbsp;
                    </c:when>
                    <c:otherwise>
                    <h1><a href="<c:url value='/processes/index' />">家つくりナビ</a></h1>&nbsp;&nbsp;&nbsp;
                    </c:otherwise>
                </c:choose>
                </div>
                <c:if test="${sessionScope.login_user != null}">
                    <div id="user_name">
                        <c:out value="${sessionScope.login_user.name}" />&nbsp;さん&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>
            <div id="content">
                ${param.content}
            </div>
            <div id="footer">
                by Shinka Okuyama
            </div>
        </div>
    </body>
</html>