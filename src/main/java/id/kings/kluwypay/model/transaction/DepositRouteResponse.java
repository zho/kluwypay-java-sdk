package id.kings.kluwypay.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DepositRouteResponse{

	@JsonProperty("data")
	private List<TrxItem> data;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public List<TrxItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}