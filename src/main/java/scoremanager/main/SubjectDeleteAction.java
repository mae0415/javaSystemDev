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

// 科目削除画面用
public class SubjectDeleteAction extends Action {

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

        School school = teacher.getSchool();
        String cd = request.getParameter("cd");

        // 削除対象の取得
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);

        // セットして確認画面へ
        request.setAttribute("subject", subject);
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }
}