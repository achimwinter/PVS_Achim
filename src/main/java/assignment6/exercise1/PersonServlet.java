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

    private List<Person> persons = new LinkedList<>();

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) {

        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String birthday = request.getParameter("birthday");

        Person person = new Person(lastName, firstName, birthday);
        persons.add(person);
        if (persons.contains(person)) {
            try {
                response.getWriter().print("Created successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException {

        if (request.getQueryString() == null) {
            printAllPersons(response);
        } else {
            printSpecificPersons(request, response);
        }

    }


    private void printSpecificPersons(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        Map<String, String[]> parameters = request.getParameterMap();

        for (Map.Entry<String, String[]> parameter : parameters.entrySet()) {
            if (parameter.getKey().equals("name")) {
                iterateNameParameter(parameter, response);
            }
        }

    }

    private void iterateNameParameter(Map.Entry<String, String[]> parameter, final HttpServletResponse response) throws IOException{
        for (int i = 0; i < parameter.getValue().length; i++) {
            for (Person person : persons){
                if (person.getLastName().equals(parameter.getValue()[i]))
                printResponse(person.toString(), response);
            }
        }
    }

    private void printAllPersons(final HttpServletResponse response) throws IOException {
        for (Person person : persons) {
            printResponse(person.toString(), response);
        }
    }

    private void printResponse(String toPrint, final HttpServletResponse response) throws IOException{
        response.getWriter().print(toPrint);
    }

}
