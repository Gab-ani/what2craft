package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PriceArchive {
	
	private HashMap<ItemCombined, Integer> memory;
	
	private HashMap<String, int[]> journals;
	private HashMap<String, int[][]> materials;
	private HashMap<String, int[]> arts;
	private HashMap<String, int[]> grades;
	
	public PriceArchive(List<Artifact> artifacts) {
		materials = new HashMap<>();
		materials.put("planks", new int[8][4]);		// first index is tier (+1), second is enchantment level 
		materials.put("ingots", new int[8][4]);
		materials.put("cloth", new int[8][4]);
		materials.put("leather", new int[8][4]);
		arts = new HashMap<>();
		for(Artifact art : artifacts) {
			arts.put(art.name(), new int[8]);				// index is tier (+1)
		}
		grades = new HashMap<>();
		grades.put("runes", new int[8]);		
		grades.put("souls", new int[8]);
		grades.put("relics", new int[8]);
		grades.put("shards", new int[8]);
		memory = new HashMap<>();
		journals = new HashMap<>();
		journals.put("Hunter empty", new int[8]);
		journals.put("Hunter full", new int[8]);
		journals.put("Mage empty", new int[8]);
		journals.put("Mage full", new int[8]);
		journals.put("Warrior empty", new int[8]);
		journals.put("Warrior full", new int[8]);
	}
	
	public int rememberItem(ItemCombined item) {
		return memory.get(item);
	}
	
	public void memorizeItem(ItemCombined item, int price) {
		memory.put(item, price);
	}
	
	public void setJournalPrice(String journal, int tier, int price) {
		journals.get(journal)[tier - 1] = price;
	}
	
	public void setGradePrice(String gradeType, int tier, int price) {
		grades.get(gradeType)[tier - 1] = price;
	}
	
	public void setPrice(String material, int tier, int enchantment, int price) {			//  "tier" can be from 1 to 8, so -1. Enchantment level can be from 0 to 3 so no modifications
		materials.get(material)[tier-1][enchantment] = price;
	}
	
	public void setPrice(Artifact artifact, int tier, int price) {
		System.out.println(artifact.name());
		arts.get(artifact.name())[tier-1] = price;
	}
	
	public int[] getGradePrices(String gradeType) {
		return grades.get(gradeType);
	}

	public int[][] getPrices(String resource) {
		return materials.get(resource);
	}
	
	public int getArtifactPrice(Artifact artifact, int tier) {
		return arts.get(artifact.name())[tier - 1];
	}
	
	public double getCraftFamePrice(String branch, int tier) {
		return journals.get(branch + " full")[tier - 1] - journals.get(branch + " empty")[tier - 1];
	}

	public HashMap<ItemCombined, Integer> getItemsMemory() {
		return memory;
	}
}