package NonDefaultMethods.store;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import static Util.GeneralPrefs.*;

public class Get {
    public static final String FIND_ORDER_BY_STATUS = "inventory?status=";
    public static final String ORDER = "order/";
    public static final String INVENTORY = "inventory/";

    public static Map<String, Integer> findOrderStatuses() {
        String url = DEFAULT_ORDER_URL + INVENTORY;

        System.out.println(url);

        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            Type type = new TypeToken<LinkedHashMap<String, Integer>>() {
            }.getType();

            return GSON.fromJson(body, type);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
