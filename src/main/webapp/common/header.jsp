<%-- 小垣幸流 --%>
<%-- ヘッダー用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
	<h1 class="fs-1">得点管理システム</h1>
</div>

<c:if test="${not empty user}">
    <div class="pb-1">
        <span class="me-3">${user.name}様</span>
        <a class="btn btn-outline-primary btn-sm" href="Logout.action">ログアウト</a>
    </div>
</c:if>