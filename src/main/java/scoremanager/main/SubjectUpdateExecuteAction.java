// 野村啓仁
package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 科目情報変更実行用
public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 画面から送られてきた値を取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao sDao = new SubjectDao();
        
        // ★【重要】更新する直前に、もう一度DBにデータがあるか確認する
        Subject exist = sDao.get(cd, teacher.getSchool());

        if (exist == null) {
            // 他のタブで既に削除されていた場合のエラー処理
            request.setAttribute("errors", "科目が存在しません");
            
            Subject sub = new Subject();
            sub.setCd(cd);
            sub.setName(name);
            request.setAttribute("subject", sub);

            // 変更画面に戻す
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // 正常な更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        sDao.update(subject);

        // 完了画面へ
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}