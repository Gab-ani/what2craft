package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import albionDataCommunication.AuctionHousePunching;
import database.ItemService;
import database.StatService;

@Service
public class CraftAdvisor {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private StatService statService;
	@Autowired
	private CraftSimulator craftSimulator;
	@Autowired
	private Prices prices;
	
	public void init(int tier, int chant, City city) {
		statService.setTaxes(2350, 2350, 2350, city.name());
		prices.update(city, "planks", tier, chant);
		prices.update(city, "leather", tier, chant);
		prices.update(city, "cloth", tier, chant);
		prices.update(city, "ingots", tier, chant);
		prices.updateJournalsCost(city, "Hunter", tier);
		prices.updateJournalsCost(city, "Mage", tier);
		prices.updateJournalsCost(city, "Warrior", tier);
	}
	
	public ArrayList<RecommendationForm> adviseFromListUncommon(CraftAdvisorData setup) {
		craftSimulator.setCity(setup.city());
		prices.memorize(setup.items(), setup.city().name());
		
		ArrayList<RecommendationForm> recommendations = new ArrayList<>();
		
		setup.items().forEach(item -> {
			
			RecommendationForm decision = checkSingleUncommon(item, setup.city());
			if(decision.isRecommended()) {
				recommendations.add(decision);
			}
			
			
			try {									// TODO rework
				Thread.sleep(1000);					// temporal workaround to pass API calls per minute quota
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		});
		
		System.out.println("fin");
		sumUpRecommendations(recommendations, setup.blacklist());
		return recommendations;
	}
	
//	public ArrayList<RecommendationForm> adviseFromListDisenchanted(ArrayList<ItemCombined> pool, City city) {
	public ArrayList<RecommendationForm> adviseFromListDisenchanted(CraftAdvisorData setup) {
		craftSimulator.setCity(setup.city());
		prices.memorize(setup.items(), setup.city().name());
		
		ArrayList<RecommendationForm> recommendations = new ArrayList<>();
		
		setup.items().forEach(item -> {
			
			RecommendationForm decision = checkSingleDisenchanted(item, setup.city());
			if(decision.isRecommended()) {
				recommendations.add(decision);
			}
			
			try {									// TODO rework
				Thread.sleep(1000);					// temporal workaround to pass API calls per minute quota
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		
		System.out.println("fin");
		sumUpRecommendations(recommendations, setup.blacklist());
		return recommendations;
	}

	private void sumUpRecommendations(ArrayList<RecommendationForm> recommendations, ArrayList<String> blacklist) {
		
		HashMap<String, Integer> materialsAmount = new HashMap<>();
		materialsAmount.put("planks", 0);
		materialsAmount.put("leather", 0);
		materialsAmount.put("cloth", 0);
		materialsAmount.put("ingots", 0);
		
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
		for(RecommendationForm recommendation : recommendations) {
			blacklist.forEach(substring -> {
				if(recommendation.item().getName().contains(substring)) {
					recommendations.remove(recommendation);
				}
			});
		}
	}

	private RecommendationForm checkSingleUncommon(ItemCombined item, City city) {
		RecommendationForm decision = new RecommendationForm(item);
		
		System.out.println("Чекаю " + item.getName());
		if(item.getBase().requiresArtifact()) {
			System.out.println(item.getBase().getArtifact().name());
			prices.update(city, itemService.artifactByName(item.getBase().getArtifact().name()), item.getTier());			// request artifact price from albion db
		}

		int priceToCraft = craftSimulator.uncommonItemCraftCost(item.getBase(), item.getTier());
		System.out.println("крафт: " + priceToCraft + " стоимость: " + (int)(prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)));
		
		int sellPrice = (int) (prices.priceForItem(item, city) * (1 - StatService.sellOrderTax));
		int profit = sellPrice - priceToCraft;
		double profitability = ((double)profit /(double)priceToCraft);
		if(profit > 0 && profitability > 0.15) {
			System.out.println("хочу скрафтить " + item.getName() + " выгода: " + profit + " рентабельность: " + profitability);
			decision.setStatus(true);
			decision.setProfitFactors(profit, profitability);
			decision.setAmountToCraft( ( 50000/profit ) + 1 );
		} else {
			decision.setStatus(false);
		}
		System.out.println("____________________________________________");
		
		return decision;
	}
	
	private RecommendationForm checkSingleDisenchanted(ItemCombined item, City city) {
		RecommendationForm decision = new RecommendationForm(item);
		
		System.out.println("Проверяю " + item.getName());
		if(item.getBase().requiresArtifact()) {
			System.out.println("Его артефакт - " + item.getBase().getArtifact().name());
			prices.update(city, itemService.artifactByName(item.getBase().getArtifact().name()), item.getTier());			// request artifact price from albion db			
		}
		
		int priceToCraft = craftSimulator.disenchantedItemCraftCost(item.getBase(), item.getTier());
		System.out.println("крафт: " + priceToCraft + " стоимость: " + prices.priceForItem(item, city));
		
		int sellPrice = (int) (prices.priceForItem(item, city) * (1 - StatService.sellOrderTax));
		int profit = sellPrice - priceToCraft;
		double profitability = ((double)profit /(double)priceToCraft);
		if(profit > 0 && profitability > 0.15) {
			System.out.println("хочу скрафтить " + item.getName() + " выгода: " + profit + " рентабельность: " + profitability);
			decision.setStatus(true);
			decision.setProfitFactors(profit, profitability);
			decision.setAmountToCraft( ( 50000/profit ) + 1 );
		} else {
			decision.setStatus(false);
		}
		System.out.println("____________________________________________");
		return decision;						// return profits
	}
	
}
