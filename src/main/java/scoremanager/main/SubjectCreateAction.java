// 野村啓仁
package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

// 科目登録画面用
public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 登録画面へ
        request.getRequestDispatcher("subject_create.jsp").forward(request, response);
    }
}