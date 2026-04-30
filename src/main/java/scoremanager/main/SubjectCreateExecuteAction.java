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

// 科目登録実行用
public class SubjectCreateExecuteAction extends Action {

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
        String name = request.getParameter("name");

        // 文字数チェック
        if (cd != null && cd.length() != 3) {
            request.setAttribute("error", "科目コードは3文字で入力してください");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(cd, school);

        if (subject == null) {
            // 登録
            subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);
            sDao.save(subject);
            
            // 完了画面へ
            request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
        } else {
            // 重複エラー
            request.setAttribute("error", "科目コードが重複しています");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            
            // 入力画面へ
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
        }
    }
}