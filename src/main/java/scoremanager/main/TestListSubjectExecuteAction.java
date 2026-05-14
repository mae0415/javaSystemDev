// 前田春太
package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subject");

        ClassNumDao cDao = new ClassNumDao();
        SubjectDao sDao = new SubjectDao();
        TestListSubjectDao dao = new TestListSubjectDao();

        List<String> classList = cDao.filter(school);
        List<Subject> subjectList = sDao.filter(school); 

        List<Integer> entYearList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year - 10; i <= year; i++) {
            entYearList.add(i);
        }

        // ① 入学年度・クラス・科目のいずれかが未選択の場合
        if (entYearStr == null || entYearStr.isEmpty() || classNum == null || classNum.isEmpty() || subjectCd == null || subjectCd.isEmpty()) {
            request.setAttribute("classList", classList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("entYearList", entYearList);
            request.setAttribute("selectedEntYear", entYearStr);
            request.setAttribute("selectedClassNum", classNum);
            request.setAttribute("selectedSubject", subjectCd);
            // カード内に表示するエラー
            request.setAttribute("input_error", "入学年度とクラスと科目を選択してください");
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        int entYear = Integer.parseInt(entYearStr);
        List<TestListSubject> list = dao.filter(entYear, classNum, subjectCd, school);

        // ② すべて選択したが学生情報が存在しない場合
        if (list == null || list.isEmpty()) {
            // カード外に表示するエラー
            request.setAttribute("error", "学生情報が存在しませんでした");
        } else {
            request.setAttribute("list", list);
        }

        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("entYearList", entYearList);
        request.setAttribute("selectedEntYear", entYear);
        request.setAttribute("selectedClassNum", classNum);
        request.setAttribute("selectedSubject", subjectCd);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}