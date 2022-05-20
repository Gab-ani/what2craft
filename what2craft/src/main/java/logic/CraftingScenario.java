package logic;

public class CraftingScenario {

	private ItemCombined result;
	private int cost;
	private int straightCraftLevel;
	
	
	
	public CraftingScenario() {
		
	}
	
	@Override
	public String toString() {
		return "craft from T" + straightCraftLevel + " then enchant further";
	}
		
	public int straightCraftLevel() {
		return straightCraftLevel;
	}
	
	public int getCost() {
		return cost;
	}
	
	public ItemCombined getResult() {
		return result;
	}
	
}
