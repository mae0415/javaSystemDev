package scoremanager.main;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String studentNo = req.getParameter("f4");

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(studentNo);

        if (student != null) {
            TestListStudentDao dao = new TestListStudentDao();
            TestListStudent tls = dao.filter(student, teacher.getSchool());

            req.setAttribute("tests", tls.getTests());
            req.setAttribute("student", student);
        } else {
            req.setAttribute("errors", "学生情報が見つかりませんでした");
        }

        req.setAttribute("f4", studentNo);
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}