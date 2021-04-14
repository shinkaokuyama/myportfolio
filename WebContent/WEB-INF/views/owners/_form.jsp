<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="cmpleted_date">完了日</label><br />
<input type="date" name="completed_date" value="<fmt:formatDate value='${process.completed_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_user.name}" />
<br /><br />

<label for="process_name">工程名</label><br />
<input type="text" name="process_name" value="${process.process_name}" />
<br /><br />

<label for="message">メッセージ</label><br />
<textarea name="message" rows="10" cols="50">${process.message}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">投稿</button>