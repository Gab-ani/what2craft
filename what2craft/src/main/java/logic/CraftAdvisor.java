package logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void init(int tier, City city) {
		statService.setTaxes(400, 450, 400, "lymhurst");
		prices.update(city, "planks", tier, 0);
		prices.update(city, "leather", tier, 0);
		prices.update(city, "cloth", tier, 0);
		prices.update(city, "ingots", tier, 0);
		prices.updateJournalsCost(city, "Hunter", tier);
		prices.updateJournalsCost(city, "Mage", tier);
		prices.updateJournalsCost(city, "Warrior", tier);
	}
	
	public ArrayList<ItemCombined> adviseFromList(ArrayList<ItemCombined> pool, int tier, City city) {
		craftSimulator.setCity(city);
		init(tier, city);
		prices.memorize(pool, city.name());
		
		pool.forEach(item -> {
			checkSingle(item, city);
		});
		
		System.out.println("fin");
		return pool;
	}
	
	public ArrayList<ItemCombined> adviseFromArtifacts(int tier, int enchantmentLevel, City city) {
		
		craftSimulator.setCity(city);
		init(tier, city);

		ArrayList<ItemCombined> possibleCrafts = itemService.findArtifactItems(tier, enchantmentLevel);
		prices.memorize(possibleCrafts, city.name());
		
		possibleCrafts.forEach(item -> {
			checkSingle(item, city);
		});
		
		System.out.println("fin");
		return possibleCrafts;
	}
	
	private int checkSingle(ItemCombined item, City city) {
		prices.update(city, itemService.artifactByName(item.getBase().getArtifact().name()), 5);
		
		System.out.println("Чекаю " + item.getName());
		int priceToCraft = craftSimulator.disenchantedItemCraftCost(item.getBase(), item.getTier());
		System.out.println("крафт: " + priceToCraft + " стоимость: " + (int)(prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)));
		if(priceToCraft < prices.priceForItem(item, city) * (1 - StatService.sellOrderTax)) {
			System.out.println("хочу скрафтить " + item.getName());
		}
		
		return prices.priceForItem(item, city) - priceToCraft;
	}
	
}
