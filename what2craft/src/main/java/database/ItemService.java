package database;

import org.springframework.beans.factory.annotation.Autowired;

import logic.Item;

public class ItemService {

	@Autowired
	ItemRepository itemDAO;
	
	public void save(Item item) {
		itemDAO.save(item);
	}
	
}
