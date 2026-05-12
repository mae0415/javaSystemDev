<%-- 上村豪 --%>
<%-- ログイン画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム - ログイン
    </c:param>

    <c:param name="scripts">
        <style>
            /* 入力フォームのスタイル調整 */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            input[type=number] {
                -moz-appearance: textfield;
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #0d6efd;
            }
        </style>
        <script>
            // パスワード可視化切り替え
            function togglePassword() {
                const passInput = document.getElementById('password');
                const check = document.getElementById('show-password');
                passInput.type = check.checked ? 'text' : 'password';
            }
        </script>
    </c:param>

    <c:param name="content">
        <section class="d-flex justify-content-center align-items-center" style="min-height: 30vh;">
            <div class="card shadow-sm" style="width: 30rem;">
                <div class="card-header text-center bg-secondary bg-opacity-10 pt-1 pb-2">
                    <h2 class="h2 mb-0" style="line-height: 1;">ログイン</h2>
                </div>
                <div class="card-body p-4">
                    <%-- エラー表示 --%>
                    <c:if test="${not empty errors}">
                        <div class="mb-3 d-flex align-items-center">
                            <span class="me-2" style="color: #000000;"></span>
                            <span style="color: #000000;">
                                <c:forEach var="error" items="${errors}">
                                    ${error}
                                </c:forEach>
                            </span>
                        </div>
                    </c:if>

                    <%-- ログインフォーム --%>
                    <form action="LoginExecute.action" method="post">
                        <div class="mb-3">
                            <label for="id" class="form-label text-secondary small">ID</label>
                            <input type="text" name="id" id="id" class="form-control" required value="${id}">
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label text-secondary small">パスワード</label>
                            <input type="password" name="password" id="password" class="form-control" required>
                        </div>

                        <div class="form-check mb-4 d-flex justify-content-center align-items-center">
                            <input class="form-check-input me-2" type="checkbox" id="show-password" onclick="togglePassword()" style="margin-top: 0;">
                            <label class="form-check-label small" for="show-password">
                                パスワードを表示
                            </label>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary py-2 px-5">ログイン</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </c:param>
</c:import>