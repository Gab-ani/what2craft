package logic;

import java.util.ArrayList;

public class CraftAdvisorData {

	private City city;
	
	private ArrayList<ItemCombined> items;
	
	private ArrayList<String> blacklist;
	
	private CraftAdvisorData() {				// privating constructor to force builder behaviour
		
	}
	
	public static CraftAdvisorData assembleCraftData() {
		return new CraftAdvisorData();
	}
	
	public CraftAdvisorData lookUp(ArrayList<ItemCombined> items) {
		this.items = items;
		return this;
	}
	
	public CraftAdvisorData inCity(City city) {
		this.city = city;
		return this;
	}
	
	public CraftAdvisorData except(ArrayList<String> blacklist) {
		this.blacklist = blacklist;
		return this;
	}
	
	public City city() {
		return city;
	}
	
	public ArrayList<ItemCombined> items() {
		return items;
	}
	
	public ArrayList<String> blacklist() {
		return blacklist;
	}
}
