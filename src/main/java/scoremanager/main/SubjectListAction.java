// 小垣幸流
package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

// 科目一覧用
public class SubjectListAction extends Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		
		// テスト用
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
		
		List<Subject> subjects = null;
		SubjectDao sDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		
		// 科目一覧取得
		subjects = sDao.filter(teacher.getSchool());
		
		// データセット
		request.setAttribute("subjects", subjects);
		
		// 一覧画面へ
		request.getRequestDispatcher("subject_list.jsp").forward(request, response);
	}
}