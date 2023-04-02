package id.kings.kluwypay.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TrxStatusResponse{

	@JsonProperty("data")
	private TrxStatusItem trxStatusItem;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public TrxStatusItem getData(){
		return trxStatusItem;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}