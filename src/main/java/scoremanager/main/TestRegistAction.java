package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws Exception {

        // 仮でメニュー.jspへフォワード
        request.getRequestDispatcher("menu.jsp")
               .forward(request, response);
    }
}
