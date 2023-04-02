package id.kings.kluwypay.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawInquiryResponse{

	@JsonProperty("data")
	private WithdrawInquiryData withdrawInquiryData;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public void setData(WithdrawInquiryData withdrawInquiryData){
		this.withdrawInquiryData = withdrawInquiryData;
	}

	public WithdrawInquiryData getData(){
		return withdrawInquiryData;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}