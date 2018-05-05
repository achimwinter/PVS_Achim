package assignment6.exercise1;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletExample", urlPatterns = {"/test"})
public class PersonServlet extends HttpServlet {


    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {

        System.out.println(request.getAttribute("name"));
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {
        System.out.println(request.getParameter("name"));
        response.getWriter().print("Hello World");
    }


}
