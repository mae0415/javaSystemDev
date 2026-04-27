<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <p class="ms-4">
                「${subject.name}(${subject.cd})」を削除してもよろしいですか
            </p>

            <form action="SubjectDeleteExecute.action" method="post" class="ms-4 mt-3">
                <input type="hidden" name="cd" value="${subject.cd}">
                <button type="submit" class="btn btn-danger">削除</button>
            </form>

            <p class="ms-4 mt-3">
                <a href="SubjectList.action">戻る</a>
            </p>
        </section>
    </c:param>
</c:import>