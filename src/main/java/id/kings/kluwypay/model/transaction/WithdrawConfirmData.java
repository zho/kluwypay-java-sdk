package id.kings.kluwypay.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawConfirmData {

	@JsonProperty("refNo")
	private String refNo;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("address")
	private String address;

	@JsonProperty("addressName")
	private String addressName;

	@JsonProperty("debitAmount")
	private int debitAmount;

	@JsonProperty("creditAmount")
	private int creditAmount;

	@JsonProperty("trxId")
	private String trxId;

	@JsonProperty("trxDate")
	private String trxDate;

	@JsonProperty("status")
	private String status;

	public void setRefNo(String refNo){
		this.refNo = refNo;
	}

	public String getRefNo(){
		return refNo;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setAddressName(String addressName){
		this.addressName = addressName;
	}

	public String getAddressName(){
		return addressName;
	}

	public void setDebitAmount(int debitAmount){
		this.debitAmount = debitAmount;
	}

	public int getDebitAmount(){
		return debitAmount;
	}

	public void setCreditAmount(int creditAmount){
		this.creditAmount = creditAmount;
	}

	public int getCreditAmount(){
		return creditAmount;
	}

	public void setTrxId(String trxId){
		this.trxId = trxId;
	}

	public String getTrxId(){
		return trxId;
	}

	public void setTrxDate(String trxDate){
		this.trxDate = trxDate;
	}

	public String getTrxDate(){
		return trxDate;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}