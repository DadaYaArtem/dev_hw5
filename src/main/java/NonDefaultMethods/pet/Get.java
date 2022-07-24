package NonDefaultMethods.pet;


import com.google.gson.reflect.TypeToken;
import objects.Pet;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static Util.GeneralPrefs.*;

public class Get {
    public static final String FIND_PET_BY_STATUS = "findByStatus?status=";


    public static List<Pet> findPetByStatus(List<String> status) {

        StringBuilder url = new StringBuilder(DEFAULT_PET_URL + FIND_PET_BY_STATUS);
        for (int i = 0; i < status.size(); i++) {
            if (i == status.size() - 1) {
                url.append(status.get(i));
            } else {
                url.append(status.get(i)).append(",");
            }
        }
        System.out.println(url);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url.toString()))
                .GET()
                .build();

        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            Type typeToken = TypeToken
                    .getParameterized(List.class, Pet.class)
                    .getType();

            return GSON.fromJson(body, typeToken);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
