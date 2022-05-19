package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PriceArchive {
	
	private HashMap<ItemCombined, Integer> memory;
	
	private HashMap<String, int[][]> materials;
	private HashMap<Artifact, int[]> arts;
	
	public PriceArchive(List<Artifact> artifacts) {
		materials = new HashMap<>();
		materials.put("planks", new int[8][4]);		// first index is tier (1), second is enchantment level 
		materials.put("ingots", new int[8][4]);
		materials.put("cloth", new int[8][4]);
		materials.put("leather", new int[8][4]);
		arts = new HashMap<>();
		for(Artifact art : artifacts) {
			arts.put(art, new int[8]);				// index is tier (1)
		}
		memory = new HashMap<>();
	}
	
	public int remember(ItemCombined item) {
		return memory.get(item);
	}
	
	public void memorize(ItemCombined item, int price) {
		memory.put(item, price);
	}
	
	public void setPrice(String good, int tier, int enchantment, int price) {			//  "tier" can be from 1 to 8, so -1. Enchantment level can be from 0 to 3 so no modifications
		materials.get(good)[tier-1][enchantment] = price;
	}
	
	public void setPrice(Artifact artifact, int tier, int price) {
		arts.get(artifact)[tier-1] = price;
	}

	public int[][] getPrices(String resource) {
		return materials.get(resource);
	}

	public HashMap<ItemCombined, Integer> getMemory() {
		return memory;
	}
}