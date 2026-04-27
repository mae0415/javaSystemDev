<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム - 成績管理
    </c:param>

    <c:param name="content">
        <h2 class="bg-light p-2 mb-3">成績管理</h2>

        <div class="container">
            <div class="alert alert-success py-2">
                登録が完了しました
            </div>

            <div class="mt-4 d-flex">
                <div class="me-4">
                    <a href="TestRegist.action">戻る</a>
                </div>

                <div>
                    <a href="TestList.action">成績参照</a>
                </div>
            </div>
        </div>
    </c:param>
</c:import>