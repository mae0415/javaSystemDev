<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">学生情報変更</h2>
            <form action="StudentUpdate.action" method="post">
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <div class="pt-1">${student.entYear}</div>
                    <input type="hidden" name="ent_year" value="${student.entYear}">
                </div>
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <div class="pt-1">${student.no}</div>
                    <input type="hidden" name="no" value="${student.no}">
                </div>
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text" name="name" class="form-control" required value="${student.name}" placeholder="氏名を記入してください">
                </div>
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="class_num" class="form-select">
                        <c:forEach var="c" items="${class_list}">
                            <option value="${c}" <c:if test="${c == student.classNum}">selected</c:if>>${c}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <%-- 文字を左、ボックスを右に配置 --%>
                <div class="mb-3 d-flex align-items-center">
                    <label class="form-check-label me-2" for="is_attend">在学中</label>
                    <input class="form-check-input" type="checkbox" name="is_attend" id="is_attend" <c:if test="${student.isAttend()}">checked</c:if>>
                </div>

                <button type="submit" class="btn btn-primary mt-3">変更</button>
            </form>
            <div class="mt-3"><a href="StudentList.action">戻る</a></div>
        </section>
    </c:param>
</c:import>