// 野村啓仁
package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 科目情報変更実行用
public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータ取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 更新データ作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // 更新実行
        SubjectDao sDao = new SubjectDao();
        sDao.update(subject);

        // 完了画面へ
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}