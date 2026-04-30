// 上村豪
package scoremanager.main;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;
 
public class MenuAction extends Action {
    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
 
        // セッション取得
        HttpSession session = request.getSession(false);
 
        // 認証チェック
        if (session == null || session.getAttribute("user") == null) {
            // 未ログインはログイン画面へ
            response.sendRedirect("Login.action");
            return;
        }
 
        // メニュー画面へ
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
}