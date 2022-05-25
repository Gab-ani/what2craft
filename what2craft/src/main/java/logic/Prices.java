package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;

import albionDataCommunication.DataFetcher;
import albionDataCommunication.PriceResponse;
import database.ItemService;
import database.StatService;

@Service
public class Prices {
	
	@Autowired
	private DataFetcher dataFetcher;
	@Autowired
	private ItemService itemService;
	
	private HashMap<String, PriceArchive> prices;
	
	public Prices() {
		
	}
	
	@PostConstruct
	private void init() {
		List<Artifact> arts = itemService.getArtifactsList();
		prices = new HashMap<>();
		prices.put("lymhurst", new PriceArchive(arts));
		prices.put("sterling", new PriceArchive(arts));
		prices.put("thedford", new PriceArchive(arts));
		prices.put("martlock", new PriceArchive(arts));
		prices.put("bridgewatch", new PriceArchive(arts));
		prices.put("caerleon", new PriceArchive(arts));
	}
	
	public void memorize(ArrayList<ItemCombined> items, String city) {
		items.forEach((item) -> {
			
			try {
				PriceResponse response = dataFetcher.fetchActualData(item, city);
				if(response.isActual()) {
					prices.get(city).memorizeItem(item, response.getMinSellPrice());
				}
			} catch (JsonProcessingException | RestClientException e) {
				e.printStackTrace();
			}
			
		});
	}
	
	public void updateJournalsCost(City city, String branch, int tier) {
		try {
			PriceResponse responseFull = dataFetcher.fetchActualData(branch, "full", tier, city.name());
			if(responseFull.isActual()) {
				prices.get(city.name()).setJournalPrice(branch + " full", tier, responseFull.getMinSellPrice());
			}
			PriceResponse responseEmpty = dataFetcher.fetchActualData(branch, "emty", tier, city.name());
			if(responseEmpty.isActual()) {
				prices.get(city.name()).setJournalPrice(branch + " emty", tier, responseEmpty.getMinSellPrice());
			}
		} catch (JsonProcessingException | RestClientException e) {
			e.printStackTrace();
		}		
	}
	
	public void update(String city, String material) {
		
	}
	
	public void update(City city, Artifact art, int tier) {
		try {
			PriceResponse response = dataFetcher.fetchActualData(art, tier, city.name());
			if(response.isActual()) {
				prices.get(city.name()).setPrice(art, tier, response.getMinSellPrice());
			}
		} catch (JsonProcessingException | RestClientException e) {
			e.printStackTrace();
		}
	}
	
	public void update(City city, String material, int tier, int chant) {
		try {
			PriceResponse response = dataFetcher.fetchActualData(material, tier, chant, city.name());
			if(response.isActual()) {
				prices.get(city.name()).setPrice(material, tier, chant, response.getMinSellPrice());
			}
		} catch (JsonProcessingException | RestClientException e) {
			e.printStackTrace();
		}
	}

	public void visualiseItemMemory(String city) {
		HashMap<ItemCombined, Integer> itemPrices = prices.get(city).getItemsMemory();
		itemPrices.forEach((item, price) -> System.out.println(item.getName() + " " + price));
	}
	
	public void visualiseMaterial(String city, String resource) {
		int[][] materialPrices = prices.get(city).getPrices(resource);
		for(int i = 0; i < materialPrices[0].length; i++) {
			for(int j = 0; j < materialPrices.length; j++) {
				System.out.printf("%6d", materialPrices[j][i]);
			}
			System.out.println();
		}
	}
	
	public double priceForFamePoint(String branch, int tier, City city) {
		return prices.get(city.name()).getCraftFamePrice(branch, tier) / StatService.journalsFame[tier - 1];
	}
	
	public int priceForArtifact(Artifact artifact, String city, int tier) {
		return prices.get(city).getArtifactPrice(artifact, tier);
	}
	
	public int priceForMaterial(String material, int tier, int chant, City city) {
		int[][] materials =  prices.get(city.name()).getPrices(material);
		return materials[tier - 1][chant];
	}

	public int priceForGrades(String grade, String city, int tier) {
		return prices.get(city).getGradePrices(grade)[tier - 1];
	}
	
}
