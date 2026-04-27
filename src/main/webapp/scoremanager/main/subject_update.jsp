<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">科目情報変更</h2>
            
            <form action="SubjectUpdateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <div class="pt-1">${subject.cd}</div>
                    <input type="hidden" name="cd" value="${subject.cd}">
                </div>
                
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" name="name" class="form-control" value="${subject.name}" 
                           maxlength="20" required>
                </div>
                
                <button type="submit" class="btn btn-primary mt-3">変更</button>
            </form>

            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>
        </section>
    </c:param>
</c:import>