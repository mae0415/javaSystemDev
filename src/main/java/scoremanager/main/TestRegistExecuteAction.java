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
        String entYearStr = request.getParameter("entYear"); 
        
        String[] studentNoList = request.getParameterValues("studentNo");
        String[] pointList = request.getParameterValues("point");

        if (pointList != null) {
            List<String> errorStudentNos = new ArrayList<>();
            for (int i = 0; i < pointList.length; i++) {
                if (pointList[i] != null && !pointList[i].isEmpty()) {
                    int p = Integer.parseInt(pointList[i]);
                    if (p < 0 || p > 100) {
                        errorStudentNos.add(studentNoList[i]);
                    }
                }
            }

            if (!errorStudentNos.isEmpty()) {
                List<String> errors = new ArrayList<>();
                errors.add("0〜100の範囲で入力してください");
                request.setAttribute("errors", errors);
                request.setAttribute("errorStudentNos", errorStudentNos);

                // エラー時は検索処理へ戻る
                request.getRequestDispatcher("TestRegist.action").forward(request, response);
                return;
            }
        }

        TestDao tDao = new TestDao();
        SubjectDao sDao = new SubjectDao();
        Subject subject = sDao.get(subjectCd, school);

        List<Test> tests = new ArrayList<>();

        if (studentNoList != null && testNoStr != null) {
            int testNo = Integer.parseInt(testNoStr);
            for (int i = 0; i < studentNoList.length; i++) {
                Test test = new Test();
                test.setStudentNo(studentNoList[i]);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(testNo);
                test.setClassNum(classNum);

                if (pointList[i] != null && !pointList[i].isEmpty()) {
                    test.setPoint(Integer.parseInt(pointList[i]));
                } else {
                    test.setPoint(-1);
                }
                tests.add(test);
            }
        }

        tDao.save(tests);
        
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}