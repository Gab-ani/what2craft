package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logic.Item;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemDAO;
	
	public void save(Item item) {
		itemDAO.save(item);
	}
	
	public ArrayList<String> getRawAmmunition() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("src/main/resources/raw data/ammunition.txt"));
		ArrayList<String> lines = new ArrayList<String>();
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			lines.add(line);
		}
		scan.close();
		return lines;
	}
	
	public void setupAmmunitionBase() {
		try {
			getRawAmmunition().forEach((string) -> save(Item.parseAmmunition(string)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
