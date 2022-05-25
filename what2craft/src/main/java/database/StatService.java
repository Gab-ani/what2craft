package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logic.City;
import logic.ItemCombined;

@Service
public class StatService {
	
	public static final int[] journalsFame = new int[] {0, 900, 1800, 3600, 7200, 14400, 28380, 58590};
	
	@Autowired
	CityRepository cityDAO;
	
	public void setTaxes(int mage, int hunter, int warrior, String cityName) {
		setMageTax(cityName, mage);
		setHunterTax(cityName, hunter);
		setWarriorTax(cityName, warrior);
	}
	
	public void setMageTax(String in, int tax) {
		cityDAO.setMageTaxIn(in, tax);
	}
	
	public void setHunterTax(String in, int tax) {
		cityDAO.setHunterTaxIn(in, tax);
	}
	
	public void setWarriorTax(String in, int tax) {
		cityDAO.setWarriorTaxIn(in, tax);
	}
	
	public City cityByName(String name) {
		return cityDAO.findByName(name);
	}
	
	public void saveCity(City city) {
		cityDAO.save(city);
	}
	
	public void setUpCitiesDatabase() {				// call once and forget - not deleting for possible forks
		saveCity(new City("lymhurst", new String[] {"Swords", "Bows", "Arcane", "Hoods", "Shoes"}));
		saveCity(new City("sterling", new String[] {"Hammers", "Spears", "Holy", "Helmets", "Robes"}));
		saveCity(new City("thedford", new String[] {"Maces", "Nature", "Fire", "Jackets", "Cowls"}));
		saveCity(new City("martlock", new String[] {"Axes", "Quarterstaffs", "Frost", "Boots", "Shields", "Talismans", "Torches"}));
		saveCity(new City("bridgewatch", new String[] {"Crossbows", "Daggers", "Cursed", "Armor", "Sandals"}));
		saveCity(new City("caerleon", new String[] {"Knuckles"}));
	}
	
}
