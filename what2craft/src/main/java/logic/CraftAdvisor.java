package logic;

import java.util.ArrayList;
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
		statService.setTaxes(400, 450, 400, "lymhurst");
		prices.update(city, "planks", tier, chant);
		prices.update(city, "leather", tier, chant);
		prices.update(city, "cloth", tier, chant);
		prices.update(city, "ingots", tier, chant);
		prices.updateJournalsCost(city, "Hunter", tier);
		prices.updateJournalsCost(city, "Mage", tier);
		prices.updateJournalsCost(city, "Warrior", tier);
	}
	
	public ArrayList<ItemCombined> adviseFromListUncommon(ArrayList<ItemCombined> pool, int tier, City city) {
		craftSimulator.setCity(city);
		init(tier, city, 1);
		prices.memorize(pool, city.name());
		
		pool.forEach(item -> {
			checkSingleUncommon(item, city);
			
			try {									// TODO rework
				Thread.sleep(1000);					// temporal workaround to pass API calls per minute quota
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		
		System.out.println("fin");
		return pool;
	}
	
	public ArrayList<ItemCombined> adviseFromListDisenchanted(ArrayList<ItemCombined> pool, int tier, City city) {
		craftSimulator.setCity(city);
		init(tier, city, 0);
		prices.memorize(pool, city.name());
		
		pool.forEach(item -> {
			checkSingleDisenchanted(item, city);
			
			try {									// TODO rework
				Thread.sleep(1000);					// temporal workaround to pass API calls per minute quota
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		});
		
		System.out.println("fin");
		return pool;
	}
	
//	public ArrayList<ItemCombined> adviseFromArtifacts(int tier, int enchantmentLevel, City city) {
//		
//		craftSimulator.setCity(city);
//		init(tier, city,);
//
//		ArrayList<ItemCombined> possibleCrafts = itemService.findArtifactItems(tier, enchantmentLevel);
//		prices.memorize(possibleCrafts, city.name());
//		
//		possibleCrafts.forEach(item -> {
//			checkSingleDisenchanted(item, city);			
//		});
//		
//		System.out.println("fin");
//		return possibleCrafts;
//	}
	
	private int checkSingleUncommon(ItemCombined item, City city) {
		System.out.println("Чекаю " + item.getName());
		System.out.println(item.getBase().getArtifact().name());
		prices.update(city, itemService.artifactByName(item.getBase().getArtifact().name()), 5);			// request artifact price from albion db
		
		int priceToCraft = craftSimulator.uncommonItemCraftCost(item.getBase(), item.getTier());
		System.out.println("крафт: " + priceToCraft + " стоимость: " + (int)(prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)));
		if(priceToCraft < prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)) {
			System.out.println("хочу скрафтить " + item.getName());
		}
		System.out.println("____________________________________________");
		return prices.priceForItem(item, city) - priceToCraft;
	}
	
	private int checkSingleDisenchanted(ItemCombined item, City city) {
		System.out.println("Чекаю " + item.getName());
		System.out.println(item.getBase().getArtifact().name());
		prices.update(city, itemService.artifactByName(item.getBase().getArtifact().name()), 5);			// request artifact price from albion db
		
		int priceToCraft = craftSimulator.disenchantedItemCraftCost(item.getBase(), item.getTier());		// count craft price
		System.out.println("крафт: " + priceToCraft + " стоимость: " + (int)(prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)));
		if(priceToCraft < prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)) {				// check if craft price < market price & taxes
			System.out.println("хочу скрафтить " + item.getName());
		}
		System.out.println("____________________________________________");
		return prices.priceForItem(item, city) - priceToCraft;												// return profits
	}
	
}
