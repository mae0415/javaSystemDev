package scoremanager.main;
 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentListAction extends Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); // セッション取得
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザー取得
		
		if (teacher == null) {
		    teacher = new Teacher();
		    teacher.setId("admin");
		    teacher.setName("テスト講師");
		    // 一時的にスクールオブジェクト
		    School school = new School();
		    school.setCd("tes"); 
		    school.setName("テスト校");
		    teacher.setSchool(school);
		    session.setAttribute("user", teacher);
		}
		
		String entYearStr = "";   // 入力された入学年度
		String classNum = "";     // 入力されたクラス番号
		String isAttendStr = "";  // 入力された在学フラグ
		int entYear = 0;          // 入学年度（数値型）
		boolean isAttend = false; // 在学フラグ（boolean型）
		List<Student> students = null; // 学生リスト
		LocalDate todayDate = LocalDate.now(); // 現在の日付を取得
		int year = todayDate.getYear(); // 現在の年を取得
		StudentDao sDao = new StudentDao(); // 学生Daoを初期化
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ格納用
		
		// リクエストパラメーターの取得
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");
		
		// 入学年度が送信されている場合、数値に変換
		if (entYearStr != null && !entYearStr.equals("0")) {
			entYear = Integer.parseInt(entYearStr);
		}
		
		// 在学フラグが送信されている場合、trueをセット
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}

		// 検索フォーム用の入学年度リストを作成（10年前から今年まで）
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i <= year; i++) {
			entYearSet.add(i);
		}
		
		// DBからクラス番号の一覧を取得（ログインユーザーの学校コードを元にする）
		List<String> list = cNumDao.filter(teacher.getSchool());
 
		// --- 検索ロジックの実行 ---
		if (entYear != 0 && !classNum.equals("0")) {
		    // 入学年度とクラス番号の両方が指定されている場合
		    students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && (classNum == null || classNum.equals("0"))) {
		    // 入学年度のみ指定されている場合
		    students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
		    // 何も指定されていない場合（全学生情報を取得）
		    students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
		    // クラスだけ指定して入学年度がない場合などはエラー
		    errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
		    request.setAttribute("errors", errors);
		    // 全学生情報を取得
		    students = sDao.filter(teacher.getSchool(), isAttend);
		}
		
		// --- JSPへのデータセット ---
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		//request.setAttribute("f3", isAttendStr);
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		
		// JSPへフォワード
		// 【修正点】request.response ではなく response を渡します
		request.getRequestDispatcher("student_list.jsp").forward(request, response);
	}
	
}