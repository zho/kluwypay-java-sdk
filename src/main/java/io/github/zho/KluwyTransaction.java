package io.github.zho;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.zho.model.transaction.*;
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

    /**
     *
     * Get available deposit route
     *
     * @param kluwyAuth Authentication Object
     * @param routeType the type of route usually "va" or "qris"
     * @return {@link DepositRouteResponse} Object
     */
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

    /**
     *
     * Get Transaction Status based on refId
     *
     * @param kluwyAuth Authentication Object
     * @param refId reference id that posted while you create the transaction
     * @return {@link TrxStatusResponse} Object
     */
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

    /**
     *
     * Create deposit / Post Deposit
     *
     * @param kluwyAuth Authentication Object
     * @param requestData Map Of Request Data
     * @return {@link PostDepositResponse} Object
     */
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

    /**
     *
     * Get/Update Withdraw Status
     *
     * @param kluwyAuth Authentication Object
     * @param requestData Map Of Request Data
     * @return {@link WithdrawInquiryResponse} Object
     */
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

    /**
     * Withdraw Confirm
     *
     * @param kluwyAuth   Authentication Object
     * @param requestData Map Of Request Data
     * @return {@link WithdrawConfirmResponse} Object
     */
    public static WithdrawConfirmResponse withdrawConfirm(KluwyAuth kluwyAuth, LinkedHashMap<String, Object> requestData) {

        WithdrawConfirmResponse withdrawConfirmResponse;

        try {

            String signature = getSignature(kluwyAuth, requestData);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.set("accesstoken", "Bearer " + kluwyAuth.getAuthResponse().getData().getAccessToken());
            httpHeaders.set("signature", signature);
            httpHeaders.set("platform", "api");

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestData, httpHeaders);

            withdrawConfirmResponse = restTemplate.postForEntity(kluwyAuth.BASE_URL + "/v1/api/withdraw", request, WithdrawConfirmResponse.class).getBody();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return withdrawConfirmResponse;
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
