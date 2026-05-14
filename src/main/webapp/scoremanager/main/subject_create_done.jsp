<%-- 小垣幸流 --%>
<%-- 科目登録完了画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <div class="alert alert-success mx-4">
                登録が完了しました。
            </div>

            <div class="ms-4 mt-3 d-flex gap-5">

                <a href="SubjectCreate.action">戻る</a>

                <a href="SubjectList.action">科目一覧へ</a>
            </div>
        </section>
    </c:param>
</c:import>