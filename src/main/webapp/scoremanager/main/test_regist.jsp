<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <div class="card mb-4">
                <div class="card-header bg-light">
                    <h2 class="h3 mb-0 fw-normal">成績管理</h2>
                </div>

                <div class="card-body">
                    <form action="TestRegist.action" method="post">
                        <div class="row align-items-end">
                            <div class="col-2">
                                <label class="form-label">入学年度</label>
                                <select name="f1" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="year" items="${ent_year_set}">
                                        <option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-2">
                                <label class="form-label">クラス</label>
                                <select name="f2" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="c" items="${class_num_list}">
                                        <option value="${c}" <c:if test="${c == param.f2}">selected</c:if>>${c}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-4">
                                <label class="form-label">科目</label>
                                <select name="f3" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="s" items="${subjects}">
                                        <option value="${s.cd}" <c:if test="${s.cd == param.f3}">selected</c:if>>${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-2">
                                <label class="form-label">回数</label>
                                <select name="f4" class="form-select">
                                    <option value="0">--------</option>
                                    <c:forEach var="n" begin="1" end="2">
                                        <option value="${n}" <c:if test="${n == param.f4}">selected</c:if>>${n}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-2">
                                <button class="btn btn-secondary w-100" id="search-btn">検索</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${not empty tests}">
                <div class="mt-4">
                    <div class="mb-3">科目：${subject.name} （${param.f4}回）</div>
                    
                    <form action="TestRegistExecute.action" method="post">
                        <input type="hidden" name="f1" value="${param.f1}">
                        <input type="hidden" name="f2" value="${param.f2}">
                        <input type="hidden" name="f3" value="${param.f3}">
                        <input type="hidden" name="f4" value="${param.f4}">
                        
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学籍番号</th>
                                    <th>氏名</th>
                                    <th style="width: 150px;">点数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <td>${test.student.entYear}</td>
                                        <td>${test.student.classNum}</td>
                                        <td>${test.student.no}</td>
                                        <td>${test.student.name}</td>
                                        <td>
                                            <input type="number" name="point_${test.student.no}" 
                                                   value="${test.point != -1 ? test.point : ''}" 
                                                   class="form-control" min="0" max="100">
                                            <input type="hidden" name="student_no_list" value="${test.student.no}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="mt-3">
                            <button type="submit" class="btn btn-primary">登録して終了</button>
                        </div>
                    </form>
                </div>
            </c:if>
            
            <c:if test="${not empty errors}">
                <div class="text-danger mt-3">${errors}</div>
            </c:if>
        </section>
    </c:param>
</c:import>