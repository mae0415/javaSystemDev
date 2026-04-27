package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Teacher;
import bean.TestListSubject;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        int entYear = Integer.parseInt(request.getParameter("f1"));
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");

        if (entYear == 0 || classNum.equals("0") || subjectCd.equals("0")) {
            request.setAttribute("errors", "入学年度とクラスと科目を選択してください");
            new TestListAction().execute(request, response);
            return;
        }

        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> list = dao.filter(entYear, classNum, subjectCd, school);

        if (list == null || list.isEmpty()) {
            request.setAttribute("errors", "学生情報が存在しませんでした");
            new TestListAction().execute(request, response);
        } else {
            request.setAttribute("tests", list);
            request.setAttribute("subject_name", list.get(0).getSubjectName());
            
            request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
        }
    }
}