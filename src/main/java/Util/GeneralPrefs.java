package Util;

import com.google.gson.*;

import java.net.http.HttpClient;

public class GeneralPrefs {
    public static final String DEFAULT_PET_URL = "https://petstore.swagger.io/v2/pet/";
    public static final String DEFAULT_ORDER_URL = "https://petstore.swagger.io/v2/store/";
    public static final String DEFAULT_USER_URL = "https://petstore.swagger.io/v2/user/";
    public static final String ORDER = "order/";
    public static final String NAME_RESPONSE_TYPE = "accept";
    public static final String FORM_JSON = "application/json";
    public static final String NAME_REQUEST_TYPE = "Content-Type";
    public static final HttpClient CLIENT = HttpClient.newHttpClient();
    public static final Gson GSON = new Gson();
}
