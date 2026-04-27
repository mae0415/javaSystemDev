package scoremanager.main;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String entYearStr = request.getParameter("ent_year");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        int entYear = Integer.parseInt(entYearStr);

        StudentDao sDao = new StudentDao();
        Student student = sDao.get(no);

        if (student != null) {
            request.setAttribute("errors", "学生番号が重複しています。");
            request.setAttribute("ent_year", entYear);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);

            request.getRequestDispatcher("student_create.jsp").forward(request, response);
        } else {
            Student newStudent = new Student();
            newStudent.setNo(no);
            newStudent.setName(name);
            newStudent.setEntYear(entYear);
            newStudent.setClassNum(classNum);
            newStudent.setSchool(school);
            newStudent.setAttend(true);

            sDao.save(newStudent);

            request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
        }
    }
}