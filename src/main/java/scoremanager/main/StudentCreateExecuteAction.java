// 野村啓仁
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
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

// 学生登録実行用
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

        // 入学年度チェック
        if (entYearStr == null || entYearStr.isEmpty() || entYearStr.equals("0")) {
            request.setAttribute("error_ent", "入学年度を選択してください");
            
            int year = LocalDate.now().getYear();
            List<Integer> yearList = new ArrayList<>();
            for (int i = year - 10; i <= year; i++) {
                yearList.add(i);
            }
            ClassNumDao cDao = new ClassNumDao();
            List<String> classList = cDao.filter(school);

            request.setAttribute("year_list", yearList);
            request.setAttribute("class_list", classList);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("class_num", classNum);

            // 入力画面へ戻る
            request.getRequestDispatcher("student_create.jsp").forward(request, response);
            return;
        }

        // 重複チェック
        StudentDao sDao = new StudentDao();
        if (sDao.get(no) != null) {
            request.setAttribute("error_no", "学生番号が重複しています");
            
            int year = LocalDate.now().getYear();
            List<Integer> yearList = new ArrayList<>();
            for (int i = year - 10; i <= year; i++) {
                yearList.add(i);
            }
            ClassNumDao cDao = new ClassNumDao();
            List<String> classList = cDao.filter(school);

            request.setAttribute("year_list", yearList);
            request.setAttribute("class_list", classList);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("class_num", classNum);

            // 入力画面へ戻る
            request.getRequestDispatcher("student_create.jsp").forward(request, response);
            return;
        }

        // 保存
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(Integer.parseInt(entYearStr));
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(school);

        sDao.save(student);

        // 完了画面へ
        request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    }
}