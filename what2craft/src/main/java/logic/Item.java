package logic;

import javax.persistence.Entity;

@Entity
public class Item {
	
	private long id;	// not generated, I plan to use ids from formatted/items list
	
	private int tier;
	private int quality;
	private String name;
	
	private String requestName;
	
	private int planks;
	private int cloth;
	private int ingots;
	private int leather;
	private String artifact;
	
	private String gradeType;
	private int gradesToEnchant;
	
	private int actualPrice;
	
	public Item() {
		
	}

	public int getQuality() {
		return quality;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String id) {
		this.requestName = id;	
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}
	
}
