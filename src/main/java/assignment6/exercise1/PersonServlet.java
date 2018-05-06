package assignment6.exercise1;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletExample", urlPatterns = {"/test"})
public class PersonServlet extends HttpServlet {

    List<Person> persons = new LinkedList<>();

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String birthday = request.getParameter("birthday");

        Person person = new Person(lastName, firstName, birthday);
        persons.add(person);

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {

        if (request.getQueryString() == null) {
            for (Person person : persons) {
                response.getWriter().print(person.toString());
            }
        } else {
            Map<String, String[]> parameters = request.getParameterMap();
            for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
                if (parameter.getKey().equals("name")) {
                    for (int i = 0; i < parameter.getValue().length; i++) {
                        for (Person p : persons) {
                            if (p.getLastName().equals(parameter.getValue()[i])) {
                                response.getWriter().print(p.toString());
                            }
                        }
                    }
                }
            }
        }
    }

}
