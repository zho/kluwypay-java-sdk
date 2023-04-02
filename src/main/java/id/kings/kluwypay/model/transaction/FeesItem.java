package id.kings.kluwypay.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeesItem{

	@JsonProperty("feeamount")
	private int feeamount;

	@JsonProperty("feename")
	private String feename;

	@JsonProperty("id")
	private String id;

	public void setFeeamount(int feeamount){
		this.feeamount = feeamount;
	}

	public int getFeeamount(){
		return feeamount;
	}

	public void setFeename(String feename){
		this.feename = feename;
	}

	public String getFeename(){
		return feename;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}