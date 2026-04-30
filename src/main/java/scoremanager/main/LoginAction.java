// 前田春太
package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

// ログイン表示用
public class LoginAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 判定用フラグ
        request.setAttribute("isLoginPage", true);
        
        // JSPへ
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}