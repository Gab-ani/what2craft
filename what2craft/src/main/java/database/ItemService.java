package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logic.ItemBasic;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemDAO;
	
	public void save(ItemBasic item) {
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
	
//	public void setupAmmunitionBase() {
//		try {
//			getRawAmmunition().forEach((string) -> save(ItemBasic.parseAmmunition(string)));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	public ItemBasic getByName(String name) {
		return itemDAO.findByHumanName(name);
	}

	public void setUpDatabase() {					// call once and forget, kind of hardcoding - not deleting for possible forks				
		
		save(new ItemBasic("Crossbow", "2H_CROSSBOW", "Crossbows", "Warrior"));
		save(new ItemBasic("Heavy Crossbow", "2H_CROSSBOWLARGE", "Crossbows", "Warrior"));
		save(new ItemBasic("Light Crossbow", "MAIN_1HCROSSBOW", "Crossbows", "Warrior"));
		save(new ItemBasic("Weeping Repeater", "2H_REPEATINGCROSSBOW_UNDEAD", "Crossbows", "Warrior"));		// crossbows
		save(new ItemBasic("Boltcasters", "2H_DUALCROSSBOW_HELL", "Crossbows", "Warrior"));
		save(new ItemBasic("Siegebow", "2H_CROSSBOWLARGE_MORGANA", "Crossbows", "Warrior"));
		save(new ItemBasic("Energy Shaper", "2H_CROSSBOW_CANNON_AVALON", "Crossbows", "Warrior"));
		
		save(new ItemBasic("Broadsword", "MAIN_SWORD", "Swords", "Warrior"));
		save(new ItemBasic("Claymore", "2H_CLAYMORE", "Swords", "Warrior"));
		save(new ItemBasic("Clarent Blade", "MAIN_SCIMITAR_MORGANA", "Swords", "Warrior"));
		save(new ItemBasic("Carving Sword", "2H_CLEAVER_HELL", "Swords", "Warrior"));		// swords
		save(new ItemBasic("Dual Swords", "2H_DUALSWORD", "Swords", "Warrior"));
		save(new ItemBasic("Kingmaker", "2H_CLAYMORE_AVALON", "Swords", "Warrior"));
		save(new ItemBasic("Galatine Pair", "2H_DUALSCIMITAR_UNDEAD", "Swords", "Warrior"));
		
		save(new ItemBasic("Mace", "MAIN_MACE", "Maces", "Warrior"));
		save(new ItemBasic("Heavy Mace", "2H_MACE", "Maces", "Warrior"));
		save(new ItemBasic("Morning Star", "2H_FLAIL", "Maces", "Warrior"));
		save(new ItemBasic("Incubus Mace", "MAIN_MACE_HELL", "Maces", "Warrior"));		// maces
		save(new ItemBasic("Camlann Mace", "2H_MACE_MORGANA", "Maces", "Warrior"));
		save(new ItemBasic("Bedrock Mace", "MAIN_ROCKMACE_KEEPER", "Maces", "Warrior"));
		save(new ItemBasic("Oathkeepers", "2H_DUALMACE_AVALON", "Maces", "Warrior"));
		
		save(new ItemBasic("Hammer", "MAIN_HAMMER", "Hammers", "Warrior"));
		save(new ItemBasic("Polehammer", "2H_POLEHAMMER", "Hammers", "Warrior"));
		save(new ItemBasic("Tombhammer", "2H_HAMMER_UNDEAD", "Hammers", "Warrior"));
		save(new ItemBasic("Forge Hammers", "2H_DUALHAMMER_HELL", "Hammers", "Warrior"));		// hammers
		save(new ItemBasic("Grovekeeper", "2H_RAM_KEEPER", "Hammers", "Warrior"));
		save(new ItemBasic("Great Hammer", "2H_HAMMER", "Hammers", "Warrior"));
		save(new ItemBasic("Hand of Justice", "2H_HAMMER_AVALON", "Hammers", "Warrior"));
		
		save(new ItemBasic("Battleaxe", "MAIN_AXE", "Axes", "Warrior"));
		save(new ItemBasic("Carrioncaller", "2H_HALBERD_MORGANA", "Axes", "Warrior"));
		save(new ItemBasic("Infernal Scythe", "2H_SCYTHE_HELL", "Axes", "Warrior"));
		save(new ItemBasic("Greataxe", "2H_AXE", "Axes", "Warrior"));							// axes
		save(new ItemBasic("Halberd", "2H_HALBERD", "Axes", "Warrior"));
		save(new ItemBasic("Realmbreaker", "2H_AXE_AVALON", "Axes", "Warrior"));
		save(new ItemBasic("Bear Paws", "2H_DUALAXE_KEEPER", "Axes", "Warrior"));
		
		save(new ItemBasic("Brawler Gloves", "2H_KNUCKLES_SET1", "Knuckles", "Warrior"));
		save(new ItemBasic("Spiked Gauntlets", "2H_KNUCKLES_SET3", "Knuckles", "Warrior"));
		save(new ItemBasic("Ursine Maulers", "2H_KNUCKLES_KEEPER", "Knuckles", "Warrior"));
		save(new ItemBasic("Hellfire Hands", "2H_KNUCKLES_HELL", "Knuckles", "Warrior"));		// knuckles
		save(new ItemBasic("Battle Bracers", "2H_KNUCKLES_SET2", "Knuckles", "Warrior"));
		save(new ItemBasic("Ravenstrike Cestus", "2H_KNUCKLES_MORGANA", "Knuckles", "Warrior"));
		save(new ItemBasic("Fists of Avalon", "2H_KNUCKLES_AVALON", "Knuckles", "Warrior"));
		
		save(new ItemBasic("Shield", "OFF_SHIELD", "Shields", "Warrior"));
		save(new ItemBasic("Sarcophagus", "OFF_TOWERSHIELD_UNDEAD", "Shields", "Warrior"));
		save(new ItemBasic("Caitiff Shield", "OFF_SHIELD_HELL", "Shields", "Warrior"));
		save(new ItemBasic("Facebreaker", "OFF_SPIKEDSHIELD_MORGANA", "Shields", "Warrior"));							// shields
		save(new ItemBasic("Astral Aegis", "OFF_SHIELD_AVALON", "Shields", "Warrior"));
		
		save(new ItemBasic("Soldier Helmet", "HEAD_PLATE_SET1", "Helmets", "Warrior"));
		save(new ItemBasic("Knight Helmet", "HEAD_PLATE_SET2", "Helmets", "Warrior"));
		save(new ItemBasic("Guardian Helmet", "HEAD_PLATE_SET3", "Helmets", "Warrior"));
		save(new ItemBasic("Graveguard Helmet", "HEAD_PLATE_UNDEAD", "Helmets", "Warrior"));		// helmets
		save(new ItemBasic("Demon Helmet", "HEAD_PLATE_HELL", "Helmets", "Warrior"));
		save(new ItemBasic("Judicator Helmet", "HEAD_PLATE_KEEPER", "Helmets", "Warrior"));
		save(new ItemBasic("Helmet of Valor", "HEAD_PLATE_AVALON", "Helmets", "Warrior"));
		save(new ItemBasic("Royal Helmet", "HEAD_PLATE_ROYAL", "Helmets", "Warrior"));
		
		save(new ItemBasic("Soldier Armor", "ARMOR_PLATE_SET1", "Armor", "Warrior"));
		save(new ItemBasic("Knight Armor", "ARMOR_PLATE_SET2", "Armor", "Warrior"));
		save(new ItemBasic("Guardian Armor", "ARMOR_PLATE_SET3", "Armor", "Warrior"));
		save(new ItemBasic("Graveguard Armor", "ARMOR_PLATE_UNDEAD", "Armor", "Warrior"));		// armor
		save(new ItemBasic("Demon Armor", "ARMOR_PLATE_HELL", "Armor", "Warrior"));
		save(new ItemBasic("Judicator Armor", "ARMOR_PLATE_KEEPER", "Armor", "Warrior"));
		save(new ItemBasic("Armor of Valor", "ARMOR_PLATE_AVALON", "Armor", "Warrior"));
		save(new ItemBasic("Royal Armor", "ARMOR_PLATE_ROYAL", "Armor", "Warrior"));
		
		save(new ItemBasic("Soldier Boots", "SHOES_PLATE_SET1", "Boots", "Warrior"));
		save(new ItemBasic("Knight Boots", "SHOES_PLATE_SET2", "Boots", "Warrior"));
		save(new ItemBasic("Guardian Boots", "SHOES_PLATE_SET3", "Boots", "Warrior"));
		save(new ItemBasic("Graveguard Boots", "SHOES_PLATE_UNDEAD", "Boots", "Warrior"));		// boots
		save(new ItemBasic("Demon Boots", "SHOES_PLATE_HELL", "Boots", "Warrior"));
		save(new ItemBasic("Judicator Boots", "SHOES_PLATE_KEEPER", "Boots", "Warrior"));
		save(new ItemBasic("Boots of Valor", "SHOES_PLATE_AVALON", "Boots", "Warrior"));
		save(new ItemBasic("Royal Boots", "SHOES_PLATE_ROYAL", "Boots", "Warrior"));
		

		
		save(new ItemBasic("Dagger", "MAIN_DAGGER", "Daggers", "Hunter"));
		save(new ItemBasic("Claws", "2H_CLAWPAIR", "Daggers", "Hunter"));
		save(new ItemBasic("Deathgivers", "2H_DUALSICKLE_UNDEAD", "Daggers", "Hunter"));
		save(new ItemBasic("Bridled Fury", "2H_DAGGER_KATAR_AVALON", "Daggers", "Hunter"));	//  daggers 
		save(new ItemBasic("Demonfang", "MAIN_DAGGER_HELL", "Daggers", "Hunter"));
		save(new ItemBasic("Dagger Pair", "2H_DAGGERPAIR", "Daggers", "Hunter"));
		save(new ItemBasic("Bloodletter", "MAIN_RAPIER_MORGANA", "Daggers", "Hunter"));
		
		save(new ItemBasic("Nature Staff", "MAIN_NATURESTAFF", "Nature", "Hunter"));
		save(new ItemBasic("Great Nature Staff", "2H_NATURESTAFF", "Nature", "Hunter"));
		save(new ItemBasic("Druidic Staff", "MAIN_NATURESTAFF_KEEPER", "Nature", "Hunter"));
		save(new ItemBasic("Rampant Staff", "2H_NATURESTAFF_KEEPER", "Nature", "Hunter"));			// nature
		save(new ItemBasic("Wild Staff", "2H_WILDSTAFF", "Nature", "Hunter"));
		save(new ItemBasic("Ironroot Staff", "MAIN_NATURESTAFF_AVALON", "Nature", "Hunter"));
		save(new ItemBasic("Blight Staff", "2H_NATURESTAFF_HELL", "Nature", "Hunter"));
		
		save(new ItemBasic("Quarterstaff", "2H_QUARTERSTAFF", "Quarterstaffs", "Hunter"));
		save(new ItemBasic("Soulscythe", "2H_TWINSCYTHE_HELL", "Quarterstaffs", "Hunter"));
		save(new ItemBasic("Black Monk Stave", "2H_COMBATSTAFF_MORGANA", "Quarterstaffs", "Hunter"));
		save(new ItemBasic("Grailseeker", "2H_QUARTERSTAFF_AVALON", "Quarterstaffs", "Hunter"));		// Quarterstaffs
		save(new ItemBasic("Double Bladed Staff", "2H_DOUBLEBLADEDSTAFF", "Quarterstaffs", "Hunter"));
		save(new ItemBasic("Staff of Balance", "2H_ROCKSTAFF_KEEPER", "Quarterstaffs", "Hunter"));
		save(new ItemBasic("Iron-clad Staff", "2H_IRONCLADEDSTAFF", "Quarterstaffs", "Hunter"));
		
		save(new ItemBasic("Glaive", "2H_GLAIVE", "Spears", "Hunter"));
		save(new ItemBasic("Spear", "MAIN_SPEAR", "Spears", "Hunter"));
		save(new ItemBasic("Spirithunter", "2H_HARPOON_HELL", "Spears", "Hunter"));
		save(new ItemBasic("Trinity Spear", "2H_TRIDENT_UNDEAD", "Spears", "Hunter"));			//  Spears
		save(new ItemBasic("Daybreaker", "MAIN_SPEAR_LANCE_AVALON", "Spears", "Hunter"));
		save(new ItemBasic("Pike", "2H_SPEAR", "Spears", "Hunter"));
		save(new ItemBasic("Heron Spear", "MAIN_SPEAR_KEEPER", "Spears", "Hunter"));
		
		save(new ItemBasic("Bow", "2H_BOW", "Bows", "Hunter"));
		save(new ItemBasic("Warbow", "2H_WARBOW", "Bows", "Hunter"));
		save(new ItemBasic("Longbow", "2H_LONGBOW", "Bows", "Hunter"));
		save(new ItemBasic("Whispering Bow", "2H_LONGBOW_UNDEAD", "Bows", "Hunter"));			// bows
		save(new ItemBasic("Bow of Badon", "2H_BOW_KEEPER", "Bows", "Hunter"));
		save(new ItemBasic("Mistpiercer", "2H_BOW_AVALON", "Bows", "Hunter"));
		save(new ItemBasic("Wailing Bow", "2H_BOW_HELL", "Bows", "Hunter"));
		
		save(new ItemBasic("Torch", "OFF_TORCH", "Torches", "Hunter"));
		save(new ItemBasic("Mistcaller", "OFF_HORN_KEEPER", "Torches", "Hunter"));
		save(new ItemBasic("Sacred Scepter", "OFF_TALISMAN_AVALON", "Torches", "Hunter"));
		save(new ItemBasic("Cryptcandle", "OFF_LAMP_UNDEAD", "Torches", "Hunter"));							// talismans
		save(new ItemBasic("Leering Cane", "OFF_JESTERCANE_HELL", "Torches", "Hunter"));
		
		save(new ItemBasic("Mercenary Hood", "HEAD_LEATHER_SET1", "Hoods", "Hunter"));
		save(new ItemBasic("Hunter Hood", "HEAD_LEATHER_SET2", "Hoods", "Hunter"));
		save(new ItemBasic("Assassin Hood", "HEAD_LEATHER_SET3", "Hoods", "Hunter"));
		save(new ItemBasic("Stalker Hood", "HEAD_LEATHER_MORGANA", "Hoods", "Hunter"));		// hoods
		save(new ItemBasic("Hellion Hood", "HEAD_LEATHER_HELL", "Hoods", "Hunter"));
		save(new ItemBasic("Specter Hood", "HEAD_LEATHER_UNDEAD", "Hoods", "Hunter"));
		save(new ItemBasic("Hood of Tenacity", "HEAD_LEATHER_AVALON", "Hoods", "Hunter"));
		save(new ItemBasic("Royal Hood", "HEAD_LEATHER_ROYAL", "Hoods", "Hunter"));
		
		save(new ItemBasic("Mercenary Jacket", "ARMOR_LEATHER_SET1", "Jackets", "Hunter"));
		save(new ItemBasic("Hunter Jacket", "ARMOR_LEATHER_SET2", "Jackets", "Hunter"));
		save(new ItemBasic("Assassin Jacket", "ARMOR_LEATHER_SET3", "Jackets", "Hunter"));
		save(new ItemBasic("Stalker Jacket", "ARMOR_LEATHER_MORGANA", "Jackets", "Hunter"));		// jackets
		save(new ItemBasic("Hellion Jacket", "ARMOR_LEATHER_HELL", "Jackets", "Hunter"));
		save(new ItemBasic("Specter Jacket", "ARMOR_LEATHER_UNDEAD", "Jackets", "Hunter"));
		save(new ItemBasic("Jacket of Tenacity", "ARMOR_LEATHER_AVALON", "Jackets", "Hunter"));
		save(new ItemBasic("Royal Jacket", "ARMOR_LEATHER_ROYAL", "Jackets", "Hunter"));
		
		save(new ItemBasic("Mercenary Shoes", "SHOES_LEATHER_SET1", "Shoes", "Hunter"));
		save(new ItemBasic("Hunter Shoes", "SHOES_LEATHER_SET2", "Shoes", "Hunter"));
		save(new ItemBasic("Assassin Shoes", "SHOES_LEATHER_SET3", "Shoes", "Hunter"));
		save(new ItemBasic("Stalker Shoes", "SHOES_LEATHER_MORGANA", "Shoes", "Hunter"));		// shoes
		save(new ItemBasic("Hellion Shoes", "SHOES_LEATHER_HELL", "Shoes", "Hunter"));
		save(new ItemBasic("Specter Shoes", "SHOES_LEATHER_UNDEAD", "Shoes", "Hunter"));
		save(new ItemBasic("Shoes of Tenacity", "SHOES_LEATHER_AVALON", "Shoes", "Hunter"));
		save(new ItemBasic("Royal Shoes", "SHOES_LEATHER_ROYAL", "Shoes", "Hunter"));
		
		
		
		save(new ItemBasic("Cursed Staff", "MAIN_CURSEDSTAFF", "Curse", "Mage"));
		save(new ItemBasic("Great Cursed Staff", "2H_CURSEDSTAFF", "Curse", "Mage"));
		save(new ItemBasic("Great Fire Staff", "2H_DEMONICSTAFF", "Curse", "Mage"));
		save(new ItemBasic("Lifecurse Staff", "MAIN_CURSEDSTAFF_UNDEAD", "Curse", "Mage"));		// curse
		save(new ItemBasic("Cursed Skull", "2H_SKULLORB_HELL", "Curse", "Mage"));
		save(new ItemBasic("Damnation Staff", "2H_CURSEDSTAFF_MORGANA", "Curse", "Mage"));
		save(new ItemBasic("Shadowcaller", "MAIN_CURSEDSTAFF_AVALON", "Curse", "Mage"));
		
		save(new ItemBasic("Fire Staff", "MAIN_FIRESTAFF", "Fire", "Mage"));
		save(new ItemBasic("Brimstone Staff", "2H_FIRESTAFF_HELL", "Fire", "Mage"));
		save(new ItemBasic("Longbow", "2H_FIRESTAFF", "Fire", "Mage"));
		save(new ItemBasic("Infernal Staff", "2H_INFERNOSTAFF", "Fire", "Mage"));			// fire
		save(new ItemBasic("Wildfire Staff", "MAIN_FIRESTAFF_KEEPER", "Fire", "Mage"));
		save(new ItemBasic("Blazing Staff", "2H_INFERNOSTAFF_MORGANA", "Fire", "Mage"));
		save(new ItemBasic("Dawnsong", "2H_FIRE_RINGPAIR_AVALON", "Fire", "Mage"));
		
		save(new ItemBasic("Arcane Staff", "MAIN_ARCANESTAFF", "Arcane", "Mage"));
		save(new ItemBasic("Enigmatic Staff", "2H_ENIGMATICSTAFF", "Arcane", "Mage"));
		save(new ItemBasic("Witchwork Staff", "MAIN_ARCANESTAFF_UNDEAD", "Arcane", "Mage"));
		save(new ItemBasic("Occult Staff", "2H_ARCANESTAFF_HELL", "Arcane", "Mage"));			// arcane
		save(new ItemBasic("Great Arcane Staff", "2H_ARCANESTAFF", "Arcane", "Mage"));
		save(new ItemBasic("Evensong", "2H_ARCANE_RINGPAIR_AVALON", "Arcane", "Mage"));
		save(new ItemBasic("Malevolent Locus", "2H_ENIGMATICORB_MORGANA", "Arcane", "Mage"));
		
		save(new ItemBasic("Frost Staff", "MAIN_FROSTSTAFF", "Frost", "Mage"));
		save(new ItemBasic("Hoarfrost Staff", "MAIN_FROSTSTAFF_KEEPER", "Frost", "Mage"));
		save(new ItemBasic("Icicle Staff", "2H_ICEGAUNTLETS_HELL", "Frost", "Mage"));
		save(new ItemBasic("Great Frost Staff", "2H_FROSTSTAFF", "Frost", "Mage"));			// frost
		save(new ItemBasic("Glacial Staff", "2H_GLACIALSTAFF", "Frost", "Mage"));
		save(new ItemBasic("Chillhowl", "MAIN_FROSTSTAFF_AVALON", "Frost", "Mage"));
		save(new ItemBasic("Permafrost Prism", "2H_ICECRYSTAL_UNDEAD", "Frost", "Mage"));
		
		save(new ItemBasic("Holy Staff", "MAIN_HOLYSTAFF", "Frost", "Mage"));
		save(new ItemBasic("Lifetouch Staff", "MAIN_HOLYSTAFF_MORGANA", "Frost", "Mage"));
		save(new ItemBasic("Fallen Staff", "2H_HOLYSTAFF_HELL", "Frost", "Mage"));
		save(new ItemBasic("Great Holy Staff", "2H_HOLYSTAFF", "Frost", "Mage"));			// holy
		save(new ItemBasic("Divine Staff", "2H_DIVINESTAFF", "Frost", "Mage"));
		save(new ItemBasic("Hallowfall", "MAIN_HOLYSTAFF_AVALON", "Frost", "Mage"));
		save(new ItemBasic("Redemption Staff", "2H_HOLYSTAFF_UNDEAD", "Frost", "Mage"));
		
		save(new ItemBasic("Tome of Spells", "OFF_BOOK", "Talismans", "Mage"));
		save(new ItemBasic("Eye of Secrets", "OFF_ORB_MORGANA", "Talismans", "Mage"));
		save(new ItemBasic("Muisak", "OFF_DEMONSKULL_HELL", "Talismans", "Mage"));
		save(new ItemBasic("Taproot", "OFF_TOTEM_KEEPER", "Talismans", "Mage"));							// talismans
		save(new ItemBasic("Celestial Censer", "OFF_CENSER_AVALON", "Talismans", "Mage"));
		
		save(new ItemBasic("Scholar Cowl", "HEAD_CLOTH_SET1", "Cowls", "Mage"));
		save(new ItemBasic("Cleric Cowl", "HEAD_CLOTH_SET2", "Cowls", "Mage"));
		save(new ItemBasic("Mage Cowl", "HEAD_CLOTH_SET3", "Cowls", "Mage"));
		save(new ItemBasic("Druid Cowl", "HEAD_CLOTH_KEEPER", "Cowls", "Mage"));		// cowls
		save(new ItemBasic("Fiend Cowl", "HEAD_CLOTH_HELL", "Cowls", "Mage"));
		save(new ItemBasic("Cultist Cowl", "HEAD_CLOTH_MORGANA", "Cowls", "Mage"));
		save(new ItemBasic("Cowl of Purity", "HEAD_CLOTH_AVALON", "Cowls", "Mage"));
		save(new ItemBasic("Royal Cowl", "HEAD_CLOTH_ROYAL", "Cowls", "Mage"));
		
		save(new ItemBasic("Scholar Robe", "ARMOR_CLOTH_SET1", "Robes", "Mage"));
		save(new ItemBasic("Cleric Robe", "ARMOR_CLOTH_SET2", "Robes", "Mage"));
		save(new ItemBasic("Mage Robe", "ARMOR_CLOTH_SET3", "Robes", "Mage"));
		save(new ItemBasic("Druid Robe", "ARMOR_CLOTH_KEEPER", "Robes", "Mage"));		// robes
		save(new ItemBasic("Fiend Robe", "ARMOR_CLOTH_HELL", "Robes", "Mage"));
		save(new ItemBasic("Cultist Robe", "ARMOR_CLOTH_MORGANA", "Robes", "Mage"));
		save(new ItemBasic("Robe of Purity", "ARMOR_CLOTH_AVALON", "Robes", "Mage"));
		save(new ItemBasic("Royal Robe", "ARMOR_CLOTH_ROYAL", "Robes", "Mage"));
		
		save(new ItemBasic("Scholar Sandals", "SHOES_CLOTH_SET1", "Sandals", "Mage"));
		save(new ItemBasic("Cleric Sandals", "SHOES_CLOTH_SET2", "Sandals", "Mage"));
		save(new ItemBasic("Mage Sandals", "SHOES_CLOTH_SET3", "Sandals", "Mage"));
		save(new ItemBasic("Druid Sandals", "SHOES_CLOTH_KEEPER", "Sandals", "Mage"));		// sandals
		save(new ItemBasic("Fiend Sandals", "SHOES_CLOTH_HELL", "Sandals", "Mage"));
		save(new ItemBasic("Cultist Sandals", "SHOES_CLOTH_MORGANA", "Sandals", "Mage"));
		save(new ItemBasic("Sandals of Purity", "SHOES_CLOTH_AVALON", "Sandals", "Mage"));
		save(new ItemBasic("Royal Sandals", "SHOES_CLOTH_ROYAL", "Sandals", "Mage"));
		
	}
	
}
