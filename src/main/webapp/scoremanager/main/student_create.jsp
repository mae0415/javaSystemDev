<%-- 小垣幸流 --%>
<%-- 学生登録画面用 --%>
<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報登録</h2>

            <%-- 全体エラー表示 --%>
            <c:if test="${not empty errors}">
                <div class="alert alert-danger py-2 small">
                    ${errors}
                </div>
            </c:if>
            
            <form action="StudentCreateExecute.action" method="post">
                <%-- 入学年度 --%>
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select name="ent_year" class="form-select">
                        <option value="0">--------</option>
                        <c:forEach var="y" items="${year_list}">
                            <option value="${y}" <c:if test="${y == ent_year}">selected</c:if>>${y}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty error_ent}">
                        <div class="small mt-1" style="color: #ffc107;">${error_ent}</div>
                    </c:if>
                </div>
 
                <%-- 学生番号 --%>
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" name="no" class="form-control" required
                           placeholder="学生番号を入力してください" value="${no}" maxlength="10">
                    <c:if test="${not empty error_no}">
                    <div class="small mt-1" style="color: #ffc107;">${error_no}</div>
                    </c:if>
                </div>
 
                <%-- 氏名 --%>
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control" required
                           placeholder="氏名を入力してください" value="${name}">
                </div>
 
                <%-- クラス --%>
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="class_num" class="form-select">
                        <c:forEach var="c" items="${class_list}">
                            <option value="${c}" <c:if test="${c == class_num}">selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </div>
 
                <button type="submit" class="btn btn-secondary mt-3">登録して終了</button>
            </form>
            
            <div class="mt-3">
                <a href="StudentList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>