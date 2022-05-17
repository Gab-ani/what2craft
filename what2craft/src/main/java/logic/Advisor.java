package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.ItemService;

@Service
public class Advisor {

	@Autowired
	ItemService itemService;
	
	
	public int byStraightCraft() {
		
	}
	
	public int byEnchanting() {
		
	}
	
	public int craftPrice(Item item) {
		
	}
	
	public int journalsValue(Item item) {
		
	}
	
	public int craftTaxes(Item item) {
		
	}
	
	public int marketTaxes(Item item) {
		
	}
	
}
