package albionDataCommunication;

import logic.Item;

public class PriceResponse {

	private String itemId;					// snake case because I want the fields to better represent given Json to improve readability to anyone in the future
	private String city;
	private int quality;
	private int sellPriceMin;
	private String sellPriceMinDate;
	private int sellPriceMax;
	private String sellPriceMaxDate;
	private int buyPriceMin;
	private String buyPriceMinDate;
	private int buyPriceMax;
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

	public void setItem_id(String item_id) {
		this.itemId = item_id;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void setSell_price_min(int sell_price_min) {
		this.sellPriceMin = sell_price_min;
	}

	public void setSell_price_min_date(String sell_price_min_date) {
		this.sellPriceMinDate = sell_price_min_date;
	}

	public void setSell_price_max(int sell_price_max) {
		this.buyPriceMax = sell_price_max;
	}

	public void setSell_price_max_date(String sell_price_max_date) {
		this.buyPriceMaxDate = sell_price_max_date;
	}

	public void setBuy_price_min(int buy_price_min) {
		this.buyPriceMin = buy_price_min;
	}

	public void setBuy_price_min_date(String buy_price_min_date) {
		this.buyPriceMinDate = buy_price_min_date;
	}

	public void setBuy_price_max(int buy_price_max) {
		this.buyPriceMax = buy_price_max;
	}

	public void setBuy_price_max_date(String buy_price_max_date) {
		this.buyPriceMaxDate = buy_price_max_date;
	}
}
