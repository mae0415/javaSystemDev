// 野村啓仁
package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 全リクエストを制御
@WebServlet("*.action")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // パスからクラス名を特定
            String path = request.getServletPath();

            String className =
                    "scoremanager.main." +
                    path.substring(
                        path.lastIndexOf("/") + 1,
                        path.indexOf(".action")
                    ) + "Action";

            // インスタンス化して実行
            Class<?> clazz = Class.forName(className);
            Action action = (Action) clazz.getDeclaredConstructor().newInstance();
            action.execute(request, response);

        } catch (Exception e) {
            // エラー時
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}