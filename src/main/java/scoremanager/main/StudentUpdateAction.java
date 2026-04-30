// 野村啓仁
package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

// 学生情報変更用
public class StudentUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        StudentDao sDao = new StudentDao();
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");

        if (method.equals("GET")) {
            // 対象を検索してセット
            request.setAttribute("student", sDao.get(request.getParameter("no")));
            request.setAttribute("class_list", new ClassNumDao().filter(teacher.getSchool()));
            // 編集画面へ
            request.getRequestDispatcher("student_update.jsp").forward(request, response);
        } else {
            // 更新データ作成
            Student s = new Student();
            s.setNo(request.getParameter("no"));
            s.setName(request.getParameter("name"));
            s.setEntYear(Integer.parseInt(request.getParameter("ent_year")));
            s.setClassNum(request.getParameter("class_num"));
            // 在学判定
            s.setAttend(request.getParameter("is_attend") != null);
            s.setSchool(teacher.getSchool());
            
            // 保存
            sDao.save(s);
            // 完了画面へ
            request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
        }
    }
}