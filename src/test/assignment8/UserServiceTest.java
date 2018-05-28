package assignment8;

import assignment8.data.User;
import assignment8.data.UserManager;
import com.owlike.genson.Genson;
import okhttp3.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UserServiceTest {

    private final static MediaType JSON = MediaType.parse("application/json; charset=utf8");

    private static String BASE_URL = "http://localhost:5000/zickzack/api";

    private UserManager userManager;

    private OkHttpClient client;

    private Genson genson;

    private List<User> createdUsers;

    private List<String> createdLocations;

    @Before
    public void setUp() {
        userManager = UserManager.getInstance();

        client = new OkHttpClient();

        genson = new Genson();

        createdUsers = new LinkedList<>();

        createdLocations = new LinkedList<>();

        BASE_URL = getUserBaseURL();

        postUser(new User(10));
        postUser(new User(11));
        postUser(new User(12));
    }

    @After
    public void tearDown() {
        for (final String createdLocation : createdLocations) {
            final Request request = new Request.Builder()
                    .url(createdLocation)
                    .delete()
                    .build();

            executeRequest(request);
        }
    }

    @Test
    public void testCreateUser() {
        userManager.createUser();

        final RequestBody body = RequestBody.create(JSON, genson.serialize(userManager.getUser(0)));

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        Response response = executeRequest(request);

        Assert.assertEquals("User was not created", 201, response.code());
    }

    @Test
    public void testDeleteUser() {
        Request request = new Request.Builder()
                .url(createdLocations.get(0))
                .delete()
                .build();

        Response response = executeRequest(request);

        Assert.assertEquals("User was not deleted", 204, response.code());

        request = new Request.Builder()
                .url(createdLocations.get(0))
                .get()
                .build();

        response = executeRequest(request);

        Assert.assertEquals("User was not deleted", 404, response.code());

        if (response.code() == 404) {
            createdLocations.remove(0);
        }
    }

    private void postUser(final User user) {
        final RequestBody body = RequestBody.create(JSON, genson.serialize(user));

        final Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        final Response response = executeRequest(request);

        if (response.code() == 201) {
            createdLocations.add(response.header("Location"));
            createdUsers.add(user);
        }
    }

    private Response executeRequest(final Request request) {
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (final IOException e) {
            response = null;
        }
        return response;
    }

    private String getUserBaseURL() {
        final Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        final Response response = executeRequest(request);

        Headers headers = response.headers();

        for (int i = 0; i < headers.size(); i++) {
            final String headerValue = headers.value(i);
            if (headerValue.contains("users")) {
                final int startIndex = headerValue.indexOf("<");
                final int endIndex = headerValue.indexOf(">");
                return headerValue.substring(startIndex + 1, endIndex);
            }
        }
        return "not found";
    }
}
