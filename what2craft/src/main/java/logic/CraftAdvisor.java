package logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.ItemService;

@Service
public class CraftAdvisor {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private CraftSimulator craftSimulator;
	@Autowired
	private Prices prices;
	
	public ArrayList<ItemCombined> adviseFromTags(ArrayList<String> tags, int tier, int enchantmentLevel, String inCity) {
		
		ArrayList<ItemCombined> toCraft = new ArrayList<>();
		ArrayList<ItemCombined> possibleCrafts = itemService.findByTags(tags, tier, enchantmentLevel);
		prices.memorize(possibleCrafts, inCity);
		
//		possibleCrafts.forEach(item -> {
//			int priceToCraft = craftSimulator.calculateOptimalCraftPath(item, inCity).getPrice();
//			
//		});
		
		
		return toCraft;
	}
	
}
