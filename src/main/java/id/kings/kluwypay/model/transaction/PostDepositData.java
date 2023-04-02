package id.kings.kluwypay.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDepositData {

	@JsonProperty("bankCode")
	private String bankCode;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("fees")
	private List<FeesItem> fees;

	@JsonProperty("address")
	private String address;

	@JsonProperty("totAmount")
	private int totAmount;

	@JsonProperty("debitAmount")
	private int debitAmount;

	@JsonProperty("type")
	private String type;

	@JsonProperty("trxId")
	private String trxId;

	@JsonProperty("accountId")
	private String accountId;

	@JsonProperty("feeAmount")
	private int feeAmount;

	@JsonProperty("channelName")
	private String channelName;

	@JsonProperty("addressName")
	private String addressName;

	@JsonProperty("refId")
	private String refId;

	@JsonProperty("creditAmount")
	private int creditAmount;

	@JsonProperty("trxDate")
	private String trxDate;

	@JsonProperty("channelId")
	private String channelId;

	@JsonProperty("status")
	private String status;

	public String getBankCode(){
		return bankCode;
	}

	public int getAmount(){
		return amount;
	}

	public List<FeesItem> getFees(){
		return fees;
	}

	public String getAddress(){
		return address;
	}

	public int getTotAmount(){
		return totAmount;
	}

	public int getDebitAmount(){
		return debitAmount;
	}

	public String getType(){
		return type;
	}

	public String getTrxId(){
		return trxId;
	}

	public String getAccountId(){
		return accountId;
	}

	public int getFeeAmount(){
		return feeAmount;
	}

	public String getChannelName(){
		return channelName;
	}

	public String getAddressName(){
		return addressName;
	}

	public String getRefId(){
		return refId;
	}

	public int getCreditAmount(){
		return creditAmount;
	}

	public String getTrxDate(){
		return trxDate;
	}

	public String getChannelId(){
		return channelId;
	}

	public String getStatus(){
		return status;
	}
}