package id.kings.kluwypay.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponse{

	@JsonProperty("data")
	private AuthData authData;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public void setData(AuthData authData){
		this.authData = authData;
	}

	public AuthData getData(){
		return authData;
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