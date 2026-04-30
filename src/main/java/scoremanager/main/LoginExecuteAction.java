// 前田春太
package scoremanager.main;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// ログイン実行
public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);
        
        if (teacher != null) {
            // ユーザー保存
            HttpSession session = request.getSession();
            session.setAttribute("user", teacher);
            // メニューへ
            response.sendRedirect("menu.jsp");
        } else {
            // エラーセット
            request.setAttribute("errors", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
            // ログインへ戻る
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}