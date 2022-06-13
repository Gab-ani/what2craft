package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import albionDataCommunication.AuctionHousePunching;
import albionDataCommunication.DataFetcher;
import albionDataCommunication.PriceResponse;
import database.ItemService;
import database.StatService;
import logic.City;
import logic.CraftAdvisor;
import logic.CraftSimulator;
import logic.ItemBasic;
import logic.ItemCombined;
import logic.Prices;

@EnableJpaRepositories({"database"})
@ComponentScan({"database", "albionDataCommunication", "logic"})
@EntityScan({"logic"})
@Configuration
@SpringBootApplication
public class EntryPoint {
	
	@Autowired
	@Lazy
	ItemService itemService;
	
	@Autowired
	StatService statService;
	
	@Autowired
	@Lazy
	Prices prices;
	
	@Autowired
	CraftSimulator simulator;
	
	@Autowired
	DataFetcher dataFetcher;
	
	@Autowired
	CraftAdvisor advisor;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			

			ArrayList<ItemCombined> items = itemService.findArtifactItems(5, 1);
//			ArrayList<String> tags = new ArrayList<>();
//			tags.add("Bows");
//			tags.add("Robes");
//			tags.add("Swords");
//			ArrayList<ItemCombined> items = itemService.findByTags(tags, 6, 0);
			
			ArrayList<ItemBasic> bases = new ArrayList<>();
			items.forEach(item -> bases.add(item.getBase()));
			
			AuctionHousePunching clicker = new AuctionHousePunching();
			clicker.checkListOf(bases, 5, 1);
			clicker.delay(59000);
			clicker.delay(59000);
			
			advisor.init(5, statService.cityByName("fortsterling"), 1);
			advisor.adviseFromListUncommon(items, 5, statService.cityByName("fortsterling"));
//			advisor.adviseFromListUncommon(items, 5, statService.cityByName("sterling"));

			
						
//			System.out.println(simulator.disenchantedItemCraftCost(itemService.getByName("Bloodletter"), 5));
//			System.out.println(dataFetcher.fetchActualData(ItemCombined.forBase(itemService.getByName("Bloodletter")).forTier(5).withEnchantmentLevelOf(0).ofQuality(1), lymhurst.name()));
			
//			itemService.linkArtsAndItems();
		
//			itemService.findByTags(new String[] {"Mage",  "Bows", "Swords"}, 5, 1).forEach(item -> System.out.println(item.getName()));
			
//			ArrayList<ItemCombined> itemsToMemorize = new ArrayList<>();
//			
//			itemsToMemorize.add( ItemCombined.forBase( itemService.getByName("Bow") ).forTier(5).withEnchantmentLevelOf(1).ofQuality(1));
//			itemsToMemorize.add( ItemCombined.forBase( itemService.getByName("Longbow") ).forTier(5).withEnchantmentLevelOf(1).ofQuality(1));
//			itemsToMemorize.add( ItemCombined.forBase( itemService.getByName("Occult Staff") ).forTier(5).withEnchantmentLevelOf(1).ofQuality(1));
//			itemsToMemorize.add( ItemCombined.forBase( itemService.getByName("Judicator Armor") ).forTier(5).withEnchantmentLevelOf(1).ofQuality(1));
//			
//			itemsToMemorize.forEach(item -> System.out.println(item.toString()));
//			
//			prices.memorize(itemsToMemorize, "lymhurst");
//			prices.visualiseItemMemory("lymhurst");
//			
//			prices.visualiseMaterial("lymhurst", "planks");
			
//			ItemBasic item = itemService.getByName("Claymore");
//			
//			System.out.println(dataFetcher().fetchActualData(item, 5, 1, 1, "Lymhurst")[0]);
			
			
			// wrath rampant? hand of khor diary? 
		};
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, RestClientException {
			
		SpringApplication.run(EntryPoint.class, args);
		
	}

}
