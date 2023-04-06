package io.github.zho.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostDepositResponse{

	@JsonProperty("data")
	private PostDepositData postDepositData;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public PostDepositData getData(){
		return postDepositData;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}