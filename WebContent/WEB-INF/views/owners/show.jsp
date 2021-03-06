<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${process != null}">
                <h2>工程　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>作成者</th>
                            <td><c:out value="${process.user.name}" /></td>
                        </tr>
                        <tr>
                            <th>日付</th>
                            <td><fmt:formatDate value="${process.completed_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                        <tr>
                            <th>メッセージ</th>
                            <td>
                                <pre><c:out value="${process.message}" /></pre>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_user.admin_flag == 1}">
                    <p><a href="<c:url value="/processes/edit?id=${process.id}" />">この工程を編集する</a></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value="/processes/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>