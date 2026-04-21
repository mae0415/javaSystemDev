package scoremanager.main;
import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        
        TeacherDao dao = new TeacherDao();
        Teacher teacher = dao.login(id, password);
        
        if (teacher != null) {
            // セッションにユーザー情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("user", teacher);
            // ログイン成功後、学生一覧へ
            response.sendRedirect("menu.jsp");
        } else {
            // 失敗時はエラーメッセージを持ってログイン画面へ戻す
            request.setAttribute("errors", "ログインに失敗しました。IDまたはパスワードが正しくありません。");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}