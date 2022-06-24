package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logic.Artifact;
import logic.ItemBasic;
import logic.ItemCombined;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemDAO;
	
	@Autowired
	ArtifactRepository artifactDAO;
	
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
	
	public ArrayList<ItemCombined> findArtifactItems(int tier, int enchantmentLevel) {
		ArrayList<ItemBasic> bases =  itemDAO.getArtifactItems();
		ArrayList<ItemCombined> artifactItemCombined = new ArrayList<>();
		bases.forEach(base -> {
			artifactItemCombined.add( ItemCombined. forBase( base ).forTier( tier ). withEnchantmentLevelOf( enchantmentLevel ). ofQuality(1) );		
		});
		return artifactItemCombined;
	}
	
	public ArrayList<ItemBasic> basesByTags(ArrayList<String> tags) {
		ArrayList<ItemBasic> bases = new ArrayList<>();
		for(String tag : tags) {
			bases.addAll(findByTag(tag));
		}
		
		return bases;
	}
	
	public ArrayList<ItemCombined> itemsByTags(ArrayList<String> tags, int tier, int enchantmentLevel) {
		ArrayList<ItemCombined> result = new ArrayList<>();
		
		ArrayList<ItemBasic> bases = new ArrayList<>();
		for(String tag : tags) {
			bases.addAll(findByTag(tag));
		}
		
		bases.forEach(base -> {
			result.add( ItemCombined. forBase( base ).forTier( tier ). withEnchantmentLevelOf( enchantmentLevel ). ofQuality(1) );		
		});
		
		return result;
	}
	
	private ArrayList<ItemBasic> findByTag(String tag) {
		ArrayList<ItemBasic> result = new ArrayList<>();
		result.addAll(itemDAO.findByCraftBranch(tag));
		result.addAll(itemDAO.findByCraftNode(tag));
		return result;
	}
	
	public ItemBasic getByName(String name) {
		ItemBasic item = itemDAO.findByHumanName(name);
		System.out.println(item.getName());
		return item;
	}
	
	public List<Artifact> getArtifactsList() {
		return artifactDAO.findAll();
	}
	
	public Artifact artifactByName(String name) {
		return artifactDAO.findByName(name);
	}
	
	public void setUpArtifactDatabase() {					// call once and forget, kind of hardcoding - not deleting for possible forks
		
		artifactDAO.save(new Artifact("Occult Orb", "Occult Staff", "ARTEFACT_2H_ARCANESTAFF_HELL"));
		artifactDAO.save(new Artifact("Possessed Catalyst", "Malevolent Locus", "ARTEFACT_2H_ENIGMATICORB_MORGANA"));
		artifactDAO.save(new Artifact("Witchwork Staff", "Lost Arcane Crystal", "ARTEFACT_MAIN_ARCANESTAFF_UNDEAD"));
		
		artifactDAO.save(new Artifact("Carved bone", "Bow of Badon", "ARTEFACT_2H_BOW_KEEPER"));
		artifactDAO.save(new Artifact("Demonic Arrowheads", "Wailing Bow", "ARTEFACT_2H_BOW_HELL"));
		artifactDAO.save(new Artifact("Ghastly Arrows", "Whispering Bow", "ARTEFACT_2H_LONGBOW_UNDEAD"));
		
		artifactDAO.save(new Artifact("Demonic Blade", "Carving Sword", "ARTEFACT_2H_CLEAVER_HELL"));
		artifactDAO.save(new Artifact("Cursed Blades", "Galatine Pair", "ARTEFACT_2H_DUALSCIMITAR_UNDEAD"));
		artifactDAO.save(new Artifact("Bloodforged Blade", "Clarent Blade", "ARTEFACT_MAIN_SCIMITAR_MORGANA"));
		
		artifactDAO.save(new Artifact("Reinforced Morgana Pole", "Black Monk Stave", "ARTEFACT_2H_COMBATSTAFF_MORGANA"));
		artifactDAO.save(new Artifact("Preserved Rocks", "Staff of Balance", "ARTEFACT_2H_ROCKSTAFF_KEEPER"));
		artifactDAO.save(new Artifact("Hellish Sicklehead Pair", "Soulscythe", "ARTEFACT_2H_TWINSCYTHE_HELL"));
		
		artifactDAO.save(new Artifact("Alluring Bolts", "Siegebow", "ARTEFACT_2H_CROSSBOWLARGE_MORGANA"));
		artifactDAO.save(new Artifact("Hellish Bolts", "Boltcasters", "ARTEFACT_2H_DUALCROSSBOW_HELL"));
		artifactDAO.save(new Artifact("Lost Crossbow Mechanism", "Weeping Repeater", "ARTEFACT_2H_REPEATINGCROSSBOW_UNDEAD"));
		
		artifactDAO.save(new Artifact("Bloodforged Catalyst", "Damnation Staff", "ARTEFACT_2H_CURSEDSTAFF_MORGANA"));
		artifactDAO.save(new Artifact("Cursed Jawbone", "Cursed Skull", "ARTEFACT_2H_SKULLORB_HELL"));
		artifactDAO.save(new Artifact("Lost Cursed Crystal", "Lifecurse Staff", "ARTEFACT_MAIN_CURSEDSTAFF_UNDEAD"));
		
		artifactDAO.save(new Artifact("Keeper Axeheads", "Bear Paws", "ARTEFACT_2H_DUALAXE_KEEPER"));
		artifactDAO.save(new Artifact("Morgana Halberd Head", "Carrioncaller", "ARTEFACT_2H_HALBERD_MORGANA"));
		artifactDAO.save(new Artifact("Hellish Sicklehead", "Infernal Scythe", "ARTEFACT_2H_SCYTHE_HELL"));
		
		artifactDAO.save(new Artifact("Hellish Hammer Heads", "Forge Hammers", "ARTEFACT_2H_DUALHAMMER_HELL"));
		artifactDAO.save(new Artifact("Ancient Hammer Head", "Tombhammer", "ARTEFACT_2H_HAMMER_UNDEAD"));
		artifactDAO.save(new Artifact("Engraved Log", "Grovekeeper", "ARTEFACT_2H_RAM_KEEPER"));
		
		artifactDAO.save(new Artifact("Ghastly Blades", "Deathgivers", "ARTEFACT_2H_DUALSICKLE_UNDEAD"));
		artifactDAO.save(new Artifact("Black Leather", "Black Hands", "ARTEFACT_2H_IRONGAUNTLETS_HELL"));
		artifactDAO.save(new Artifact("Hardened Debole", "Bloodletter", "ARTEFACT_MAIN_RAPIER_MORGANA"));

		artifactDAO.save(new Artifact("Burning Orb", "Brimstone Staff", "ARTEFACT_2H_FIRESTAFF_HELL"));
		artifactDAO.save(new Artifact("Unholy Scroll", "Blazing Staff", "ARTEFACT_2H_INFERNOSTAFF_MORGANA"));
		artifactDAO.save(new Artifact("Wildfire Orb", "Wildfire Staff", "ARTEFACT_MAIN_FIRESTAFF_KEEPER"));
		
		artifactDAO.save(new Artifact("Infernal Harpoon Tip", "Spirithunter", "ARTEFACT_2H_HARPOON_HELL"));
		artifactDAO.save(new Artifact("Cursed Barbs", "Trinity Spear", "ARTEFACT_2H_TRIDENT_UNDEAD"));
		artifactDAO.save(new Artifact("Keeper Spearhead", "Heron Spear", "ARTEFACT_MAIN_SPEAR_KEEPER"));
		
		artifactDAO.save(new Artifact("Possessed Scroll", "Lifetouch Staff", "ARTEFACT_MAIN_HOLYSTAFF_MORGANA"));
		artifactDAO.save(new Artifact("Infernal Scroll", "Fallen Staff", "ARTEFACT_2H_HOLYSTAFF_HELL"));
		artifactDAO.save(new Artifact("Ghastly Scroll", "Redemption Staff", "ARTEFACT_2H_HOLYSTAFF_UNDEAD"));
		
		artifactDAO.save(new Artifact("Cursed Frozen Crystal", "Permafrost Prism", "ARTEFACT_2H_ICECRYSTAL_UNDEAD"));
		artifactDAO.save(new Artifact("Icicle Orb", "Icicle Staff", "ARTEFACT_2H_ICEGAUNTLETS_HELL"));
		artifactDAO.save(new Artifact("Hoarfrost Orb", "Hoarfrost Staff", "ARTEFACT_MAIN_FROSTSTAFF_KEEPER"));
		
		artifactDAO.save(new Artifact("Imbued Mace Head", "Camlann Mace", "ARTEFACT_2H_MACE_MORGANA"));
		artifactDAO.save(new Artifact("Infernal Mace Head", "Incubus Mace", "ARTEFACT_MAIN_MACE_HELL"));
		artifactDAO.save(new Artifact("Runed Rock", "Bedrock Mace", "ARTEFACT_MAIN_ROCKMACE_KEEPER"));
		
		artifactDAO.save(new Artifact("Symbol of Blight", "Blight Staff", "ARTEFACT_2H_NATURESTAFF_HELL"));
		artifactDAO.save(new Artifact("Preserved Log", "Rampant Staff", "ARTEFACT_2H_NATURESTAFF_KEEPER"));
		artifactDAO.save(new Artifact("Druidic Inscriptions", "Druidic Staff", "ARTEFACT_MAIN_NATURESTAFF_KEEPER"));
		
		artifactDAO.save(new Artifact("Bloodstained Antiquities", "Bridled Fury", "ARTEFACT_2H_DAGGER_KATAR_AVALON"));
		artifactDAO.save(new Artifact("Ruined Ancestral Vamplate", "Daybreaker", "ARTEFACT_MAIN_SPEAR_LANCE_AVALON"));
		artifactDAO.save(new Artifact("Avalonian Battle Memoir", "Realmbreaker", "ARTEFACT_2H_AXE_AVALON"));
		artifactDAO.save(new Artifact("Remnants of the Old King", "Kingmaker", "ARTEFACT_2H_CLAYMORE_AVALON"));
		artifactDAO.save(new Artifact("Timeworn Walking Staff", "Grailseeker", "ARTEFACT_2H_QUARTERSTAFF_AVALON"));
		artifactDAO.save(new Artifact("Massive Metallic Hand", "Hand of Justice", "ARTEFACT_2H_HAMMER_AVALON"));
		artifactDAO.save(new Artifact("Broken Oaths", "Oathkeepers", "ARTEFACT_2H_DUALMACE_AVALON"));
		artifactDAO.save(new Artifact("Immaculately Crafted Riser", "Mistpiercer", "ARTEFACT_2H_BOW_AVALON"));
		artifactDAO.save(new Artifact("Humming Avalonian Whirligig", "Energy Shaper", "ARTEFACT_2H_CROSSBOW_CANNON_AVALON"));
		artifactDAO.save(new Artifact("Fractured Opaque Orb", "Shadowcaller", "ARTEFACT_MAIN_CURSEDSTAFF_AVALON"));
		artifactDAO.save(new Artifact("Glowing Harmonic Ring", "Dawnsong", "ARTEFACT_2H_FIRE_RINGPAIR_AVALON"));
		artifactDAO.save(new Artifact("Chilled Crystalline Shard", "Chillhowl", "ARTEFACT_MAIN_FROSTSTAFF_AVALON"));
		artifactDAO.save(new Artifact("Hypnotic Harmonic Ring", "Evensong", "ARTEFACT_2H_ARCANE_RINGPAIR_AVALON"));
		artifactDAO.save(new Artifact("Messianic Curio", "Hallowfall", "ARTEFACT_MAIN_HOLYSTAFF_AVALON "));
		artifactDAO.save(new Artifact("Uprooted Perennial Sapling", "Ironroot Staff", "ARTEFACT_MAIN_NATURESTAFF_AVALON"));
		
		artifactDAO.save(new Artifact("Ursine Guardian Remains", "Ursine Maulers", "ARTEFACT_2H_KNUCKLES_KEEPER"));
		artifactDAO.save(new Artifact("Severed Demonic Horns", "Hellfire Hands", "ARTEFACT_2H_KNUCKLES_HELL"));
		artifactDAO.save(new Artifact("Warped Raven Plate", "Ravenstrike Cestus", "ARTEFACT_2H_KNUCKLES_MORGANA"));
		artifactDAO.save(new Artifact("Damaged Avalonian Gauntlet", "Fists of Avalon", "ARTEFACT_2H_KNUCKLES_AVALON"));
		
		artifactDAO.save(new Artifact("Broken Demonic Fang", "Demonfang", "ARTEFACT_MAIN_DAGGER_HELL"));
		
		artifactDAO.save(new Artifact("Ancient Shield Core", "Sarcophagus", "ARTEFACT_OFF_TOWERSHIELD_UNDEAD"));
		artifactDAO.save(new Artifact("Infernal Shield Core", "Caitiff Shield", "ARTEFACT_OFF_SHIELD_HELL"));
		artifactDAO.save(new Artifact("Bloodforged Spikes", "Facebreaker", "ARTEFACT_OFF_SPIKEDSHIELD_MORGANA"));
		artifactDAO.save(new Artifact("Crushed Avalonian Heirloom", "Astral Aegis", "ARTEFACT_OFF_SHIELD_AVALON"));
		
		artifactDAO.save(new Artifact("Alluring Crystal", "Eye of Secrets", "ARTEFACT_OFF_ORB_MORGANA"));
		artifactDAO.save(new Artifact("Demonic Jawbone", "Muisak", "ARTEFACT_OFF_DEMONSKULL_HELL"));
		artifactDAO.save(new Artifact("Inscribed Stone", "Taproot", "ARTEFACT_OFF_TOTEM_KEEPER"));
		artifactDAO.save(new Artifact("Severed Celestial Keepsake", "Celestial Censer", "ARTEFACT_OFF_CENSER_AVALON"));
		
		artifactDAO.save(new Artifact("Runed Horn", "Mistcaller", "ARTEFACT_OFF_HORN_KEEPER"));
		artifactDAO.save(new Artifact("Hellish Handle", "Leering Cane", "ARTEFACT_OFF_JESTERCANE_HELL"));
		artifactDAO.save(new Artifact("Ghastly Candle", "Cryptcandle", "ARTEFACT_OFF_LAMP_UNDEAD"));
		artifactDAO.save(new Artifact("Shattered Avalonian Memento", "Sacred Scepter", "ARTEFACT_OFF_TALISMAN_AVALON"));
		
		artifactDAO.save(new Artifact("Ancient Padding", "Graveguard Helmet", "ARTEFACT_HEAD_PLATE_UNDEAD"));
		artifactDAO.save(new Artifact("Ancient Chain Rings", "Graveguard Armor", "ARTEFACT_ARMOR_PLATE_UNDEAD"));
		artifactDAO.save(new Artifact("Ancient Bindings", "Graveguard Boots", "ARTEFACT_SHOES_PLATE_UNDEAD"));
		artifactDAO.save(new Artifact("Demonic Scraps", "Demon Helmet", "ARTEFACT_HEAD_PLATE_HELL"));
		artifactDAO.save(new Artifact("Demonic Plates", "Demon Armor", "ARTEFACT_ARMOR_PLATE_HELL"));
		artifactDAO.save(new Artifact("Demonic Filling", "Demon Boots", "ARTEFACT_SHOES_PLATE_HELL"));
		artifactDAO.save(new Artifact("Carved Skull Padding", "Judicator Helmet", "ARTEFACT_HEAD_PLATE_KEEPER"));
		artifactDAO.save(new Artifact("Preserved Animal Fur", "Judicator Armor", "ARTEFACT_ARMOR_PLATE_KEEPER"));
		artifactDAO.save(new Artifact("Inscribed Bindings", "Judicator Boots", "ARTEFACT_SHOES_PLATE_KEEPER"));
		artifactDAO.save(new Artifact("Exalted Visor", "Helmet of Valor", "ARTEFACT_HEAD_PLATE_AVALON"));
		artifactDAO.save(new Artifact("Exalted Plating", "Armor of Valor", "ARTEFACT_ARMOR_PLATE_AVALON"));
		artifactDAO.save(new Artifact("Exalted Greave", "Boots of Valor", "ARTEFACT_SHOES_PLATE_AVALON"));
		
		artifactDAO.save(new Artifact("Imbued Visor", "Stalker Hood", "ARTEFACT_HEAD_LEATHER_MORGANA"));
		artifactDAO.save(new Artifact("Imbued Leather Folds", "Stalker Jacket", "ARTEFACT_ARMOR_LEATHER_MORGANA"));
		artifactDAO.save(new Artifact("Imbued Soles", "Stalker Shoes", "ARTEFACT_SHOES_LEATHER_MORGANA"));
		artifactDAO.save(new Artifact("Demonhide Padding", "Hellion Hood", "ARTEFACT_HEAD_LEATHER_HELL"));
		artifactDAO.save(new Artifact("Demonhide Leather", "Hellion Jacket", "ARTEFACT_ARMOR_LEATHER_HELL"));
		artifactDAO.save(new Artifact("Demonhide Bindings", "Hellion Shoes", "ARTEFACT_SHOES_LEATHER_HELL"));
		artifactDAO.save(new Artifact("Ghastly Visor", "Specter Hood", "ARTEFACT_HEAD_LEATHER_UNDEAD"));
		artifactDAO.save(new Artifact("Ghastly Leather", "Specter Jacket", "ARTEFACT_ARMOR_LEATHER_UNDEAD"));
		artifactDAO.save(new Artifact("Ghastly Bindings", "Specter Shoes", "ARTEFACT_SHOES_LEATHER_UNDEAD"));
		artifactDAO.save(new Artifact("Augured Padding", "Hood of Tenacity", "ARTEFACT_HEAD_LEATHER_AVALON"));
		artifactDAO.save(new Artifact("Augured Sash", "Jacket of Tenacity", "ARTEFACT_ARMOR_LEATHER_AVALON"));
		artifactDAO.save(new Artifact("Augured Fasteners", "Shoes of Tenacity", "ARTEFACT_SHOES_LEATHER_AVALON"));
		
		artifactDAO.save(new Artifact("Druidic Preserved Beak", "Druid Cowl", "ARTEFACT_HEAD_CLOTH_KEEPER"));
		artifactDAO.save(new Artifact("Druidic Feathers", "Druid Robe", "ARTEFACT_ARMOR_CLOTH_KEEPER"));
		artifactDAO.save(new Artifact("Druidic Bindings", "Druid Sandals", "ARTEFACT_SHOES_CLOTH_KEEPER"));
		artifactDAO.save(new Artifact("Infernal Cloth Visor", "Fiend Cowl", "ARTEFACT_HEAD_CLOTH_HELL"));
		artifactDAO.save(new Artifact("Infernal Cloth Folds", "Fiend Robe", "ARTEFACT_ARMOR_CLOTH_HELL"));
		artifactDAO.save(new Artifact("Infernal Cloth Bindings", "Fiend Sandals", "ARTEFACT_SHOES_CLOTH_HELL"));
		artifactDAO.save(new Artifact("Alluring Padding", "Cultist Cowl", "ARTEFACT_HEAD_CLOTH_MORGANA"));
		artifactDAO.save(new Artifact("Alluring Amulet", "Cultist Robe", "ARTEFACT_ARMOR_CLOTH_MORGANA"));
		artifactDAO.save(new Artifact("Alluring Bindings", "Cultist Sandals", "ARTEFACT_SHOES_CLOTH_MORGANA"));
		artifactDAO.save(new Artifact("Sanctified Mask", "Cowl of Purity", "ARTEFACT_HEAD_CLOTH_AVALON"));
		artifactDAO.save(new Artifact("Sanctified Belt", "Robe of Purity", "ARTEFACT_ARMOR_CLOTH_AVALON"));
		artifactDAO.save(new Artifact("Sanctified Bindings", "Sandals of Purity", "ARTEFACT_SHOES_CLOTH_AVALON"));
		

	}

	public void setUpAmmunitionDatabase() {					// call once and forget, kind of hardcoding - not deleting for possible forks				
		
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
		save(new ItemBasic("Great Fire Staff", "2H_FIRESTAFF", "Fire", "Mage"));
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
		
		save(new ItemBasic("Holy Staff", "MAIN_HOLYSTAFF", "Holy", "Mage"));
		save(new ItemBasic("Lifetouch Staff", "MAIN_HOLYSTAFF_MORGANA", "Holy", "Mage"));
		save(new ItemBasic("Fallen Staff", "2H_HOLYSTAFF_HELL", "Holy", "Mage"));
		save(new ItemBasic("Great Holy Staff", "2H_HOLYSTAFF", "Holy", "Mage"));			// holy
		save(new ItemBasic("Divine Staff", "2H_DIVINESTAFF", "Holy", "Mage"));
		save(new ItemBasic("Hallowfall", "MAIN_HOLYSTAFF_AVALON", "Holy", "Mage"));
		save(new ItemBasic("Redemption Staff", "2H_HOLYSTAFF_UNDEAD", "Holy", "Mage"));
		
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
	
	public void setupRecipeDatabase() {											// call once and forget, kind of hardcoding - not deleting for possible forks	
		itemDAO.setRecipe("Broadsword", new String[] {"16", "ingots", "8", "leather"});
		itemDAO.setRecipe("Claymore", new String[] {"20", "ingots", "12", "leather"});
		itemDAO.setRecipe("Kingmaker", new String[] {"20", "ingots", "12", "leather"});
		itemDAO.setRecipe("Dual Swords", new String[] {"20", "ingots", "12", "leather"});
		itemDAO.setRecipe("Clarent Blade", new String[] {"16", "ingots", "8", "leather"});
		itemDAO.setRecipe("Carving Sword", new String[] {"20", "ingots", "12", "leather"});
		itemDAO.setRecipe("Galatine Pair", new String[] {"20", "ingots", "12", "leather"});
		
		itemDAO.setRecipe("Battleaxe", new String[] {"8", "planks", "16", "ingots"});
		itemDAO.setRecipe("Greataxe", new String[] {"12", "planks", "20", "ingots"});
		itemDAO.setRecipe("Realmbreaker", new String[] {"12", "planks", "20", "ingots"});
		itemDAO.setRecipe("Infernal Scythe", new String[] {"12", "planks", "20", "ingots"});
		itemDAO.setRecipe("Bear Paws", new String[] {"12", "planks", "20", "ingots"});
		itemDAO.setRecipe("Carrioncaller", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Halberd", new String[] {"20", "planks", "12", "ingots"});
		
		itemDAO.setRecipe("Mace", new String[] {"16", "ingots", "8", "cloth"});
		itemDAO.setRecipe("Incubus Mace", new String[] {"16", "ingots", "8", "cloth"});
		itemDAO.setRecipe("Bedrock Mace", new String[] {"16", "ingots", "8", "cloth"});
		itemDAO.setRecipe("Heavy Mace", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Morning Star", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Camlann Mace", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Oathkeepers", new String[] {"20", "ingots", "12", "cloth"});
		
		itemDAO.setRecipe("Hammer", new String[] {"24", "ingots"});
		itemDAO.setRecipe("Polehammer", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Tombhammer", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Forge Hammers", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Grovekeeper", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Great Hammer", new String[] {"20", "ingots", "12", "cloth"});
		itemDAO.setRecipe("Hand of Justice", new String[] {"20", "ingots", "12", "cloth"});
		
		itemDAO.setRecipe("Brawler Gloves", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Spiked Gauntlets", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Ursine Maulers", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Hellfire Hands", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Battle Bracers", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Ravenstrike Cestus", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Fists of Avalon", new String[] {"12", "ingots", "20", "leather"});
		
		itemDAO.setRecipe("Light Crossbow", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Crossbow", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Heavy Crossbow", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Weeping Repeater", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Boltcasters", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Siegebow", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Energy Shaper", new String[] {"20", "planks", "12", "ingots"});
		
		itemDAO.setRecipe("Bow", new String[] {"32", "planks"});
		itemDAO.setRecipe("Warbow", new String[] {"32", "planks"});
		itemDAO.setRecipe("Longbow", new String[] {"32", "planks"});
		itemDAO.setRecipe("Whispering Bow", new String[] {"32", "planks"});
		itemDAO.setRecipe("Bow of Badon", new String[] {"32", "planks"});
		itemDAO.setRecipe("Mistpiercer", new String[] {"32", "planks"});
		itemDAO.setRecipe("Wailing Bow", new String[] {"32", "planks"});
		
		itemDAO.setRecipe("Spear", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Heron Spear", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Daybreaker", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Spirithunter", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Trinity Spear", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Pike", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Glaive", new String[] {"12", "planks", "20", "ingots"});
		
		itemDAO.setRecipe("Nature Staff", new String[] {"16", "planks", "8", "cloth"});
		itemDAO.setRecipe("Ironroot Staff", new String[] {"16", "planks", "8", "cloth"});
		itemDAO.setRecipe("Druidic Staff", new String[] {"16", "planks", "8", "cloth"});
		itemDAO.setRecipe("Great Nature Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Rampant Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Blight Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Wild Staff", new String[] {"20", "planks", "12", "cloth"});
		
		itemDAO.setRecipe("Dagger", new String[] {"12", "ingots", "12", "leather"});
		itemDAO.setRecipe("Demonfang", new String[] {"12", "ingots", "12", "leather"});
		itemDAO.setRecipe("Deathgivers", new String[] {"16", "ingots", "16", "leather"});
		itemDAO.setRecipe("Dagger Pair", new String[] {"16", "ingots", "16", "leather"});
		itemDAO.setRecipe("Bridled Fury", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Claws", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Bloodletter", new String[] {"16", "ingots", "8", "leather"});

		itemDAO.setRecipe("Quarterstaff", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Soulscythe", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Black Monk Stave", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Grailseeker", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Double Bladed Staff", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Staff of Balance", new String[] {"12", "ingots", "20", "leather"});
		itemDAO.setRecipe("Iron-clad Staff", new String[] {"12", "ingots", "20", "leather"});
		
		itemDAO.setRecipe("Fire Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Wildfire Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Brimstone Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Infernal Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Blazing Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Dawnsong", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Great Fire Staff", new String[] {"20", "planks", "12", "ingots"});

		itemDAO.setRecipe("Holy Staff", new String[] {"16", "planks", "8", "cloth"});
		itemDAO.setRecipe("Lifetouch Staff", new String[] {"16", "planks", "8", "cloth"});
		itemDAO.setRecipe("Hallowfall", new String[] {"16", "planks", "8", "cloth"});		
		itemDAO.setRecipe("Redemption Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Fallen Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Great Holy Staff", new String[] {"20", "planks", "12", "cloth"});
		itemDAO.setRecipe("Divine Staff", new String[] {"20", "planks", "12", "cloth"});
		
		itemDAO.setRecipe("Arcane Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Witchwork Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Enigmatic Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Occult Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Great Arcane Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Evensong", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Malevolent Locus", new String[] {"20", "planks", "12", "ingots"});

		itemDAO.setRecipe("Frost Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Hoarfrost Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Chillhowl", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Great Frost Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Icicle Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Glacial Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Permafrost Prism", new String[] {"20", "planks", "12", "ingots"});
		
		itemDAO.setRecipe("Cursed Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Lifecurse Staff", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Shadowcaller", new String[] {"16", "planks", "8", "ingots"});
		itemDAO.setRecipe("Great Cursed Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Great Fire Staff", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Cursed Skull", new String[] {"20", "planks", "12", "ingots"});
		itemDAO.setRecipe("Damnation Staff", new String[] {"20", "planks", "12", "ingots"});
		
		ArrayList<ItemBasic> talismans = findByTag("Talismans");
		talismans.forEach(talisman -> {
			itemDAO.setRecipe(talisman.getName(), new String[] {"4", "cloth", "4", "leather"});
//			save(talisman.setRecipe(new String[] {"4", "cloth", "4", "leather"});
		});
		ArrayList<ItemBasic> shields = findByTag("Shields");
		shields.forEach(shield -> {
			itemDAO.setRecipe(shield.getName(), new String[] {"4", "planks", "4", "ingots"});
		});
		ArrayList<ItemBasic> torches = findByTag("Torches");
		torches.forEach(torch -> {
			itemDAO.setRecipe(torch.getName(), new String[] {"4", "planks", "4", "cloth"});
		});
		
		ArrayList<ItemBasic> robes = findByTag("Robes");
		robes.forEach(robe -> {
			itemDAO.setRecipe(robe.getName(), new String[] {"16", "cloth"});
		});
		ArrayList<ItemBasic> armors = findByTag("Armor");
		armors.forEach(armor -> {
			itemDAO.setRecipe(armor.getName(), new String[] {"16", "ingots"});
		});
		ArrayList<ItemBasic> jackets = findByTag("Jackets");
		jackets.forEach(jacket -> {
			itemDAO.setRecipe(jacket.getName(), new String[] {"16", "leather"});
		});
		
		ArrayList<ItemBasic> sandals = findByTag("Sandals");
		sandals.forEach(sandal -> {
			itemDAO.setRecipe(sandal.getName(), new String[] {"8", "cloth"});
		});
		ArrayList<ItemBasic> boots = findByTag("Boots");
		boots.forEach(boot -> {
			itemDAO.setRecipe(boot.getName(), new String[] {"8", "ingots"});
		});
		ArrayList<ItemBasic> shoes = findByTag("Shoes");
		shoes.forEach(shoe -> {
			itemDAO.setRecipe(shoe.getName(), new String[] {"8", "leather"});
		});
		
		ArrayList<ItemBasic> cowls = findByTag("Cowls");
		cowls.forEach(cowl -> {
			itemDAO.setRecipe(cowl.getName(), new String[] {"8", "cloth"});
		});
		ArrayList<ItemBasic> helmets = findByTag("Helmets");
		helmets.forEach(helmet -> {
			itemDAO.setRecipe(helmet.getName(), new String[] {"8", "ingots"});
		});
		ArrayList<ItemBasic> hoods = findByTag("Hoods");
		hoods.forEach(hood -> {
			itemDAO.setRecipe(hood.getName(), new String[] {"8", "leather"});
		});
	}
	
	public void linkArtsAndItems() {						// TODO rewrite this shit to JPA field linking
		artifactDAO.findAll().forEach(artifact -> {
			ItemBasic item = itemDAO.findByHumanName(artifact.partOf());
			item.setArtifact(artifact);
			save(item);
		});
	}
	
}
