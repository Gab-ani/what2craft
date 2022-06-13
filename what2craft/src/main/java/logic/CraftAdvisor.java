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
	
	public void init(int tier, City city, int chant) {
		statService.setTaxes(1400, 700, 900, city.name());
		prices.update(city, "planks", tier, chant);
		prices.update(city, "leather", tier, chant);
		prices.update(city, "cloth", tier, chant);
		prices.update(city, "ingots", tier, chant);
		prices.updateJournalsCost(city, "Hunter", tier);
		prices.updateJournalsCost(city, "Mage", tier);
		prices.updateJournalsCost(city, "Warrior", tier);
	}
	
	public ArrayList<RecommendationForm> adviseFromListUncommon(ArrayList<ItemCombined> pool, int tier, City city) {
		craftSimulator.setCity(city);
		prices.memorize(pool, city.name());
		
		ArrayList<RecommendationForm> recommendations = new ArrayList<>();
		
		pool.forEach(item -> {
			
			RecommendationForm decision = checkSingleUncommon(item, city);
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
		return recommendations;
	}
	
	public ArrayList<RecommendationForm> adviseFromListDisenchanted(ArrayList<ItemCombined> pool, int tier, City city) {
		craftSimulator.setCity(city);
		prices.memorize(pool, city.name());
		
		ArrayList<RecommendationForm> recommendations = new ArrayList<>();
		
		pool.forEach(item -> {
			
			RecommendationForm decision = checkSingleDisenchanted(item, city);
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
		return recommendations;
	}

	private void sumUpRecommendations(ArrayList<RecommendationForm> recommendations) {
		
		HashMap<String, Integer> materialsAmount = new HashMap<>();
		materialsAmount.put("planks", 0);
		materialsAmount.put("leather", 0);
		materialsAmount.put("cloth", 0);
		materialsAmount.put("ingots", 0);
		
		recommendations.forEach(recommendation -> {
			
		});
		
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
		if(profit > 0) {
			double profitability = ((double)profit /(double)priceToCraft);
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
		if(profit > 0) {
			double profitability = ((double)profit /(double)priceToCraft);
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
