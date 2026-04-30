<%-- 小垣幸流 --%>
<%-- 科目登録画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">科目情報登録</h2>

            <%-- 全体エラー表示 --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger py-2 small">
                    ${errors}
                </div>
            </c:if>

            <form action="SubjectCreateExecute.action" method="post">
                <%-- 科目コード --%>
                <div class="mb-3">
                    <label class="form-label" for="subject-cd-input">科目コード</label>
                    <input class="form-control" type="text" id="subject-cd-input" name="cd" 
                           placeholder="科目コードを入力してください" 
                           value="${cd}" required>
                    <c:if test="${not empty error}">
                        <div class="small mt-1" style="color: #ffc107;">${error}</div>
                    </c:if>
                </div>

                <%-- 科目名 --%>
                <div class="mb-3">
                    <label class="form-label" for="subject-name-input">科目名</label>
                    <input class="form-control" type="text" id="subject-name-input" name="name" 
                           placeholder="科目名を入力してください" maxlength="20" 
                           value="${name}" required>
                </div>

                <button class="btn btn-primary" type="submit">登録</button>

                <div class="mt-3">
                    <a href="SubjectList.action">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>