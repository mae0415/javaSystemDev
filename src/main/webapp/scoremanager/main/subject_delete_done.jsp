<%-- 小垣幸流 --%>
<%-- 科目削除完了画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <%-- 完了メッセージ --%>
            <div class="alert alert-success mx-4">
                削除が完了しました。
            </div>

            <%-- ナビゲーション --%>
            <p class="ms-4 mt-3">
                <a href="SubjectList.action">科目一覧へ</a>
            </p>
        </section>
    </c:param>
</c:import>