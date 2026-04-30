<%-- 上村豪 --%>
<%-- ログアウト完了画面用 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ログアウト</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* レイアウト調整用スタイル */
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        #wrapper {
            flex: 1;
        }
        .logout-container {
            max-width: 800px;
            margin: 2rem auto;
            border: 1px solid #dee2e6;
        }
    </style>
</head>
<body>
    <div id="wrapper" class="container">
        <%-- ヘッダー --%>
        <header class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
            <c:import url="/common/header.jsp" />
        </header>

        <div class="row justify-content-center">
            <main class="col-8">
                <%-- ログアウトメッセージ表示 --%>
                <div class="logout-container">
                    <h2 class="bg-light py-2 px-4 fs-4 mb-0 border-bottom">ログアウト</h2>
                    <div class="p-3">
                        <div class="bg-success text-white py-2 px-4 text-center">
                            ログアウトしました
                        </div>
                        <div class="text-center mt-4">
                            <a href="Login.action" class="text-primary text-decoration-underline">ログイン</a>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <%-- フッター --%>
        <footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3">
            <c:import url="/common/footer.jsp" />
        </footer>
    </div>
</body>
</html>