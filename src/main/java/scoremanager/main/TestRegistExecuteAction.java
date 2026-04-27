package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String subjectCd = request.getParameter("f3");
        int num = Integer.parseInt(request.getParameter("f4"));
        String[] studentNoList = request.getParameterValues("student_no_list");

        List<Test> tests = new ArrayList<>();
        for (String no : studentNoList) {
            String pointStr = request.getParameter("point_" + no);
            int point = Integer.parseInt(pointStr);

            if (point < 0 || point > 100) {
                request.setAttribute("errors", "0～100の範囲で入力してください");
                request.getRequestDispatcher("TestRegist.action").forward(request, response);
                return;
            }

            Test test = new Test();
            Student student = new Student();
            student.setNo(no);
            test.setStudent(student);
            
            Subject subject = new Subject();
            subject.setCd(subjectCd);
            test.setSubject(subject);
            
            test.setNo(num);
            test.setPoint(point);
            test.setSchool(teacher.getSchool());
            test.setClassNum(request.getParameter("f2"));
            
            tests.add(test);
        }

        TestDao tDao = new TestDao();
        tDao.save(tests);

        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}