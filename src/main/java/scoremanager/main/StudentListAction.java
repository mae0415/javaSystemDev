package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

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

        String entYearStr = "";
        String classNum = "";
        String isAttendStr = "";
        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;

        LocalDate todayDate = LocalDate.now();
        int year = todayDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        isAttendStr = request.getParameter("f3");

        if (entYearStr != null && !entYearStr.equals("0")) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (isAttendStr != null) {
            isAttend = true;
            request.setAttribute("f3", isAttendStr);
        }

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        List<String> list = cNumDao.filter(teacher.getSchool());

        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }
}