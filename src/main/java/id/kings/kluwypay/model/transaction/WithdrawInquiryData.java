package id.kings.kluwypay.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawInquiryData {

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

	@JsonProperty("totFee")
	private int totFee;

	@JsonProperty("bankName")
	private String bankName;

	@JsonProperty("debitAmount")
	private int debitAmount;

	@JsonProperty("alias")
	private String alias;

	@JsonProperty("addressName")
	private String addressName;

	@JsonProperty("creditAmount")
	private int creditAmount;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("trxDate")
	private String trxDate;

	public void setBankCode(String bankCode){
		this.bankCode = bankCode;
	}

	public String getBankCode(){
		return bankCode;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setFees(List<FeesItem> fees){
		this.fees = fees;
	}

	public List<FeesItem> getFees(){
		return fees;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setTotAmount(int totAmount){
		this.totAmount = totAmount;
	}

	public int getTotAmount(){
		return totAmount;
	}

	public void setTotFee(int totFee){
		this.totFee = totFee;
	}

	public int getTotFee(){
		return totFee;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public void setDebitAmount(int debitAmount){
		this.debitAmount = debitAmount;
	}

	public int getDebitAmount(){
		return debitAmount;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public String getAlias(){
		return alias;
	}

	public void setAddressName(String addressName){
		this.addressName = addressName;
	}

	public String getAddressName(){
		return addressName;
	}

	public void setCreditAmount(int creditAmount){
		this.creditAmount = creditAmount;
	}

	public int getCreditAmount(){
		return creditAmount;
	}

	public void setRemarks(String remarks){
		this.remarks = remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setTrxDate(String trxDate){
		this.trxDate = trxDate;
	}

	public String getTrxDate(){
		return trxDate;
	}
}