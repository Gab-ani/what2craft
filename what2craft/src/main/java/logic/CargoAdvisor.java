package logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import database.ItemService;
import database.StatService;

public class CargoAdvisor {

	@Autowired
	private ItemService itemService;
	@Autowired
	private StatService statService;
	@Autowired
	private Prices prices;
	
	public ArrayList<ItemCombined> adviseFromList(City from, City to, ArrayList<ItemCombined> items) {
		ArrayList<ItemCombined> whiteList = new ArrayList<>();
		
		items.forEach(item -> {
			
			if(prices.priceForItem(item, to) - prices.priceForItem(item, from) * (1 - StatService.sellOrderTax) > 0) {
				
			}
			
		});
		
		return null;
	}
	
}
