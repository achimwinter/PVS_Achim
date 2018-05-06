package assignment6.exercise1;

import okhttp3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class PersonServletTest {

    final OkHttpClient client = new OkHttpClient();

    @Test
    public void createPerson() {
        RequestBody formBody = new FormBody.Builder()
                .add("lastName", "Wurst")
                .add("firstName", "Hans")
                .add("birthday", "02.02.2020")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/demo/test")
                .post(formBody)
                .build();

        boolean result = false;

        String responseBody = runClientCall(request);
        if (responseBody.equals("Created successfully"))
            result = true;

        Assert.assertTrue("Person was not created", result);
    }

    @Test
    public void getAllPersons() {
        Request request = new Request.Builder()
                .url("http://localhost:8080/demo/test")
                .get()
                .build();

        String response = runClientCall(request);
        //TODO: How to get List of PersonServlet?
        System.out.println(response);
    }

    @Test
    public void getSpecificPersons(){
        Request request = new Request.Builder()
                .url("http://localhost:8080/demo/test?name=Wurst")
                .get()
                .build();

        String response = runClientCall(request);
        System.out.println(response);
    }



    private String runClientCall(Request request) {
        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
