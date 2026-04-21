package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得し、存在すれば無効化（削除）する
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // ログアウト完了画面（JSP）へ遷移
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}