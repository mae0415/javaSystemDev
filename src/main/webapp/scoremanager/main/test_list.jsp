<%-- 上村豪 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                <c:choose>
                    <c:when test="${not empty list}">成績一覧（科目）</c:when>
                    <c:otherwise>成績参照</c:otherwise>
                </c:choose>
            </h2>

            <div class="card p-4 shadow-sm mb-4">
                <form action="TestListSubjectExecute.action" method="get" class="mb-3">
                    <div class="row">
                        <div class="col-2 fw-bold pt-1">科目情報</div>
                        <div class="col-10">
                            <div class="d-flex align-items-end gap-3 mb-2">
                                <div>
                                    <label class="form-label mb-1">入学年度</label>
                                    <select class="form-select form-select-sm" name="entYear" style="width:120px;">
                                        <option value="">--------</option>
                                        <c:forEach var="year" items="${entYearList}">
                                            <option value="${year}" <c:if test="${year == selectedEntYear}">selected</c:if>>${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label class="form-label mb-1">クラス</label>
                                    <select class="form-select form-select-sm" name="classNum" style="width:120px;">
                                        <option value="">--------</option>
                                        <c:forEach var="c" items="${classList}">
                                            <option value="${c}" <c:if test="${c == selectedClassNum}">selected</c:if>>${c}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <label class="form-label mb-1">科目</label>
                                    <select class="form-select form-select-sm" name="subject" style="width:180px;">
                                        <option value="">--------</option>
                                        <c:forEach var="s" items="${subjectList}">
                                            <option value="${s.cd}" <c:if test="${s.cd == selectedSubject}">selected</c:if>>${s.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                                </div>
                            </div>
                            <%-- ① image_cb7943.png のパターン：未選択エラーをカード内に表示 --%>
                            <c:if test="${not empty input_error}">
                                <div style="color: #ffc107;">
                                    <c:out value="${input_error}" />
                                </div>
                            </c:if>
                        </div>
                    </div>
                </form>

                <hr class="mt-0 mb-3 text-secondary">

                <form action="TestListStudentExecute.action" method="get">
                    <div class="row align-items-center">
                        <div class="col-2 fw-bold">学生情報</div>
                        <div class="col-auto">
                            <label class="form-label mb-1">学生番号</label>
                            <input type="text" name="studentNo" value="${studentNo}" class="form-control form-control-sm" placeholder="学生番号を入力してください" style="width:250px;" required>
                        </div>
                        <div class="col-auto align-self-end">
                            <button type="submit" class="btn btn-secondary btn-sm px-4">検索</button>
                        </div>
                    </div>
                </form>
            </div>

            <c:if test="${not empty error}">
                <div class="mb-3 text-dark">
                    <c:out value="${error}" />
                </div>
            </c:if>

            <c:choose>
                <c:when test="${not empty list}">
                    <div class="mb-3">
                        科目：<c:forEach var="s" items="${subjectList}"><c:if test="${s.cd == selectedSubject}">${s.name}</c:if></c:forEach>
                    </div>
                    <table class="table table-hover border-top mb-5">
                        <thead>
                            <tr class="table-light">
                                <th>入学年度</th><th>クラス</th><th>学生番号</th><th>氏名</th><th class="text-center">1回</th><th class="text-center">2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="ts" items="${list}">
                                <tr>
                                    <td>${ts.entYear}</td><td>${ts.classNum}</td><td>${ts.studentNo}</td><td>${ts.studentName}</td>
                                    <td class="text-center"><c:choose><c:when test="${not empty ts.getPoint(1) && ts.getPoint(1) != -1}">${ts.getPoint(1)}</c:when><c:otherwise>-</c:otherwise></c:choose></td>
                                    <td class="text-center"><c:choose><c:when test="${not empty ts.getPoint(2) && ts.getPoint(2) != -1}">${ts.getPoint(2)}</c:when><c:otherwise>-</c:otherwise></c:choose></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
 
                <c:when test="${not empty target_student}">
                    <div class="mb-1 text-dark">氏名：${target_student.name} (${target_student.no})</div>
                    <c:choose>
                        <c:when test="${not empty tests_student}">
                            <table class="table table-hover border-top mt-3">
                                <thead>
                                    <tr class="table-light">
                                        <th>科目名</th><th>科目コード</th><th class="text-center">回数</th><th class="text-center">点数</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="ts" items="${tests_student}">
                                        <tr><td>${ts.subjectName}</td><td>${ts.subjectCd}</td><td class="text-center">${ts.num}</td><td class="text-center">${ts.point}</td></tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise><div class="text-dark">成績情報が存在しませんでした</div></c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <%-- エラーが出ていない初期状態のみガイドを表示 --%>
                   <c:if test="${empty error}">
                        <div class="mt-3" style="color: #0dcaf0;">
                            科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>