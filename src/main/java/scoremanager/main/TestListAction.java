package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = 2016; i <= 2026; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();

        List<String> classNumList = cDao.filter(teacher.getSchool());
        List<Subject> subjects = sDao.filter(teacher.getSchool());

        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_list", classNumList);
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}