package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
            // 例: /main/StudentList.action
            String path = request.getServletPath();

            // StudentListAction を作る
            String className =
                    "scoremanager.main." +
                    path.substring(
                        path.lastIndexOf("/") + 1,
                        path.indexOf(".action")
                    ) + "Action";

            Class<?> clazz = Class.forName(className);
            Action action = (Action) clazz.getDeclaredConstructor().newInstance();
            action.execute(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}