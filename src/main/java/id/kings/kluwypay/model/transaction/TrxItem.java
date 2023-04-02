package id.kings.kluwypay.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class TrxItem {

	@JsonProperty("minAmount")
	private int minAmount;

	@JsonProperty("code")
	private String code;

	@JsonProperty("mId")
	private String mId;

	@JsonProperty("weight")
	private int weight;

	@JsonProperty("pId")
	private String pId;

	@JsonProperty("trxType")
	private String trxType;

	@JsonProperty("chId")
	private String chId;

	@JsonProperty("tId")
	private String tId;

	@JsonProperty("createdtime")
	private Timestamp createdtime;

	@JsonProperty("updatedtime")
	private Timestamp updatedtime;

	@JsonProperty("ntId")
	private String ntId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("maxAmount")
	private int maxAmount;

	@JsonProperty("mName")
	private String mName;

	@JsonProperty("chName")
	private String chName;

	public int getMinAmount(){
		return minAmount;
	}

	public String getCode(){
		return code;
	}

	public String getMId(){
		return mId;
	}

	public int getWeight(){
		return weight;
	}

	public String getPId(){
		return pId;
	}

	public String getTrxType(){
		return trxType;
	}

	public String getChId(){
		return chId;
	}

	public String getTId(){
		return tId;
	}

	public Timestamp getCreatedtime(){
		return createdtime;
	}

	public Timestamp getUpdatedtime(){
		return updatedtime;
	}

	public String getNtId(){
		return ntId;
	}

	public String getName(){
		return name;
	}

	public int getMaxAmount(){
		return maxAmount;
	}

	public String getMName(){
		return mName;
	}

	public String getChName(){
		return chName;
	}
}