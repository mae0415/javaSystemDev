<%-- 小垣幸流 --%>
<%-- 学生登録完了画面用 --%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報登録</h2>
            
            <%-- 完了メッセージ --%>
            <div class="alert alert-success mt-3" role="alert">
                登録が完了しました
            </div>

            <%-- ナビゲーション --%>
            <div class="mt-4">
                <a href="StudentCreate.action" class="me-3">戻る</a>
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>