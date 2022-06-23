package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	
	private City city;
	
	public void init(int tier, int chant) {
		prices.update(city, "planks", tier, chant);
		prices.update(city, "leather", tier, chant);
		prices.update(city, "cloth", tier, chant);
		prices.update(city, "ingots", tier, chant);
		prices.updateJournalsCost(city, "Hunter", tier);
		prices.updateJournalsCost(city, "Mage", tier);
		prices.updateJournalsCost(city, "Warrior", tier);
	}
	
	public void setCity(String cityName) {
		city = statService.cityByName(cityName);
	}
	
	public void setTaxes(int mage, int hunter, int warrior) {
		setMageTax(mage);
		setHunterTax(hunter);
		setWarriorTax(warrior);
	}
	
	public void setMageTax(int tax) {
		statService.setMageTax(city.name(), tax);
	}
	
	public void setHunterTax(int tax) {
		statService.setHunterTax(city.name(), tax);
	}
	
	public void setWarriorTax(int tax) {
		statService.setWarriorTax(city.name(), tax);
	}
	
	public ArrayList<RecommendationForm> adviseFromList(CraftAdvisorData setup) {
		craftSimulator.setCity(setup.city());
		prices.memorize(setup.items(), setup.city().name());
		
		ArrayList<RecommendationForm> recommendations = new ArrayList<>();
		
		setup.items().forEach(item -> {
			RecommendationForm decision;
			
			if(item.getChant() == 0) {
				decision = checkSingleDisenchanted(item, setup.city());				
			} else {														// TODO 1, 2 and 3 chant else-ifs
				decision = checkSingleUncommon(item, setup.city());				
			}
			
			if(decision.isRecommended()) {
				recommendations.add(decision);
			}
			
			try {									// TODO rework
				Thread.sleep(1000);					// temporal workaround to pass API calls per minute quota
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		
//		sumUpRecommendations(recommendations, setup.blacklist());
		return recommendations;
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
			if(decision.recommendedAmount() > 10) {
				decision.setAmountToCraft(10);
			}
		} else {
			decision.setStatus(false);
		}
		System.out.println("____________________________________________");
		return decision;						// return profits
	}
	
}
