package id.kings.kluwypay.model.bank;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankListResponse{

	@JsonProperty("data")
	private List<BankItem> data;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public List<BankItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}