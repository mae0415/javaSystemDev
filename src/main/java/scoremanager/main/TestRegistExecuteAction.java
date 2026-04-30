// 前田春太
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 成績登録実行用
public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // パラメータ取得
        String subjectCd = request.getParameter("subjectCd");
        String testNoStr = request.getParameter("testNo");
        String classNum = request.getParameter("classNum");
        String[] studentNoList = request.getParameterValues("studentNo");
        String[] pointList = request.getParameterValues("point");

        TestDao tDao = new TestDao();
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        List<Test> tests = new ArrayList<>();

        // リスト作成
        if (studentNoList != null && testNoStr != null) {
            int testNo = Integer.parseInt(testNoStr);
            for (int i = 0; i < studentNoList.length; i++) {
                Test test = new Test();
                test.setStudentNo(studentNoList[i]);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(testNo);
                test.setClassNum(classNum);

                // 得点のセット
                if (pointList[i] != null && !pointList[i].isEmpty()) {
                    test.setPoint(Integer.parseInt(pointList[i]));
                } else {
                    test.setPoint(-1);
                }
                tests.add(test);
            }
        }

        // 保存実行
        tDao.save(tests);
        
        // 完了画面へ
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}