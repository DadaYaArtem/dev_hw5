package NonDefaultMethods.user;

import objects.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static Util.GeneralPrefs.*;

public class Post {
    public static final String CREATE_ARRAY_OF_USERS = "createWithArray";
    private static final String NAME_RESPONSE_TYPE = "accept";
    private static final String FORM_JSON = "application/json";
    private static final String NAME_REQUEST_TYPE = "Content-Type";

    public static int createWithArrayOfUsers(User[] arrayOfUsers){
        try {
            String url = DEFAULT_USER_URL + CREATE_ARRAY_OF_USERS;

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header(NAME_RESPONSE_TYPE, FORM_JSON)
                    .header(NAME_REQUEST_TYPE, FORM_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(arrayOfUsers)))
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

    public static int createWithListOfUsers(List<User> listOfUsers){
        try {
            String url = DEFAULT_USER_URL + CREATE_ARRAY_OF_USERS;

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header(NAME_RESPONSE_TYPE, FORM_JSON)
                    .header(NAME_REQUEST_TYPE, FORM_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(listOfUsers)))
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

}
