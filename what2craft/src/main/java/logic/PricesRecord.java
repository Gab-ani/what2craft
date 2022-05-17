package logic;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "price_records")
public class PricesRecord {

	@Id
	private int id;
	
	@OneToOne(targetEntity = Item.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "item_id")
	private Item item;
	
	private int lymhurst;
	private int sterling;
	private int thedford;
	private int martlock;
	private int bridgewatch;
	private int caerleon;
	
	public PricesRecord() {
		
	}
	
	public int priceIn(String city) throws UnknownCityException {
		if(city == "lymhurst")
			return lymhurst;
		if(city == "sterling")
			return sterling;
		if(city == "thedford")
			return thedford;
		if(city == "martlock")
			return martlock;
		if(city == "bridgewatch")
			return bridgewatch;
		if(city == "caerleon")
			return caerleon;
		throw new UnknownCityException("unknown city " + city);
	}
	
}
