// 野村啓仁
package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 科目削除実行用
public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // テスト用
        if (teacher == null) {
            teacher = new Teacher();
            teacher.setId("admin");
            teacher.setName("テスト講師");
            School school = new School();
            school.setCd("tes");
            school.setName("テスト校");
            teacher.setSchool(school);
            session.setAttribute("user", teacher);
        }

        String cd = request.getParameter("cd");
        School school = teacher.getSchool();

        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);

        // 存在すれば削除
        if (subject != null) {
            sDao.delete(subject);
        }

        // 完了画面へ
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}