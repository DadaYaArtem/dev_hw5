package NonDefaultMethods.user;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static Util.GeneralPrefs.*;

public class Get {
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";

    public static int logUserInSystem(String username, String password) {
        try {
            String url = String.format(DEFAULT_USER_URL + LOGIN + "?username=%s&password=%s",username, password);
            System.out.println(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

    public static int logUserOutSystem(){
        try {
            String url = DEFAULT_USER_URL + LOGOUT;

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }
}
