package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        StudentDao sDao = new StudentDao();
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");

        if (method.equals("GET")) {
            request.setAttribute("student", sDao.get(request.getParameter("no")));
            request.setAttribute("class_list", new ClassNumDao().filter(teacher.getSchool()));

            request.getRequestDispatcher("student_update.jsp").forward(request, response);
        } else {
            Student s = new Student();
            s.setNo(request.getParameter("no"));
            s.setName(request.getParameter("name"));
            s.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
            s.setClassNum(request.getParameter("class_num"));
            s.setAttend(request.getParameter("is_attend") != null);
            s.setSchool(teacher.getSchool());

            sDao.save(s);

            request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
        }
    }
}