<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content">
        <section class="mt-4">
            <h2 class="h3 mb-3 fw-normal">成績参照</h2>

            <div class="bg-light p-4 border rounded shadow-sm">
                <form action="TestListSubjectExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-auto"><span class="fw-bold">科目情報</span></div>
                        <div class="col-2">
                            <label class="form-label small">入学年度</label>
                            <select name="f1" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="year" items="${ent_year_set}">
                                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-2">
                            <label class="form-label small">クラス</label>
                            <select name="f2" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="num" items="${class_num_list}">
                                    <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-3">
                            <label class="form-label small">科目</label>
                            <select name="f3" class="form-select">
                                <option value="0">--------</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto pt-4">
                            <button class="btn btn-secondary px-4">検索</button>
                        </div>
                    </div>
                    
                    <c:if test="${not empty errors && errors == '入学年度とクラスと科目を選択してください'}">
                        <div class="row mt-2">
                            <div class="col offset-1 text-danger small">
                                 ${errors}
                            </div>
                        </div>
                    </c:if>
                </form>

                <hr class="my-4">

                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-auto"><span class="fw-bold">学生情報</span></div>
                        <div class="col-2 text-end"><label class="form-label mb-0">学生番号</label></div>
                        <div class="col-4">
                            <input type="text" name="f4" class="form-control" placeholder="学生番号を入力してください" value="${f4}">
                        </div>
                        <div class="col-auto">
                            <button class="btn btn-secondary px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="mt-4">
                <c:choose>
                    <c:when test="${not empty errors && errors == '学生情報が存在しませんでした'}">
                        <p class="text-secondary small">${errors}</p>
                    </c:when>
                    <c:when test="${empty tests && empty student}">
                        <p class="text-info small">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
                    </c:when>
                </c:choose>
            </div>

            <c:if test="${not empty tests}">
                <div class="mt-4">
                    <h3 class="h5 mb-3">成績一覧（科目）</h3>
                    <table class="table table-hover border">
                        <thead class="table-light">
                            <tr>
                                <th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th>1回</th><th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.entYear}</td>
                                    <td>${test.classNum}</td>
                                    <td>${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td>${test.point1 != -1 ? test.point1 : '-'}</td>
                                    <td>${test.point2 != -1 ? test.point2 : '-'}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </section>
    </c:param>
</c:import>