package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import database.StatService;
import logic.CraftAdvisor;
import logic.CraftAdvisorData;
import logic.RecommendationForm;

@ShellComponent
public class CLInterface {
	
	@Autowired
	StatService statServise;
	@Autowired
	CraftAdvisor craftAdvisor;
	
	CraftAdvisorData setup;
	
	@ShellMethod(key = {"craft", "what2craft", "w2c"}, value = "make a craft advise")
	public void adviseFromTags(@ShellOption(value = "tags", arity = 5) String[] tags) {
		
		
		
	}
	
	@ShellMethod(key = {"scity", "sc", "setcity"}, value = "set city to lookup")
	public void setCity(@ShellOption(value = "city") String cityName) {
		setup = setup.inCity(statServise.cityByName(cityName));
	}
	
	@ShellMethod(key = {"prepare", "prep", "tier"}, value = "lookup tier and enchantment level for materials and journals")
	public void prepareTier(@ShellOption(value = "tier") String tier) {
		int[] tierDecoded = decodeTier(tier);
		craftAdvisor.init(tierDecoded[0], tierDecoded[1]);
	}
	
	@ShellMethod(key = {"settax", "taxes"}, value = "set all taxes as one number")
	public void setTaxes(@ShellOption(value = "all taxes number") int tax) {
		craftAdvisor.setMageTax(tax);
		craftAdvisor.setHunterTax(tax);
		craftAdvisor.setWarriorTax(tax);
	}
	
	@ShellMethod(key = {"setmage", "mage", "mtax"}, value = "set mages tower tax")
	public void setMageTax(@ShellOption(value = "tax") int tax) {
		craftAdvisor.setMageTax(tax);
	}
	@ShellMethod(key = {"sethunter", "hunter", "htax"}, value = "set hunters dome tax")
	public void setHunterTax(@ShellOption(value = "tax") int tax) {
		craftAdvisor.setHunterTax(tax);
	}
	@ShellMethod(key = {"setwarrior", "warrior", "wtax"}, value = "set warrior tax")
	public void setWarriorTax(@ShellOption(value = "tax") int tax) {
		craftAdvisor.setWarriorTax(tax);
	}
	
private void sumUpRecommendations(ArrayList<RecommendationForm> recommendations, ArrayList<String> blacklist) {
		
		HashMap<String, Integer> materialsAmount = new HashMap<>();
		materialsAmount.put("planks", 0);
		materialsAmount.put("leather", 0);
		materialsAmount.put("cloth", 0);
		materialsAmount.put("ingots", 0);
		
		if(blacklist != null)
			clear(recommendations, blacklist);
		
		recommendations.forEach(recommendation -> {
			
			System.out.println("__________________________");
			String[] recipe = recommendation.item().getRecipe();
			
			// recipe always has even number of strings and structured like { |amount| , |name|, |amount|, |name|... }
			// for example { "20", "planks", "12", "cloth" }	
			// so this cycle adds all materials coded in recipe multiplied by amount of items to craft to materialsAmount map to visualize later.
			for(int i = 0; i < recipe.length; i +=2) {																															
				materialsAmount.put (  recipe[i + 1],    materialsAmount.get(recipe[i + 1]) + Integer.parseInt(recipe[i]) * recommendation.recommendedAmount()  );	
			}

			if(recommendation.item().containsArtifact()) {
				System.out.println(recommendation.item().getBase().getArtifact().name());
			}
			System.out.println(recommendation.item().getName());
			System.out.println("прибыль: " + recommendation.profit() + ", рентабельность: " + recommendation.profitability());
			System.out.println(recommendation.recommendedAmount() + " предметов");
			
		});
		
		System.out.println("__________________________");
		
		
		System.out.println(materialsAmount.get("planks") + " planks");
		System.out.println(materialsAmount.get("leather") + " leather");
		System.out.println(materialsAmount.get("cloth") + " cloth");
		System.out.println(materialsAmount.get("ingots") + " ingots");
		
	}

	private void clear(ArrayList<RecommendationForm> recommendations, ArrayList<String> blacklist) {
		
		Iterator<RecommendationForm> iterator = recommendations.iterator();
		while(iterator.hasNext()) {
			RecommendationForm recommendation = iterator.next();
			for(String substring : blacklist) {
				if(recommendation.item().getName().contains(substring)) {
					System.out.println(recommendation.item().getName() + " содержит " + substring);
					iterator.remove();
				}
			}
		}
	}
	
	// in the game people always reference tier and chant level as two numbers splitted by '.', ie 5.1, 6.2, 7.0 etc
	private int[] decodeTier(String tier) {
		int[] tierDecoded = new int[2];
		String[] splittedTier = tier.split(".");
		tierDecoded[0] = Integer.parseInt(splittedTier[0]);
		tierDecoded[1] = Integer.parseInt(splittedTier[1]);
		return tierDecoded;
	}
	
}
