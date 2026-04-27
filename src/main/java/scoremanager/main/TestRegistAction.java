package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 2016; i <= 2026; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        List<String> classNumList = cDao.filter(school);
        List<Subject> subjects = sDao.filter(school);

        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_list", classNumList);
        request.setAttribute("subjects", subjects);

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectCd = request.getParameter("f3");
        String numStr = request.getParameter("f4");

        if (entYearStr != null && !entYearStr.equals("0") && 
            classNum != null && !classNum.equals("0") && 
            subjectCd != null && !subjectCd.equals("0") && 
            numStr != null && !numStr.equals("0")) {

            int entYear = Integer.parseInt(entYearStr);
            int num = Integer.parseInt(numStr);

            TestDao tDao = new TestDao();
            List<Test> tests = tDao.filter(entYear, classNum, subjectCd, num, school);
            
            Subject subject = sDao.get(subjectCd, school);

            request.setAttribute("tests", tests);
            request.setAttribute("subject", subject);
        }

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}