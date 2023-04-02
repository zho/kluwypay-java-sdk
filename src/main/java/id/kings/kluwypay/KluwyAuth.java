package id.kings.kluwypay;

import id.kings.kluwypay.model.auth.AuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class KluwyAuth {

    public static final String STAGING_URL = "https://api.kluwypay.dev";

    public static final String LIVE_URL = "https://api.kluwypay.com";

    public String CLIENT_ID;
    public String CLIENT_SECRET;
    public String API_KEY;
    public String SECRET_KEY;
    public URI BASE_URL;

    public boolean PRODUCTION;

    /**
     * Authentication
     *
     * @param CLIENT_ID From Partner Portal
     * @param CLIENT_SECRET From Partner Portal
     * @param API_KEY From Partner Portal
     * @param SECRET_KEY From Partner Portal
     * @param PRODUCTION true = use production environment, false = use staging environment
     */
    public KluwyAuth(String CLIENT_ID,
                     String CLIENT_SECRET,
                     String API_KEY,
                     String SECRET_KEY, boolean PRODUCTION) {

        this.CLIENT_ID = CLIENT_ID;
        this.CLIENT_SECRET = CLIENT_SECRET;
        this.API_KEY = API_KEY;
        this.SECRET_KEY = SECRET_KEY;
        this.PRODUCTION = PRODUCTION;

        try {
            if (PRODUCTION) {
                this.BASE_URL = new URI(LIVE_URL);
            } else {
                this.BASE_URL = new URI(STAGING_URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AuthResponse getAuthResponse() {

        Base64.Encoder encoder = Base64.getEncoder();
        String authToken = encoder.encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(authToken);

        Map<String, String> bodyMap = new LinkedHashMap<>();
        bodyMap.put("grant_type", "client_credentials");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        HttpEntity<Map<String, String>> request = new HttpEntity<>(bodyMap, httpHeaders);

        return restTemplate.postForEntity(BASE_URL + "/v1/access-token", request, AuthResponse.class).getBody();
    }

    private static List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }
}
