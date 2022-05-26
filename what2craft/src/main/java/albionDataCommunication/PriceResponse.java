package albionDataCommunication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

import logic.ItemBasic;

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


	
	public boolean isActual() {
		if(sellPriceMin != 0 && ( LocalDateTime.now().isAfter( parseDate(sellPriceMinDate).minusMinutes(40) ) ) )		// ie we didn't get 0 price from request and it's last update was recent
			return true;
		return false;
	}
	
	private LocalDateTime parseDate(String date) {
		LocalDateTime lastUpdate = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		//System.out.println(lastUpdate);
		return lastUpdate;
	}
	
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
