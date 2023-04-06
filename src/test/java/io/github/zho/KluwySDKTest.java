package io.github.zho;

import io.github.zho.model.auth.AuthResponse;
import io.github.zho.model.bank.BankListResponse;
import io.github.zho.model.transaction.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class KluwySDKTest {

    @Test
    void getAuthResponse() {

        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        AuthResponse authResponse = kluwyAuth.getAuthResponse();

        assertInstanceOf(AuthResponse.class, authResponse);
        assertEquals(1, Integer.parseInt(authResponse.getStatus()));
    }

    @Test
    void getBankList() {

        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        BankListResponse bankListResponse = KluwyBank.getBankList(kluwyAuth);

        assertInstanceOf(BankListResponse.class, bankListResponse);
        assertEquals(1, Integer.parseInt(bankListResponse.getStatus()));
    }

    @Test
    void getDepositRoute() {

        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        DepositRouteResponse depositRouteResponse = KluwyTransaction.getDepositRoute(kluwyAuth, "va");

        assertInstanceOf(DepositRouteResponse.class, depositRouteResponse);
        assertEquals(1, Integer.parseInt(depositRouteResponse.getStatus()));
    }

    @Test
    void getTransactionStatus() {

        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        TrxStatusResponse trxStatusResponse = KluwyTransaction.getTransactionStatus(kluwyAuth, "8yixT7T25R");

        assertInstanceOf(TrxStatusResponse.class, trxStatusResponse);
        assertEquals(1, Integer.parseInt(trxStatusResponse.getStatus()));
    }

    @Test
    void postDeposit() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        LinkedHashMap<String, Object> depositDataMap = new LinkedHashMap<>();
        depositDataMap.put("address", "12341234");
        depositDataMap.put("amount", 100000000);
        depositDataMap.put("bankCode", "demo");
        depositDataMap.put("alias", "alias-lohh");
        depositDataMap.put("remarks", "alias-lohh");
        depositDataMap.put("type", "va");
        depositDataMap.put("addressName", "JOANDI TEST");
        depositDataMap.put("channelId", "44689828-4479-4299-a32d-59073237657e");
        depositDataMap.put("refId", generateRandomString(10));

        PostDepositResponse postDepositResponse = KluwyTransaction.postDeposit(kluwyAuth, depositDataMap);

        assertInstanceOf(PostDepositResponse.class, postDepositResponse);
        assertEquals(1, Integer.parseInt(postDepositResponse.getStatus()));
    }

    @Test
    void postWithdrawInquiry() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
        requestDataMap.put("address", "12341234");
        requestDataMap.put("amount", 1000000);
        requestDataMap.put("alias", "alias-lohh");
        requestDataMap.put("bankCode", "demo");
        requestDataMap.put("remarks", "alias-lohh");
        requestDataMap.put("refId", generateRandomString(10));

        WithdrawInquiryResponse withdrawInquiryResponse = KluwyTransaction.withdrawInquiry(kluwyAuth, requestDataMap);

        assertInstanceOf(WithdrawInquiryResponse.class, withdrawInquiryResponse);
        assertEquals(1, Integer.parseInt(withdrawInquiryResponse.getStatus()));
    }

    @Test
    void postWithdrawConfirm() {
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String apiKey = System.getenv("API_KEY");
        String secretKey = System.getenv("SECRET_KEY");

        KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, false);

        LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
        requestDataMap.put("address", "12341234");
        requestDataMap.put("amount", 1000000);
        requestDataMap.put("alias", "alias-lohh");
        requestDataMap.put("bankCode", "demo");
        requestDataMap.put("remarks", "alias-lohh");
        requestDataMap.put("refId", generateRandomString(10));

        WithdrawConfirmResponse withdrawConfirmResponse = KluwyTransaction.withdrawConfirm(kluwyAuth, requestDataMap);

        assertInstanceOf(WithdrawConfirmResponse.class, withdrawConfirmResponse);
        assertEquals(1, Integer.parseInt(withdrawConfirmResponse.getStatus()));
    }

    public static String generateRandomString(int length) {

        String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return builder.toString();
    }
}