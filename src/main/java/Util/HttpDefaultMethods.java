package Util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static Util.GeneralPrefs.*;

public class HttpDefaultMethods {

    public static int deleteBySmth(String endpoint, String id){
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(endpoint + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

    public static Object getBySmth(String endpoint, String id) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(endpoint + id))
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), Object.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int postNewObject(String endpoint,Object object) {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(endpoint))
                    .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(object)))
                    .header(NAME_RESPONSE_TYPE, FORM_JSON)
                    .header(NAME_REQUEST_TYPE, FORM_JSON)
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

    public static int updateObject(String endpoint, String username, Object object){
        try {
            String json = GSON.toJson(object);

            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(endpoint + username))
                    .header(NAME_REQUEST_TYPE, FORM_JSON)
                    .header(NAME_RESPONSE_TYPE, FORM_JSON)
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return 500;
    }
}
