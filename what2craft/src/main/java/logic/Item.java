package logic;

import javax.persistence.Entity;

@Entity
public class Item {
	
	private long id;	// not generated, I plan to use ids from formatted/items list
	
	private int tier;
	private int quality;
	private String name;
	
	private int planks;
	private int cloth;
	private int ingots;
	private int leather;
	private String artifact;
	
	private int actualPrice;
	
}
