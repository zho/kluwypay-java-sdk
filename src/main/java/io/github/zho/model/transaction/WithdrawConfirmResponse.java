package io.github.zho.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawConfirmResponse{

	@JsonProperty("data")
	private WithdrawConfirmData withdrawConfirmData;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public void setData(WithdrawConfirmData withdrawConfirmData){
		this.withdrawConfirmData = withdrawConfirmData;
	}

	public WithdrawConfirmData getData(){
		return withdrawConfirmData;
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