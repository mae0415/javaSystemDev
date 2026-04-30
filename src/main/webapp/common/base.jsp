<%-- 前田春太 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>${param.title}</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    ${param.scripts}
</head>
<body>
    <div id="wrapper" class="container">
        <%-- ヘッダー --%>
        <header class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
            <c:import url="/common/header.jsp" />
        </header>

        <div class="row justify-content-center">
            <c:choose>
                <%-- ログイン時 --%>
                <c:when test="${not empty user}">
                    <nav class="col-3" style="min-height:40rem;">
                        <c:import url="/common/navigation.jsp" />
                    </nav>
                    <main class="col-9 border-start">
                        ${param.content}
                    </main>
                </c:when>

                <%-- 未ログイン時 --%>
                <c:otherwise>
                    <main class="col-8">
                        ${param.content}
                    </main>
                </c:otherwise>
            </c:choose>
        </div>

        <%-- フッター --%>
        <footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3 align-bottom">
            <c:import url="/common/footer.jsp" />
        </footer>
    </div>
</body>
</html>