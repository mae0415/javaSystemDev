// 野村啓仁
package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// アクション抽象クラス
public abstract class Action {
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}