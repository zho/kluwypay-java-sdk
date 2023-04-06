# kluwypay-java-sdk
Kluwypay Integration Java SDK

# Installation

add dependency to pom.xml
```
<dependency>
  <groupId>io.github.zho</groupId>
  <artifactId>kluwypay-java-sdk</artifactId>
  <version>1.0</version>
</dependency>
```

# Usage
## Authentication
to use the api functionality, you need to get client id, client secret, api key, secret key from kluwypay's partner dashboard
```
String clientId = System.getenv("CLIENT_ID");
String clientSecret = System.getenv("CLIENT_SECRET");
String apiKey = System.getenv("API_KEY");
String secretKey = System.getenv("SECRET_KEY");

KluwyAuth kluwyAuth = new KluwyAuth(clientId, clientSecret, apiKey, secretKey, <production environment true|false>);
```
## Functionality
### Get Available Bank List
```
BankListResponse bankListResponse = KluwyBank.getBankList(kluwyAuth);
```
### Get Available Deposit Route
```
DepositRouteResponse depositRouteResponse = KluwyTransaction.getDepositRoute(kluwyAuth, "<deposit type va|qris>");
```
### Get Transaction Status
```
TrxStatusResponse trxStatusResponse = KluwyTransaction.getTransactionStatus(kluwyAuth, "<reference id>");
```
### Create Deposit Transaction
```
LinkedHashMap<String, Object> depositDataMap = new LinkedHashMap<>();
depositDataMap.put("address", "<bank account number>");
depositDataMap.put("amount", 100000000);
depositDataMap.put("bankCode", "demo");
depositDataMap.put("alias", "alias-lohh");
depositDataMap.put("remarks", "alias-lohh");
depositDataMap.put("type", "va");
depositDataMap.put("addressName", "<Bank Account Owner's>");
depositDataMap.put("channelId", "<your partner's channel id>");
depositDataMap.put("refId", <reference id>);

PostDepositResponse postDepositResponse = KluwyTransaction.postDeposit(kluwyAuth, depositDataMap);
```
### Create Withdraw Inquiry
```
LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
requestDataMap.put("address", "<bank account number>");
requestDataMap.put("amount", 1000000);
requestDataMap.put("alias", "alias-lohh");
requestDataMap.put("bankCode", "demo");
requestDataMap.put("remarks", "alias-lohh");
requestDataMap.put("refId", <reference id>);

WithdrawInquiryResponse withdrawInquiryResponse = KluwyTransaction.withdrawInquiry(kluwyAuth, requestDataMap);
```
### Create Withdraw Confirmation
```
LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
requestDataMap.put("address", "<bank account number>");
requestDataMap.put("amount", 1000000);
requestDataMap.put("alias", "alias-lohh");
requestDataMap.put("bankCode", "demo");
requestDataMap.put("remarks", "alias-lohh");
requestDataMap.put("refId", <reference id>);

WithdrawConfirmResponse withdrawConfirmResponse = KluwyTransaction.withdrawConfirm(kluwyAuth, requestDataMap);
```
