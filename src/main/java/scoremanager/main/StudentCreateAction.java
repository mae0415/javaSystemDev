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

        // HTTPメソッド（GET/POST）を取得
        String method = request.getMethod();

        if (method.equals("GET")) {
            // --- 登録画面を表示する処理 ---
            
            // 入学年度リストの作成（今年を中心に前後10年）
            int year = Calendar.getInstance().get(Calendar.YEAR);
            List<Integer> yearList = new ArrayList<>();
            for (int i = year - 10; i <= year + 10; i++) {
                yearList.add(i);
            }
            
            // クラスリストの取得
            ClassNumDao cDao = new ClassNumDao();
            List<String> classList = cDao.filter(school);

            // リクエスト属性にセット
            request.setAttribute("year_list", yearList);
            request.setAttribute("class_list", classList);

            // JSPへフォワード
            request.getRequestDispatcher("student_create.jsp").forward(request, response);

        } else {
            // --- 登録を実行する処理 ---
            
            String entYearStr = request.getParameter("ent_year");
            String no = request.getParameter("no");
            String name = request.getParameter("name");
            String classNum = request.getParameter("class_num");

            // バリデーション：入学年度未選択
            if (entYearStr == null || entYearStr.equals("0")) {
                request.setAttribute("error", "入学年度を選択してください");
                // 入力内容を保持して再表示
                this.execute(request, response);
                return;
            }

            StudentDao sDao = new StudentDao();
            // バリデーション：学生番号重複チェック
            if (sDao.get(no) != null) {
                request.setAttribute("error", "学生番号が重複しています");
                request.setAttribute("no", no);
                request.setAttribute("name", name);
                this.execute(request, response);
                return;
            }

            // 保存用Beanの作成
            Student student = new Student();
            student.setNo(no);
            student.setName(name);
            student.setEntYear(Integer.parseInt(entYearStr));
            student.setClassNum(classNum);
            student.setAttend(true); // 初期値は在学中
            student.setSchool(school);

            // DBへ保存
            sDao.save(student);

            // 完了画面へフォワード
            request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
        }
    }
}