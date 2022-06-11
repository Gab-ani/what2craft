package logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.ItemService;
import database.StatService;

@Service
public class CraftSimulator {

	private static final double NUTRITION_COST = 0.001125055;
	
	@Autowired
	private Prices prices;
	@Autowired
	private StatService gameConstants;
	@Autowired
	private ItemService itemService;
	
	private City city;
	
	public int disenchantedItemCraftCost(ItemBasic goalBase, int tier) {
		
		ItemCombined goal = ItemCombined.forBase(goalBase).forTier(tier).withEnchantmentLevelOf(0).ofQuality(1);
		
		int craftCost = 0;
		String[] recipe =	goal.getRecipe();
		
		for(int i = 0; i < recipe.length; i += 2) {								// sum up cost of materials
			craftCost += prices.priceForMaterial(recipe[ i + 1 ], goal.getTier(), 0, city) * Integer.parseInt(recipe[i]);
		}
//		System.out.println("стоимость ресурсов: " + craftCost);
		
		String bonusNodes = "";												
		for(String node : gameConstants.cityByName(city.name()).getBonusNodes()) {	
			bonusNodes += node;
		}
		if(bonusNodes.contains(goal.craftNode())) {				// see if current city is "good" for this particular crafting recipe			
			craftCost /= city.bonusReturn();
		} else {
			craftCost /= City.defaultReturns();
		}
		
		if(goal.getBase().requiresArtifact()) {										// if item contains artifact - add artifact cost into raw cost
			craftCost += prices.priceForArtifact(goal.getBase().getArtifact(), city.name(), tier); 
		}
		
		
		craftCost += craftTaxes(goal);
		
		craftCost -= fameCost(goal);
		
		return craftCost;		
	}
	
	private int fameCost(ItemCombined goal) {
		int fameCreated = goal.fameForCrafting();
//		System.out.println("славы: " + fameCreated);
		System.out.println("денег за славу: " + (int) (fameCreated * prices.priceForFamePoint(goal.craftBranch(), goal.getTier(), city)));
		return (int) (fameCreated * prices.priceForFamePoint(goal.craftBranch(), goal.getTier(), city));
	}
	
	private int craftTaxes(ItemCombined item) {
		double tax = 0;
		if(item.getBase().getCraftBranch().equals("Mage")) {
			tax = city.getMageTax();
		}
		if(item.getBase().getCraftBranch().equals("Hunter")) {
			tax = city.getHunterTax();			
		}
		if(item.getBase().getCraftBranch().equals("Warrior")) {
			tax = city.getWarriorTax();
		}
		tax *= item.getItemValue() * NUTRITION_COST;
		return (int)tax;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
	
//	public CraftingScenario calculateOptimalPath(ItemCombined forItem) {
//		
//		ArrayList<CraftingScenario> scenarios = allPathsTo(forItem);
//		
//		return null;
//	}

//	private ArrayList<CraftingScenario> allPathsTo(ItemCombined toItem) {
//		
//		
//		
//		return null;
//	}
	
//	private int calculateCost(CraftingScenario scenario) {
//		int scenarioCost = 0;
//		scenarioCost += calculateStraightCraft(scenario);
//		scenarioCost += calculateEnchantmentCost(scenario);
//		return scenarioCost;
//	}

//	private int calculateStraightCraft(CraftingScenario scenario) {				// !aware! heavy brain damage, all below is difficult ingame business logic
//		
//		int craftCost = 0;
//		String[] recipe =	scenario.getResult().getRecipe();
//		
//		for(int i = 0; i < recipe.length; i += 2) {								// sum up cost of materials
//			craftCost += prices.priceForMaterial(recipe[ i + 1 ], scenario.getResult().getTier(), scenario.straightCraftLevel(), city.name());
//		}
//		System.out.println(craftCost);
//		
//		String bonusNodes = "";												
//		for(String node : gameConstants.cityByName(city.name()).getBonusNodes()) {	
//			bonusNodes += node;
//		}
//		if(bonusNodes.contains(scenario.getResult().craftNode())) {				// see if current city is "good" for this particular crafting recipe			
//			craftCost /= city.bonusReturn();
//		} else {
//			craftCost /= City.defaultReturns();
//		}
//		
//		if(scenario.requiresArtifact()) {										// if item contains artifact - add artifact cost into raw cost
//			craftCost += prices.priceForArtifact(scenario.getResult().getBase().getArtifact(), city.name(), scenario.straightCraftLevel()); 
//		}
//		
//		craftCost -= craftTaxes(scenario.getStraighCrafttStub());
//		
//		return craftCost;
//	}

//	private int calculateEnchantmentCost(CraftingScenario scenario) {
//		int chantCost = 0;
//		if(scenario.getResult().getTier() == scenario.straightCraftLevel())
//			return 0;
//		if(scenario.straightCraftLevel() == 0) {
//			chantCost += prices.priceForGrades("runes", city.name(), scenario.getResult().getTier());
//			if(scenario.getResult().getTier() == scenario.straightCraftLevel() + 1)
//				return chantCost;
//		}
//		
//	}
	

	
}
