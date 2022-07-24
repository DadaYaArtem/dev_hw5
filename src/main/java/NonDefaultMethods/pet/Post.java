package NonDefaultMethods.pet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static Util.GeneralPrefs.*;


public class Post {

    private static final String NAME_RESPONSE_TYPE = "accept";
    private static final String FORM_JSON = "application/json";
    private static final String NAME_REQUEST_TYPE = "Content-Type";
    private static final String POST_UPLOAD_IMAGE = "/uploadImage";
    private static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String ADDITIONAL_METADATA = "additionalMetadata";
    private static final String FILE = "file";
    private static final String NAME = "name";
    private static final String STATUS = "status";

    public static int updatePetWithFormData(long idPet, String newName, String newStatus) {
        try {
            Map<String, String> values = new HashMap<>() {{

                put(NAME, newName);
                put(STATUS, newStatus);
            }};

            ObjectMapper objectMapper = new ObjectMapper();

            String requestBody = objectMapper
                    .writeValueAsString(values);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(DEFAULT_PET_URL + idPet))
                    .header(NAME_REQUEST_TYPE, FORM_URL_ENCODED)
                    .header(NAME_RESPONSE_TYPE, FORM_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return 500;
    }

    public static int addPhotoToPet(long idPet, String imgUrl, String addMetadata) {

        String requestURL = DEFAULT_PET_URL + idPet + POST_UPLOAD_IMAGE;
        File img = new File(imgUrl);


        try {
            InputStream fis = new FileInputStream(img);
            byte[] allBytes = fis.readAllBytes();
            CloseableHttpClient client = HttpClients.createDefault();


            HttpEntity multipart = MultipartEntityBuilder.create().
                    addTextBody(ADDITIONAL_METADATA, addMetadata, ContentType.TEXT_PLAIN).
                    addBinaryBody(FILE, allBytes, ContentType.DEFAULT_BINARY, img.getName()).
                    build();

            HttpPost httpPost = new HttpPost(requestURL);
            httpPost.setEntity(multipart);

            client.execute(httpPost);
            return client.execute(httpPost).getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 500;
    }
}
