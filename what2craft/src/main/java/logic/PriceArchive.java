package logic;

import java.util.ArrayList;
import java.util.HashMap;

class PriceArchive {
	
	private HashMap<String, int[][]> materials;
	private HashMap<String, int[]> arts;
	
	public PriceArchive(ArrayList<String> artifacts) {
		materials = new HashMap<>();
		materials.put("planks", new int[8][4]);		// first index is tier (1), second is enchantment level 
		materials.put("ingots", new int[8][4]);
		materials.put("cloth", new int[8][4]);
		materials.put("leather", new int[8][4]);
		arts = new HashMap<>();
		for(String art : artifacts) {
			arts.put(art, new int[8]);				// index is tier (1)
		}
	}
	
	public void setPrice(String good, int tier, int enchantment, int price) {
		materials.get(good)[tier][enchantment] = price;
	}
	
	public void setPrice(String artifact, int tier, int price) {
		arts.get(artifact)[tier] = price;
	}
}