package id.kings.kluwypay;

import id.kings.kluwypay.model.bank.BankListResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


public class KluwyBank {

    public static BankListResponse getBankList(KluwyAuth kluwyAuth) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("platform", "api");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, String>> request = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(kluwyAuth.BASE_URL + "/v1/api/bank", HttpMethod.GET, request, BankListResponse.class).getBody();
    }
}
