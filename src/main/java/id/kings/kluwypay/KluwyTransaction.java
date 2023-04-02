package id.kings.kluwypay;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.kings.kluwypay.model.transaction.DepositRouteResponse;
import id.kings.kluwypay.model.transaction.PostDepositResponse;
import id.kings.kluwypay.model.transaction.TrxStatusResponse;
import id.kings.kluwypay.model.transaction.WithdrawInquiryResponse;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class KluwyTransaction {

    public static DepositRouteResponse getDepositRoute(KluwyAuth kluwyAuth, String routeType) {

        DepositRouteResponse depositRouteResponse = null;

        try {
            String signature = getSignature(kluwyAuth, new LinkedHashMap<>());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("platform", "api");
            httpHeaders.set("type", routeType);
            httpHeaders.set("accesstoken", "Bearer " + kluwyAuth.getAuthResponse().getData().getAccessToken());
            httpHeaders.set("signature", signature);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Map<String, String>> request = new HttpEntity<>(httpHeaders);

            depositRouteResponse = restTemplate.exchange(kluwyAuth.BASE_URL + "/v1/api/deposit/route", HttpMethod.GET, request, DepositRouteResponse.class).getBody();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return depositRouteResponse;
    }

    public static TrxStatusResponse getTransactionStatus(KluwyAuth kluwyAuth, String refId) {

        TrxStatusResponse trxStatusResponse;

        try {
            String signature = getSignature(kluwyAuth, new LinkedHashMap<>());

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("platform", "api");
            httpHeaders.set("accesstoken", "Bearer " + kluwyAuth.getAuthResponse().getData().getAccessToken());
            httpHeaders.set("signature", signature);
            httpHeaders.set("refid", refId);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Map<String, String>> request = new HttpEntity<>(httpHeaders);

            trxStatusResponse = restTemplate.exchange(kluwyAuth.BASE_URL + "/v1/api/transaction/status", HttpMethod.GET, request, TrxStatusResponse.class).getBody();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return trxStatusResponse;
    }

    public static PostDepositResponse postDeposit(KluwyAuth kluwyAuth, LinkedHashMap<String, Object> requestData) {

        PostDepositResponse postDepositResponse;

        try {
            String signature = getSignature(kluwyAuth, requestData);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("accesstoken", "Bearer " + kluwyAuth.getAuthResponse().getData().getAccessToken());
            httpHeaders.set("signature", signature);
            httpHeaders.set("platform", "api");

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, httpHeaders);

            postDepositResponse = restTemplate.postForEntity(kluwyAuth.BASE_URL + "/v1/api/deposit", request, PostDepositResponse.class).getBody();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return postDepositResponse;
    }

    public static WithdrawInquiryResponse withdrawInquiry(KluwyAuth kluwyAuth, LinkedHashMap<String, Object> requestData) {

        WithdrawInquiryResponse withdrawInquiryResponse;

        try {

            String signature = getSignature(kluwyAuth, requestData);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("accesstoken", "Bearer " + kluwyAuth.getAuthResponse().getData().getAccessToken());
            httpHeaders.set("signature", signature);
            httpHeaders.set("platform", "api");

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, httpHeaders);

            withdrawInquiryResponse = restTemplate.postForEntity(kluwyAuth.BASE_URL + "/v1/api/withdraw/inquiry", request, WithdrawInquiryResponse.class).getBody();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return withdrawInquiryResponse;
    }

    private static String getSignature(KluwyAuth kluwyAuth, LinkedHashMap<String, Object> requestBody) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        String signature = "";
        Map<String, String> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        String encodedHeader = base64Url(mapper.writeValueAsString(header));

        signature += encodedHeader + ".";

        String encodedPayload = base64Url(mapper.writeValueAsString(requestBody));

        signature += encodedPayload;
        String signedKey = hmacWithApacheCommons(signature, kluwyAuth.SECRET_KEY);
        signedKey = hexEncode(signedKey);
        signature += "." + signedKey;

        return signature;
    }

    private static String base64Url(String source) {
        String encodedSource = Base64.getEncoder().encodeToString(source.getBytes());
        encodedSource = encodedSource.replace("=", "");

        // Replace characters according to base64url specifications
        encodedSource = encodedSource.replace("+", "-");
        encodedSource = encodedSource.replace("/", "_");

        return encodedSource;
    }

    private static String hexEncode(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        // return data;
        String encodedSource = Base64.getEncoder().encodeToString(data);
        encodedSource = encodedSource.replace("=", "");

        // Replace characters according to base64url specifications
        encodedSource = encodedSource.replace("+", "-");
        encodedSource = encodedSource.replace("/", "_");

        return encodedSource;
    }

    private static String hmacWithApacheCommons(String data, String key) {
        return new HmacUtils("HmacSHA256", key).hmacHex(data);
    }

}
