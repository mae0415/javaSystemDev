// 前田春太
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 学生別成績参照実行用
public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        School school = teacher.getSchool();
        
        // 学生番号取得
        String studentNo = request.getParameter("studentNo");
        if (studentNo == null || studentNo.isEmpty()) {
            studentNo = request.getParameter("f4");
        }

        // 初期表示用データの準備
        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        request.setAttribute("classList", classNumDao.filter(school));
        request.setAttribute("subjectList", subjectDao.filter(school));

        List<Integer> entYearList = new ArrayList<>();
        for (int i = 2016; i <= 2026; i++) {
            entYearList.add(i);
        }
        request.setAttribute("entYearList", entYearList);

        // 検索処理
        if (studentNo != null && !studentNo.isEmpty()) {
            StudentDao sDao = new StudentDao();
            Student target = sDao.get(studentNo);

            if (target == null) {
                request.setAttribute("error", "学生情報が存在しませんでした");
            } else {
                request.setAttribute("target_student", target);
                request.setAttribute("studentNo", studentNo);

                TestListStudentDao dao = new TestListStudentDao();
                List<TestListStudent> list = dao.filter(target);

                if (list == null || list.isEmpty()) {
                    request.setAttribute("error", "成績情報が存在しませんでした");
                } else {
                    request.setAttribute("tests_student", list);
                }
            }
        } else if (request.getParameterMap().containsKey("studentNo") || request.getParameterMap().containsKey("f4")) {
            // 未入力時の処理（現在は何も行わない）
            
        }

        request.setAttribute("isSearchExecuted", true);
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}