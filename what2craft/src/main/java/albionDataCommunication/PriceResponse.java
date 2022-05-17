package albionDataCommunication;

import com.fasterxml.jackson.annotation.JsonProperty;

import logic.Item;

public class PriceResponse {

	@JsonProperty("item_id")
	private String itemId;
	@JsonProperty("city")
	private String city;
	@JsonProperty("quality")
	private int quality;
	@JsonProperty("sell_price_min")
	private int sellPriceMin;
	@JsonProperty("sell_price_min_date")
	private String sellPriceMinDate;
	@JsonProperty("sell_price_max")
	private int sellPriceMax;
	@JsonProperty("sell_price_max_date")
	private String sellPriceMaxDate;
	@JsonProperty("buy_price_min")
	private int buyPriceMin;
	@JsonProperty("buy_price_min_date")
	private String buyPriceMinDate;
	@JsonProperty("buy_price_max")
	private int buyPriceMax;
	@JsonProperty("buy_price_max_date")
	private String buyPriceMaxDate;
	
	
	
//	public Item toItem() {
//		
//	}



	public String getItemId() {
		return itemId;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getMinSellPrice() {
		return sellPriceMin;
	}
	
	@Override
	public String toString() {
		return itemId + " in " + city + " for " + sellPriceMin;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void setSellPriceMin(int sellPriceMin) {
		this.sellPriceMin = sellPriceMin;
	}

	public void setSellPriceMinDate(String sellPriceMinDate) {
		this.sellPriceMinDate = sellPriceMinDate;
	}

	public void setSellPriceMax(int sellPriceMax) {
		this.buyPriceMax = sellPriceMax;
	}

	public void setSellPriceMaxDate(String sellPriceMaxDate) {
		this.buyPriceMaxDate = sellPriceMaxDate;
	}

	public void setBuyPriceMin(int buyPriceMin) {
		this.buyPriceMin = buyPriceMin;
	}

	public void setBuyPriceMinDate(String buyPriceMinDate) {
		this.buyPriceMinDate = buyPriceMinDate;
	}

	public void setBuyPriceMax(int buyPriceMax) {
		this.buyPriceMax = buyPriceMax;
	}

	public void setBuyPriceMaxDate(String buyPriceMaxDate) {
		this.buyPriceMaxDate = buyPriceMaxDate;
	}
}
