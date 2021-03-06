<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>工程　一覧</h2>
        <table id="process_list">
            <tbody>
                <tr>
                    <th class="process_name">工程名</th>
                    <th class="process_date">完了日</th>
                </tr>
                <c:forEach var="process" items="${processes}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="process_name"><c:out value="${process.process_name}" /></td>
                        <td class="completed_date"><fmt:formatDate value='${process.completed_date}' pattern='yyyy-MM-dd' /></td>
                        <td class="process_action"><a href="<c:url value='/processes/show?id=${process.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${processes_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((processes_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/processes/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
         <c:if test="${sessionScope.login_user.admin_flag == 1}">
        <p><a href="<c:url value='/processes/new' />">新規工程の登録</a></p>
        </c:if>

    </c:param>
</c:import>