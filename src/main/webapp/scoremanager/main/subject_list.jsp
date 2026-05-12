<%-- 小垣幸流 --%>
<%-- 科目一覧画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">科目管理</h2>
            
            <%-- 新規登録ボタン --%>
            <div class="text-end mb-3">
                <a href="SubjectCreate.action">新規登録</a>
            </div>

            <%-- 科目一覧テーブル --%>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>科目コード</th>
                        <th>科目名</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="subject" items="${subjects}">
                        <tr>
                            <td>${subject.cd}</td>
                            <td>${subject.name}</td>
                            <td><a href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
                            <td><a href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%-- データなしの場合 --%>
            <c:if test="${subjects.size() == 0}">
                <div class="mt-3"></div>
            </c:if>
        </section>
    </c:param>
</c:import>