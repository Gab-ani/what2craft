package logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CraftSimulator {

	
	
	public CraftSimulator() {
		
	}
	
	public CraftingScenario calculateOptimalPath(ItemCombined forItem) {
		
		ArrayList<CraftingScenario> scenarios = allPathsTo(forItem);
		
		return null;
	}

	private ArrayList<CraftingScenario> allPathsTo(ItemCombined toItem) {
		
		
		
		return null;
	}
	
//	private int calculateItemValue(ItemCombined forItem) {										// IV is ingame term used in most formulas
//		
//	}
	
	private void calculateCost(CraftingScenario scenario) {
		
		int straightCraftCost = calculateStraightCraft(scenario);
		
		int enchantmentCost = calculateEnchantment(scenario);
		
	}

	private int calculateStraightCraft(CraftingScenario scenario) {
		
		
		
		return 0;
	}

	private int calculateEnchantment(CraftingScenario scenario) {
		return 0;
	}
	
}
