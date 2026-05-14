// 前田春太
package scoremanager.main;

import java.time.LocalDate;
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

// 成績登録画面用
public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        ClassNumDao classNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();

        // クラス・科目一覧取得
        List<String> classList = classNumDao.filter(school);
        List<Subject> subjectList = subjectDao.filter(school);

        // 入学年度リスト作成
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // パラメータ取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String testNoStr = request.getParameter("testNo");

        // 検索条件が揃っている場合
        if (entYearStr != null && classNum != null && subjectCd != null && testNoStr != null &&
            !entYearStr.isEmpty() && !classNum.isEmpty() && !subjectCd.isEmpty() && !testNoStr.isEmpty()) {
            
            int entYear = Integer.parseInt(entYearStr);
            int testNo = Integer.parseInt(testNoStr);
            
            Subject subject = subjectDao.get(subjectCd, school);
            
            // 成績データ取得
            List<Test> testList = testDao.filter(entYear, classNum, subject, testNo, school);
            
            request.setAttribute("testList", testList);
            request.setAttribute("subjectName", subject.getName());
            request.setAttribute("testNo", testNo);
        } else if (entYearStr != null || classNum != null || subjectCd != null || testNoStr != null) {
            request.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
        }

        // データセット
        request.setAttribute("entYearSet", entYearSet);
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        
        request.setAttribute("entYear", entYearStr);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subjectCd", subjectCd);
        request.setAttribute("testNo", testNoStr);

        // 登録画面へ
        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
    }
}