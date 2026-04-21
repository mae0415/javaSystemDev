<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="p-3">
    <ul class="list-unstyled">
        <%-- No.3 メニュー：../ で階層を補正 --%>
        <li class="mb-2">
            <a href="../Menu.action" class="link-primary text-decoration-none">メニュー</a>
        </li>
        
        <%-- No.4 学生管理 --%>
        <li class="mb-2">
            <a href="../StudentList.action" class="link-primary text-decoration-none">学生管理</a>
        </li>
        
        <%-- No.5 成績管理 (ラベル) --%>
        <li class="mt-3 mb-1 text-secondary small fw-bold">成績管理</li>
        
        <li>
            <ul class="list-unstyled ps-3">
                <%-- No.6 成績登録 --%>
                <li class="mb-2">
                    <a href="../ScoreUpdate.action" class="link-primary text-decoration-none">成績登録</a>
                </li>
                <%-- No.7 成績参照 --%>
                <li class="mb-2">
                    <a href="../ScoreList.action" class="link-primary text-decoration-none">成績参照</a>
                </li>
            </ul>
        </li>
        
        <%-- No.8 科目管理 --%>
        <li class="mt-3">
            <a href="../SubjectList.action" class="link-primary text-decoration-none">科目管理</a>
        </li>
        
        <%-- クラス管理 (タイルに合わせて追加) --%>
        <li class="mt-2">
            <a href="../ClassList.action" class="link-primary text-decoration-none">クラス管理</a>
        </li>
    </ul>
</div>