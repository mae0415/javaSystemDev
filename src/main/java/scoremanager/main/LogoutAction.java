// 前田春太
package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

// ログアウト処理
public class LogoutAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション破棄
        request.getSession().invalidate();
        
        // ログアウト画面へ
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}