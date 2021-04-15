<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>ユーザー　一覧</h2>
        <table id="user_list">
            <tbody>
                <tr>
                    <th>氏名</th>
                    <th>契約日</th>
                    <th>住所</th>
                </tr>
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="row${status.count % 2}">

                        <td><a href="<c:url value='/processes/index?id=${user.id}' />"><c:out value="${user.name}" /></a></td>
                        <td><c:out value="${user.contract_date}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.delete_flag == 1}">
                                    (消去済み)
                                </c:when>
                                <c:otherwise>
                                      <a><c:out value="${user.address}" /></a>
                                </c:otherwise>
                            </c:choose>
                       </td>
                   </tr>
               </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            (全 ${users_count}件)<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1)/15)+1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${1}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/users/new' />">新規オーナー様の登録</a></p>

        </c:param>
</c:import>