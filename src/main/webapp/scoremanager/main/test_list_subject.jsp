<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">成績参照</h2>

            <div class="mb-4">
                <a href="TestList.action" class="btn btn-outline-secondary">検索条件を変える</a>
            </div>

            <c:if test="${not empty tests}">
                <table class="table table-hover border">
                    <thead class="table-light">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>1回</th>
                            <th>2回</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="test" items="${tests}">
                            <tr>
                                <td>${test.entYear}</td>
                                <td>${test.classNum}</td>
                                <td>${test.studentNo}</td>
                                <td>${test.studentName}</td>
                                <td>${test.points != -1 ? test.points : '-'}</td>
                                <td>-</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${not empty errors}">
                <div class="alert alert-warning">${errors}</div>
            </c:if>
        </section>
    </c:param>
</c:import>