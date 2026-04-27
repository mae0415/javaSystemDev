package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        String method = request.getMethod();
        if (method.equals("GET")) {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            List<Integer> yearList = new ArrayList<>();
            for (int i = year - 10; i <= year + 10; i++) {
                yearList.add(i);
            }
            ClassNumDao cDao = new ClassNumDao();
            List<String> classList = cDao.filter(school);
            request.setAttribute("year_list", yearList);
            request.setAttribute("class_list", classList);
            request.getRequestDispatcher("student_create.jsp").forward(request, response);
        } else {
            String entYearStr = request.getParameter("ent_year");
            String no = request.getParameter("no");
            String name = request.getParameter("name");
            String classNum = request.getParameter("class_num");
            if (entYearStr == null || entYearStr.equals("0")) {
                request.setAttribute("error", "入学年度を選択してください");
                this.execute(request, response);
                return;
            }
            StudentDao sDao = new StudentDao();
            if (sDao.get(no) != null) {
                request.setAttribute("error", "学生番号が重複しています");
                request.setAttribute("no", no);
                request.setAttribute("name", name);
                this.execute(request, response);
                return;
            }
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(Integer.parseInt(entYearStr));
            student.setClassNum(classNum);
            student.setAttend(true);
            student.setSchool(school);
            sDao.save(student);
            request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
        }
    }
}