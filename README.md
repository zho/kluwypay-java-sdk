# kluwypay-java-sdk
Kluwypay Integration Java SDK

# Installation

add the repository configuration in your pom.xml
```
<repositories>
  <repository>
    <id>kluwypay-java-sdk</id>
    <url>https://zho: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@maven.pkg.github.com/zho/kluwypay-java-sdk</url>
  </repository>
</repositories>
```

add dependency to pom.xml
```
<dependency>
  <groupId>id.kings.kluwypay</groupId>
  <artifactId>kluwypay-java-sdk</artifactId>
  <version>1.0</version>
</dependency>
```

# Usage
## Authentication
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
depositDataMap.put("address", "12341234");
depositDataMap.put("amount", 100000000);
depositDataMap.put("bankCode", "demo");
depositDataMap.put("alias", "alias-lohh");
depositDataMap.put("remarks", "alias-lohh");
depositDataMap.put("type", "va");
depositDataMap.put("addressName", "JOANDI TEST");
depositDataMap.put("channelId", "44689828-4479-4299-a32d-59073237657e");
depositDataMap.put("refId", <reference id>);

PostDepositResponse postDepositResponse = KluwyTransaction.postDeposit(kluwyAuth, depositDataMap);
```
### Create Withdraw Inquiry
```
LinkedHashMap<String, Object> requestDataMap = new LinkedHashMap<>();
requestDataMap.put("address", "12341234");
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
requestDataMap.put("address", "12341234");
requestDataMap.put("amount", 1000000);
requestDataMap.put("alias", "alias-lohh");
requestDataMap.put("bankCode", "demo");
requestDataMap.put("remarks", "alias-lohh");
requestDataMap.put("refId", <reference id>);

WithdrawConfirmResponse withdrawConfirmResponse = KluwyTransaction.withdrawConfirm(kluwyAuth, requestDataMap);
```
