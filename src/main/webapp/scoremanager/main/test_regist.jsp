<%-- 前田春太 --%>
<%-- 成績登録入力画面用 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 絞込み検索フォーム --%>
            <form action="TestRegist.action" method="get">
                <div class="bg-light p-3 border d-flex align-items-end gap-3 mb-4">
                    <%-- 入学年度 --%>
                    <div style="width: 120px;">
                        <label class="form-label small mb-1">入学年度</label>
                        <select name="entYear" class="form-select form-select-sm bg-white">
                            <option value="">--------</option>
                            <c:forEach var="y" items="${entYearSet}">
                                <option value="${y}" <c:if test="${y == entYear}">selected</c:if>>${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス --%>
                    <div style="width: 120px;">
                        <label class="form-label small mb-1">クラス</label>
                        <select name="classNum" class="form-select form-select-sm bg-white">
                            <option value="">--------</option>
                            <c:forEach var="cNum" items="${classList}">
                                <option value="${cNum}" <c:if test="${cNum == classNum}">selected</c:if>>${cNum}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div style="width: 250px;">
                        <label class="form-label small mb-1">科目</label>
                        <select name="subjectCd" class="form-select form-select-sm bg-white">
                            <option value="">----------------</option>
                            <c:forEach var="sub" items="${subjectList}">
                                <option value="${sub.cd}" <c:if test="${sub.cd == subjectCd}">selected</c:if>>${sub.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 回数 --%>
                    <div style="width: 120px;">
                        <label class="form-label small mb-1">回数</label>
                        <select name="testNo" class="form-select form-select-sm bg-white">
                            <option value="">--------</option>
                            <option value="1" <c:if test="${testNo == '1'}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${testNo == '2'}">selected</c:if>>2</option>
                        </select>
                    </div>

                    <div>
                        <input type="submit" value="検索" class="btn btn-secondary btn-sm px-3 shadow-sm">
                    </div>
                </div>

                <%-- 未入力エラーがある場合に表示 --%>
                <c:if test="${not empty error}">
                    <div class="mb-4" style="color: #ffc107;">
                        <c:out value="${error}" />
                    </div>
                </c:if>
            </form>

            <%-- 成績入力テーブル --%>
            <c:if test="${not empty testList}">
                <div class="mb-2 text-dark fw-bold">科目：${subjectName} (${testNo}回)</div>

                <form action="TestRegistExecute.action" method="post">
                    <%-- 検索条件をhiddenで保持（再表示用） --%>
                    <input type="hidden" name="entYear" value="${entYear}">
                    <input type="hidden" name="subjectCd" value="${subjectCd}">
                    <input type="hidden" name="testNo" value="${testNo}">
                    <input type="hidden" name="classNum" value="${classNum}">
                    
                    <table class="table table-hover mt-3">
                        <thead>
                            <tr class="border-bottom border-dark">
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学籍番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${testList}">
                                <tr>
                                    <td>${test.student.entYear}</td>
                                    <td>${test.student.classNum}</td>
                                    <td>${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td>
                                        <input type="hidden" name="studentNo" value="${test.student.no}">
                                        <input type="number" name="point" 
                                               value="${test.point == -1 ? 0 : test.point}" 
                                               class="form-control form-control-sm bg-white" 
                                               style="width: 150px;">
                                        
                                        <%-- エラー判定：該当者のみ表示。普段は余白を作らない --%>
                                        <c:forEach var="errNo" items="${errorStudentNos}">
                                            <c:if test="${errNo == test.student.no}">
                                                <div class="small mt-1" style="color: #ffc107; white-space: nowrap;">
                                                    0〜100の範囲で入力してください
                                                </div>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="mt-3">
                        <button type="submit" class="btn btn-secondary px-3">登録して終了</button>
                    </div>
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>